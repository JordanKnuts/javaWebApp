<%-- 
    Document   : rentPage
    Created on : 3 déc. 2020, 14:42:20
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
        <link rel="stylesheet" href="http://code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
        <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
        <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
        <style>
            * {
                box-sizing: border-box;
            }

            /* Create two unequal columns that floats next to each other */
            .column {
                float: left;
                padding: 30px;
            }

            .left {
                width: 50%;
            }

            .right {
                width: 50%;
            }

            /* Clear floats after the columns */
            .row:after {
                content: "";
                display: table;
                clear: both;
            }
        </style>
        <%
            Enumeration keys = session.getAttributeNames();
            while (keys.hasMoreElements()) {
                String key = (String) keys.nextElement();
                out.println(key + ": " + session.getValue(key) + "<br>");
            }
        %>

        <script>
            $(function () {
                $("#calendar").datepicker();
            });
        </script>
    </head>
    <body>
        <form action="rentServlet.do"  method="POST">
            <div class="row">

                <div class="column left" >
                    <h1>CHOOSE A BOOK</h1>
                    <table border="2">
                        <tr>
                            
                            <th>Titre</th>
                            <th>Auteur</th>
                            <th>Edition</th>
                            <th>Type</th>
                            <th>&zwnj;</th>
                        </tr>
                        <tr>
                            
                            <td name="titre" value="${exemplaire.livre.titre}">${exemplaire.livre.titre}</td>
                            <td name="titre" value="${exemplaire.livre.auteur}">${exemplaire.livre.auteur}</td>
                            <td name="titre" value="${exemplaire.livre.edition}">${exemplaire.livre.edition}</td>
                            <td >
                                <select name="idExe">
                                    <c:forEach  var="ex" items="${listExe}">
                                        <option value="${ex.id}">${ex.type}</option>
                                    </c:forEach>
                                </select>
                            </td>
                            <td>                               
                                <input type="submit" value="LOUER">
                            </td>
                        </tr>
                    </table>
                </div>
                <div class="column right">
                    <h1>CHOOSE A DATE</h1>
                    <input  name="calendar" type="text"  id="calendar">
                </div>

            </div>
            <p style="font-size:200%;color:red;">${message}</p>
        </form>
    </body>
</html>
