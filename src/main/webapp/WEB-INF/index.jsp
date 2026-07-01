<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Postres - Login y Registro</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Postres</h1>
    </div>
    <div class="row mt-3">
        <div class="col-6">
            <h2>Registro</h2>
            <form:form action="/registro" method="POST" modelAttribute="nuevoUsuario">
                <div class="mb-2">
                    <form:label path="nombre">Nombre</form:label>
                    <form:input path="nombre" class="form-control"/>
                    <form:errors path="nombre" class="text-danger"/>
                </div>
                <div class="mb-2">
                    <form:label path="apellido">Apellido</form:label>
                    <form:input path="apellido" class="form-control"/>
                    <form:errors path="apellido" class="text-danger"/>
                </div>
                <div class="mb-2">
                    <form:label path="email">Correo</form:label>
                    <form:input path="email" class="form-control"/>
                    <form:errors path="email" class="text-danger"/>
                </div>
                <div class="mb-2">
                    <form:label path="password">Contraseña</form:label>
                    <form:password path="password" class="form-control"/>
                    <form:errors path="password" class="text-danger"/>
                </div>
                <div class="mb-2">
                    <form:label path="confirmacion">Confirmar contraseña</form:label>
                    <form:password path="confirmacion" class="form-control"/>
                    <form:errors path="confirmacion" class="text-danger"/>
                </div>
                <input type="submit" value="Registrarse" class="btn btn-primary mt-2">
            </form:form>
        </div>
        <div class="col-6">
            <h2>Login</h2>
            <form:form action="/login" method="POST" modelAttribute="loginUsuario">
                <div class="mb-2">
                    <form:label path="emailLogin">Correo</form:label>
                    <form:input path="emailLogin" class="form-control"/>
                    <form:errors path="emailLogin" class="text-danger"/>
                </div>
                <div class="mb-2">
                    <form:label path="passwordLogin">Contraseña</form:label>
                    <form:password path="passwordLogin" class="form-control"/>
                    <form:errors path="passwordLogin" class="text-danger"/>
                </div>
                <input type="submit" value="Login" class="btn btn-primary mt-2">
            </form:form>
        </div>
    </div>
</div>
</body>
</html>