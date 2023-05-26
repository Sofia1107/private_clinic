package com.app.controller;

import com.app.consts.AppConstants;
import com.app.entity.Person;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.sql.Date;

@Slf4j
@Controller
@RequestMapping("/registration")
public class RegistrationController {
    @Autowired
    private UserService userService;

    private static final String REGISTRATION_VIEW = "Registration";

    @GetMapping()
    public ModelAndView registration() {
        ModelAndView model = new ModelAndView();
        model.setViewName(REGISTRATION_VIEW);

        return model;
    }
    @PostMapping(value = "")
    public String registration(HttpServletRequest request, Model model) throws Exception {
        Person person = Person.builder()
                .setFirstName(request.getParameter("firstName"))
                .setLastName(request.getParameter("lastName"))
                .setEmail(request.getParameter("email"))
                .setPhoneNumber(request.getParameter("phoneNumber"))
                .setDateOfBirth(Date.valueOf(request.getParameter("birthDate")))
                .setBloodGroup(Integer.valueOf(request.getParameter("bloodGroup")))
                .setRH(request.getParameter("rh"))
                .setAllergy(request.getParameter("allergy"))
                .setPassword(request.getParameter("password"))
                .setUserRole(AppConstants.USER_ROLE_CLIENT)
                .build();
        ResponseEntity responseEntity;
        if (person.getPassword().equals(request.getParameter("passwordRepeated"))){
            responseEntity = userService.registerClient(person);
            if (responseEntity.getStatusCode() == HttpStatus.CREATED){
                model.addAttribute("success", AppConstants.SUCCESS_REGISTRATION);
                populateModel(model,person);

            } else if (responseEntity.getStatusCode() == HttpStatus.BAD_REQUEST){
                model.addAttribute("error", responseEntity.getBody());
                populateModel(model,person);
            }
        } else {
            model.addAttribute("error", AppConstants.PASSWORDS_NOT_EQUAL);
            populateModel(model,person);
        }
        return REGISTRATION_VIEW;
    }

    public void populateModel(Model model, Person person){
        model.addAttribute("firstName", person.getFirstName());
        model.addAttribute("lastName", person.getLastName());
        model.addAttribute("email", person.getEmail());
        model.addAttribute("phoneNumber", person.getPhoneNumber());
        model.addAttribute("birthdate", person.getDateOfBirth());
        model.addAttribute("bloodGroup", person.getBloodGroup());
        model.addAttribute("rh", person.getRH());
        model.addAttribute("allergy", person.getAllergy());
    }
}
