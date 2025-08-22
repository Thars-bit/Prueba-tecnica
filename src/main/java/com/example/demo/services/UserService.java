package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;


    /**
     * Constructor con inyección de dependencias.
     * @param userRepository Repositorio de usuarios gestionado por Spring
     */
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Busca un usuario por su ID.
     * @param id ID del usuario
     * @return Optional<User> con el usuario si existe
     */
    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    /**
     * Busca un usuario por su correo electrónico.
     * @param email correo del usuario
     * @return Optional<User> con el usuario si existe
     */
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    /**
     * Registra un nuevo usuario validando que el email no esté en uso.
     * @param user Usuario a registrar
     * @return Usuario registrado
     * @throws RuntimeException si el email ya está en uso
     */
    public User registerUser(User user) {
        if (userRepository.existsByEmail(user.getEmail())) {
            throw new RuntimeException("El correo electrónico ya está en uso");
        }
        return userRepository.save(user);
    }

    /**
     * Busca un usuario por su nombre de usuario.
     * @param username nombre de usuario
     * @return Optional<User> con el usuario si existe
     */
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    /**
     * Obtiene un usuario por ID (semánticamente para mostrar perfil).
     * @param id ID del usuario
     * @return Optional<User> con el usuario si existe
     */
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
