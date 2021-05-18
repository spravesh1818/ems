package com.ems.ems.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;

@Controller

public class IndexController {
    @GetMapping("/")
    public String home() {
        return ("<h1>Welcome</h1>");
    }

    @GetMapping("/employee")
    public String user() {
        return "employee_templates/employee";
    }

    @GetMapping("/admin")
    public String admin() {
        return "redirect:/admin/employee";
    }
}
