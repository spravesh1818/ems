package com.ems.ems.controller;

import com.ems.ems.dto.GenericResponse;
import com.ems.ems.dto.LeaveDTO;
import com.ems.ems.dto.UserResponseDTO;
import com.ems.ems.model.LeaveStatus;
import com.ems.ems.service.LeaveService;
import com.ems.ems.service.UserService;
import org.modelmapper.ModelMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.text.ParseException;
import java.util.Collections;
import java.util.List;

@RestController
@CrossOrigin(value = "*")
@RequestMapping(value = "/leave")
public class LeaveController {
    private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private LeaveService leaveService;

    @Autowired
    private UserService userService;

    @Autowired
    private ModelMapper modelMapper;


    @PreAuthorize("hasRole('ROLE_HR')")
    @PostMapping("")
    public ResponseEntity<?> addLeave(@RequestBody LeaveDTO dto) throws ParseException {
        System.out.println(dto);
        leaveService.addLeave(dto);
        logger.info("Employee added to the database");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee added successfully")));
    }


    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    @PostMapping("/employee")
    public ResponseEntity<?> addLeaveByEmployee(HttpServletRequest req,@RequestBody LeaveDTO dto) throws ParseException {
        UserResponseDTO user=modelMapper.map(userService.whoami(req), UserResponseDTO.class);
        dto.setEmployee_id(user.getId());
        dto.setStatus(LeaveStatus.PENDING);
        System.out.println(dto);
        dto.setEmployee_email(user.getEmail());
        leaveService.addLeave(dto);
        logger.info("Employee added to the database");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee added successfully")));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR')")
    @GetMapping("")
    public ResponseEntity<?> getAllLeaves(){
        List<LeaveDTO> leaves=leaveService.getAllLeaves();
        logger.info("All employee information retrieved");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(leaves)));

    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_HR') or hasRole('ROLE_EMPLOYEE')")
    @GetMapping("/employee/all")
    public ResponseEntity<?> getAllLeavesOfAnEmployee(HttpServletRequest req){
        UserResponseDTO user=modelMapper.map(userService.whoami(req), UserResponseDTO.class);
        List<LeaveDTO> leaves=leaveService.getAllLeavesOfAnEmployee(user.getEmail());
        logger.info("All employee information retrieved");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(leaves)));

    }

    @PreAuthorize("hasRole('ROLE_HR') or hasRole('ROLE_EMPLOYEE')")
    @DeleteMapping(value="/{id}")
    public ResponseEntity<?> deleteLeave(@PathVariable int id){
        logger.info("User with id="+id+" to be deleted");
        leaveService.deleteLeave(id);
        logger.info("Leave deleted successfully");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList("Employee Deleted Successfully")));
    }


    @PreAuthorize("hasRole('ROLE_HR') or hasRole(ROLE_EMPLOYEE)")
    @PutMapping(value="/{id}")
    public ResponseEntity<?> editLeave(@PathVariable("id") int id,@RequestBody LeaveDTO leaveDTO){
        leaveDTO=leaveService.editLeave(id,leaveDTO);
        logger.info("Leave edited successfully");
        return ResponseEntity.ok(new GenericResponse(200, "SUCCESS", Collections.singletonList(leaveDTO)));
    }
}
