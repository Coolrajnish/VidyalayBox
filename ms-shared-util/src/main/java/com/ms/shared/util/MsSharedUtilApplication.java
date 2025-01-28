package com.ms.shared.util;

import org.slf4j.MDC;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class MsSharedUtilApplication {

	public static void main(String[] args) {
		MDC.put("app.name", "til-shared-util");
		SpringApplication.run(MsSharedUtilApplication.class, args);
	}

}
