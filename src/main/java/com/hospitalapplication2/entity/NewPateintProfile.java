package com.hospitalapplication2.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import java.util.List;

@Data
public class NewPateintProfile {

    private int pateintId;
    private String pateintName;
    @JsonIgnore
    private String doctorName;
    @JsonIgnore
    private String dayOfAppointment;
    private int pateintAge;
    @JsonIgnore
    private String pateintIllness;
    private String address;
    private List<Appointment> appointments;
}
