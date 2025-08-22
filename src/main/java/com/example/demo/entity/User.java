package com.example.demo.entity;
import com.fasterxml.jackson.annotation.JsonIgnore;


import jakarta.persistence.*;
import java.util.*;

@Entity                 // Marca esta clase como una entidad gestionada por JPA
@Table(name = "users") // Define que esta entidad se guardará en la tabla users de la BD
public class User {

    @Id                  // aca se esta indicando que este campo es la clave primaria
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Generación automática del ID autoincremental
    private Long id;          //variable para almacenar el ID del usuario

    @Column(nullable = false, unique = false) // Define las propiedades de la columna en la BD no puede ser nula y no es única
    private String username;

    @Column(nullable = false, unique = false)
    private String userLastname;

    @Column(nullable = false, unique = true)
    private String document_number;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    /**
     * Relación 1:N con tareas.
     * Un usuario puede tener múltiples tareas asignadas.
     *
     * - mappedBy = "user": indica que la relación está definida en Task.user
     * - cascade = ALL: si se elimina el usuario, se eliminan sus tareas
     * - orphanRemoval = true: si se elimina de la lista, también se borra en la BD
     * - @JsonIgnore: evita bucles infinitos en JSON
     */

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL, orphanRemoval = true)
    @JsonIgnore
    private List<Task> tasks = new ArrayList<>();

    // Constructor vacío para JPA
    public User() {}



    // Constructor con parámetros para crear un usuario
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
