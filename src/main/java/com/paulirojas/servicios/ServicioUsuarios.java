package com.paulirojas.servicios;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;
import com.paulirojas.modelos.LoginUsuario;
import com.paulirojas.modelos.Usuario;
import com.paulirojas.repositorios.RepositorioUsuarios;

@Service
public class ServicioUsuarios {

    @Autowired
    private RepositorioUsuarios repoUsuarios;

    //Registro con validaciones manuales + hash de contraseña
    public Usuario registrar(Usuario nuevoUsuario, BindingResult result) {
        // Las contraseñas deben coincidir
        if (!nuevoUsuario.getPassword().equals(nuevoUsuario.getConfirmacion())) {
            result.rejectValue("confirmacion", "Matches", "Las contraseñas no coinciden");
        }
        // email no debe existir antes en los registros
        if (repoUsuarios.findByEmail(nuevoUsuario.getEmail()) != null) {
            result.rejectValue("email", "Unique", "El correo ya está registrado");
        }

        if (result.hasErrors()) {
            return null;
        }

        //Hash de la contraseña con BCrypt
        String hasheada = BCrypt.hashpw(nuevoUsuario.getPassword(), BCrypt.gensalt());
        nuevoUsuario.setPassword(hasheada);
        return repoUsuarios.save(nuevoUsuario);
    }

    //Login valida mail y compara contraseña hasheada
    public Usuario login(LoginUsuario usuarioLogin, BindingResult result) {
        Usuario existe = repoUsuarios.findByEmail(usuarioLogin.getEmailLogin());
        if (existe == null) {
            result.rejectValue("emailLogin", "Unique", "Credenciales incorrectas");
        } else if (!BCrypt.checkpw(usuarioLogin.getPasswordLogin(), existe.getPassword())) {
            result.rejectValue("passwordLogin", "Matches", "Credenciales incorrectas");
        }

        if (result.hasErrors()) {
            return null;
        }
        return existe;
    }

    public Usuario buscarUsuario(Long id) {
        return repoUsuarios.findById(id).orElse(null);
    }
}