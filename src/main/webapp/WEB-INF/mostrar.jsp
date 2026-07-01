<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>${postre.nombre}</title>
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

    <h3 class="mt-3">${postre.nombre}</h3>
    <div class="row">
        <div class="col-md-4">
            <img src="${postre.urlImagen}" class="img-fluid" alt="${postre.nombre}">
        </div>
        <div class="col-md-8">
            <p><strong>Autor:</strong> ${postre.autor.nombre} ${postre.autor.apellido}</p>
            <p><strong>Ingredientes:</strong> ${postre.ingredientes}</p>
            <p><strong>Instrucciones:</strong> ${postre.instrucciones}</p>
            <p><strong>Tiempo de preparación:</strong> ${postre.tiempoPreparacion} minutos</p>

            <!-- Botón Me gusta (Oro): revisa si el usuario ya dio like -->
            <c:set var="yaDioLike" value="false"/>
            <c:forEach items="${postre.usuariosQueGustan}" var="u">
                <c:if test="${u.id == usuario.id}">
                    <c:set var="yaDioLike" value="true"/>
                </c:if>
            </c:forEach>

            <c:choose>
                <c:when test="${yaDioLike}">
                    <button class="btn btn-success" disabled>¡Te gusta!</button>
                </c:when>
                <c:otherwise>
                    <a href="/postres/megusta/${usuario.id}/${postre.id}" class="btn btn-outline-success">Me gusta</a>
                </c:otherwise>
            </c:choose>

            <!-- Eliminar solo si es el autor (Oro) -->
            <c:if test="${postre.autor.id == usuario.id}">
                <form action="/postres/borrar/${postre.id}" method="POST" style="display:inline">
                    <input type="hidden" name="_method" value="DELETE">
                    <input type="submit" value="Eliminar" class="btn btn-danger">
                </form>
            </c:if>

            <!-- Lista de usuarios que dieron me gusta (Oro) -->
            <h5 class="mt-4">Usuarios a los que les gusta este postre:</h5>
            <ul>
                <c:forEach items="${postre.usuariosQueGustan}" var="u">
                    <li>${u.nombre} ${u.apellido}</li>
                </c:forEach>
            </ul>
        </div>
    </div>
    <a href="/postres" class="btn btn-secondary mt-3">← Volver</a>
</div>
</body>
</html>