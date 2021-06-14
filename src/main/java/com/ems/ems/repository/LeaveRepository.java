package com.ems.ems.repository;

import com.ems.ems.model.Employee;
import com.ems.ems.model.Leave;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface LeaveRepository extends JpaRepository<Leave,Integer> {
    @Override
    Optional<Leave> findById(Integer integer);

    List<Leave> findLeaveByEmployee(Employee employee);
}
