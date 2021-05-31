package com.ems.ems.model;

import javax.persistence.*;
import java.util.Set;

@Entity
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    private String firstName;
    private String lastName;

    @Column(unique = true, nullable = false)
    private String email;


    @OneToMany(mappedBy="employee",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<SalarySlip> salarySlips;

    @OneToMany(mappedBy="employee",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Attendence> attendences;

    @OneToMany(mappedBy="employee",orphanRemoval = true,cascade = CascadeType.ALL)
    private Set<Shift> shifts;

    public Set<Shift> getShifts() {
        return shifts;
    }

    public void setShifts(Set<Shift> shifts) {
        this.shifts = shifts;
    }

    public Set<Attendence> getAttendences() {
        return attendences;
    }

    public void setAttendences(Set<Attendence> attendences) {
        this.attendences = attendences;
    }

    public Set<SalarySlip> getSalarySlips() {
        return salarySlips;
    }

    public void setSalarySlips(Set<SalarySlip> salarySlips) {
        this.salarySlips = salarySlips;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
