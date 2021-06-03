package com.ems.ems.dto;

import java.util.Date;

public class AttendenceDTO {
    private Integer id;
    private String date;
    private String timeArrived;
    private String timeLeft;
    private double totalHours;
    private boolean absent;
    private int employee_id;

    public AttendenceDTO(Integer id, String date, String timeArrived, String timeLeft, double totalHours, boolean absent, int employee_id) {
        this.id = id;
        this.date = date;
        this.timeArrived = timeArrived;
        this.timeLeft = timeLeft;
        this.totalHours = totalHours;
        this.absent = absent;
        this.employee_id = employee_id;
    }

    public AttendenceDTO() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTimeArrived() {
        return timeArrived;
    }

    public void setTimeArrived(String timeArrived) {
        this.timeArrived = timeArrived;
    }

    public String getTimeLeft() {
        return timeLeft;
    }

    public void setTimeLeft(String timeLeft) {
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

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }
}
