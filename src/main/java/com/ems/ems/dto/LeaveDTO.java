package com.ems.ems.dto;

import com.ems.ems.model.LeaveStatus;
import com.ems.ems.model.LeaveType;

public class LeaveDTO {
    private int id;
    private String startDate;
    private String endDate;
    private LeaveType type;
    private LeaveStatus status;
    private String reason;
    private int employee_id;
    private String employee_email;

    public LeaveDTO() {
    }

    public LeaveDTO(int id, String startDate, String endDate, LeaveType type, LeaveStatus status, String reason, int employee_id, String employee_email) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.type = type;
        this.status = status;
        this.reason = reason;
        this.employee_id = employee_id;
        this.employee_email = employee_email;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getEmployee_email() {
        return employee_email;
    }

    public void setEmployee_email(String employee_email) {
        this.employee_email = employee_email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public LeaveType getType() {
        return type;
    }

    public void setType(LeaveType type) {
        this.type = type;
    }

    public LeaveStatus getStatus() {
        return status;
    }

    public void setStatus(LeaveStatus status) {
        this.status = status;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
