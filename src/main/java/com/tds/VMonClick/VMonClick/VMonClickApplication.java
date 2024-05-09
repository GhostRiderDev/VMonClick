package com.tds.VMonClick.VMonClick;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;
import com.tds.VMonClick.VMonClick.service.HostRscService;
import lombok.extern.java.Log;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@EnableScheduling
@SpringBootApplication
public class VMonClickApplication {

    @Autowired
    private HostRscService hostRscService;

    public static void main(String[] args) {
        SpringApplication.run(VMonClickApplication.class, args);
    }

    @Bean
    public CommandLineRunner commandLineRunner() {
        return args -> {
            fetchHostResources();
        };

    }

    @Scheduled(fixedRate = 30000) // 30 seconds
    public void fetchHostResources() {
        try {
            List<String> command = new ArrayList<>();
            command.add("VBoxManage");
            command.add("list");
            command.add("hostinfo");

            ProcessBuilder pb = new ProcessBuilder(command);
            pb.redirectErrorStream(true);

            Process process = pb.start();

            InputStream inputStream = process.getInputStream();
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line;
            var hostRsc = new HostRscEntity();
            while ((line = reader.readLine()) != null) {
                if (line.contains("Memory available")) {
                    String[] lineram = line.split(":");
                    var ram = Integer.parseInt(lineram[1].trim().split(" ")[0]);
                    hostRsc.setRam(ram);;
                }
                if (line.contains("Processor online count")) {
                    String[] lineCpu = line.split(":");
                    var cpu = Integer.parseInt(lineCpu[1].trim());
                    hostRsc.setCpu(cpu);
                }
            }

            // Esperar a que el proceso termine
            int exitCode = process.waitFor();
            if (exitCode == 0) {
                System.out.println("**************" + freeDisk());
                System.out.println("Comando ejecutado correctamente.");
            } else {
                System.out.println("Error al ejecutar el comando. Código de salida: " + exitCode);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }
    }

    public Long freeDisk() {
        Long disk = -1L;

        ProcessBuilder processBuilderDisk = new ProcessBuilder();
        processBuilderDisk.command("cmd.exe", "/c", "wmic logicaldisk get size,freespace,DeviceID");

        try {
            Process processDisk = processBuilderDisk.start();
            BufferedReader readerDisk =
                    new BufferedReader(new InputStreamReader(processDisk.getInputStream()));

            String lineDisk;
            while ((lineDisk = readerDisk.readLine()) != null) {
                if (lineDisk.contains("C:") || lineDisk.contains("D:")) {
                    String[] lineDiskParts = lineDisk.split("\\s+");
                    disk = Long.parseLong(lineDiskParts[1].trim());
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return disk;
    }
}
