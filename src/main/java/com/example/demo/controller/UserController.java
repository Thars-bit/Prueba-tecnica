package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


/**
 * Controlador REST que gestiona operaciones relacionadas con la entidad User.
 * Expone endpoints bajo la ruta base "/users".
 */
@RestController
@RequestMapping("/users")
public class UserController {

    // Servicio que contiene la lógica de negocio para manejar usuarios
    private final UserService userService;

    // Constructor con inyección de dependencias del servicio de usuarios
    public UserController(UserService userService) {
        this.userService = userService;
    }

    /**
     * Endpoint para obtener un usuario por su ID.
     *
     * Método HTTP: GET
     * Ruta: /users/{id}
     *
     * Ejemplo de uso:
     * GET http://localhost:8080/users/1
     *
     * @param id identificador del usuario
     * @return un Optional<User> que puede contener el usuario si existe, o estar vacío si no se encuentra
     */
    @GetMapping("/{id}")
    public Optional<User> getUserById(@PathVariable Long id) {   // implementa el endpoint para obtener un usuario por ID rol admin?
        return userService.getUserById(id);
    }
}
