package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;


/**
 * Controlador encargado de manejar las operaciones relacionadas con las tareas (Task).
 * Proporciona endpoints para visualizar, crear, actualizar y eliminar tareas
 * asociadas a un usuario específico.
 */
@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    /**
     * Muestra la vista de tareas de un usuario.
     *
     * @param userId ID del usuario cuyas tareas se van a mostrar
     * @param model Modelo de Spring para pasar atributos a la vista
     * @return Nombre de la vista "tasks"
     */
    @GetMapping("/view")
    public String viewTasks(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "tasks";
    }

    /**
     * Obtiene todas las tareas asociadas a un usuario específico.
     *
     * @param userId ID del usuario
     * @return Lista de tareas del usuario
     */

    @GetMapping("/all/{userId}")
    @ResponseBody
    public List<Task> getTasks(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow();
        return taskService.getTasksByUser(user);
    }

    /**
     * Crea una nueva tarea.
     *
     * @param task Objeto Task enviado en el cuerpo de la petición (JSON)
     * @return La tarea creada y guardada en la base de datos
     */
    @PostMapping("/add")
    @ResponseBody
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    /**
     * Actualiza una tarea existente según su ID.
     *
     * @param id ID de la tarea a actualizar
     * @param updatedTask Objeto con los nuevos valores de la tarea
     * @return La tarea actualizada
     */
    @PutMapping("/update/{id}")
    @ResponseBody
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        Task task = taskService.findById(id).orElseThrow();
        task.setTitle(updatedTask.getTitle());
        task.setDescription(updatedTask.getDescription());
        task.setStatus(updatedTask.getStatus());
        task.setDueDate(updatedTask.getDueDate());
        return taskService.saveTask(task);
    }

    /**
     * Elimina una tarea según su ID.
     *
     * @param id ID de la tarea a eliminar
     * @return Mensaje de confirmación de eliminación
     */
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "Tarea eliminada";
    }
}
