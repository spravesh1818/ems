package com.ems.ems.service;

import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.dto.ShiftDTO;
import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import com.ems.ems.model.Shift;
import com.ems.ems.model.ShiftTime;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.repository.ShiftRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class ShiftService {

    @Autowired
    private ShiftRepository shiftRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



    public ShiftDTO addShift(Shift shift){
        shiftRepository.save(shift);
        return new ShiftDTO(shift.getId(),shift.getDate().toString(),shift.getShiftTime().toString(),shift.getEmployee().getId());
    }

    public void deleteShift(int id){
        Optional<Shift> salarySlip=shiftRepository.findById(id);
        salarySlip.ifPresent(shift -> {
            shiftRepository.delete(shift);
        });
    }


    public List<ShiftDTO> getAllShifts(){
        List<Shift> salarySlips=shiftRepository.findAll();
        return toDtoList(salarySlips);
    }


    public List<ShiftDTO> getShiftByEmployeeId(int id){
        Optional<Employee> employee=employeeRepository.findById(id);
        List<Shift> shifts=shiftRepository.findShiftByEmployee(employee.get());
        return toDtoList(shifts);
    }

    private ShiftDTO toDto(Shift shift){
        return new ShiftDTO(shift.getId(),shift.getDate().toString(),shift.getShiftTime().toString(),shift.getEmployee().getId());
    }

    private List<ShiftDTO> toDtoList(List<Shift> shifts){
        List<ShiftDTO> shiftDTOS=new ArrayList<>();
        for (Shift shift:shifts) {
            shiftDTOS.add(toDto(shift));
        }
        return shiftDTOS;
    }


}
