package com.web.pucmm.practica10;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import java.io.File;

@SpringBootApplication
public class DemoApplication {

	public static void main(String[] args) {

		new File(System.getProperty("user.dir") + "/src/main/resources/static/avatars").mkdir();

		SpringApplication.run(DemoApplication.class, args);
	}
}
