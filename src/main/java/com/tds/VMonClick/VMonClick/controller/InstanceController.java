package com.tds.VMonClick.VMonClick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
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
import java.io.IOException;
import java.util.List;

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

  @GetMapping("/user/{id}")
  public List<InstanceEntity> getInstancesByUser(@PathVariable("id") String idUser) {
    return instanceService.getInstancesByUser(idUser);
  }

  @PostMapping("/{id}/stop")
  public ResponseEntity<String> stopInstance(@PathVariable("id") String id)
      throws IOException, InterruptedException {
    return instanceService.stopInstance(id);
  }

  @CrossOrigin(origins = "http://localhost:5173")
  @GetMapping("/{id}/delete")
  public ResponseEntity<String> deleteInstance(@PathVariable("id") String id)
      throws IOException, InterruptedException {
    return instanceService.deleteInstance(id);
  }

  @PostMapping
  public ResponseEntity<InstanceEntity> saveInstance(@RequestBody InstanceEntity instance)
      throws IOException, InterruptedException {
    return ResponseEntity.status(HttpStatus.CREATED).body(instanceService.saveInstance(instance));
  }

  @PutMapping("/{id}")
  public void updateInstance(@PathVariable String id,
      @RequestBody InstanceEntity instanceToUpdate) {
    instanceService.updateInstance(id, instanceToUpdate);
  }

  @PostMapping("/{id}/start")
  public ResponseEntity<String> startInstance(@PathVariable("id") String id)
      throws IOException, InterruptedException {
    return instanceService.startInstance(id);
  }
}
