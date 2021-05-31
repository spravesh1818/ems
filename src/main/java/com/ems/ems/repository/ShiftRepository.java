package com.ems.ems.repository;

import com.ems.ems.model.Shift;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShiftRepository extends JpaRepository<Shift,Integer> {
}
