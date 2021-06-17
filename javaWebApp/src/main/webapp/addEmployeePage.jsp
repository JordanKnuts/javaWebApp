<%-- 
    Document   : addEmployeePage
    Created on : 3 déc. 2020, 13:27:47
    Author     : MediaMonster
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "header/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <%
            Enumeration keys = session.getAttributeNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                out.println(key + ": " + session.getValue(key) + "<br>");
            }
        %>    
    </head>
    <body>
        <p>${ListRo}</p>
        <form align="center" action="addEmployeeServlet.do" method="POST">

            <div>
                <select name="role" >
                    <c:forEach  var="role" items="${ListRo}">

                        <option name="role"   value="${role.nom}">${role.nom}</option>

                    </c:forEach>
<!--                        <input type="hidden" name="idRole" value="${role.idRole}">-->
                </select>
            </div>

            <div>
                <label for="nom">Nom</label>
                <input type="text" name="nom" required>
            </div>
            <div>
                <label for="prenom">Prenom</label>
                <input type="text" name="prenom" required>
            </div>
            <div>
                <label for="adresse">Adresse</label>
                <input type="text" name="adresse" required>
            </div>
            <div>
                <label for="telephone">Telephone</label>
                <input type="text" name="telephone" required>
            </div>
            <div>
                <label for="mail">E-mail</label>
                <input type="email" name="mail" required>
            </div>
            <div>
                <label for="login">Login</label>
                <input type="text" name="login" required>
            </div>
            <div>
                <label for="password">Password</label>
                <input type="password" name="password" required>
            </div>
            <div align="center">
                <button  type="submit" value="SUBMIT" > AJOUTER</button>
            </div>
            <p style="font-size:150%;color:green;">${message}</p>
            <p style="font-size:150%;color:red;">${messageE}</p>
        </form>
    </body>
</html>
