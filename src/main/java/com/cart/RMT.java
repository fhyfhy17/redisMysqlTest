package com.cart;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.annotation.DependsOn;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.EnableAsync;

@SpringBootApplication(exclude={DataSourceAutoConfiguration.class})
@DependsOn(value = {"springUtils"})
@EnableAsync
public class RMT implements CommandLineRunner {

    public static void main(String[] args) {
        SpringApplication.run(RMT.class, args);
    }

    public void run(String... args) throws Exception {

    }


    @EventListener
    void afterSpringBoot(ApplicationReadyEvent event) throws Exception {
    }
}
