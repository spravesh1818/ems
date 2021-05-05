package com.ems.ems;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ems.ems.repository")
public class EmployeeManagementSystemApplication {

    public static void main(String[] args) throws Throwable {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

}
