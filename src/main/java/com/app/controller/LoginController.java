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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
//@RequestMapping("/login")
public class LoginController {

    private static final String LOGIN_VIEW = "Login";
    private static final String REDIRECT_ADMIN = "redirect:/admin";
    private static final String REDIRECT_CLIENT = "redirect:/client";
    private static final String REDIRECT_LOGIN = "redirect:/login";
    @Autowired
    private UserService userService;

    @GetMapping("/login")
    public String login() {
        return LOGIN_VIEW;
    }
    @PostMapping("/login")
    public String login(HttpServletRequest request, Model model) throws Exception {
        Person person = Person.builder()
                .setEmail(request.getParameter("email"))
                .setPassword(request.getParameter("password"))
                .build();
        ResponseEntity responseEntity = userService.authorizeUser(person);
        if (responseEntity.getStatusCode() == HttpStatus.UNAUTHORIZED){
            model.addAttribute("error", AppConstants.INCORRECT_PWD_ERROR);
            model.addAttribute("email", person.getEmail());
        } else if (responseEntity.getStatusCode() == HttpStatus.NO_CONTENT){
            model.addAttribute("error", AppConstants.USER_WITH_EMAIL_DOES_NOT_EXIST);
            model.addAttribute("email", person.getEmail());
        } else {
            Person user = (Person) responseEntity.getBody();
            request.getSession().setAttribute("userRole", user.getUserRole());
            request.getSession().setAttribute("userId", user.getId());
            if (user.getUserRole().equals(AppConstants.USER_ROLE_ADMIN)){
                return REDIRECT_ADMIN;
            } else if (user.getUserRole().equals(AppConstants.USER_ROLE_CLIENT)){
                return REDIRECT_CLIENT;
            }
        }
        return LOGIN_VIEW;
    }

    @GetMapping("/logout")
    public String logout(HttpServletRequest httpServletRequest){
        httpServletRequest.getSession().invalidate();
        return REDIRECT_LOGIN;
    }
}
