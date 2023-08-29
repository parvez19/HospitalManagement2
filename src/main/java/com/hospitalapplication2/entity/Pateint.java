package com.hospitalapplication2.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class Pateint {

    @Id
    @GeneratedValue
    private int id;
    private String patientName;
    private String address;
    private int age;
    private String sex;
    private String illness;

}
