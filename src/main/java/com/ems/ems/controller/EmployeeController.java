package com.ems.ems.controller;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.model.Employee;
import com.ems.ems.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/admin/employee")
@CrossOrigin("*")
public class EmployeeController {

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public String addEmployee(@ModelAttribute("user") EmployeeDto dto){
        employeeService.save(dto);
        return "redirect:/admin/employee";
    }

    @GetMapping("")
    public String addEmployeeForm(Model model){
        EmployeeDto employeeDto = new EmployeeDto();
        employeeDto.setFirstName("");
        employeeDto.setLastName("");
        employeeDto.setEmail("");
        model.addAttribute("employee", employeeDto);
        model.addAttribute("user_list",employeeService.getAllEmployees());
        return "admin_templates/employee_add";
    }



}
