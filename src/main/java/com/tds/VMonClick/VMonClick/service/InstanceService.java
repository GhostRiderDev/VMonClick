package com.tds.VMonClick.VMonClick.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.model.MetricEntity;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.repository.InstanceRepository;
import com.tds.VMonClick.VMonClick.repository.ResourceRepository;
import com.tds.VMonClick.VMonClick.repository.VmRepository;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class InstanceService {

  @Autowired
  private InstanceRepository instanceRepository;

  @Autowired
  private VmRepository vmRepository;

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private VBoxManage vBoxManage;

  @Autowired
  private MetricService metricService;

  public InstanceEntity saveInstance(InstanceEntity instance)
      throws IOException, InterruptedException {
    instance.setDateCreated(LocalDateTime.now());
    instance.setId(UUID.randomUUID().toString());
    instance.setDate_finished(null);
    instance.setStop(true);
    instance.setPort(null);

    var vmEntity = vmRepository.findById(instance.getIdVM()).get();

    ResourceEntity resourceEntity = resourceRepository.findById(instance.getIdRsc()).get();

    var instanceDB = instanceRepository.save(instance);

    vmRepository.save(vmEntity);
    vBoxManage.createVM(vmEntity, resourceEntity, instanceDB);
    return instanceDB;
  }

  public List<InstanceEntity> getInstancesByUser(String idUser) {
    List<InstanceEntity> intancesDB = instanceRepository.findByIdUser(idUser);
    List<InstanceEntity> intancesMetrics = intancesDB.stream().map(instance -> {
      List<MetricEntity> metricsInstance = metricService.getMetricsInstance(instance.getId());
      instance.setMetrics(metricsInstance);
      return instance;
    }).toList();
    return intancesMetrics;
  }


  public InstanceEntity getInstanceById(String id) {
    return instanceRepository.findById(id).get();
  }

  public List<InstanceEntity> getAllInstances() {
    List<InstanceEntity> instances = instanceRepository.findAll().stream().sorted((i1, i2) -> {
      var date1 = LocalDateTime.from(i1.getDateCreated());
      var date2 = LocalDateTime.from(i2.getDateCreated());
      return date2.compareTo(date1);
    }).toList();
    List<InstanceEntity> instancesAddMetrics = instances.stream().map(instance -> {
      List<MetricEntity> metricsInstance = metricService.getMetricsInstance(instance.getId());
      instance.setMetrics(metricsInstance);
      return instance;
    }).toList();
    return instancesAddMetrics;
  }

  public ResponseEntity<String> stopInstance(String id) throws IOException, InterruptedException {
    var instance = instanceRepository.findById(id).get();
    if (instance == null) {
      return new ResponseEntity<>("Instance not found", HttpStatus.NOT_FOUND);
    }

    if (instance.isStop()) {
      return new ResponseEntity<>("Instance already stopped", HttpStatus.BAD_REQUEST);
    }
    vBoxManage.stopVmIntance(instance);
    instance.setStop(true);
    instanceRepository.save(instance);
    return new ResponseEntity<>("Instance stopped", HttpStatus.OK);
  }

  public void updateInstance(String id, InstanceEntity instance) {
    var instanceDB = instanceRepository.findById(id).get();
    if (instanceDB == null) {
      return;
    }
    instance.setId(id);

    instanceRepository.save(instance);
  }



  public ResponseEntity<String> startInstance(String id) throws IOException, InterruptedException {
    var instance = instanceRepository.findById(id).get();
    if (instance == null) {
      return new ResponseEntity<>("Instance not found", HttpStatus.NOT_FOUND);
    }
    if (instance.isFinish()) {
      return new ResponseEntity<>("Instance already finished", HttpStatus.BAD_REQUEST);
    }
    var resource = resourceRepository.findById(instance.getIdRsc()).get();
    var vm = vmRepository.findById(instance.getIdVM()).get();
    vBoxManage.startVMInstance(vm, resource, instance);
    instance.setStop(false);
    instanceRepository.save(instance);
    return new ResponseEntity<>("Instance started", HttpStatus.OK);
  }

  public ResponseEntity<String> deleteInstance(String id) throws IOException, InterruptedException {
    var instance = instanceRepository.findById(id).get();
    if (instance == null) {
      return new ResponseEntity<>("Instance not found", HttpStatus.NOT_FOUND);
    }
    if (instance.isFinish()) {
      return new ResponseEntity<>("Instance already finished", HttpStatus.BAD_REQUEST);
    }
    if (!instance.isStop()) {
      return new ResponseEntity<>("Instance is running, please stop first", HttpStatus.BAD_REQUEST);
    }
    vBoxManage.deleteVM(instance);
    instance.setFinish(true);
    instance.setDate_finished(LocalDateTime.now());
    instanceRepository.save(instance);
    return new ResponseEntity<>("Instance deleted", HttpStatus.NO_CONTENT);
  }

  public String getIpInstance(String id) {
    InstanceEntity instance = instanceRepository.findById(id).get();
    if (instance == null) {
      return "Instance not found";
    }
    if (instance.isStop()) {
      return "Instance is stopped";
    }
    if (instance.isFinish()) {
      return "Instance is finished";
    }

    return vBoxManage.getIpInstance(id);
  }
}
