<%-- 
    Document   : adminHomePage
    Created on : 30 nov. 2020, 18:27:59
    Author     : MediaMonster
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "header/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <style>
            * {
                box-sizing: border-box;
            }
            .column {

                padding: 40px;
            }
            .left {
                width: 50%;
            }
            .right {
                width: 50%;
            }
        </style>

    </head>
    <%
        Enumeration keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            out.println(key + ": " + session.getValue(key) + "<br>");
        }
    %>
    <body>>
        <h1>Bienvenue ${user.login} tu es connecté à ${bibliotheque.nom}</h1>
        <div class="container-sm border">


            <h2>LOCATION D'UN LIVRE</h2>
            <p style="font-size:150%;color:red;">${messageE}</p>
            <table class="table table-dark">
                <thead>


                    <tr>
                        <th>ID</th>
                        <th>Titre</th>
                        <th>Auteur</th>
                        <th>Edition</th>
                        <th>Type</th>

                        <th>&zwnj;</th>
                        <th>&zwnj;</th>
                    </tr>
                </thead>
                <tbody>


                    <c:forEach  var="exemplaire" items="${listE}">
                        <tr>
                            <th scope="row">${exemplaire.id}</th>
                            <td name="titre" value="${exemplaire.livre.titre}">${exemplaire.livre.titre}</td>
                            <td name="titre" value="${exemplaire.livre.auteur}">${exemplaire.livre.auteur}</td>
                            <td name="titre" value="${exemplaire.livre.edition}">${exemplaire.livre.edition}</td>
                            <td name="type" value="${exemplaire.type}">${exemplaire.type}</td>
                    <form action="rentServlet.do" method="GET">
                        <td><button type="submit" value="LOUER">LOUER</button></td>
                        <input name="idE" type="hidden" value="${exemplaire.id}">
                        <input name="auteurLivre" type="hidden" value="${exemplaire.livre.auteur}">
                    </form>
                    <form action="AvisServlet.do" method="GET">
                        <td><button type="submit" value="AVIS">AVIS</button></td>
                        <input name="livreID" type="hidden" value ="${exemplaire.livre.idLivre}">
                    </form>
                    </tr>  
                </c:forEach>
                </tbody>
            </table>

        </div>




    </body>
</html>
