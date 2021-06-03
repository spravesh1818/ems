package com.ems.ems.repository;

import com.ems.ems.model.Attendence;
import com.ems.ems.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {

    public List<Attendence> findAttendenceByEmployee(Employee employee);
}
