package com.tds.VMonClick.VMonClick.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.VmEntity;
import com.tds.VMonClick.VMonClick.repository.VmRepository;
import java.util.Date;
import java.util.List;

@Service
public class VmService {
  @Autowired
  private VmRepository vmRepository;

  public String createVm(VmEntity vm) {
    vm.setId(vmRepository.findAll().size() + 1);
    vm.setDateCreated(new Date());
    vmRepository.save(vm);
    return "VM created successfully";
  }

  public String deleteVm(Integer id) {
    var vm = vmRepository.findById(id);
    if (vm.isEmpty()) {
      return "VM not found";
    }
    vmRepository.deleteById(id);
    return "VM deleted successfully";
  }

  public VmEntity getVm(Integer id) {
    return vmRepository.findById(id).orElse(null);
  }

  public List<VmEntity> getVms() {
    return vmRepository.findAll();
  }

}
