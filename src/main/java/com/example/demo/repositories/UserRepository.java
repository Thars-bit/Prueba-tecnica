package com.example.demo.repositories;

import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.Optional;


/**
 * Repositorio para la entidad User.
 * Extiende JpaRepository para proporcionar operaciones CRUD automáticas.
 *
 * Métodos personalizados definidos aquí generan consultas
 * automáticamente gracias a Spring Data JPA.
 */


public interface UserRepository extends JpaRepository<User, Long> {   //va atrabajar con la entidad User y el tipo de ID Long

    /**
     * Busca un usuario por su nombre de usuario.
     *
     * @param username nombre de usuario a buscar
     * @return Optional con el usuario si existe, vacío en caso contrario
     */
    Optional<User> findByUsername(String username);

    /**
     * Busca un usuario por su correo electrónico.
     *
     * @param email correo electrónico a buscar
     * @return Optional con el usuario si existe, vacío en caso contrario
     */
    Optional<User> findByEmail(String email);

    /**
     * Verifica si ya existe un usuario con el username dado.
     *
     * @param username nombre de usuario a verificar
     * @return true si ya existe, false en caso contrario
     */
    boolean existsByUsername(String username);


    /**
     * Verifica si ya existe un usuario con el email dado.
     *
     * @param email correo electrónico a verificar
     * @return true si ya existe, false en caso contrario
     */
    boolean existsByEmail(String email);
}
