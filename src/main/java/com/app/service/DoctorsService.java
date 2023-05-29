package com.app.service;

import com.app.consts.AppConstants;
import com.app.entity.Doctor;
import com.app.repository.dao.AppointmentDao;
import com.app.repository.dao.DoctorDao;
import com.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
public class DoctorsService {
    @Autowired
    DoctorDao doctorDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Transactional
    public ResponseEntity saveDoctor(Doctor doctor) {
        boolean isEmailValid = ValidationUtil.validateEmail(doctor.getEmail());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(doctor.getPhoneNumber());
        if (isMobileNumberValid && isEmailValid) {
            if (isDoctorExistsByEmail(doctor.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.DOCTOR_EMAIL_ALREADY_EXIST);
            } else if (isDoctorExistsByPhoneNumber(doctor.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.DOCTOR_PHONE_NUMBER_ALREADY_EXIST);
            }
            int result = doctorDao.saveDoctor(doctor);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            if (!isEmailValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.EMAIL_DOES_NOT_MATCH_PATTERN);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.PHONE_NUMBER_DOES_NOT_MATCH_PATTERN);
            }
        }
    }

    @Transactional
    public ResponseEntity updateDoctor(Doctor doctor) {
        boolean isEmailValid = ValidationUtil.validateEmail(doctor.getEmail());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(doctor.getPhoneNumber());
        if (isMobileNumberValid && isEmailValid) {
            if (!isDoctorNewEmailUnique(doctor)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.DOCTOR_EMAIL_ALREADY_EXIST);
            } else if (!isDoctorNewPhoneUnique(doctor)) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.DOCTOR_PHONE_NUMBER_ALREADY_EXIST);
            }
            int result = doctorDao.updateDoctor(doctor);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.OK).build();
        } else {
            if (!isEmailValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.EMAIL_DOES_NOT_MATCH_PATTERN);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.PHONE_NUMBER_DOES_NOT_MATCH_PATTERN);
            }
        }
    }

    public List<Doctor> getAllDoctors() {
        return doctorDao.getDoctors();
    }

    public ResponseEntity deleteDoctorById(Integer id) {
        int resultAppointment = appointmentDao.deleteAppointmentByDoctorId(id);
        int result = doctorDao.deleteDoctorById(id);
        return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    private boolean isDoctorExistsByEmail(String email) {
        Doctor doctor = doctorDao.getDoctorByEmail(email);
        return doctor != null;
    }

    private boolean isDoctorExistsByPhoneNumber(String phoneNumber) {
        Doctor doctor = doctorDao.getDoctorByPhoneNumber(phoneNumber);
        return doctor != null;
    }

    private boolean isDoctorNewEmailUnique(Doctor doctor) {
        Doctor existingDoctor = doctorDao.getDoctorByEmail(doctor.getEmail());
        return existingDoctor == null || Objects.equals(existingDoctor.getId(), doctor.getId());
    }

    private boolean isDoctorNewPhoneUnique(Doctor doctor) {
        Doctor existingDoctor = doctorDao.getDoctorByPhoneNumber(doctor.getPhoneNumber());
        return existingDoctor == null || Objects.equals(existingDoctor.getId(), doctor.getId());
    }
}
