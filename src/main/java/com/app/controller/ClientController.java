package com.app.controller;

import com.app.consts.AppConstants;
import com.app.entity.Appointment;
import com.app.entity.Doctor;
import com.app.repository.DatabaseAccess;
import com.app.service.AppointmentService;
import com.app.service.DoctorsService;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;
import java.sql.Timestamp;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/client")
public class ClientController {
    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorsService doctorsService;

    private static final String CLIENT_VIEW = "ClientView";
    private static final String CREATE_APPOINTMENT_VIEW = "CreateAppointmentView";

    @GetMapping()
    public String getClientView() {
        return CLIENT_VIEW;
    }

    @PostMapping("/appointment/create")
    public String createAppointment(HttpServletRequest httpServletRequest, Model model){
        Integer userId = (Integer) httpServletRequest.getSession().getAttribute("userId");
        List<Doctor> doctors = (List<Doctor>) doctorsService.getAllDoctors();
        String doctorId = httpServletRequest.getParameter("doctorId");
        if (doctorId!=null && !doctorId.isEmpty()){
            Appointment appointment = Appointment.builder()
                    .setClientId(userId)
                    .setDoctorId(Integer.valueOf(doctorId))
                    .setDateTime(Timestamp.valueOf(httpServletRequest.getParameter("dateTime")))
                    .setSummary(httpServletRequest.getParameter("summary"))
                    .build();
            ResponseEntity responseEntity = appointmentService.createAppointment(appointment);
            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)){
                model.addAttribute("success", AppConstants.SUCCESS_APPOINTMENT_CREATION);
            } else if (responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                model.addAttribute("error", AppConstants.TECHNICAL_ERROR);
            }
        }
        model.addAttribute("doctors", doctors);


        return CREATE_APPOINTMENT_VIEW;
    }
}
