package com.ems.ems.service;

import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.repository.SalarySlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class SalarySlipService {

    @Autowired
    private SalarySlipRepository salarySlipRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



    public SalaryDTO addSalarySlip(SalarySlip salarySlip){
        salarySlipRepository.save(salarySlip);
        return new SalaryDTO(salarySlip.getId(),salarySlip.getGrossAmount(),salarySlip.getNetAmount(),salarySlip.getDeductions(),salarySlip.getGrandTotal(),salarySlip.getDate().toString(),salarySlip.getEmployee().getId());
    }

    public void deleteSalarySlip(int id){
        Optional<SalarySlip> salarySlip=salarySlipRepository.findById(id);
        salarySlip.ifPresent(slip -> {
            salarySlipRepository.delete(slip);
        });
    }


    public List<SalaryDTO> getAllSalarySlip(){
        List<SalarySlip> salarySlips=salarySlipRepository.findAll();
        return toDtoList(salarySlips);
    }


    public List<SalaryDTO> getSalarySlipByEmployeeId(int id){
        Optional<Employee> employee=employeeRepository.findById(id);
        List<SalarySlip> salarySlips=salarySlipRepository.findSalarySlipByEmployee(employee.get());
        return toDtoList(salarySlips);
    }

    private SalaryDTO toDto(SalarySlip salarySlip){
        return new SalaryDTO(salarySlip.getId(),salarySlip.getGrossAmount(),salarySlip.getNetAmount(),salarySlip.getDeductions(),salarySlip.getGrandTotal(),salarySlip.getDate().toString(),salarySlip.getEmployee().getId());
    }

    private List<SalaryDTO> toDtoList(List<SalarySlip> salarySlips){
        List<SalaryDTO> salaryDTOS=new ArrayList<>();
        for (SalarySlip salarySlip:salarySlips) {
            salaryDTOS.add(toDto(salarySlip));
        }
        return salaryDTOS;
    }



}
