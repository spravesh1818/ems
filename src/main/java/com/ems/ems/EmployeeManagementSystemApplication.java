package com.ems.ems;

import com.ems.ems.model.Role;
import com.ems.ems.model.User;
import com.ems.ems.service.UserService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.util.ArrayList;
import java.util.Arrays;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ems.ems.repository")
public class EmployeeManagementSystemApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeManagementSystemApplication.class);


    @Autowired
    UserService userService;


    public static void main(String[] args) throws Throwable {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

    @Override
    public void run(String... params){
        try{
            User admin = new User();
            admin.setUsername("admin");
            admin.setPassword("admin");
            admin.setEmail("admin@email.com");
            admin.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_ADMIN)));

            userService.signup(admin);

            User client = new User();
            client.setUsername("client");
            client.setPassword("client");
            client.setEmail("client@email.com");
            client.setRoles(new ArrayList<Role>(Arrays.asList(Role.ROLE_CLIENT)));

            userService.signup(client);
        }catch(Exception ex){
            logger.info("Users already exist");
        }

    }

}
