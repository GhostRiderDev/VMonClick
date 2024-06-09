package com.tds.VMonClick.VMonClick.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.tds.VMonClick.VMonClick.model.MetricEntity;
import com.tds.VMonClick.VMonClick.service.MetricService;

import java.util.*;

@RestController
@RequestMapping("metrics")
public class MetricController {

  @Autowired
  private MetricService metricService;

  @GetMapping()
  public ResponseEntity<List<MetricEntity>> findMetrics() {
    return ResponseEntity.ok(metricService.getMetrics());
  }

  @GetMapping("/instance/{idInstance}")
  public ResponseEntity<List<MetricEntity>> findMetricsInstance(
      @PathVariable("idInstance") String idInstance) {
    return ResponseEntity.ok(metricService.getMetricsInstance(idInstance));
  }

}
