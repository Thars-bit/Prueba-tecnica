package com.example.demo.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "tasks")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(length = 500)
    private String description;

    @Column(name = "due_date")
    private LocalDate dueDate;

    @Column(nullable = false)
    private String status; // "pendiente" o "completada"

    // Relación N:1 con User (muchas tareas pueden pertenecer a un usuario)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    // Constructor vacío
    public Task() {}

    // Constructor con parámetros
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
