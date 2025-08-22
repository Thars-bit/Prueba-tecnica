package com.example.demo.controller;

import com.example.demo.entity.User;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;


/**
 * Controlador encargado de gestionar las rutas de autenticación
 * y registro de usuarios dentro de la aplicación.
 *
 * - /auth/login (GET): Carga la vista del login.
 * - /auth/register (GET): Carga la vista de registro.
 * - /auth/register (POST): Registra un usuario en la base de datos.
 * - /auth/login (POST): Valida credenciales de acceso con email y password.
 */
@Controller
@RequestMapping("/auth")
public class AuthController {

    private final UserService userService;

    public AuthController(UserService userService) {
        this.userService = userService;
    }

    /**
     * GET /auth/login
     *
     * Retorna la página de inicio de sesión.
     * Spring buscará el archivo login.html en la carpeta templates.
     *
     * @return Nombre de la plantilla login.html
     */
    @GetMapping("/login")
    public String loginPage() {
        return "login"; // carga templates/login.html
    }

    /**
     * GET /auth/register
     *
     * Retorna la página de registro de usuario.
     * Spring buscará el archivo register.html en la carpeta templates.
     *
     * @return Nombre de la plantilla register.html
     */
    @GetMapping("/register")
    public String registerPage() {
        return "register";
    }

    /**
     * POST /auth/register
     *
     * Permite registrar un nuevo usuario enviando datos en formato JSON.
     * Ejemplo de petición:
     * {
     *   "name": "Juan",
     *   "email": "juan@example.com",
     *   "password": "12345"
     * }
     *
     * @param user Objeto User recibido desde el cuerpo de la petición.
     * @return Mensaje de éxito o error en el registro.
     */
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

    /**
     * POST /auth/login
     *
     * Permite autenticar un usuario mediante email y contraseña.
     * Ejemplo de petición:
     * {
     *   "email": "juan@example.com",
     *   "password": "12345"
     * }
     *
     * @param loginRequest Objeto User recibido con email y password.
     * @return "OK:{id}" si las credenciales son correctas,
     *         o mensaje de error si fallan.
     */
    @PostMapping("/login")
    @ResponseBody
    public String login(@RequestBody User loginRequest) {
        return userService.findByEmail(loginRequest.getEmail()) //
                .filter(user -> user.getPassword().equals(loginRequest.getPassword()))
                .map(user -> "OK:" + user.getId())
                .orElse("ERROR: Correo o contraseña incorrectos");
    }
}