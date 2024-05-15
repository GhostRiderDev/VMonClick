package com.tds.VMonClick.VMonClick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.repository.HostRscRepository;
import com.tds.VMonClick.VMonClick.repository.ResourceRepository;
import lombok.extern.slf4j.Slf4j;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Slf4j
public class ResourceService {

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private HostRscService hostRscService;

  public ResourceEntity saveResource(ResourceEntity resource) {
    var lastRegister = hostRscService.getLastRegister();
    if (resource.getDisk() > lastRegister.getDisk() || resource.getCpu() > lastRegister.getCpu()
        || resource.getRam() > lastRegister.getRam()) {
      return null;
    }

    if (resourceRepository
        .isDuplicateResource(resource.getCpu(), resource.getRam(), resource.getDisk()).size() > 0) {
      return null;
    }

    resource.setId(ResourceEntity.incrementalId++);
    resource.setCreated_at(LocalDateTime.now());

    System.out.println("****************************");
    System.out.println("Resource created: " + resource.toString());
    System.out.println("****************************");

    return resourceRepository.save(resource);
  }

  public ResourceEntity getResourceById(Integer id) {
    return resourceRepository.findById(id).orElse(null);
  }

  public void deleteResourceById(Integer id) {
    resourceRepository.deleteById(id);
  }

  public List<ResourceEntity> getAllResources() {
    return resourceRepository.findAll();
  }

  public ResourceEntity updateResource(Integer id, ResourceEntity resource) {
    var existingResource = resourceRepository.findById(id).orElse(null);
    if (existingResource == null) {
      return null;
    }
    resource.setId(id);
    return resourceRepository.save(resource);
  }
}
