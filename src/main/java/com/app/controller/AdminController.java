package com.app.controller;

import com.app.consts.AppConstants;
import com.app.entity.Appointment;
import com.app.entity.Doctor;
import com.app.entity.Person;
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
import java.sql.Date;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;
    @Autowired
    private AppointmentService appointmentService;
    @Autowired
    private DoctorsService doctorsService;

    private static final String ADMIN_VIEW = "AdminView";
    private static final String ADMIN_DOCTORS_VIEW = "AdminDoctorsView";
    private static final String ADMIN_CLIENTS_VIEW = "AdminClientsView";
    private static final String ADMIN_DOCTORS_CREATE_VIEW = "AdminDoctorsCreateView";
    private static final String ADMIN_APPOINTMENTS_VIEW = "AdminAppointmentsView";
    private static final String REDIRECT_APPOINTMENTS = "redirect:/admin/appointments";
    private static final String REDIRECT_DOCTORS = "redirect:/admin/doctors";
    private static final String REDIRECT_CLIENTS = "redirect:/admin/clients";
    private static final String REDIRECT_LOGIN = "redirect:/login";


    @GetMapping()
    public String getAdminView(HttpServletRequest request) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        return ADMIN_VIEW;
    }

    @GetMapping("/doctors")
    public String getDoctorsView(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_DOCTORS_VIEW;
    }

    @GetMapping("/clients")
    public String getClientsView(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        List<Person> clients = userService.getClients();
        model.addAttribute("clients", clients);
        return ADMIN_CLIENTS_VIEW;
    }

    @GetMapping("/appointments")
    public String getAppointments(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        List<Appointment> appointments = appointmentService.getAppointments();
        model.addAttribute("appointments", appointments);
        return ADMIN_APPOINTMENTS_VIEW;
    }

    @GetMapping("/doctors/create")
    public String getCreateDoctorsForm(HttpServletRequest request) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        return ADMIN_DOCTORS_CREATE_VIEW;
    }

    @PostMapping("/appointments/update")
    public String updateAppointment(HttpServletRequest request) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Appointment appointment = Appointment.builder()
                .setSummary(request.getParameter("summary"))
                .setId(Integer.valueOf(request.getParameter("appointmentId")))
                .setDateTime(formatDate(request.getParameter("dateTime")))
                .build();
        appointmentService.updateAppointment(appointment);
        return REDIRECT_APPOINTMENTS;
    }

    @PostMapping("/appointments/delete")
    public String deleteAppointment(HttpServletRequest request,
                                    @RequestParam(value = "id") Integer id) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        appointmentService.deleteAppointment(id);
        return REDIRECT_APPOINTMENTS;
    }

    @PostMapping("/clients/update")
    public String updateClient(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Integer clientId = Integer.valueOf(request.getParameter("clientId"));
        Person person = Person.builder()
                .setId(clientId)
                .setFirstName(request.getParameter("firstName" + clientId))
                .setLastName(request.getParameter("lastName" + clientId))
                .setEmail(request.getParameter("email" + clientId))
                .setPhoneNumber(request.getParameter("phoneNumber" + clientId))
                .setDateOfBirth(Date.valueOf(request.getParameter("birthDate" + clientId)))
                .setBloodGroup(Integer.valueOf(request.getParameter("bloodGroup" + clientId)))
                .setRH(request.getParameter("rh" + clientId))
                .setAllergy(request.getParameter("allergy" + clientId))
                .setPassword(request.getParameter("password" + clientId))
                .setUserRole(AppConstants.USER_ROLE_CLIENT)
                .build();
        ResponseEntity responseEntity = userService.updateClient(person);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("success", AppConstants.SUCCESS_UPDATING);
        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            model.addAttribute("error", responseEntity.getBody());
        }
        List<Person> clients = userService.getClients();
        model.addAttribute("clients", clients);
        return ADMIN_CLIENTS_VIEW;
    }

    @PostMapping("/clients/delete")
    public String deleteClient(HttpServletRequest request,
                               @RequestParam(value = "id") Integer id) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        userService.deleteClientById(id);
        return REDIRECT_CLIENTS;
    }

    @PostMapping("/doctors/delete")
    public String deleteDoctor(HttpServletRequest request,
                               @RequestParam(value = "id") Integer id) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        doctorsService.deleteDoctorById(id);
        return REDIRECT_DOCTORS;
    }

    @PostMapping("/doctors/update")
    public String updateDoctor(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Integer doctorId = Integer.valueOf(request.getParameter("doctorId"));
        Doctor doctor = Doctor.builder()
                .setId(doctorId)
                .setFirstName(request.getParameter("firstName" + doctorId))
                .setLastName(request.getParameter("lastName" + doctorId))
                .setEmail(request.getParameter("email" + doctorId))
                .setPhoneNumber(request.getParameter("phoneNumber" + doctorId))
                .setSpecialization(request.getParameter("specialization" + doctorId))
                .build();
        ResponseEntity responseEntity = doctorsService.updateDoctor(doctor);
        if (responseEntity.getStatusCode() == HttpStatus.OK) {
            model.addAttribute("success", AppConstants.DOCTOR_SUCCESS_UPDATING);
        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            model.addAttribute("error", responseEntity.getBody());
        }
        List<Doctor> doctors = doctorsService.getAllDoctors();
        model.addAttribute("doctors", doctors);
        return ADMIN_DOCTORS_VIEW;
    }

    @PostMapping("/doctors/create")
    public String createDoctor(HttpServletRequest request, Model model) {
        if (denyAccess(request)) {
            return REDIRECT_LOGIN;
        }
        Doctor doctor = Doctor.builder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .setPhoneNumber(request.getParameter("phoneNumber"))
                .setSpecialization(request.getParameter("specialization"))
                .build();

        ResponseEntity responseEntity;
        responseEntity = doctorsService.saveDoctor(doctor);
        if (responseEntity.getStatusCode() == HttpStatus.CREATED) {
            model.addAttribute("success", AppConstants.SUCCESS_REGISTRATION);
            populateModel(model, doctor);

        } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST) {
            model.addAttribute("error", responseEntity.getBody());
            populateModel(model, doctor);
        }
        return ADMIN_DOCTORS_CREATE_VIEW;
    }


    private Timestamp formatDate(String date) {
        LocalDateTime localDateTime = LocalDateTime.parse(date, DateTimeFormatter.ISO_LOCAL_DATE_TIME);
        return Timestamp.valueOf(localDateTime);
    }

    public void populateModel(Model model, Doctor doctor) {
        model.addAttribute("firstName", doctor.getFirstName());
        model.addAttribute("lastName", doctor.getLastName());
        model.addAttribute("email", doctor.getEmail());
        model.addAttribute("phoneNumber", doctor.getPhoneNumber());
        model.addAttribute("specialization", doctor.getSpecialization());
    }

    private boolean denyAccess(HttpServletRequest request) {
        String userRole = (String) request.getSession().getAttribute("userRole");
        Object userId = request.getSession().getAttribute("userId");
        return userId == null || userRole == null || !StringUtils.equalsIgnoreCase(userRole, AppConstants.USER_ROLE_ADMIN);
    }
}
