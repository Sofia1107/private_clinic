package com.app.service;

import com.app.entity.Appointment;
import com.app.repository.dao.AppointmentDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AppointmentService {
    @Autowired
    AppointmentDao appointmentDao;

    @Transactional
    public ResponseEntity createAppointment(Appointment appointment) {
        int result = appointmentDao.saveAppointment(appointment);
        return result <= 0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() : ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity updateAppointment(Appointment appointment) {
        int result = appointmentDao.updateAppointment(appointment);
        return result <= 0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointment(int id) {
        int result = appointmentDao.deleteAppointmentById(id);
        return result <= 0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointmentByClientId(int id) {
        int result = appointmentDao.deleteAppointmentByClientId(id);
        return result <= 0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointmentByDoctorId(int id) {
        int result = appointmentDao.deleteAppointmentByDoctorId(id);
        return result <= 0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    public List<Appointment> getAppointmentsByClientId(int id) {
        return appointmentDao.getAppointmentsByClientId(id);
    }

    public List<Appointment> getAppointments() {
        return appointmentDao.getAppointments();
    }

}
