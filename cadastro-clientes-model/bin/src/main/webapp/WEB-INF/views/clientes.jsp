<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head><title>Clientes</title></head>
<body>
<h2>Lista de Clientes:</h2>
<ul>
    <c:forEach var="cliente" items="${clientes}">
        <li>${cliente.nome}</li>
    </c:forEach>
</ul>
</body>
</html>
