package com.tds.VMonClick.VMonClick.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
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

  public InstanceEntity saveInstance(InstanceEntity instance)
      throws IOException, InterruptedException {
    instance.setDateCreated(LocalDateTime.now());
    instance.setId(UUID.randomUUID().toString());
    instance.setDate_finished(null);

    var vmEntity = vmRepository.findById(instance.getIdVM()).get();

    ResourceEntity resourceEntity = resourceRepository.findById(instance.getIdRsc()).get();

    var instanceDB = instanceRepository.save(instance);
    List<InstanceEntity> instancesToSave = new ArrayList<>(getAllInstances());
    instancesToSave.add(instanceDB);
    vmEntity.setInstances(instancesToSave);
    vmRepository.save(vmEntity);
    vBoxManage.createVM(vmEntity, resourceEntity, instanceDB);
    return instanceDB;
  }

  public InstanceEntity getInstanceById(String id) {
    return instanceRepository.findById(id).get();
  }

  public List<InstanceEntity> getAllInstances() {
    return instanceRepository.findAll().stream().sorted((i1, i2) -> {
      var date1 = LocalDateTime.from(i1.getDateCreated());
      var date2 = LocalDateTime.from(i2.getDateCreated());
      return date2.compareTo(date1);
    }).toList();
  }

  public void updateInstance(String id, InstanceEntity instance) {
    var instanceDB = instanceRepository.findById(id).get();
    if (instanceDB == null) {
      return;
    }
    instance.setId(id);

    instanceRepository.save(instance);
  }

  public void deleteInstanceById(String id) {
    instanceRepository.deleteById(id);
  }

  public void startInstance(String id) throws IOException, InterruptedException {
    var instance = instanceRepository.findById(id).get();
    if (instance == null) {
      return;
    }
    var resource = resourceRepository.findById(instance.getIdRsc()).get();
    var vm = vmRepository.findById(instance.getIdVM()).get();
    vBoxManage.startVMInstance(vm, resource, instance);
  }
}
