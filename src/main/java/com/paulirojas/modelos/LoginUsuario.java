package com.paulirojas.modelos;

import jakarta.validation.constraints.*;

public class LoginUsuario {

    @NotBlank(message = "El correo es obligatorio")
    @Email(message = "Credenciales incorrectas")
    private String emailLogin;

    @NotBlank(message = "La contraseña es obligatoria")
    private String passwordLogin;

    public LoginUsuario() {}

    public String getEmailLogin() { return emailLogin; }
    public void setEmailLogin(String emailLogin) { this.emailLogin = emailLogin; }
    public String getPasswordLogin() { return passwordLogin; }
    public void setPasswordLogin(String passwordLogin) { this.passwordLogin = passwordLogin; }
}