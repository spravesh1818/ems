package com.ems.ems;

import com.ems.ems.model.User;
import com.ems.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EnableJpaRepositories(basePackages = "com.ems.ems.repository")
public class EmployeeManagementSystemApplication implements CommandLineRunner {

    @Autowired
    UserRepository userRepository;


    public static void main(String[] args) throws Throwable {
        SpringApplication.run(EmployeeManagementSystemApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        loadUserData();
    }

    private void loadUserData() {
        if (userRepository.count() == 0) {
            User user = new User();
            user.setUserName("admin");
            user.setPassword("pass");
            user.setActive(true);
            user.setRoles("ROLE_ADMIN");
            userRepository.save(user);
        }
        System.out.println(userRepository.count());
    }

}
