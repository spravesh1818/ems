package com.ems.ems.controller;


import com.ems.ems.dto.AttendenceDTO;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(value = "*")
@RequestMapping("/attendence")
public class AttendenceController {

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAllAttendence(){
        return null;
    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> addAttendenceRecord(){
        return null;
    }

    @GetMapping("/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAttendenceByEmployee(@PathVariable String username){
        return null;
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> deleteAttendenceRecord(@PathVariable int id){
        return null;
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> editAttendenceEntry(@PathVariable int id, AttendenceDTO attendenceDTO){
        return null;
    }


}
