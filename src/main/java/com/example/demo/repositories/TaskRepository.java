package com.example.demo.repositories;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    // Listar todas las tareas de un usuario específico
    List<Task> findByUser(User user);

    // Listar todas las tareas de un usuario según estado
    List<Task> findByUserAndStatus(User user, String status);
}
