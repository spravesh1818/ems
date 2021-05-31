package com.ems.ems.model;

import javax.persistence.*;
import java.util.Date;

@Entity
public class SalarySlip {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private double grossAmount;
    private double netAmount;
    private double deductions;
    private double grandTotal;
    private Date date;

    @ManyToOne
    @JoinColumn(name="employee_id", nullable=false)
    private Employee employee;


    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getGrossAmount() {
        return grossAmount;
    }

    public void setGrossAmount(double grossAmount) {
        this.grossAmount = grossAmount;
    }

    public double getNetAmount() {
        return netAmount;
    }

    public void setNetAmount(double netAmount) {
        this.netAmount = netAmount;
    }

    public double getDeductions() {
        return deductions;
    }

    public void setDeductions(double deductions) {
        this.deductions = deductions;
    }

    public double getGrandTotal() {
        return grandTotal;
    }

    public void setGrandTotal(double grandTotal) {
        this.grandTotal = grandTotal;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
