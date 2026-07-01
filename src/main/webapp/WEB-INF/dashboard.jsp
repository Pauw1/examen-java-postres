<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Postres</title>
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

    <h3 class="mt-3">Bienvenido de vuelta ${usuario.nombre} ${usuario.apellido}</h3>

    <form action="/postres/buscar" method="GET" class="d-flex mb-4 mt-3">
        <input type="text" name="palabra" class="form-control me-2" placeholder="Buscar postre...">
        <input type="submit" value="Filtrar" class="btn btn-primary">
    </form>

    <c:forEach items="${postres}" var="postre">
        <div class="card mb-3">
            <div class="row g-0">
                <div class="col-md-3">
                    <img src="${postre.urlImagen}" class="img-fluid" alt="${postre.nombre}">
                </div>
                <div class="col-md-9">
                    <div class="card-body">
                        <div class="d-flex justify-content-between">
                            <h4>${postre.nombre}</h4>
                            <c:if test="${postre.autor.id == usuario.id}">
                                <a href="/postres/editar/${postre.id}" class="btn btn-sm btn-outline-secondary">✏️ Editar</a>
                            </c:if>
                        </div>
                        <p>Autor: ${postre.autor.nombre} ${postre.autor.apellido}</p>
                        <p>Tiempo de preparación: ${postre.tiempoPreparacion} minutos</p>
                        <a href="/postres/${postre.id}" class="btn btn-primary btn-sm">Detalle</a>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>