package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    // Buscar usuario por nombre de usuario (para login)
    Optional<User> findByUsername(String username);
    Optional<User> findByEmail(String email);

    // Verificar si un username ya existe
    boolean existsByUsername(String username);
}
