package com.ems.ems.service;

import com.ems.ems.dto.EmployeeDto;
import com.ems.ems.model.Employee;
import com.ems.ems.model.Role;
import com.ems.ems.model.User;
import com.ems.ems.repository.EmployeeRepository;
import com.ems.ems.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class EmployeeService {

    @Autowired
    EmployeeRepository employeeRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public EmployeeDto save(EmployeeDto employeeDto){
        Employee entity=toEntity(employeeDto);
        entity=employeeRepository.save(entity);
        Role role=null;

        if(employeeDto.getRole().equals("hr")){
            role=Role.ROLE_HR;
        }else if(employeeDto.getRole().equals("finance")){
            role=Role.ROLE_FINANCE;
        }else{
            role=Role.ROLE_EMPLOYEE;
        }

        User user=new User();
        user.setEmail(entity.getEmail());
        user.setUsername(entity.getEmail());
        user.setPassword(passwordEncoder.encode("employee123"));
        List<Role> roles=new ArrayList<>();
        roles.add(role);
        user.setRoles(roles);
        user.setEmployee(entity);
        userRepository.save(user);
        return toDto(entity);
    }

    public List<EmployeeDto> getAllEmployees() throws NoSuchElementException{
        List<Employee> employees=employeeRepository.findAll();
        return toDtoList(employees);
    }

    public EmployeeDto getEmployeeId(int id){
        if(employeeRepository.findById(id).isPresent()){
            Employee employee=employeeRepository.findById(id).get();
            return toDto(employee);
        }else{
            return null;
        }

    }

    public void deleteEmployee(int id) throws NoSuchElementException {
        if(employeeRepository.findById(id).isPresent()){
            employeeRepository.delete(employeeRepository.findById(id).get());
        }

    }

    public EmployeeDto editEmployeeData(int id,EmployeeDto employeeDto) throws NoSuchElementException{
        Employee employee=employeeRepository.findById(id).get();
        User user=userService.search(employee.getEmail());
        user.setUsername(employeeDto.getEmail());
        user.setEmail(employeeDto.getEmail());
        userService.edit(user);
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
