package com.app.service;

import com.app.consts.AppConstants;
import com.app.entity.*;
import com.app.exceptions.model.Error;
import com.app.exceptions.model.ErrorMessage;
import com.app.repository.dao.AppointmentDao;
import com.app.repository.dao.UserDao;
import com.app.util.PasswordUtil;
import com.app.util.ValidationUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserService {
    @Autowired
    private UserDao userDao;

    @Autowired
    private AppointmentDao appointmentDao;

    @Transactional
    public ResponseEntity registerClient(Person client) throws Exception {
        boolean isEmailValid = ValidationUtil.validateEmail(client.getEmail());
        boolean isPwdValid = ValidationUtil.validatePassword(client.getPassword());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(client.getPhoneNumber());
        if (isPwdValid && isEmailValid && isMobileNumberValid) {
            if (isClientExistsWithEmail(client.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.EMAIL_ALREADY_EXIST);
            } else if (isClientExistsWithPhone(client.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.PHONE_NUMBER_ALREADY_EXIST);
            }
            encryptPassword(client);
            int result = userDao.saveClient(client);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.CREATED).build();
        } else {
            if (!isEmailValid)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.EMAIL_DOES_NOT_MATCH_PATTERN);
            else if (!isPwdValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.PASSWORD_NOT_MATCH_PATTERN);
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(AppConstants.PHONE_NUMBER_DOES_NOT_MATCH_PATTERN);
            }
        }
    }

    @Transactional
    public ResponseEntity updateClient(Person client) throws Exception {
        boolean isEmailValid = ValidationUtil.validateEmail(client.getEmail());
        boolean isPwdValid = ValidationUtil.validatePassword(client.getPassword());
        boolean isMobileNumberValid = ValidationUtil.validatePhoneNumber(client.getPhoneNumber());
        if (isPwdValid && isEmailValid && isMobileNumberValid) {
            if (isClientExistsWithEmail(client.getEmail())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_EMAIL.getError())
                                .message(Error.DUPLICATE_EMAIL.getMessage())
                                .build());
            } else if (isClientExistsWithPhone(client.getPhoneNumber())) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.DUPLICATE_PHONE_NUMBER.getError())
                                .message(Error.DUPLICATE_PHONE_NUMBER.getMessage())
                                .build());
            }
            encryptPassword(client);
            int result = userDao.updateClient(client);
            return result <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.OK).build();
        } else {
            if (!isEmailValid)
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.EMAIL_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            else if (!isPwdValid) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.PASSWORD_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            } else {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST)
                        .body(ErrorMessage.builder()
                                .error(Error.PHONE_NUMBER_NOT_MATCH_REQUIREMENTS.getError())
                                .build());
            }
        }
    }


    @Transactional
    public ResponseEntity deleteClientById(int id) {
        int resultAppointment = appointmentDao.deleteAppointmentByClientId(id);
        int resultUser = userDao.deleteClientById(id);
        return resultUser <= 0 || resultAppointment <= 0 ? ResponseEntity.status(HttpStatus.CONFLICT).build() : ResponseEntity.status(HttpStatus.OK).build();
    }

    public ResponseEntity getClientByEmail(String email) {
        Person clientByEmail = userDao.getClientByEmail(email);
        return ResponseEntity.status(HttpStatus.OK)
                .body(clientByEmail);
    }

    public ResponseEntity authorizeUser(Person person) throws Exception {
        Person userByEmail = findUserByEmail(person.getEmail());
        if (userByEmail != null) {
            return PasswordUtil.passwordsMatch(person.getPassword(), userByEmail.getPassword()) ?
                    ResponseEntity.status(HttpStatus.ACCEPTED).body(userByEmail) :
                    ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        } else
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    private Person findUserByEmail(String email) {
        return userDao.getUserByEmail(email);
    }

    private boolean isClientExistsWithEmail(String email) {
        Person client = userDao.getClientByEmail(email);
        return client != null;
    }

    private boolean isClientExistsWithPhone(String phone) {
        Person client = userDao.getClientByPhoneNumber(phone);
        return client != null;
    }

    private void encryptPassword(Person client) throws Exception {
        String encryptedPwd = PasswordUtil.encrypt(client.getPassword());
        client.setPassword(encryptedPwd);
    }
}
