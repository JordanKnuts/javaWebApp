<%-- 
    Document   : homePage
    Created on : 5 nov. 2020, 23:38:24
    Author     : MediaMonster
--%>

<%@page import="java.util.Enumeration"%>
<%@page import="model.Exemplaire"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
                float: left;
                padding: 40px;
            }
            .left {
                width: 50%;
            }
            .right {
                width: 50%;
            }
            .row:after {
                content: "";
                display: table;
                clear: both;
                padding: 40px;
            }
        </style>
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    </head>
    <%
        Enumeration keys = session.getAttributeNames();
        while (keys.hasMoreElements()) {
            String key = (String) keys.nextElement();
            out.println(key + ": " + session.getValue(key) + "<br>");
        }
    %>

 
    <body >
        <div class="row">
            <h1>Bienvenue ${user.login} tu es connecté à ${bibliotheque.nom}</h1>
            <div class="column left" >
                <table border="2">
                    <tr>
                        <th>ID</th >
                        <th>Titre</th>  
                        <th>Auteur</th>
                        <th>Edition</th>
                        <th>Type</th>

                        <th>&zwnj;</th>
                        <th>&zwnj;</th>
                    </tr>


                    <c:forEach  var="exemplaire" items="${listE}">
                        <tr>
                            <td >${exemplaire.id}</td>
                            <td name="titre" value="${exemplaire.livre.titre}">${exemplaire.livre.titre}</td>
                            <td name="titre" value="${exemplaire.livre.auteur}">${exemplaire.livre.auteur}</td>
                            <td name="titre" value="${exemplaire.livre.edition}">${exemplaire.livre.edition}</td>
                            <td name="type" value="${exemplaire.type}">${exemplaire.type}</td>
                        <form action="rentServlet.do" method="GET">
                            <td><button type="submit" value="LOUER">LOUER</button></td>
                            <input name="livreID" type="hidden" value="${exemplaire.livre.idLivre}">
                        </form>
                        <form action="AvisServlet.do" method="GET">
                            <td><button type="submit" value="AVIS">AVIS</button></td>
                            <input name="livreID" type="hidden" value ="${exemplaire.livre.idLivre}">
                        </form>

                        </tr>
                    </c:forEach>


                </table>

            </div>
            <div class="column right">
                <form action="questionServlet.do" method="GET">

                    <label for="question">Qestion:</label>
                    <input type="text"  name="question" style="height:120px; width:200px;">
                    <button type="submit">PUBLIER</button>

                </form>
            </div>
            <p style="font-size:150%;color:green;">${message}</p>
            <p style="font-size:150%;color:red;">${messageE}</p>
            <p>${ messageE}</p>
        </div> 



    </body>

</html>
