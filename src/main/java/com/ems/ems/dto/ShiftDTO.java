package com.ems.ems.dto;

import com.ems.ems.model.ShiftTime;

import java.util.Date;

public class ShiftDTO {

    private Integer id;
    private String date;
    private String shiftTime;

    private int employee_id;

    public ShiftDTO(Integer id, String date, String shiftTime, int employee_id) {
        this.id = id;
        this.date = date;
        this.shiftTime = shiftTime;
        this.employee_id = employee_id;
    }

    public ShiftDTO() {
    }
}
