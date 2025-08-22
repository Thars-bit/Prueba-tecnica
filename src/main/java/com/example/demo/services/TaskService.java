package com.example.demo.services;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.repositories.TaskRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    private final TaskRepository taskRepository;


    // Inyección de dependencias por constructor
    public TaskService(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }


    /**
     * Guarda una tarea en la base de datos
     * @param task Objeto Task que se quiere guardar
     * @return La tarea guardada con su ID generado
     */
    public Task saveTask(Task task) {
        return taskRepository.save(task);
    }

    /**
     * Obtiene todas las tareas asignadas a un usuario específico
     * @param user Usuario dueño de las tareas
     * @return Lista de tareas asociadas al usuario
     */
    public List<Task> getTasksByUser(User user) {
        return taskRepository.findByUser(user);
    }

    /**
     * Busca una tarea por su ID
     * @param id Identificador único de la tarea
     * @return Optional<Task> (puede estar vacío si no existe la tarea)
     */

    public Optional<Task> findById(Long id) {
        return taskRepository.findById(id);
    }


    /**
     * Elimina una tarea por su ID
     * @param id Identificador de la tarea a eliminar
     */
    public void deleteById(Long id) {
        taskRepository.deleteById(id);
    }


}
