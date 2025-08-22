package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity                       // Marca esta clase como una entidad gestionada por JPA
@Table(name = "tasks")         // Define que esta entidad se guardará en la tabla tasks de la BD
public class Task {

    @Id                       // Indica que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // Generación automática del ID autoincremental
    private Long id;    // Variable para almacenar el ID de la tarea

    @Column(nullable = false)  // Define que esta columna no puede ser nula
    private String title;

    @Column(length = 500)   // Define la longitud máxima de la descripción
    private String description;

    @Column(name = "due_date")  // Mapea esta propiedad a la columna due_date en la BD
    private LocalDate dueDate;

    @Column(nullable = false)   // Define que esta columna no puede ser nula
    private String status; // "pendiente" o "completada"

    /**
     * Relación N:1 con Usuario.
     * Muchas tareas pueden pertenecer a un único usuario.
     *
     * - fetch = LAZY: solo carga el usuario cuando se solicita
     * - JoinColumn = "user_id": clave foránea hacia users.id
     */

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor vacío para JPA
    public Task() {}

    // Constructor con parámetros para crear una tarea
    public Task(String title, String description, LocalDate dueDate, String status, User user) {
        this.title = title;
        this.description = description;
        this.dueDate = dueDate;
        this.status = status;
        this.user = user;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getTitle() { return title; }

    public void setTitle(String title) { this.title = title; }

    public String getDescription() { return description; }

    public void setDescription(String description) { this.description = description; }

    public LocalDate getDueDate() { return dueDate; }

    public void setDueDate(LocalDate dueDate) { this.dueDate = dueDate; }

    public String getStatus() { return status; }

    public void setStatus(String status) { this.status = status; }

    public User getUser() { return user; }

    public void setUser(User user) { this.user = user; }
}
