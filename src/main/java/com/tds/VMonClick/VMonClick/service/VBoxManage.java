package com.tds.VMonClick.VMonClick.service;

import java.io.BufferedReader;
import java.io.File;
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

    String idVm = String.format("\"_ubuntu_%s", instanceEntity.getId() + "\"");

    command.add("VBoxManage");
    command.add("createvm");
    command.add("--name");
    command.add(idVm);
    command.add("--ostype");
    command.add("Ubuntu_64");
    command.add("--uuid");
    command.add(instanceEntity.getId());
    command.add("--register");

    ProcessBuilder processBuilder = new ProcessBuilder();
    File directory = new File("C:/Program Files/Oracle/VirtualBox");
    processBuilder.directory(directory);
    processBuilder.command(command);
    Process process = processBuilder.start();
    printProcessOutput(process);

    command.clear();


    command.add("VBoxManage");
    command.add("modifyvm");
    command.add(idVm);
    command.add("--cpus");
    command.add(resourceEntity.getCpu().toString());
    command.add("--memory");
    command.add(resourceEntity.getRam().toString());
    command.add("--vram");
    command.add("128");
    command.add("--graphicscontroller");
    command.add("vmsvga");
    command.add("--usbohci");
    command.add("on");
    command.add("--mouse");
    command.add("usbtablet");


    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();


    // Crear el disco duro virtual
    String vdiFilePath = "C:\\Users\\Public/vdis/vdi_ubuntu " + instanceEntity.getId() + ".vdi";


    command.add("VBoxManage");
    command.add("createhd");
    command.add("--filename");
    command.add(vdiFilePath);
    command.add("--size");
    int diskSize = ((int) (double) resourceEntity.getDisk()) * 1024;
    command.add(diskSize + "");
    command.add("--variant");
    command.add("Standard");

    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();


    String sataName = "\"SATA Controller_" + instanceEntity.getId() + "\"";

    command.add("VBoxManage");
    command.add("storagectl");
    command.add(idVm);
    command.add("--name");
    command.add(sataName);
    command.add("--add");
    command.add("sata");
    command.add("--bootable");
    command.add("on");



    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();



    command.add("VBoxManage");
    command.add("storageattach");
    command.add(idVm);
    command.add("--storagectl");
    command.add(sataName);
    command.add("--port");
    command.add("0");
    command.add("--device");
    command.add("0");
    command.add("--type");
    command.add("hdd");
    command.add("--medium");
    command.add(vdiFilePath);


    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();

    String ideName = "\"IDE Controller_" + instanceEntity.getId() + "\"";
    command.add("VBoxManage");
    command.add("storagectl");
    command.add(idVm);
    command.add("--name");
    command.add(ideName);
    command.add("--add");
    command.add("ide");


    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();


    command.add("VBoxManage");
    command.add("storageattach");
    command.add(idVm);
    command.add("--storagectl");
    command.add(ideName);
    command.add("--port");
    command.add("0");
    command.add("--device");
    command.add("0");
    command.add("--type");
    command.add("dvddrive");
    command.add("--medium");
    command.add(vmEntity.getIso());

    processBuilder.command(command);
    process = processBuilder.start();
    printProcessOutput(process);
    command.clear();
  }

  public void startVMInstance(VmEntity vmEntity, ResourceEntity resourceEntity,
      InstanceEntity instanceEntity) throws IOException, InterruptedException {
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    command.add("VBoxManage");
    command.add("startvm");
    command.add(instanceEntity.getId());

    if (!command.isEmpty()) {
      processBuilder.command(command);
      Process process = processBuilder.start();
      printProcessOutput(process);
      command.clear();
    } else {
      System.out.println("La lista de comandos está vacía, no se puede iniciar el proceso");
    }
  }

  public void stopVmIntance(InstanceEntity instance) {
    String idVm = String.format("\"_ubuntu_%s", instance.getId() + "\"");
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    command.add("VBoxManage");
    command.add("controlvm");
    command.add(idVm);
    command.add("poweroff");
    processBuilder.command(command);
    try {
      Process process = processBuilder.start();
      printProcessOutput(process);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }

  public void deleteVM(InstanceEntity instance) {
    String idVm = String.format("\"_ubuntu_%s", instance.getId() + "\"");
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    command.add("VBoxManage");
    command.add("unregistervm");
    command.add(idVm);
    command.add("--delete");
    try {
      processBuilder.command(command);
      Process process = processBuilder.start();
      printProcessOutput(process);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
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

  public void getMetricsInstance() {
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    command.add("VBoxManage");
    command.add("metrics");
    command.add("query");
    command.add("3395d479-bb56-4c8d-9f23-025561b70231");
    command.add("CPU/Load/User,RAM/Usage/Used,Disk/Usage/Used,Net/Rate/Tx,Net/Rate/Rx");
    try {
      processBuilder.command(command);
      Process process = processBuilder.start();
      printProcessOutput(process);
    } catch (IOException | InterruptedException e) {
      e.printStackTrace();
    }
  }
}
