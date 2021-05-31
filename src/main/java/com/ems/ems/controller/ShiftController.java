package com.ems.ems.controller;

import com.ems.ems.dto.ShiftDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/shift")
public class ShiftController {

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAllShifts(){
        return null;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getShiftByUsername(@PathVariable String username){
        return null;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> addShift(){
        return null;
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
