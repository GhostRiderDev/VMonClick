package com.tds.VMonClick.VMonClick.cron;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import com.tds.VMonClick.VMonClick.model.HostRscEntity;

public class HostInfoCron {
  public static Double freeDisk() {
    Double disk = 1.0;

    ProcessBuilder processBuilderDisk = new ProcessBuilder();
    processBuilderDisk.command("cmd.exe", "/c", "wmic logicaldisk get size,freespace,DeviceID");

    try {
      Process processDisk = processBuilderDisk.start();
      BufferedReader readerDisk =
          new BufferedReader(new InputStreamReader(processDisk.getInputStream()));

      String lineDisk;
      while ((lineDisk = readerDisk.readLine()) != null) {
        if (lineDisk.contains("C:")) {
          String[] lineDiskParts = lineDisk.split("\\s+");
          disk = Double.parseDouble(lineDiskParts[1].trim());
          disk = disk / 1024 / 1024 / 1024;
        }
      }
    } catch (IOException e) {
      e.printStackTrace();
    }
    return disk;
  }

  public static Map<String, Number> getRamDiskCpu() {
    Map<String, Number> hostRscs = new HashMap<String, Number>();
    var hostRsc = new HostRscEntity();
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

      int exitCode = process.waitFor();
      if (exitCode == 0) {
      } else {
        System.out.println("Error al ejecutar el comando. Código de salida: " + exitCode);
      }
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
    hostRscs.put("RAM", hostRsc.getRam());
    hostRscs.put("CPU", hostRsc.getCpu());
    hostRscs.put("DISK", HostInfoCron.freeDisk().doubleValue());
    return hostRscs;
  }

}
