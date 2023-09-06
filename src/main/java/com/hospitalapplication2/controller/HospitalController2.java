package com.hospitalapplication2.controller;


import com.hospitalapplication2.entity.Appointment;
import com.hospitalapplication2.entity.NewPateintProfile;
import com.hospitalapplication2.entity.PateintProfile;
import com.hospitalapplication2.exceptions.RecordNotAvailableException;
import com.hospitalapplication2.service.AppointmentService2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
public class HospitalController2 {

    @Autowired
    AppointmentService2 appointmentService2;

    @PostMapping("add/appointment")
    public ResponseEntity<String> getAppointment (@RequestBody Appointment appointment) throws RecordNotAvailableException {

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
   public ResponseEntity<Appointment> getByIdAppointment (@PathVariable int id)  throws RecordNotAvailableException{

        return appointmentService2.getByIdAppointmentInfo(id);
   }
   @GetMapping("getpateint/Profile")
    public ResponseEntity<PateintProfile> getPateintProfile (@RequestParam int appointmentId) throws RecordNotAvailableException {

        return appointmentService2.getByPateintProfileInfo(appointmentId);
   }

   @GetMapping("getpateint/newProfile")
    public   ResponseEntity<NewPateintProfile> getNewPateintProfile (@RequestParam int PateintId) throws RecordNotAvailableException {

        return appointmentService2.getByNewPateintProfile(PateintId);
   }

}
