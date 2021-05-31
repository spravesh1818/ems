package com.ems.ems.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Shift {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    private ShiftTime shiftTime;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public ShiftTime getShiftTime() {
        return shiftTime;
    }

    public void setShiftTime(ShiftTime shiftTime) {
        this.shiftTime = shiftTime;
    }

    public Integer getId() {
        return id;
    }
}
