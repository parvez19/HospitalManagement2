package com.hospitalapplication2.repository;

import com.hospitalapplication2.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;


@Repository
public interface AppointmentRepo2 extends JpaRepository<Appointment,Integer> {

    public List<Appointment> findByPatientId(int id);
}
