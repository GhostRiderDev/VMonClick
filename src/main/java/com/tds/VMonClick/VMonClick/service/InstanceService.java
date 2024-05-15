package com.tds.VMonClick.VMonClick.service;

import java.util.UUID;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.repository.InstanceRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class InstanceService {

  @Autowired
  private InstanceRepository instanceRepository;

  public void saveInstance(InstanceEntity instance) {
    instance.setDateCreated(LocalDateTime.now());
    instance.setId(UUID.randomUUID().toString());
    instance.setDate_finished(null);

    instanceRepository.save(instance);
  }

  public InstanceEntity getInstanceById(String id) {
    return instanceRepository.findById(id).get();
  }

  public List<InstanceEntity> getAllInstances() {
    return instanceRepository.findAll();
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

}
