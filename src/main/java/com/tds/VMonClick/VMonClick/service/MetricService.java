package com.tds.VMonClick.VMonClick.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.model.MetricEntity;
import com.tds.VMonClick.VMonClick.repository.InstanceRepository;
import com.tds.VMonClick.VMonClick.repository.MetricRepository;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
@Slf4j
public class MetricService {
  @Autowired
  private MetricRepository metricRepository;

  @Autowired
  private InstanceRepository instanceRepository;

  @Autowired
  private VBoxManage vboxManage;

  public List<MetricEntity> getMetrics() {
    return metricRepository.findAll();
  }

  public List<MetricEntity> getMetricsInstance(String idInstance) {
    return metricRepository.findByInstance(idInstance);
  }

  public MetricEntity getMetric(String id) throws BadRequestException {
    return metricRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new BadRequestException());
  }



  public MetricEntity saveMetric(MetricEntity metric) {
    return metricRepository.save(metric);
  }

  public void deleteMetric(String id) {
    metricRepository.deleteById(UUID.fromString(id));
  }

  public void saveMetricsIntance() {
    List<InstanceEntity> instances = instanceRepository.findIntancesActive();
    instances.stream().filter(in -> !in.isStop()).forEach(instance -> {
      System.out.println("Id:" + instance.getId());
      try {
        Map<String, Integer> data = vboxManage.getMetricsInstance(instance.getId());
        int cpu = data.get("CPU");
        int ram = data.get("RAM");
        int disk = data.get("DISK");
        int netTx = data.get("NET_TX");
        int netRx = data.get("NET_RX");
        log.info("^*******************PASA POR AQUI*******************^");
        MetricEntity metricEntity =
            MetricEntity.builder().idInstance(instance.getId()).cpu(cpu).ram(ram).disk(disk)
                .bandWidth(netTx).id(UUID.randomUUID()).dateRegistered(LocalDateTime.now()).build();
        metricRepository.save(metricEntity);
      } catch (Exception e) {
        // instance.setStop(true);
        instanceRepository.save(instance);
      }
    });

  }
}
