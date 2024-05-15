package com.tds.VMonClick.VMonClick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.service.InstanceService;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/instances")
public class InstanceController {

  @Autowired
  private InstanceService instanceService;

  @GetMapping
  public List<InstanceEntity> getInstances() {
    return instanceService.getAllInstances();
  }

  @GetMapping("/{id}")
  public InstanceEntity getInstanceById(@PathVariable String id) {
    return instanceService.getInstanceById(id);
  }

  @PostMapping
  public void saveInstance(@RequestBody InstanceEntity instance) {

    instanceService.saveInstance(instance);
  }

  @PutMapping("/{id}")
  public void updateInstance(@PathVariable String id,
      @RequestBody InstanceEntity instanceToUpdate) {
    instanceService.updateInstance(id, instanceToUpdate);
  }

  @DeleteMapping("/{id}")
  public void deleteInstance(@PathVariable String id) {
    instanceService.deleteInstanceById(id);
  }
}
