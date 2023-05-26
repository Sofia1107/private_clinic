package com.app.controller;

import com.app.consts.AppConstants;
import com.app.service.UserService;
import lombok.extern.slf4j.Slf4j;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Slf4j
@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserService userService;

    private static final String ADMIN_VIEW = "AdminView";

    @GetMapping()
    public ModelAndView getClientView() {
        ModelAndView model = new ModelAndView();
        model.setViewName(ADMIN_VIEW);

        return model;
    }


}
