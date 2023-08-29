package com.hospitalapplication2.controller;


import com.hospitalapplication2.entity.Appointment;
import com.hospitalapplication2.entity.Pateint;
import com.hospitalapplication2.entity.PateintProfile;
import com.hospitalapplication2.exceptions.RecordNotAvailableException;
import com.hospitalapplication2.service.AppointmentService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HospitalController2 {

    @Autowired
    AppointmentService2 appointmentService2;

    @PostMapping("add/appointment")
    public String getAppointment (@RequestBody Appointment appointment) {

        return appointmentService2.getAppointment(appointment);
    }
//    @PutMapping("update/appointment")
//
//    public Appointment updateAppointment (@RequestParam int appointmentId,@RequestBody Appointment appointment) {
//
//        return appointmentService2.updateAppointmentInfo(appointment);
//
//    }

     @GetMapping("getappointment/{id}")
   public Appointment getByIdAppointment (@PathVariable int id) {

        return appointmentService2.getByIdAppointmentInfo(id).get();
   }
   @GetMapping("getpateint/Profile")
    public PateintProfile getPateintProfile (@RequestParam int appointmentId) throws RecordNotAvailableException {

        return appointmentService2.getByPateintProfileInfo(appointmentId);
   }

}
