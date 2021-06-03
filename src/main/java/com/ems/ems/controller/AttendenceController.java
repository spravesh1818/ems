package com.ems.ems.controller;


import com.ems.ems.dto.AttendenceDTO;
import com.ems.ems.dto.GenericResponse;
import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.model.Attendence;
import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.service.AttendenceService;
import com.google.j2objc.annotations.AutoreleasePool;
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
@RequestMapping("/attendence")
public class AttendenceController {

    private static final Logger logger = LoggerFactory.getLogger(AttendenceController.class);

    @Autowired
    private AttendenceService attendenceService;

    @Autowired
    private EmployeeRepository employeeRepo;

    @GetMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAllAttendence(){

        logger.info("Getting all attendences");
        try {
            List<AttendenceDTO> attendenceDTOS = attendenceService.getAllAttendences();
            return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(attendenceDTOS)));
        }catch (Exception ex){
            logger.info(ex.getMessage());
            ex.printStackTrace();
            logger.info("An exception occurred");
            return null;
        }


    }

    @PostMapping("")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> addAttendenceRecord(@RequestBody AttendenceDTO attendenceDTO) throws ParseException {

        logger.info("Adding attendence information");
        Optional<Employee> employee=employeeRepo.findById(attendenceDTO.getEmployee_id());

        //first parse the date

        Date date=new SimpleDateFormat("dd/MM/yyyy").parse(attendenceDTO.getDate());
        Date timeArrived=new SimpleDateFormat("dd/MM/yyyy").parse(attendenceDTO.getTimeArrived());
        Date timeLeft=new SimpleDateFormat("dd/MM/yyyy").parse(attendenceDTO.getTimeLeft());
        Attendence attendence=new Attendence();
        attendence.setEmployee(employee.get());
        attendence.setDate(date);
        attendence.setTimeArrived(timeArrived);
        attendence.setTimeLeft(timeLeft);
        attendence.setTotalHours(attendenceDTO.getTotalHours());

        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(attendenceDTO)));


    }

    @GetMapping("/employee/{id}")
    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    public ResponseEntity<?> getAttendenceByEmployee(@PathVariable int id){
        List<AttendenceDTO> attendenceDTOS=attendenceService.getAttendenceByEmployeeId(id);
        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList(attendenceDTOS)));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> deleteAttendenceRecord(@PathVariable int id){
        attendenceService.deleteAttendence(id);
        return ResponseEntity.ok(new GenericResponse(200,"SUCCESS", Collections.singletonList("Attendence Deleted Successfully")));

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasRole('ROLE_HR')")
    public ResponseEntity<?> editAttendenceEntry(@PathVariable int id, AttendenceDTO attendenceDTO){
        return null;
    }


}
