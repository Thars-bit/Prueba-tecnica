package com.example.demo.services;

import com.example.demo.entity.User;
import com.example.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserService {

    private final UserRepository userRepository;

    // Inyección de dependencias por constructor
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public Optional<User> findById(Long id) {
        return userRepository.findById(id);
    }

    // Registrar un nuevo usuario (validando que no exista)
    public User registerUser(User user) {
        if (userRepository.existsByUsername(user.getUsername())) {
            throw new RuntimeException("El nombre de usuario ya existe");
        }
        return userRepository.save(user);
    }

    // Buscar un usuario por username (para login)
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    // Obtener usuario por ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }
}
