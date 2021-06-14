package com.ems.ems.service;


import com.ems.ems.dto.LeaveDTO;
import com.ems.ems.model.Employee;
import com.ems.ems.model.Leave;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.repository.LeaveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class LeaveService {

    @Autowired
    private LeaveRepository leaveRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    public LeaveDTO addLeave(LeaveDTO leaveDTO) throws ParseException {
        Employee employee=employeeRepository.findEmployeeByEmail(leaveDTO.getEmployee_email()).get();
        Leave leave=new Leave();
        Date startDate=new SimpleDateFormat("dd/MM/yyyy").parse(leaveDTO.getStartDate());
        Date endDate=new SimpleDateFormat("dd/MM/yyyy").parse(leaveDTO.getEndDate());

        leave.setStartDate(startDate);
        leave.setEndDate(endDate);
        leave.setReason(leaveDTO.getReason());
        leave.setType(leaveDTO.getType());
        leave.setStatus(leaveDTO.getStatus());
        leave.setEmployee(employee);
        leaveRepository.save(leave);

        return leaveDTO;
    }

    public void deleteLeave(int id){
        Optional<Leave> leave=leaveRepository.findById(id);
        if(leave.isPresent()){
            leaveRepository.delete(leave.get());
        }

    }

    public LeaveDTO editLeave(int id,LeaveDTO dto){
        return null;
    }

    public List<LeaveDTO> getAllLeaves(){
        return null;
    }

    public List<LeaveDTO> getAllLeavesOfAnEmployee(String email){
        Employee employee=employeeRepository.findEmployeeByEmail(email).get();
        List<Leave> leaves=leaveRepository.findLeaveByEmployee(employee);
        return toDtoList(leaves);
    }

    public List<LeaveDTO> getLeavesOfAnEmployee(int employee_id){
        return null;
    }

    public List<LeaveDTO> toDtoList(List<Leave> leaves){
        List<LeaveDTO> leaveDTOS=new ArrayList<>();
        for (Leave leave:
             leaves) {
            leaveDTOS.add(toDto(leave));
        }
        return leaveDTOS;
    }

    public LeaveDTO toDto(Leave leave){
        return new LeaveDTO(leave.getId(),leave.getStartDate().toString(),leave.getEndDate().toString(),leave.getType(),leave.getStatus(),leave.getReason(),leave.getEmployee().getId(),leave.getEmployee().getEmail());
    }

}
