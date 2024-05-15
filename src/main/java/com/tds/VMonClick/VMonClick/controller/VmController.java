package com.tds.VMonClick.VMonClick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tds.VMonClick.VMonClick.model.VmEntity;
import com.tds.VMonClick.VMonClick.service.VmService;

import java.util.List;

@RestController
@RequestMapping("/vm")
public class VmController {

  @Autowired
  private VmService vmService;

  @PostMapping()
  public String createVm(@RequestBody VmEntity vm) {
    return vmService.createVm(vm);
  }

  @DeleteMapping("/{id}")
  public String deleteVm(@PathVariable("id") Integer id) {
    return vmService.deleteVm(id);
  }

  @GetMapping("/{id}")
  public VmEntity getVm(@PathVariable("id") Integer id) {
    return vmService.getVm(id);
  }

  @GetMapping
  public List<VmEntity> getVms() {
    return vmService.getVms();
  }
}
