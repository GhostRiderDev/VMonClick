package com.tds.VMonClick.VMonClick.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.model.VmEntity;

@Service
public class VBoxManage {
  public void createVM(VmEntity vmEntity, ResourceEntity resourceEntity,
      InstanceEntity instanceEntity) throws IOException, InterruptedException {
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();

    // Crear la VM
    command.add("VBoxManage");
    command.add("createvm");
    command.add("--name");
    command.add("Ubuntu_".concat(
        resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-" + resourceEntity.getDisk()));
    command.add("--ostype");
    command.add("Ubuntu_64");
    command.add("--register");
    command.add("--basefolder");
    command.add("C:/Users/Public/VMs");
    command.add("--uuid");
    command.add(instanceEntity.getId().toString());

    processBuilder.command(command);
    Process process = processBuilder.start();
    printProcessOutput(process);

    command.clear();

    // Establecer el número de CPUs
    command.add("VBoxManage");
    command.add("modifyvm");
    command.add("Ubuntu_" + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-"
        + resourceEntity.getDisk());
    command.add("--cpus");
    command.add(resourceEntity.getCpu().toString());
    command.add("--memory");
    command.add(resourceEntity.getRam().toString());
    command.add("--graphicscontroller");
    command.add("vmsvga");

    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);

    command.clear();

    // Crear el controlador IDE
    command.add("VBoxManage");
    command.add("storagectl");
    command.add("Ubuntu_" + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-"
        + resourceEntity.getDisk());
    command.add("--name");
    command.add("IDE Controller");
    command.add("--add");
    command.add("ide");

    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);

    command.clear();

    // Asociar la imagen ISO a la VM
    command.add("VBoxManage");
    command.add("storageattach");
    command.add("Ubuntu_" + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-"
        + resourceEntity.getDisk());
    command.add("--storagectl");
    command.add("IDE Controller");
    command.add("--port");
    command.add("1");
    command.add("--device");
    command.add("0");
    command.add("--type");
    command.add("dvddrive");
    command.add("--medium");
    command.add(vmEntity.getIso());

    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
  }

  private void printProcessOutput(Process process) throws IOException, InterruptedException {
    BufferedReader stdInput = new BufferedReader(new InputStreamReader(process.getInputStream()));
    BufferedReader stdError = new BufferedReader(new InputStreamReader(process.getErrorStream()));
    String line;
    while ((line = stdInput.readLine()) != null) {
      System.out.println(LocalDateTime.now().toString() + " - [FINE] - " + line);
    }
    while ((line = stdError.readLine()) != null) {
      System.err.println(LocalDateTime.now().toString() + " - [ERROR] - " + line);
    }
    int exitCode = process.waitFor();
    if (exitCode != 0)
      System.out.println("Error code: " + exitCode);
    else
      System.out.println("Process executed successfully");
  }
}
