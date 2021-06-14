package com.ems.ems.service;

import com.ems.ems.dto.AttendenceDTO;
import com.ems.ems.model.Attendence;
import com.ems.ems.model.Employee;
import com.ems.ems.repository.AttendenceRepository;
import com.ems.ems.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


@Service
public class AttendenceService {

    @Autowired
    private AttendenceRepository attendenceRepository;

    @Autowired
    private EmployeeRepository employeeRepository;



    public AttendenceDTO addAttendence(Attendence attendence){
        try{
            attendenceRepository.save(attendence);
            return new AttendenceDTO(attendence.getId(),attendence.getDate().toString(),attendence.getTimeArrived().toString(),attendence.getTimeLeft().toString(),attendence.getTotalHours(),attendence.isAbsent(),attendence.getEmployee().getId());
        }catch (Exception ex){
            ex.printStackTrace();
            return null;
        }
    }

    public void deleteAttendence(int id){
        Optional<Attendence> attendence=attendenceRepository.findById(id);
        if(attendence.isPresent()){
            Attendence attendence1=attendence.get();
            attendenceRepository.delete(attendence1);
        }
    }


    public List<AttendenceDTO> getAllAttendences(){
        List<Attendence> attendences=attendenceRepository.findAll();
        return toDtoList(attendences);
    }


    public List<AttendenceDTO> getAttendenceByEmployeeId(int id){
        Optional<Employee> employee=employeeRepository.findById(id);
        List<Attendence> attendances=attendenceRepository.findAttendenceByEmployee(employee.get());
        return toDtoList(attendances);
    }

    private AttendenceDTO toDto(Attendence attendence){
        return new AttendenceDTO(attendence.getId(),attendence.getDate().toString(),attendence.getTimeArrived().toString(),attendence.getTimeLeft().toString(),attendence.getTotalHours(),attendence.isAbsent(),attendence.getEmployee().getId());
    }

    private List<AttendenceDTO> toDtoList(List<Attendence> attendences){
        List<AttendenceDTO> attendenceDTOS=new ArrayList<>();
        for (Attendence attendence:attendences) {
            attendenceDTOS.add(toDto(attendence));
        }
        return attendenceDTOS;
    }
}
