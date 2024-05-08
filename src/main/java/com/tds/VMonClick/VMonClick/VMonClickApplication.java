package com.tds.VMonClick.VMonClick;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VMonClickApplication {

    public static void main(String[] args) {
       SpringApplication.run(VMonClickApplication.class, args);
    }

    // try

    // {
    // // Comando para listar todas las máquinas virtuales
    // List<String> command = new ArrayList<>();
    // command.add("VBoxManage");
    // command.add("list");
    // command.add("hostinfo");

    // // Construir el proceso
    // ProcessBuilder pb = new ProcessBuilder(command);
    // pb.redirectErrorStream(true);

    // // Ejecutar el comando
    // Process process = pb.start();

    // // Leer la salida del proceso
    // InputStream inputStream = process.getInputStream();
    // BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
    // String line;
    // while ((line = reader.readLine()) != null) {
    // System.out.println(line);
    // }

    // // Esperar a que el proceso termine
    // int exitCode = process.waitFor();
    // if (exitCode == 0) {
    // System.out.println("Comando ejecutado correctamente.");
    // } else {
    // System.out.println("Error al ejecutar el comando. Código de salida: " + exitCode);
    // }
    // }catch(IOException|
    // InterruptedException e)
    // {
    // e.printStackTrace();
    // }

}
