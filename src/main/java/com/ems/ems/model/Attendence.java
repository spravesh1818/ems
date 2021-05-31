package com.ems.ems.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class Attendence {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Date date;
    private Date timeArrived;
    private Date timeLeft;
    private double totalHours;
    private boolean absent;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(Date timeArrived) {
        this.timeArrived = timeArrived;
    }

    public Date getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(Date timeLeft) {
        this.timeLeft = timeLeft;
    }

    public double getTotalHours() {
        return totalHours;
    }

    public void setTotalHours(double totalHours) {
        this.totalHours = totalHours;
    }

    public boolean isAbsent() {
        return absent;
    }

    public void setAbsent(boolean absent) {
        this.absent = absent;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    public Integer getId() {
        return id;
    }
}
