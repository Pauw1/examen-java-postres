package com.paulirojas.modelos;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "usuarios")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Por favor proporciona tu nombre")
    @Size(min = 3, message = "El nombre debe tener al menos 3 caracteres")
    private String nombre;

    @NotBlank(message = "Por favor proporciona tu apellido")
    @Size(min = 3, message = "El apellido debe tener al menos 3 caracteres")
    private String apellido;

    @NotBlank(message = "Por favor ingresa un correo válido")
    @Email(message = "Por favor ingresa un correo válido")
    private String email;

    @NotBlank(message = "La contraseña es obligatoria")
    @Size(min = 8, message = "La contraseña necesita tener al menos 8 caracteres")
    private String password;

    @Transient //No se crea columna en la BD
    private String confirmacion;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    // Un usuario puede crear muchos postres (many to many)
    @OneToMany(mappedBy = "autor", fetch = FetchType.LAZY)
    private List<Postre> postresCreados;

    // postres a los que el usuario dio "Me gusta" (ManyToMany, para Oro)
    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
        name = "me_gusta",
        joinColumns = @JoinColumn(name = "usuario_id"),
        inverseJoinColumns = @JoinColumn(name = "postre_id")
    )
    private List<Postre> postresGustados;

    public Usuario() {}

    @PrePersist
    protected void onCreate() {
        this.createdAt = new Date();
        this.updatedAt = this.createdAt;
    }

    @PreUpdate
    protected void onUpdate() {
        this.updatedAt = new Date();
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPassword() { return password; }
    public void setPassword(String password) { this.password = password; }
    public String getConfirmacion() { return confirmacion; }
    public void setConfirmacion(String confirmacion) { this.confirmacion = confirmacion; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public List<Postre> getPostresCreados() { return postresCreados; }
    public void setPostresCreados(List<Postre> postresCreados) { this.postresCreados = postresCreados; }
    public List<Postre> getPostresGustados() { return postresGustados; }
    public void setPostresGustados(List<Postre> postresGustados) { this.postresGustados = postresGustados; }
}