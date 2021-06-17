<%-- 
    Document   : questionReponse
    Created on : 7 déc. 2020, 13:05:34
    Author     : jknut
--%>

<%@page import="java.util.Enumeration"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
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

        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="container-fluid border">
            <table class="table table-dark" >
                <thead>
                    <tr>
                        <th scope="col">ID</th>
                        <th scope="col">Question</th>
                        <th scope="col">Reponse</th>
                            <c:if test="${qr.reponse==null}">
                            <th scope="col">&zwnj;</th>
                            </c:if>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="qr" items="${listQR}">
                        <tr>
                            <th scope="row">${qr.idQR}</th>
                            <td>${qr.question}</td>
                            <c:choose>
                                <c:when test="${qr.reponse==null && user.role.nom !='lecteur'}">
                            <form action="reponseServlet.do" method="POST">
                                <div>
                                    <td>
                                        <input type="text"  name="reponse" style="width:100px;">
                                    </td>
                                    <td>
                                        <button type="submit">REPONDRE</button>
                                        <input type="hidden" name="idQR" value="${qr.idQR}">
                                    </td>
                                </div>
                            </form>

                        </c:when>
                        <c:otherwise>
                            <td>${qr.reponse}</td>
                        </c:otherwise>
                    </c:choose>

                    </tr>
                </c:forEach>

                </tbody>
            </table>
        </div>
        <%--
        <table border="2" width="2" cellspacing="2" cellpadding="2">
            <thead>
                <tr>
                    <th>ID</th>
                    <th>Question</th>
                    <th>Reponse</th>
                    <th>&zwnj;</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="qr" items="${listQR}">
                    <tr>
                        <td>${qr.idQR}</td>
                        <td>${qr.question}</td>
                        <c:choose>
                            <c:when test="${qr.reponse==null}">
                        <form action="reponseServlet.do" method="POST">
                            <div>
                                <td>
                                    <input type="text"  name="reponse" style="width:100px;">
                                </td>
                                <td>
                                    <button type="submit">REPONDRE</button>
                                    <input type="hidden" name="idQR" value="${qr.idQR}">
                                </td>
                            </div>
                        </form>

            </c:when>
            <c:otherwise>
                <td>${qr.reponse}</td>
            </c:otherwise>
        </c:choose>

    </tr>
</c:forEach>

</tbody>
</table>  


        --%>

    </body>
</html>
