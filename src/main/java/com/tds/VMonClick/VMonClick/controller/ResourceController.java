package com.tds.VMonClick.VMonClick.controller;

import org.springframework.web.bind.annotation.RestController;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import java.util.List;

@RestController
@RequestMapping("/resources")
public class ResourceController {

  @Autowired
  private ResourceService resourceService;

  @GetMapping()
  public List<ResourceEntity> getResources() {
    return resourceService.getAllResources();
  }

  @GetMapping("/{id}")
  public ResourceEntity getResource(@PathVariable("id") Integer id) {

    return resourceService.getResourceById(id);
  }

  @PostMapping()
  public ResourceEntity createResource(@RequestBody ResourceEntity resource) {
    return resourceService.saveResource(resource);
  }

  @PutMapping("/{id}")
  public ResourceEntity updateResource(@RequestParam Integer id,
      @RequestBody ResourceEntity resource) {
    return resourceService.updateResource(id, resource);
  }

  @DeleteMapping("/{id}")
  public void deleteResource(@RequestParam Integer id) {
    resourceService.deleteResourceById(id);
  }
}
