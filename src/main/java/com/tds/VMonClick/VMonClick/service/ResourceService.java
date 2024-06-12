package com.tds.VMonClick.VMonClick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.repository.ResourceRepository;
import java.time.LocalDateTime;
import java.util.List;

@Service
public class ResourceService {

  @Autowired
  private ResourceRepository resourceRepository;

  @Autowired
  private HostRscService hostRscService;

  public ResourceEntity saveResource(ResourceEntity resource) {
    resource.setCreated_at(LocalDateTime.now());
    resource.setId(ResourceEntity.incrementalId);
    ResourceEntity.incrementalId++;
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
