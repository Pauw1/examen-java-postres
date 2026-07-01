package com.paulirojas.controladores;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.paulirojas.modelos.Postre;
import com.paulirojas.modelos.Usuario;
import com.paulirojas.servicios.ServicioPostres;
import com.paulirojas.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorPostres {

    @Autowired
    private ServicioPostres sp;

    @Autowired
    private ServicioUsuarios su;

    // DASHBOARD: todos los postres
    @GetMapping("/postres")
    public String dashboard(HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        List<Postre> postres = sp.todosLosPostres();
        model.addAttribute("postres", postres);
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        model.addAttribute("usuario", usuarioEnSesion);
        return "dashboard.jsp";
    }

    // BÚSQUEDA (Oro) → /postres/buscar?palabra=...
    @GetMapping("/postres/buscar")
    public String buscar(@RequestParam("palabra") String palabra,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        List<Postre> postres = sp.buscarPorPalabra(palabra);
        model.addAttribute("postres", postres);
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        model.addAttribute("usuario", usuarioEnSesion);
        return "dashboard.jsp";
    }

    // FORMULARIO NUEVO
    @GetMapping("/postres/nuevo")
    public String nuevo(@ModelAttribute("nuevoPostre") Postre nuevoPostre,
                        HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        return "nuevo.jsp";
    }

    // CREAR
    @PostMapping("/postres/crear")
    public String crear(@Valid @ModelAttribute("nuevoPostre") Postre nuevoPostre,
                        BindingResult result,
                        HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "nuevo.jsp";
        }
        // Guardo el id del usuario que creó el postre
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        nuevoPostre.setAutor(su.buscarUsuario(usuarioEnSesion.getId()));
        sp.guardarPostre(nuevoPostre);
        return "redirect:/postres";
    }

    // DETALLE
    @GetMapping("/postres/{id}")
    public String mostrar(@PathVariable("id") Long id,
                         HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        Postre postre = sp.buscarPostre(id);
        model.addAttribute("postre", postre);
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        model.addAttribute("usuario", su.buscarUsuario(usuarioEnSesion.getId()));
        return "mostrar.jsp";
    }

    // FORMULARIO EDITAR
    @GetMapping("/postres/editar/{id}")
    public String editar(@PathVariable("id") Long id,
                        @ModelAttribute("postre") Postre postre,
                        HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        Postre postreAEditar = sp.buscarPostre(id);
        // Solo el autor puede editar
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        if (usuarioEnSesion.getId() != postreAEditar.getAutor().getId()) {
            return "redirect:/postres";
        }
        model.addAttribute("postre", postreAEditar);
        return "editar.jsp";
    }

    // ACTUALIZAR
    @PutMapping("/postres/actualizar/{id}")
    public String actualizar(@PathVariable("id") Long id,
                            @Valid @ModelAttribute("postre") Postre postre,
                            BindingResult result,
                            HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        if (result.hasErrors()) {
            return "editar.jsp";
        }
        // Mantengo el autor original
        Postre original = sp.buscarPostre(id);
        postre.setAutor(original.getAutor());
        sp.guardarPostre(postre);
        return "redirect:/postres";
    }

    // BORRAR
    @DeleteMapping("/postres/borrar/{id}")
    public String borrar(@PathVariable("id") Long id, HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        sp.borrarPostre(id);
        return "redirect:/postres";
    }

    // MIS POSTRES
    @GetMapping("/postres/mis-postres")
    public String misPostres(HttpSession session, Model model) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        Usuario usuarioEnSesion = (Usuario) session.getAttribute("usuarioEnSesion");
        Usuario usuario = su.buscarUsuario(usuarioEnSesion.getId());
        model.addAttribute("usuario", usuario);
        return "misPostres.jsp";
    }

    // ME GUSTA (para el Oro jeje)
    @GetMapping("/postres/megusta/{usuarioId}/{postreId}")
    public String meGusta(@PathVariable("usuarioId") Long usuarioId,
                         @PathVariable("postreId") Long postreId,
                         HttpSession session) {
        if (session.getAttribute("usuarioEnSesion") == null) {
            return "redirect:/";
        }
        sp.darMeGusta(usuarioId, postreId);
        return "redirect:/postres/" + postreId;
    }
}