package com.ems.ems.repository;

import com.ems.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Override
    Optional<Employee> findById(Integer integer);
}
