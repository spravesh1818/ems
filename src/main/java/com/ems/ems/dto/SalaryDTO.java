package com.ems.ems.dto;

import com.ems.ems.model.SalarySlip;

import java.util.Date;

public class SalaryDTO {
    private int id;
    private double grossAmount;
    private double netAmount;
    private double deductions;
    private double grandTotal;
    private String date;
    private int employee_id;

    public SalaryDTO() {
    }

    public SalaryDTO(int id, double grossAmount, double netAmount, double deductions, double grandTotal, String date, int employee_id) {
        this.id = id;
        this.grossAmount = grossAmount;
        this.netAmount = netAmount;
        this.deductions = deductions;
        this.grandTotal = grandTotal;
        this.date = date;
        this.employee_id=employee_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
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

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }



}
