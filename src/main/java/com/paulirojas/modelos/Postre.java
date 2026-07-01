package com.paulirojas.modelos;

import java.util.Date;
import java.util.List;
import org.springframework.format.annotation.DateTimeFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "postres")
public class Postre {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "Por favor proporciona el nombre")
    @Size(min = 5, message = "El nombre debe tener al menos 5 caracteres")
    private String nombre;

    @NotBlank(message = "Por favor proporciona los ingredientes")
    @Size(min = 10, message = "Los ingredientes deben tener al menos 10 caracteres")
    @Column(columnDefinition = "TEXT")
    private String ingredientes;

    @NotBlank(message = "Por favor proporciona las instrucciones")
    @Size(min = 10, message = "Las instrucciones deben tener al menos 10 caracteres")
    @Column(columnDefinition = "TEXT")
    private String instrucciones;

    @NotBlank(message = "Por favor proporciona una URL válida con la imagen")
    @Pattern(regexp = ".+\\.(jpg|jpeg|png|gif|webp)$",
             message = "La URL debe terminar en una extensión de imagen válida")
    private String urlImagen;

    @NotNull(message = "Por favor proporciona el tiempo de preparación")
    @Min(value = 1, message = "El tiempo debe ser un número en minutos")
    private Integer tiempoPreparacion;

    @Column(updatable = false)
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date createdAt;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date updatedAt;

    //Cada postre tiene un único autor (el usuario que lo creó)
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "usuario_id")
    private Usuario autor;

    //Los usuarios que dieron "Me gusta"/ManyToMany (para el Oro)
    @ManyToMany(mappedBy = "postresGustados", fetch = FetchType.LAZY)
    private List<Usuario> usuariosQueGustan;

    public Postre() {}

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
    public String getIngredientes() { return ingredientes; }
    public void setIngredientes(String ingredientes) { this.ingredientes = ingredientes; }
    public String getInstrucciones() { return instrucciones; }
    public void setInstrucciones(String instrucciones) { this.instrucciones = instrucciones; }
    public String getUrlImagen() { return urlImagen; }
    public void setUrlImagen(String urlImagen) { this.urlImagen = urlImagen; }
    public Integer getTiempoPreparacion() { return tiempoPreparacion; }
    public void setTiempoPreparacion(Integer tiempoPreparacion) { this.tiempoPreparacion = tiempoPreparacion; }
    public Date getCreatedAt() { return createdAt; }
    public void setCreatedAt(Date createdAt) { this.createdAt = createdAt; }
    public Date getUpdatedAt() { return updatedAt; }
    public void setUpdatedAt(Date updatedAt) { this.updatedAt = updatedAt; }
    public Usuario getAutor() { return autor; }
    public void setAutor(Usuario autor) { this.autor = autor; }
    public List<Usuario> getUsuariosQueGustan() { return usuariosQueGustan; }
    public void setUsuariosQueGustan(List<Usuario> usuariosQueGustan) { this.usuariosQueGustan = usuariosQueGustan; }
}