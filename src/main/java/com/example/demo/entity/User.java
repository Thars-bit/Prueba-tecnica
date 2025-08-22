package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import java.util.*;

@Entity
@Table(name = "users") // nombre de la tabla en la BD
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // autoincremental
    private Long id;

    @Column(nullable = false, unique = true)
    private String username;

    @Column(nullable = false, unique = true)
    private String userLastname;

    @Column(nullable = false, unique = true)
    private String document_number;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    // Relación 1:N con Task (un usuario puede tener muchas tareas)
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    // Constructor vacío
    public User() {}

    // Constructor con parámetros
    public User(String username, String password, String userLastname, String document_number, String email) {
        this.username = username;
        this.password = password;
        this.userLastname = userLastname;
        this.document_number = document_number;
        this.email = email;
    }

    // Getters y Setters
    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public String getUsername() { return username; }

    public void setUsername(String username) { this.username = username; }

    public String getUserLastname() { return userLastname; }

    public void setUserLastname(String userLastname) { this.userLastname = userLastname; }

    public String getDocument_number() { return document_number; }

    public void setDocument_number(String document_number) { this.document_number = document_number; }

    public String getEmail() { return email; }

    public void setEmail(String email) { this.email = email; }

    public String getPassword() { return password; }

    public void setPassword(String password) { this.password = password; }

    public List<Task> getTasks() { return tasks; }

    public void setTasks(List<Task> tasks) { this.tasks = tasks; }
}
