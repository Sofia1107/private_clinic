package com.app.controller;

import com.app.consts.AppConstants;
import com.app.entity.Appointment;
import com.app.entity.Doctor;
import com.app.service.AppointmentService;
import com.app.service.DoctorsService;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;
import java.text.ParseException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
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
    private static final String APPOINTMENTS_VIEW = "Appointments";
    private static final String REDIRECT_APPOINTMENTS = "redirect:/client/appointments";
    private static final String REDIRECT_LOGIN = "redirect:/login";

    @GetMapping()
    public String getClientView() {
        return CLIENT_VIEW;
    }

    @PostMapping("/appointment/create")
    public String createAppointment(HttpServletRequest request, Model model) throws ParseException {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<Doctor> doctors = (List<Doctor>) doctorsService.getAllDoctors();
        String doctorId = request.getParameter("doctorId");
        if (doctorId != null && !doctorId.isEmpty()) {
            Appointment appointment = Appointment.builder()
                    .setClientId(userId)
                    .setDoctorId(Integer.valueOf(doctorId))
                    .setDateTime(formatDate(request.getParameter("dateTime")))
                    .setSummary(request.getParameter("summary"))
                    .build();
            ResponseEntity responseEntity = appointmentService.createAppointment(appointment);
            if (responseEntity.getStatusCode().equals(HttpStatus.CREATED)) {
                model.addAttribute("success", AppConstants.SUCCESS_APPOINTMENT_CREATION);
            } else if (responseEntity.getStatusCode().equals(HttpStatus.BAD_REQUEST)) {
                model.addAttribute("error", AppConstants.TECHNICAL_ERROR);
            }
        }
        model.addAttribute("doctors", doctors);


        return CREATE_APPOINTMENT_VIEW;
    }

    @GetMapping("/appointments")
    public String getAppointments(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Integer userId = (Integer) request.getSession().getAttribute("userId");
        List<Appointment> appointments = appointmentService.getAppointmentsByClientId(userId);
        model.addAttribute("appointments", appointments);
        return APPOINTMENTS_VIEW;
    }

    @PostMapping("/appointment/delete")
    public String deleteAppointment(HttpServletRequest request,
                                    @RequestParam(value = "id") Integer id) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        appointmentService.deleteAppointment(id);
        return REDIRECT_APPOINTMENTS;
    }

    private Timestamp formatDate(String date) throws ParseException {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Timestamp.valueOf(localDateTime);
    }

    private boolean denyAccess(HttpServletRequest request) {
        String userRole = (String) request.getSession().getAttribute("userRole");
        Object userId = request.getSession().getAttribute("userId");
        return userId == null || userRole == null || !StringUtils.equalsIgnoreCase(userRole, AppConstants.USER_ROLE_CLIENT);
    }
}
