package com.tds.VMonClick.VMonClick.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.tds.VMonClick.VMonClick.model.InstanceEntity;
import com.tds.VMonClick.VMonClick.model.ResourceEntity;
import com.tds.VMonClick.VMonClick.model.VmEntity;
import com.tds.VMonClick.VMonClick.repository.InstanceRepository;
import io.jsonwebtoken.lang.Collections;

@Service
public class VBoxManage {

  @Autowired
  private InstanceRepository instanceRepository;

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
    command.add("--nic1");
    command.add("bridged");
    command.add("--bridgeadapter1");
    command.add("\"Realtek RTL8821CE 802.11ac PCIe Adapter\"");
    // command.add("--natpf1");
    // int randomPort = (int) (Math.random() * 16383 + 49152);
    // command.add("\"ssh,tcp,," + randomPort + ",,22\"");

    // instanceEntity.setPort(randomPort + "");
    instanceRepository.save(instanceEntity);

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
    command.add("--type");
    command.add("headless");

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

  public Map<String, Integer> getMetricsInstance(String idInstance) throws InterruptedException {
    var command = new ArrayList<String>();
    ProcessBuilder processBuilder = new ProcessBuilder();
    command.add("VBoxManage");
    command.add("metrics");
    command.add("collect");
    command.add(idInstance);
    try {
      processBuilder.command(command);
      processBuilder.start();
      command.clear();
    } catch (IOException e) {
      e.printStackTrace();
    }

    command.add("VBoxManage");
    command.add("metrics");
    command.add("query");
    command.add(idInstance);
    command.add("CPU/Load/User,RAM/Usage/Used,Disk/Usage/Used,Net/Rate/Tx,Net/Rate/Rx");



    String cpuLoadUser = null;
    String ramUsageUsed = null;
    String diskUsageUsed = null;
    String netRateTx = null;
    String netRateRx = null;
    Map<String, Integer> metrics = new HashMap<>();



    try {
      processBuilder.command(command);
      Process process = processBuilder.start();
      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String line;
      while ((line = reader.readLine()) != null) {
        if (line.contains("CPU/Load/User")) {
          cpuLoadUser = line.split("\\s+")[2].split("%")[0];
        } else if (line.contains("RAM/Usage/Used")) {
          ramUsageUsed = line.split("\\s+")[2];
        } else if (line.contains("Disk/Usage/Used")) {
          diskUsageUsed = line.split("\\s+")[2];
        } else if (line.contains("Net/Rate/Tx")) {
          netRateTx = line.split("\\s+")[2];
        } else if (line.contains("Net/Rate/Rx")) {
          netRateRx = line.split("\\s+")[2];
        }
      }
      int cpuFormat = (int) ((double) Double.parseDouble(cpuLoadUser) * 100);
      int ramFormat = Integer.parseInt(ramUsageUsed);
      int diskFormat = (int) (double) Double.parseDouble(diskUsageUsed);
      int netRateTxFormat = (int) (double) Double.parseDouble(netRateTx);
      int netRateRxFormat = (int) (double) Double.parseDouble(netRateRx);
      System.out.println("CPU/Load/User: " + cpuFormat);
      System.out.println("RAM/Usage/Used: " + ramFormat);
      System.out.println("Disk/Usage/Used: " + diskFormat);
      System.out.println("Net/Rate/Tx: " + netRateTxFormat);
      System.out.println("Net/Rate/Rx: " + netRateRxFormat);
      metrics.put("CPU", cpuFormat);
      metrics.put("RAM", ramFormat);
      metrics.put("DISK", diskFormat);
      metrics.put("NET_TX", netRateTxFormat);
      metrics.put("NET_RX", netRateRxFormat);
    } catch (IOException e) {
      e.printStackTrace();
    }
    return metrics;
  }

  public String getIpInstance(String idInstance) {
    var bashDir = "D:\\OLVADIS\\GIT\\bin\\bash.exe"; // Update this path to your Git Bash path
    try {
      String command = "VBoxManage.exe showvminfo \"" + idInstance
          + "\" | grep \"NIC\" | awk -F 'MAC: ' '{print $2}' | sed 's/,.*//' | tr '[:upper:]' '[:lower:]' | sed 's/\\(..\\)/\\1-/g' | sed 's/-$//'";


      List<String> commandList = new ArrayList<>();
      commandList.add(bashDir); // Update this path to your Git Bash path
      commandList.add("-c");
      commandList.add(command);



      ProcessBuilder processBuilder = new ProcessBuilder(commandList);
      Process process = processBuilder.start();

      BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
      String mac = null;
      String line;
      while ((line = reader.readLine()) != null) {
        if (!line.trim().isEmpty() && line.contains("-")) {
          mac = line.trim();
        }
      }
      System.out.println("*****************************" + mac);

      printProcessOutput(process);

      commandList.clear();
      command = "arp -a | findstr \"" + mac + "\"";

      commandList.add("cmd.exe");
      commandList.add("/c");
      commandList.add(command);

      processBuilder = new ProcessBuilder(commandList);
      process = processBuilder.start();
      reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

      String ip = null;
      Pattern pattern = Pattern.compile("(\\d{1,3}\\.\\d{1,3}\\.\\d{1,3}\\.\\d{1,3})");
      String lineIp;
      while ((lineIp = reader.readLine()) != null) {
        Matcher matcher = pattern.matcher(lineIp);
        if (matcher.find()) {
          ip = matcher.group(1);
        }
      }

      return ip;

    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }
}
