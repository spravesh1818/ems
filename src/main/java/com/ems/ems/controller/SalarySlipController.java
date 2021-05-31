package com.ems.ems.controller;

import com.ems.ems.dto.SalaryDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/salary-slip")
public class SalarySlipController {

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAllSalarySlips(){
        return null;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getSalarySlipByEmployee(@PathVariable String username){
        return null;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> addSalarySlip(){
        return null;
    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> deleteSalarySlip(@PathVariable int id){
        return null;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> editSalarySlip(@PathVariable int id,@RequestBody SalaryDTO salaryDTO){
        return null;
    }


}
