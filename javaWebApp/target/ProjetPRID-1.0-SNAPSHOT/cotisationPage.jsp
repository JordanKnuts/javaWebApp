<%-- 
    Document   : cotisationPage
    Created on : 8 déc. 2020, 16:07:18
    Author     : jknut
--%>

<%@page import="model.Bibliotheque"%>
<%@page import="model.User"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "header/header.jsp" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Cotisation</title>
    </head>
    <body>

        <c:choose>
            <c:when test="${paye==false}">
                <form action="cotisationServlet.do" method="POST">
                    <button>Payer via Bancontact</button>
                    <button>Payer via PayPal</button>
                    <button>Payer via Mastercard</button>
                </form>

            </c:when>
            <c:otherwise>
                <h2 style="color:green">Cotisation payée !</h2>
            </c:otherwise>
        </c:choose>
    </body>
</html>
