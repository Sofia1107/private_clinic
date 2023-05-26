package com.app;

import com.app.controller.AdminController;
import com.app.controller.LoginController;
import com.app.util.PasswordUtil;
import com.app.util.ValidationUtil;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableTransactionManagement
public class Application extends SpringBootServletInitializer {
    public static void main(String[] args)  {
        SpringApplication.run(Application.class, args);
    }
}