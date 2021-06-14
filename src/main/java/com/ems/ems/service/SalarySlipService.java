package com.ems.ems.service;

import com.ems.ems.dto.SalaryDTO;
import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import com.ems.ems.model.User;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.repository.SalarySlipRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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
        return toDto(salarySlip);
    }

    public void deleteSalarySlip(int id){
        Optional<SalarySlip> salarySlip=salarySlipRepository.findById(id);
        if(salarySlip.isPresent()){
            salarySlipRepository.delete(salarySlip.get());
        }
    }


    public List<SalaryDTO> getAllSalarySlip(){
        List<SalarySlip> salarySlips=salarySlipRepository.findAll();
        return toDtoList(salarySlips);
    }

    public SalaryDTO editSalarySlip(int id,SalaryDTO salaryDTO) throws ParseException {
        SalarySlip salarySlip=salarySlipRepository.findById(id).get();
        Employee employee=employeeRepository.findById(salaryDTO.getEmployee_id()).get();
        salarySlip.setGrandTotal(salaryDTO.getGrandTotal());
        salarySlip.setEmployee(employee);
        salarySlip.setDeductions(salaryDTO.getDeductions());
        salarySlip.setGrossAmount(salaryDTO.getGrossAmount());
        salarySlip.setNetAmount(salaryDTO.getNetAmount());
        salarySlip.setDate(new SimpleDateFormat("dd/MM/yyyy").parse(salaryDTO.getDate()));
        salarySlip=salarySlipRepository.save(salarySlip);
        return toDto(salarySlip);
    }


    public List<SalaryDTO> getSalarySlipByEmployeeId(int id){
        Optional<Employee> employee=employeeRepository.findById(id);
        List<SalarySlip> salarySlips=salarySlipRepository.findSalarySlipByEmployee(employee.get());
        return toDtoList(salarySlips);
    }

    private SalaryDTO toDto(SalarySlip salarySlip){
        return new SalaryDTO(salarySlip.getId(),salarySlip.getGrossAmount(),salarySlip.getNetAmount(),salarySlip.getDeductions(),salarySlip.getGrandTotal(),salarySlip.getDate().toString(),salarySlip.getEmployee().getId(),salarySlip.getEmployee().getFirstName()+" "+salarySlip.getEmployee().getLastName(),salarySlip.getEmployee().getEmail());
    }

    private List<SalaryDTO> toDtoList(List<SalarySlip> salarySlips){
        List<SalaryDTO> salaryDTOS=new ArrayList<>();
        for (SalarySlip salarySlip:salarySlips) {
            salaryDTOS.add(toDto(salarySlip));
        }
        return salaryDTOS;
    }



}
