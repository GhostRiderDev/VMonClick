package com.tds.VMonClick.VMonClick.service;

import org.apache.coyote.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.tds.VMonClick.VMonClick.model.MetricEntity;
import com.tds.VMonClick.VMonClick.repository.MetricRepository;

import java.util.List;
import java.util.UUID;

@Service
public class MetricService {
  @Autowired
  private MetricRepository metricRepository;

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
    vboxManage.getMetricsInstance();
  }
}
