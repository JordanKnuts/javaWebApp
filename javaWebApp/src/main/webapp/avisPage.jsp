<%-- 
    Document   : avisPage
    Created on : 30 nov. 2020, 18:17:34
    Author     : MediaMonster
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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
            }
        %>

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
            }
        </style>    
    </head>
    <body>
        <div class="row">
            <div class="column left">
                <h2>Laisser un avis</h2> 
                <form align="left" type="submit" action="AvisServlet.do" method="POST">
                    <div>
                        <label  for="note"> Note</label>
                        <select name="note">
                            <option value = "0">0</option>
                            <option value = "1">1</option>
                            <option value = "2">2</option>
                            <option value = "3">3</option>
                            <option value = "4">4</option>
                            <option value = "5">5</option>
                            <option value = "6">6</option>
                            <option value = "7">7</option>
                            <option value = "8">8</option>
                            <option value = "9">9</option>
                            <option value = "10">10</option>
                        </select>
                    </div>
                    <div>
                        <label for="commentaire">Commentaire:</label>

                        <input type="text"  name="commentaire" style="height:120px; width:200px;">
                        <button type="submit">PUBLIER</button>

                    </div>


                </form>
            </div>
            <div class="column right">
                <h2>Liste des avis</h2> 
                <table border="2">
                    <tr>
                        <th>Auteur</th >
                        <th>Note</th>  
                        <th>Commentaire</th>
                    </tr>

                    <c:forEach  var="avis" items="${lAv}">
                        <tr>
                            <td >${avis.user.nom}</td>
                            <td >${avis.note}</td>
                            <td >${avis.commentaire}</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>

    </body>
</html>
