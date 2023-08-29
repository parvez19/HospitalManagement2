package com.hospitalapplication2.service;

import com.hospitalapplication2.entity.Appointment;
import com.hospitalapplication2.entity.Pateint;
import com.hospitalapplication2.entity.PateintProfile;
import com.hospitalapplication2.exceptions.RecordNotAvailableException;
import com.hospitalapplication2.repository.AppointmentRepo2;
import com.hospitalapplication2.repository.PateintRepo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Objects;
import java.util.Optional;

@Service
@Slf4j
public class AppointmentService2 {
    @Autowired
    AppointmentRepo2 appointmentRepo2;
    @Autowired
    PateintRepo pateintRepo;

    public String getAppointment(Appointment appointment) {
        Appointment response = appointmentRepo2.save(appointment);
        if (Objects.nonNull(response)) {
            return "appointment booked with appointment Id : " + response.getAppointmentId();
        }
        return "appointment not available";
    }


//    public Appointment getByIdAppointmentInfo(int appointmentId) {
//
//        Optional<Appointment> appointmentopt = appointmentRepo2.findById(appointmentId);
//
//        return appointmentopt.get();
//    }


    public Optional<Appointment> getByIdAppointmentInfo(int appointmentId) {

        Optional<Appointment> appointmentopt = null;
        try {
            appointmentopt = appointmentRepo2.findById(appointmentId);

        } catch (Exception e) {
            log.info("exception in AppointmentService2" + e.getMessage());
        }
        return appointmentopt;

    }

    public PateintProfile getByPateintProfileInfo(int appointmentId) throws RecordNotAvailableException {

        Pateint pateint = null;
        Appointment appointment = null;

        Optional<Appointment> appResponse = appointmentRepo2.findById(appointmentId);

        if (appResponse.isPresent()) {
            appointment = appResponse.get();
        }
        else {
        throw new RecordNotAvailableException("given id cound not fetch record");
        }

        int patientId = appResponse.get().getPatientId();
        Optional<Pateint> pateintResp = pateintRepo.findById(patientId);

        if (pateintResp.isPresent()) {
            pateint = pateintResp.get();
    }
        else {

        }

        PateintProfile pateintProfile = new PateintProfile();
        pateintProfile.setAppointmentId(appointment.getAppointmentId());
        pateintProfile.setPatientId(appointment.getPatientId());
        pateintProfile.setPateintName(pateint.getPatientName());
        pateintProfile.setDayOfAppointment(appointment.getDayOfAppointments());
        pateintProfile.setDoctorName(appointment.getDrName());
        pateintProfile.setPateintAge(pateint.getAge());
        pateintProfile.setPateintIllness(pateint.getIllness());
        pateintProfile.setAddress(pateint.getAddress());

         return pateintProfile;

    }
}
