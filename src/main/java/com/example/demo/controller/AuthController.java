package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;

import java.util.Optional;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    // Vista de login
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // carga templates/login.html
    }

    // Vista de registro
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    // Registro (POST con formulario clásico)
    @PostMapping("/register")
    @ResponseBody
    public String registerUser(@RequestBody User user) {
        try {
            userService.registerUser(user);
            return "OK: Usuario registrado correctamente.";
        } catch (RuntimeException e) {
            return "ERROR: " + e.getMessage();
        }
    }

    // Login con JSON (AJAX con fetch)
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User loginRequest) {
        return userService.findByEmail(loginRequest.getEmail()) //
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> "OK:" + user.getId())
                .orElse("ERROR: Correo o contraseña incorrectos");
    }
}