package com.ems.ems.controller;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.dto.GenericResponse;
import com.ems.ems.model.User;
import com.ems.ems.service.EmployeeService;
import com.ems.ems.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/admin/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addEmployee(Authentication authentication, @RequestBody EmployeeDto dto){
            employeeService.save(dto);
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee added successfully")));


    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("")
    public ResponseEntity<?> getAllEmployees(Authentication authentication){
            List<EmployeeDto> employees=employeeService.getAllEmployees();
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(employees)));

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
                logger.info("User with id="+id+" to be deleted");
                EmployeeDto employeeDto=employeeService.getEmployeeId(id);
                userService.delete(employeeDto.getEmail());
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee Deleted Successfully")));
    }


    @PutMapping(value="/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> editEmployeeInfo(Authentication authentication,@PathVariable int id,@RequestBody EmployeeDto employeeDto){
                employeeDto=employeeService.editEmployeeData(id,employeeDto);
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", (List<Object>) employeeDto));
    }



}
