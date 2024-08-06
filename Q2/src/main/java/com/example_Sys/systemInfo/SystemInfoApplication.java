package com.example_Sys.systemInfo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import oshi.SystemInfo;
import oshi.hardware.CentralProcessor;
import oshi.hardware.GlobalMemory;
import oshi.hardware.HardwareAbstractionLayer;
import oshi.software.os.OperatingSystem;

@SpringBootApplication
public class SystemInfoApplication implements CommandLineRunner {

	public static void main(String[] args) {
		SpringApplication.run(SystemInfoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("hello world");
		SystemInfo systemInfo = new SystemInfo();
		HardwareAbstractionLayer hal = systemInfo.getHardware();
		OperatingSystem os = systemInfo.getOperatingSystem();

		// Get CPU Information
		CentralProcessor processor = hal.getProcessor();
		System.out.println("Processor: " + processor);

		// Get Memory Information
		GlobalMemory memory = hal.getMemory();
		System.out.println("Memory: " + memory);

		// Get OS Information
		System.out.println("Operating System: " + os);
	}
}
