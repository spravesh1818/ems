package com.ems.ems.service;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.model.Employee;
import com.ems.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    public EmployeeDto save(EmployeeDto employeeDto){
        Employee entity=toEntity(employeeDto);
        entity=employeeRepository.save(entity);
        return toDto(entity);
    }

    public List<EmployeeDto> getAllEmployees(){
        List<Employee> employees=employeeRepository.findAll();
        return toDtoList(employees);
    }

    public void deleteEmployee(int id) throws NoSuchElementException {
        employeeRepository.delete(employeeRepository.findById(id).get());
    }

    public EmployeeDto editEmployeeData(int id,EmployeeDto employeeDto) throws NoSuchElementException{
        Employee employee=employeeRepository.findById(id).get();
        employee.setEmail(employeeDto.getEmail());
        employee.setFirstName(employeeDto.getFirstName());
        employee.setLastName(employeeDto.getLastName());
        employee=employeeRepository.save(employee);
        return toDto(employee);

    }

    private Employee toEntity(EmployeeDto dto){
        Employee entity=new Employee();
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        return entity;
    }

    private List<EmployeeDto> toDtoList(List<Employee> employees){
        List<EmployeeDto> dtoList=new ArrayList<>();
        for(Employee E:employees){
            EmployeeDto dto=toDto(E);
            dtoList.add(dto);
        }
        return dtoList;
    }

    private EmployeeDto toDto(Employee employee){
        EmployeeDto dto=new EmployeeDto();
        dto.setEmail(employee.getEmail());
        dto.setFirstName(employee.getFirstName());
        dto.setId(employee.getId());
        dto.setLastName(employee.getLastName());
        return dto;
    }
}
