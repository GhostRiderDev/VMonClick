package com.tds.VMonClick.VMonClick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;
import com.tds.VMonClick.VMonClick.service.HostRscService;

import java.util.*;

@RestController
@RequestMapping("host")
public class HostRscController {
  @Autowired
  private HostRscService hostRscService;

  @GetMapping
  public ResponseEntity<List<HostRscEntity>> getAllHostRscs() {
    return ResponseEntity.ok().body(hostRscService.getAllHostRscs());
  }
}
