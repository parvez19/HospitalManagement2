package com.hospitalapplication2.entity;

import jakarta.persistence.metamodel.ListAttribute;
import lombok.Data;

import java.util.List;

@Data
public class PateintProfile {


    private int appointmentId;
    private int patientId;
    private String pateintName;
    private String doctorName;
    private String dayOfAppointment;
    private int pateintAge;
    private String pateintIllness;
    private String address;

}
