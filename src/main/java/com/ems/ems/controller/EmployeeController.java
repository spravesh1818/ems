package com.ems.ems.controller;

import com.ems.ems.config.GenericResponse;
import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.service.EmployeeService;
import com.ems.ems.util.AuthorizationUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {

    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    EmployeeService employeeService;

    @PostMapping("")
    public ResponseEntity<?> addEmployee(Authentication authentication, @RequestBody EmployeeDto dto){
        if(AuthorizationUtil.getInstance().checkAdminRole(authentication)) {
            employeeService.save(dto);
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", "Employee added successfully"));
        }
        else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }

    @GetMapping("")
    public ResponseEntity<?> getAllEmployees(Authentication authentication){
        if(AuthorizationUtil.getInstance().checkAdminRole(authentication)) {
            List<EmployeeDto> employees=employeeService.getAllEmployees();
            return ResponseEntity.ok(new GenericResponse(200, "SUCCESS",employees));
        }
        else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }


    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteEmployee(Authentication authentication,@PathVariable int id){

        if(AuthorizationUtil.getInstance().checkAdminRole(authentication)) {
            try {
                employeeService.deleteEmployee(id);
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS","Employee Deleted Successfully"));
            }catch (NoSuchElementException e){
                logger.info(e.getClass().getName().toString());
                logger.info(e.getMessage());
                return ResponseEntity.ok(new GenericResponse(404, "Error","Item not present in the database"));
            }catch (Exception e){
                return ResponseEntity.ok(new GenericResponse(500, "Error","An Error Occured.Please try again later"));
            }

        }
        else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }



    @PutMapping(value="/{id}")
    public ResponseEntity<?> editEmployeeInfo(Authentication authentication,@PathVariable int id,@RequestBody EmployeeDto employeeDto){

        if(AuthorizationUtil.getInstance().checkAdminRole(authentication)) {
            try {
                employeeDto=employeeService.editEmployeeData(id,employeeDto);
                return ResponseEntity.ok(new GenericResponse(200, "SUCCESS",employeeDto));
            }catch (NoSuchElementException e){
                logger.info(e.getClass().getName().toString());
                logger.info(e.getMessage());
                return ResponseEntity.ok(new GenericResponse(404, "Error","Item not present in the database"));
            }catch (Exception e){
                return ResponseEntity.ok(new GenericResponse(500, "Error","An Error Occured.Please try again later"));
            }

        }
        else {
            return new ResponseEntity<String>("Unauthorized", HttpStatus.UNAUTHORIZED);
        }
    }



}
