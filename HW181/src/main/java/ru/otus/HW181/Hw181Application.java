package ru.otus.HW181;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.cloud.netflix.hystrix.dashboard.EnableHystrixDashboard;
import org.springframework.cloud.netflix.turbine.EnableTurbine;


@EnableHystrix
@EnableHystrixDashboard
@EnableTurbine
@SpringBootApplication
public class Hw181Application {

	public static void main(String[] args) {
		SpringApplication.run(Hw181Application.class, args);
	}

}
