package com.hospitalapplication2.service;

import com.hospitalapplication2.entity.Appointment;
import com.hospitalapplication2.entity.NewPateintProfile;
import com.hospitalapplication2.entity.Pateint;
import com.hospitalapplication2.entity.PateintProfile;
import com.hospitalapplication2.exceptions.RecordNotAvailableException;
import com.hospitalapplication2.repository.AppointmentRepo2;
import com.hospitalapplication2.repository.PateintRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Slf4j
public class AppointmentService2 {
    @Autowired
    AppointmentRepo2 appointmentRepo2;
    @Autowired
    PateintRepo pateintRepo;

    public ResponseEntity<String> getAppointment(Appointment appointment) throws RecordNotAvailableException {

        String newResponse;
        Appointment response = appointmentRepo2.save(appointment);
        if (Objects.nonNull(response)) {
            newResponse = String.valueOf(response.getAppointmentId());
        } else {
            throw new RecordNotAvailableException("appointment not available");
        }
        ResponseEntity<String> responseEntity = new ResponseEntity<>(newResponse, HttpStatus.CREATED);
        return responseEntity;
    }


//    public Appointment getByIdAppointmentInfo(int appointmentId) {
//
//        Optional<Appointment> appointmentopt = appointmentRepo2.findById(appointmentId);
//
//        return appointmentopt.get();
//    }


    public ResponseEntity<Appointment> getByIdAppointmentInfo(int appointmentId) throws RecordNotAvailableException {

        Appointment appointmentopt = appointmentRepo2.findById(appointmentId).get();
        if (Objects.isNull(appointmentopt)) {
            throw new RecordNotAvailableException("Given id couldn't fetch the record");
        }

        ResponseEntity<Appointment> responseEntity = new ResponseEntity<>(appointmentopt, HttpStatus.OK);

        return responseEntity;

    }

    public ResponseEntity<PateintProfile> getByPateintProfileInfo(int appointmentId) throws RecordNotAvailableException {

        Pateint pateint = null;
        Appointment appointment = null;

        Optional<Appointment> appResponse = appointmentRepo2.findById(appointmentId);

        if (appResponse.isPresent()) {
            appointment = appResponse.get();
        } else {
            throw new RecordNotAvailableException("given id cound not fetch record");
        }

        int patientId = appResponse.get().getPatientId();
        Optional<Pateint> pateintResp = pateintRepo.findById(patientId);

        if (pateintResp.isPresent()) {
            pateint = pateintResp.get();
        } else {
            throw new RecordNotAvailableException("could not  fetch pateint record-Id not found ");

        }

        PateintProfile pateintProfile = new PateintProfile();
        pateintProfile.setAppointmentId(appointment.getAppointmentId());
        pateintProfile.setPatientId(appointment.getPatientId());
        pateintProfile.setPateintName(pateint.getPatientName());
        pateintProfile.setDayOfAppointment(appointment.getDayOfAppointments());
        pateintProfile.setDoctorName(appointment.getDrName());
        pateintProfile.setPateintAge(pateint.getAge());
        pateintProfile.setPateintIllness(appointment.getIllness());
        pateintProfile.setAddress(pateint.getAddress());

        ResponseEntity<PateintProfile> responseEntity = new ResponseEntity<>(pateintProfile, HttpStatus.OK);
        return responseEntity;

    }

    public ResponseEntity<NewPateintProfile> getByNewPateintProfile(int id) throws RecordNotAvailableException {

        Pateint pateint = null;
        Appointment appointment = null;
        Optional<Pateint> newPateintResp = pateintRepo.findById(id);
        if (newPateintResp.isPresent()) {
            pateint = newPateintResp.get();
        } else {
            throw new RecordNotAvailableException("could not  fetch pateint record-Id not found");
        }

        int patientId = newPateintResp.get().getId();
        List<Appointment> newAppointmentResp = appointmentRepo2.findByPatientId(patientId);
        List<Appointment> newAppointmentRespNew = newAppointmentResp.stream()
                .sorted((o1, o2) -> o2.getAppointmentId()- o1.getAppointmentId()).collect(Collectors.toList());
        NewPateintProfile newPateintProfile = new NewPateintProfile();
        newPateintProfile.setPateintId(pateint.getId());
        newPateintProfile.setAppointments(newAppointmentRespNew);
        newPateintProfile.setPateintAge(pateint.getAge());
        newPateintProfile.setPateintName(pateint.getPatientName());
        newPateintProfile.setAddress(pateint.getAddress());

        return new ResponseEntity<>(newPateintProfile,HttpStatus.OK);

    }
}
