package com.ems.ems.repository;

import com.ems.ems.model.Attendence;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendenceRepository extends JpaRepository<Attendence,Integer> {
}
