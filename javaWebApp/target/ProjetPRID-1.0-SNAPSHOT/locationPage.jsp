<%-- 
    Document   : locationHistoryPAge
    Created on : 10 déc. 2020, 22:14:05
    Author     : jknut
--%>

<%@page import="java.util.Date"%>
<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "header/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <%
            Enumeration keys = session.getAttributeNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                out.println(key + ": " + session.getValue(key) + "<br>");
                Date date = new Date();
            }
        %>
    </head>
    <body>
        <h1 style='align-content: center' >LOCATION EN COURS</h1>

        <div class="container-fluid border">
            <table class="table table-dark">
                <thead>
                    <tr>
                        <th scope="col">IdLocation</th>
                        <th scope="col">Titre</th>
                        <th scope="col">Date de location</th>
                        <th scope="col">Avis </th>
                        <th scope="col"> </th>
                        <th scope="col"> </th>
                    </tr>
                </thead>
                <tbody>

                    <c:forEach var="location" items="${listL}">
                        <tr>
                            <th scope="row">ID ${location.idLocation}</th>
                            <td>TITRE ${location.exemplaire.livre.titre}</td>
                            <td>DATE ${location.dateDeLocation}</td>

                    <form action="AvisServlet.do" method="GET">       
                        <td><button type="submit" value="AVIS">Avis</button></td>
                        <input name="livreID" type="hidden" value ="${location.exemplaire.livre.idLivre}">
                    </form>
                    <c:choose>
                        <c:when test="${location.exemplaire.type == 'P' && location.exemplaire.verifie == false  && location.dateDeLocation < date}">

                            <form action="LocationListServlet.do" method="POST">
                                <td> <button type="submit">Supprimer</button></td>
                                <input name="deleteLoc" type="hidden" value="${location.idLocation}">
                                <input name="dLoc" type="hidden" value="${location.idLocation}">
                            </form>

                            <c:choose>
                                <c:when test="${location.exemplaire.rendu == true}">
                                    <form action="LocationListServlet.do" method="POST">
                                        <td> <button type="submit">Verifié</button></td>
                                        <input name="verifyLoc" type="hidden" value="${location.idLocation}">
                                        <input name="vLoc" type="hidden" value="${location.idLocation}">
                                    </form>
                                </c:when>
                            </c:choose>
                        </c:when>


                    </c:choose>



                    </tr>
                </c:forEach>


                </tbody>
            </table>
        </div>
        <p style="font-size:150%;color:green;">${message}</p>
    </body>
</html>
