package com.hospitalapplication2.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data
@Entity
public class Appointment {
      @Id
      @GeneratedValue
      private int appointmentId;
      private int patientId;
      private String drName;
      private String dayOfAppointments;
      private String illness;
}
