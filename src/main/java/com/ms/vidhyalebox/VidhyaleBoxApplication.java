package com.ms.vidhyalebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;

//@EnableAsync
@EnableTransactionManagement
@SpringBootApplication
@EnableScheduling
public class VidhyaleBoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(VidhyaleBoxApplication.class, args);
    }

}
