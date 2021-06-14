package com.ems.ems.controller;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.dto.GenericResponse;
import com.ems.ems.dto.UserResponseDTO;
import com.ems.ems.service.EmployeeService;
import com.ems.ems.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
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

    @Autowired
    private ModelMapper modelMapper;

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping("")
    public ResponseEntity<?> addEmployee(@RequestBody EmployeeDto dto){
            logger.info(dto.getRole());
            employeeService.save(dto);
            logger.info("Employee added to the database");
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee added successfully")));


    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FINANCE') or hasRole('ROLE_HR')")
    @GetMapping("")
    public ResponseEntity<?> getAllEmployees(){
            List<EmployeeDto> employees=employeeService.getAllEmployees();
            logger.info("All employee information retrieved");
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(employees)));

    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable int id){
                logger.info("User with id="+id+" to be deleted");
                EmployeeDto employeeDto=employeeService.getEmployeeId(id);
                userService.delete(employeeDto.getEmail());
                employeeService.deleteEmployee(id);
                logger.info("Employee deleted successfully");
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee Deleted Successfully")));
    }


    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping(value="/{id}")
    public ResponseEntity<?> editEmployeeInfo(@PathVariable("id") int id,@RequestBody EmployeeDto employeeDto){
                logger.info("Employee with id:"+id+" to be edited");
                logger.info(employeeDto.getEmail());
                logger.info(employeeDto.getFirstName());
                logger.info(employeeDto.getLastName());
                logger.info(String.valueOf(employeeDto.getId()));

                employeeDto=employeeService.editEmployeeData(id,employeeDto);
                logger.info("Employee edited successfully");
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(employeeDto)));
    }



}
