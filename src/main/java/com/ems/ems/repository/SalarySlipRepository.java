package com.ems.ems.repository;

import com.ems.ems.model.Employee;
import com.ems.ems.model.SalarySlip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SalarySlipRepository extends JpaRepository<SalarySlip,Integer> {
    public List<SalarySlip> findSalarySlipByEmployee(Employee employee);

}
