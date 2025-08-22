package com.example.demo.controller;

import com.example.demo.entity.Task;
import com.example.demo.entity.User;
import com.example.demo.services.TaskService;
import com.example.demo.services.UserService;
import org.springframework.web.bind.annotation.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import java.util.*;
import java.util.*;

@Controller
@RequestMapping("/tasks")
public class TaskController {

    private final TaskService taskService;
    private final UserService userService;

    public TaskController(TaskService taskService, UserService userService) {
        this.taskService = taskService;
        this.userService = userService;
    }

    // Vista de tareas (recibe el userId desde la URL)
    @GetMapping("/view")
    public String viewTasks(@RequestParam Long userId, Model model) {
        model.addAttribute("userId", userId);
        return "tasks";
    }

    // 🔹 Obtener todas las tareas de un usuario
    @GetMapping("/all/{userId}")
    @ResponseBody
    public List<Task> getTasks(@PathVariable Long userId) {
        User user = userService.findById(userId).orElseThrow();
        return taskService.getTasksByUser(user);
    }

    // 🔹 Crear nueva tarea (AJAX con fetch)
    @PostMapping("/add")
    @ResponseBody
    public Task addTask(@RequestBody Task task) {
        return taskService.saveTask(task);
    }

    // 🔹 Actualizar tarea
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

    // 🔹 Eliminar tarea
    @DeleteMapping("/delete/{id}")
    @ResponseBody
    public String deleteTask(@PathVariable Long id) {
        taskService.deleteById(id);
        return "Tarea eliminada";
    }
}
