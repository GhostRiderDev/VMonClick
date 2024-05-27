package com.tds.VMonClick.VMonClick.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
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



    var vmName = "Ubuntu_" + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-"
        + resourceEntity.getDisk() + "-" + instanceEntity.getId();
    // Crear la máquina virtual
    command.add("VBoxManage");
    command.add("createvm");
    command.add("--name");
    command.add(vmName);
    command.add("--register");
    command.add("--ostype");
    command.add("Ubuntu_64");
    processBuilder.command(command);

    Process process = processBuilder.start();
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();


    // Establecer el número de CPUs
    command.add("VBoxManage");
    command.add("modifyvm");
    command.add(vmName);
    command.add("--cpus");
    command.add(resourceEntity.getCpu().toString());
    command.add("--memory");
    command.add(resourceEntity.getRam().toString());
    command.add("--graphicscontroller");
    // ! command.add("vmsvga");
    command.add("VBoxSVGA");
    // command.add("--nic1");
    // command.add("bridged");

    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }

    // Crear el disco duro virtual
    String vdiFilePath = "C:/Users/Public/VMs/" + vmEntity.getId() + "/Ubuntu_"
        + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-" + resourceEntity.getDisk()
        + "-" + instanceEntity.getId() + ".vdi";
    command.add("VBoxManage");
    command.add("createmedium");
    command.add("--filename");
    command.add(vdiFilePath);
    command.add("--size");
    command.add(String.valueOf(30000));

    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }


    command.add("VBoxManage");
    command.add("storagectl");
    command.add(vmName);
    command.add("--name");
    command.add("IDE Controller");
    command.add("--add");
    command.add("ide");

    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }

    command.clear();

    command.add("VBoxManage");
    command.add("storagectl");
    command.add(vmName);
    command.add("--name");
    command.add("SATA Controller");
    command.add("--add");
    command.add("sata");

    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }

    command.clear();



    // Asegúrate de que el disco duro virtual está correctamente conectado
    command.add("VBoxManage");
    command.add("storageattach");
    command.add(vmName);
    command.add("--storagectl");
    command.add("SATA Controller");
    command.add("--port");
    command.add("0");
    command.add("--device");
    command.add("0");
    command.add("--type");
    command.add("hdd");
    command.add("--medium");
    command.add(vdiFilePath);

    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }

    command.clear();

    // Configurar el orden de arranque para arrancar desde el disco duro, DVD y Floppy
    command.add("VBoxManage");
    command.add("modifyvm");
    command.add(vmName);
    command.add("--boot1");
    command.add("disk");
    command.add("--boot2");
    command.add("dvd");
    command.add("--boot3");
    command.add("floppy");
    command.add("--boot4");
    command.add("none");


    if (!command.isEmpty()) {
      processBuilder.command(command);
      process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }


    // Adjuntar el archivo ISO a la máquina virtual
    String isoFilePath = vmEntity.getIso(); // Reemplaza esto con la ruta a tu archivo
                                            // ISO
    command.add("VBoxManage");
    command.add("storageattach");
    command.add(vmName);
    command.add("--storagectl");
    command.add("IDE Controller"); // Cambiado de "IDE Controller" a "SATA Controller"
    command.add("--port");
    command.add("1");
    command.add("--device");
    command.add("0");
    command.add("--type");
    command.add("dvddrive");
    command.add("--medium");
    command.add(isoFilePath);
    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();

  }

  public void startVMInstance(VmEntity vmEntity, ResourceEntity resourceEntity,
      InstanceEntity instanceEntity) throws IOException, InterruptedException {
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    var vmName = "Ubuntu_" + resourceEntity.getCpu() + "-" + resourceEntity.getRam() + "-"
        + resourceEntity.getDisk() + "-" + instanceEntity.getId();
    command.add("VBoxManage");
    command.add("unattended");
    command.add("install");
    command.add(vmName);
    command.add("--iso");
    command.add(vmEntity.getIso());
    command.add("--user");
    command.add("admin");
    command.add("--password");
    command.add("admin");
    command.add("--full-user-name");
    command.add("admin");
    command.add("--install-additions");
    command.add("--locale");
    command.add("en_US");
    command.add("--country");
    command.add("US");
    command.add("--time-zone");
    command.add("PST");
    command.add("--start-vm");
    command.add("headless");


    processBuilder.command(command);
    Process process = processBuilder.start();

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
