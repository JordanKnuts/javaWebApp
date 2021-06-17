<%-- 
    Document   : inscriptionPage
    Created on : 13 nov. 2020, 15:50:27
    Author     : MediaMonster
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>   
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <%
        Enumeration keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            out.println(key + ": " + session.getValue(key) + "<br>");
        }
    %>

    <nav class="navbar navbar-inverse">
        <div class="container-fluid" method="GET">						

            <div class="navbar-header">
                <a class="navbar-brand" href="signInPage.jsp">Bibliotheque</a>
            </div>
        </div>
    </nav>

    <body>
        <div align ="justify">
            <h1 align="center">Inscription</h1>
            <form align="center" type="submit" action="InscriptionServlet.do" method="POST">
                <div class="form-row">
                    <select name="biblio" >
                        <c:forEach  var="biblio" items="${listB}">
                            <option   value="${biblio.nom}">${biblio.nom}</option>
                        </c:forEach>
                    </select>
                    <div class="form-group col-md-6">
                        <label for="nom">Nom</label>
                        <input type="text" name="nom" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="prenom">Prenom</label>
                        <input type="text" name="prenom" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="adresse">Adresse</label>
                        <input type="text" name="adresse" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="telephone">Telephone</label>
                        <input type="text" name="telephone" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="mail">E-mail</label>
                        <input type="email" name="mail" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="login">Login</label>
                        <input type="text" name="login" required>
                    </div>
                    <div class="form-group col-md-6">
                        <label for="password">Password</label>
                        <input type="password" name="password" required>
                    </div>
                </div>



                <button  type="submit" class="btn btn-dark" value="SUBMIT" > S'ENREGISTRER</button>

                <p style="font-size:200%;color:red;">${error}</p>
            </form>
        </div> 
    </body>
</html>
