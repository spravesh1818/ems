package com.ems.ems.controller;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.dto.GenericResponse;
import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.service.SalarySlipService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/salary-slip")
public class SalarySlipController {

    private static final Logger logger = LoggerFactory.getLogger(SalarySlipController.class);


    @Autowired
    private SalarySlipService salarySlipService;

    @Autowired
    private EmployeeRepository employeeRepo;


    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR') or hasRole('ROLE_FINANCE')")
    public ResponseEntity<?> getAllSalarySlips(){
        logger.info("Getting all salary slips");
        try {
            List<SalaryDTO> salaryDTOS = salarySlipService.getAllSalarySlip();
            return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(salaryDTOS)));
        }catch (Exception ex){
            logger.info(ex.getMessage());
            ex.printStackTrace();
            logger.info("An exception occurred");
            return null;
        }
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR') or hasRole('ROLE_FINANCE')")
    public ResponseEntity<?> addSalarySlip(@RequestBody SalaryDTO salaryDTO) throws ParseException {
        logger.info("Data has arrived");
        Optional<Employee> employee=employeeRepo.findById(salaryDTO.getEmployee_id());

        //first parse the date

        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(salaryDTO.getDate());
        SalarySlip salarySlip=new SalarySlip();
        salarySlip.setEmployee(employee.get());
        salarySlip.setDate(date);
        salarySlip.setDeductions(salaryDTO.getDeductions());
        salarySlip.setGrandTotal(salaryDTO.getGrandTotal());
        salarySlip.setGrossAmount(salaryDTO.getGrossAmount());
        salarySlip.setNetAmount(salaryDTO.getNetAmount());
        salaryDTO=salarySlipService.addSalarySlip(salarySlip);
        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(salaryDTO)));
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_FINANCE')")
    public ResponseEntity<?> deleteSalarySlip(@PathVariable int id){
        salarySlipService.deleteSalarySlip(id);
        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList("SalarySlip Deleted Successfully")));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCE')")
    public ResponseEntity<?> editSalarySlip(@PathVariable int id,@RequestBody SalaryDTO salaryDTO){
        try{
        salaryDTO=salarySlipService.editSalarySlip(id,salaryDTO);
        logger.info("Salary slip edited successfully");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(salaryDTO)));}
        catch (Exception e){
            return ResponseEntity.ok(new GenericResponse(500, "Error", Collections.singletonList("Error Occured While Parsing Date")));
        }
    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ROLE_FINANCE')")
    public ResponseEntity<?> getSalarySlipByEmployeeId(@PathVariable int id){
        List<SalaryDTO> salarySlips=salarySlipService.getSalarySlipByEmployeeId(id);
        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(salarySlips)));
    }


}
