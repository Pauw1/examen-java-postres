package com.paulirojas.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import com.paulirojas.modelos.LoginUsuario;
import com.paulirojas.modelos.Usuario;
import com.paulirojas.servicios.ServicioUsuarios;
import jakarta.servlet.http.HttpSession;
import jakarta.validation.Valid;

@Controller
public class ControladorUsuarios {

    @Autowired
    private ServicioUsuarios su;

    // Vista del login + el registro
    @GetMapping("/")
    public String index(@ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
                        @ModelAttribute("loginUsuario") LoginUsuario loginUsuario) {
        return "index.jsp";
    }

    @PostMapping("/registro")
    public String registro(@Valid @ModelAttribute("nuevoUsuario") Usuario nuevoUsuario,
                           BindingResult result,
                           Model model,
                           HttpSession session) {
        su.registrar(nuevoUsuario, result);
        if (result.hasErrors()) {
            model.addAttribute("loginUsuario", new LoginUsuario());
            return "index.jsp";
        }
        session.setAttribute("usuarioEnSesion", nuevoUsuario);
        return "redirect:/postres";
    }

    @PostMapping("/login")
    public String login(@Valid @ModelAttribute("loginUsuario") LoginUsuario loginUsuario,
                        BindingResult result,
                        Model model,
                        HttpSession session) {
        Usuario usuario = su.login(loginUsuario, result);
        if (result.hasErrors()) {
            model.addAttribute("nuevoUsuario", new Usuario());
            return "index.jsp";
        }
        session.setAttribute("usuarioEnSesion", usuario);
        return "redirect:/postres";
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/";
    }
}