package com.paulirojas.repositorios;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.paulirojas.modelos.Postre;

@Repository
public interface RepositorioPostres extends JpaRepository<Postre, Long> {

    //todos los postres aparecen ordenados por nombre alfabeticamente (requisito Oro)
    List<Postre> findAllByOrderByNombreAsc();

    //La búsqueda por título ignora las mayúsculas y minúsculas (req Oro)
    List<Postre> findByNombreContainingIgnoreCase(String palabra);
}