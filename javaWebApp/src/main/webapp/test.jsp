<%-- 
    Document   : Test
    Created on : 3 déc. 2020, 16:05:13
    Author     : MediaMonster
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page  import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello Test!</h1>
        <form action="disconnectionServlet.do" method="POST">
            <input name="titre" type="text">
            <input type="submit">
        </form>


        <div>
            <h1>${empty map}</h1>

    </div>
    <table border="1">
        <thead>
            <c:forEach var="entry" items="${map}">   
                <tr>
                    <th>Titre</th>
                    <th>Nombre</th>
                </tr>
            </thead>
            <tbody>
                <tr>
                    <td><c:out value="${entry.key}"/></td>
                    <td><c:out value="${entry.value}"/></td>
                </tr>
            </c:forEach>
        </tbody>
    </table>



</body>
</html>
