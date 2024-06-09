package com.tds.VMonClick.VMonClick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.tds.VMonClick.VMonClick.service.HostRscService;
import com.tds.VMonClick.VMonClick.service.MetricService;
import com.tds.VMonClick.VMonClick.cron.HostInfoCron;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;;

@EnableScheduling
@SpringBootApplication
public class VMonClickApplication {

    @Autowired
    private HostRscService hostRscService;


    @Autowired
    MetricService metricService;

    public static void main(String[] args) {
        SpringApplication.run(VMonClickApplication.class, args);
    }

    // @Bean
    // public CommandLineRunner commandLineRunner() {
    // return args -> {
    // fetchHostResources();
    // fetchMetricInstances();
    // };
    // }

    @Scheduled(fixedRate = 30000) // 30 seconds
    public void fetchHostResources() {
        HostInfoCron.getRamDiskCpu().forEach((k, v) -> {
            System.out.println(k + " : " + v);
        });
        HostRscEntity hostRsc = new HostRscEntity();
        hostRsc.setCpu(HostInfoCron.getRamDiskCpu().get("CPU").intValue());
        hostRsc.setRam(HostInfoCron.getRamDiskCpu().get("RAM").intValue());
        hostRsc.setDisk(HostInfoCron.getRamDiskCpu().get("DISK").doubleValue());
        hostRscService.createHostRsc(hostRsc);
    }



    @Scheduled(fixedRate = 30000) // 30 seconds
    public void fetchMetricInstances() {
        metricService.saveMetricsIntance();
    }

}
