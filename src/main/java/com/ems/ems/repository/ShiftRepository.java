package com.ems.ems.repository;

import com.ems.ems.model.Employee;
import com.ems.ems.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ShiftRepository extends JpaRepository<Shift,Integer> {
    public List<Shift> findShiftByEmployee(Employee employee);

}
