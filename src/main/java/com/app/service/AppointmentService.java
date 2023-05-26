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
    public ResponseEntity createAppointment(Appointment appointment){
        int result = appointmentDao.saveAppointment(appointment);
        return result <=0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @Transactional
    public ResponseEntity updateAppointment(Appointment appointment){
        int result = appointmentDao.updateAppointment(appointment);
        return result <=0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointment(int id){
        int result = appointmentDao.deleteAppointmentById(id);
        return result <=0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointmentByClientId(int id){
        int result = appointmentDao.deleteAppointmentByClientId(id);
        return result <=0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :ResponseEntity.status(HttpStatus.OK).build();
    }

    @Transactional
    public ResponseEntity deleteAppointmentByDoctorId(int id){
        int result = appointmentDao.deleteAppointmentByDoctorId(id);
        return result <=0 ? ResponseEntity.status(HttpStatus.BAD_REQUEST).build() :ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity getAppointmentsByClientId(int id){
        List<Appointment> appointmentsByClientId = appointmentDao.getAppointmentsByClientId(id);
        return appointmentsByClientId.isEmpty()?ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.FOUND).body(appointmentsByClientId);
    }

    public ResponseEntity getAppointmentsByDoctorId(int id){
        List<Appointment> appointmentsByClientId = appointmentDao.getAppointmentsByDoctorId(id);
        return appointmentsByClientId.isEmpty()?ResponseEntity.status(HttpStatus.NO_CONTENT).build() : ResponseEntity.status(HttpStatus.FOUND).body(appointmentsByClientId);
    }

}
