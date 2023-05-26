package com.app.service;

import com.app.entity.Doctor;
import com.app.exceptions.model.Error;
import com.app.exceptions.model.ErrorMessage;
import com.app.repository.dao.DoctorDao;
import com.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class DoctorsService {
    @Autowired
    DoctorDao doctorDao;

    @Transactional
    public ResponseEntity saveDoctor(Doctor doctor) {
        boolean isEmailValid = ValidationUtil.validateEmail(doctor.getEmail());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(doctor.getPhoneNumber());
        if (isMobileNumberValid && isEmailValid) {
            if (isDoctorExistsByEmail(doctor.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_EMAIL.getError())
                                .message(Error.DUPLICATE_EMAIL.getMessage())
                                .build());
            } else if (isDoctorExistsByPhoneNumber(doctor.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_PHONE_NUMBER.getError())
                                .message(Error.DUPLICATE_PHONE_NUMBER.getMessage())
                                .build());
            }
            int result = doctorDao.saveDoctor(doctor);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            if (!isEmailValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.EMAIL_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.PHONE_NUMBER_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            }
        }
    }

    public ResponseEntity updateDoctor(Doctor doctor) {
        boolean isEmailValid = ValidationUtil.validateEmail(doctor.getEmail());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(doctor.getPhoneNumber());
        if (isMobileNumberValid && isEmailValid) {
            if (isDoctorExistsByEmail(doctor.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_EMAIL.getError())
                                .message(Error.DUPLICATE_EMAIL.getMessage())
                                .build());
            } else if (isDoctorExistsByPhoneNumber(doctor.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_PHONE_NUMBER.getError())
                                .message(Error.DUPLICATE_PHONE_NUMBER.getMessage())
                                .build());
            }
            int result = doctorDao.updateDoctor(doctor);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.OK).build();
        } else {
            if (!isEmailValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.EMAIL_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.PHONE_NUMBER_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            }
        }
    }

    public List<Doctor> getAllDoctors() {
        return doctorDao.getDoctors();
    }

    public ResponseEntity deleteDoctorById(Doctor doctor) {
        int result = doctorDao.deleteDoctorById(doctor.getId());
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
}
