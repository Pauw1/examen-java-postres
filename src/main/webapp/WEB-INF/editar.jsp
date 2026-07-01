<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Editar postre</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.8/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body>
<div class="container mt-4">
    <div class="d-flex justify-content-between align-items-center">
        <h1>Postres</h1>
        <div>
            <a href="/postres">Postres</a> |
            <a href="/postres/mis-postres">Mis postres</a> |
            <a href="/postres/nuevo">Agregar postre</a> |
            <a href="/logout">Logout</a>
        </div>
    </div>

    <h3 class="mt-3">Editar postre</h3>
    <form:form action="/postres/actualizar/${postre.id}" method="POST" modelAttribute="postre">
        <input type="hidden" name="_method" value="PUT">
        <div class="mb-2">
            <form:label path="nombre">Nombre</form:label>
            <form:input path="nombre" class="form-control"/>
            <form:errors path="nombre" class="text-danger"/>
        </div>
        <div class="mb-2">
            <form:label path="ingredientes">Ingredientes</form:label>
            <form:textarea path="ingredientes" class="form-control" rows="3"/>
            <form:errors path="ingredientes" class="text-danger"/>
        </div>
        <div class="mb-2">
            <form:label path="instrucciones">Instrucciones</form:label>
            <form:textarea path="instrucciones" class="form-control" rows="4"/>
            <form:errors path="instrucciones" class="text-danger"/>
        </div>
        <div class="mb-2">
            <form:label path="urlImagen">URL a imagen</form:label>
            <form:input path="urlImagen" class="form-control"/>
            <form:errors path="urlImagen" class="text-danger"/>
        </div>
        <div class="mb-2">
            <form:label path="tiempoPreparacion">Tiempo de preparación</form:label>
            <form:input path="tiempoPreparacion" class="form-control"/>
            <form:errors path="tiempoPreparacion" class="text-danger"/>
        </div>
        <input type="submit" value="Editar" class="btn btn-primary mt-2">
    </form:form>
</div>
</body>
</html>