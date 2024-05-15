package com.tds.VMonClick.VMonClick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;
import com.tds.VMonClick.VMonClick.repository.HostRscRepository;

import java.util.List;
import java.util.UUID;

import java.time.LocalDateTime;

@Service
public class HostRscService {
  @Autowired
  private HostRscRepository hostRscRepository;

  public List<HostRscEntity> getAllHostRscs() {
    return hostRscRepository.findAll();
  }

  public HostRscEntity getHostRscById(String id) {
    return hostRscRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RuntimeException("HostRsc not found"));
  }

  public HostRscEntity createHostRsc(HostRscEntity hostRsc) {
    hostRsc.setId(UUID.randomUUID());
    hostRsc.setDateRegistered(LocalDateTime.now());
    return hostRscRepository.save(hostRsc);
  }

  public HostRscEntity updateHostRsc(String id, HostRscEntity hostRsc) {
    HostRscEntity hostRscToUpdate = hostRscRepository.findById(UUID.fromString(id))
        .orElseThrow(() -> new RuntimeException("HostRsc not found"));
    hostRscToUpdate.setCpu(hostRsc.getCpu());
    hostRscToUpdate.setRam(hostRsc.getRam());
    hostRscToUpdate.setDisk(hostRsc.getDisk());
    hostRscToUpdate.setDateRegistered(hostRsc.getDateRegistered());
    return hostRscRepository.save(hostRscToUpdate);
  }

  public void deleteHostRsc(String id) {
    hostRscRepository.deleteById(UUID.fromString(id));
  }

  public HostRscEntity getLastRegister() {
    var allRegisters = hostRscRepository.findAll();
    return allRegisters.stream()
        .sorted((r1, r2) -> r2.getDateRegistered().compareTo(r1.getDateRegistered())).findFirst()
        .orElse(null);
  }
}
