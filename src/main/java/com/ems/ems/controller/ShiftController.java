package com.ems.ems.controller;

import com.ems.ems.dto.GenericResponse;
import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.dto.ShiftDTO;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.service.AttendenceService;
import com.ems.ems.service.ShiftService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/shift")
public class ShiftController {

    private static final Logger logger = LoggerFactory.getLogger(ShiftService.class);

    @Autowired
    private ShiftService shiftService;

    @Autowired
    private EmployeeRepository employeeRepo;



    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAllShifts(){
        logger.info("Getting all salary slips");
        try {
            List<ShiftDTO> shiftDTOS = shiftService.getAllShifts();
            return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(shiftDTOS)));
        }catch (Exception ex){
            logger.info(ex.getMessage());
            ex.printStackTrace();
            logger.info("An exception occurred");
            return null;
        }
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getShiftByUsername(@PathVariable String username){
        return null;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> addShift(@RequestBody ShiftDTO shiftDTO){

    }


    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> deleteShift(@PathVariable int id){
        return null;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> editShift(@PathVariable int id,@RequestBody ShiftDTO shiftDTO){
        return null;
    }



}
