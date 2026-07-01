package com.paulirojas.servicios;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.paulirojas.modelos.Postre;
import com.paulirojas.modelos.Usuario;
import com.paulirojas.repositorios.RepositorioPostres;
import com.paulirojas.repositorios.RepositorioUsuarios;

@Service
public class ServicioPostres {

    @Autowired
    private RepositorioPostres repoPostres;

    @Autowired
    private RepositorioUsuarios repoUsuarios;

    // Todos los postres ordenados A-Z (Oro)
    public List<Postre> todosLosPostres() {
        return repoPostres.findAllByOrderByNombreAsc();
    }

    // Guardar (crea si no tiene id, actualiza si lo tiene)
    public Postre guardarPostre(Postre postre) {
        return repoPostres.save(postre);
    }

    public Postre buscarPostre(Long id) {
        return repoPostres.findById(id).orElse(null);
    }

    public void borrarPostre(Long id) {
        Postre postre = repoPostres.findById(id).orElse(null);
        // Suelto las relaciones de "me gusta" antes de borrar
        if (postre != null && postre.getUsuariosQueGustan() != null) {
            for (Usuario u : postre.getUsuariosQueGustan()) {
                u.getPostresGustados().remove(postre);
                repoUsuarios.save(u);
            }
        }
        repoPostres.deleteById(id);
    }

    // Búsqueda por palabra en el nombre (Oro)
    public List<Postre> buscarPorPalabra(String palabra) {
        return repoPostres.findByNombreContainingIgnoreCase(palabra);
    }

    // Dar "Me gusta": agrega el postre a la lista del usuario (Oro)
    public void darMeGusta(Long usuarioId, Long postreId) {
        Usuario usuario = repoUsuarios.findById(usuarioId).orElse(null);
        Postre postre = repoPostres.findById(postreId).orElse(null);
        usuario.getPostresGustados().add(postre);
        repoUsuarios.save(usuario);
    }
}