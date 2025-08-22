package com.example.demo.repositories;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;


/**
 * Repositorio para la entidad Task.
 * Extiende JpaRepository para proporcionar operaciones CRUD automáticas.
 *
 * Métodos personalizados definidos aquí generan consultas
 * automáticamente gracias a Spring Data JPA.
 */

public interface TaskRepository extends JpaRepository<Task, Long> {

    /**
     * Obtiene todas las tareas de un usuario específico.
     *
     * @param user usuario propietario de las tareas
     * @return lista de tareas del usuario
     */
    List<Task> findByUser(User user);

    /**
     * Obtiene todas las tareas de un usuario según su estado.
     *
     * @param user usuario propietario de las tareas
     * @param status estado de las tareas (ej: "pendiente", "en proceso", "completada")
     * @return lista de tareas filtradas por usuario y estado
     */
    List<Task> findByUserAndStatus(User user, String status);   //aun no se usa falta implementacion
}
