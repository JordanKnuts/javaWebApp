<%-- 
    Document   : updateUserPage
    Created on : 17 déc. 2020, 22:51:36
    Author     : jknut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file = "header/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <form type="submit" action="updaeUserServlet.do" method="POST">
            <div class="form-row">
                <div class="form-group col-md-6">
                    <label for="nom">Nom</label>
                    <input type="text" class="form-control" name="nom" value="${user.nom}">
                </div>
                <div class="form-group col-md-6">
                    <label for="prenom">Prenom</label>
                    <input type="text" class="form-control" name="prenom" value="${user.prenom}">
                </div>
                <div class="form-group col-md-6">
                    <label for="adresse">Addresse</label>
                    <input type="text" class="form-control" name="adresse" value="${user.adresse}">
                </div>
                <div class="form-group col-md-6">
                    <label for="login">Login</label>
                    <input type="text" class="form-control" name="login" value="${user.login}">
                </div>
                <div class="form-group col-md-6">
                    <label for="email">Email</label>
                    <input type="email" class="form-control" name="email" value="${user.email}">
                </div>
                <div class="form-group col-md-6">
                    <label for="telephone">Telephone</label>
                    <input type="text" class="form-control" name="telephone" value="${user.telephone}">
                </div>
                <div class="form-group col-md-6">
                    <label for="password">Password</label>
                    <input type="password" class="form-control" name="password" value   ="${user.password}">
                </div>
            </div>




            <button type="submit" class="btn btn-dark">Enregistrer</button>
        </form>
        <p style="font-size:200%;color:red;">${requestScope.error}</p>
        <p style="font-size:200%;color:green;">${requestScope.msg}</p>
        

    </body>
</html>
