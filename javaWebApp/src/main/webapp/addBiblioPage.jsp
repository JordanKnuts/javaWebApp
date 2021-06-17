<%-- 
    Document   : addBiblioPage
    Created on : 9 déc. 2020, 16:46:57
    Author     : jknut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file = "header/header.jsp" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
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
    </style>
    <body>
        <div class="d-flex p-12">
            <div class="row">
                <div class="column left">
                    <h2>AJOUTER BIBLIOTHEQUE</h2>
                    <button btn btn-dark type="button"  href="addEmployeeServlet.do">Ajouter nouveau manager</button>
                    <p>&nbsp</p>
                    <form action="AddBiblioServlet.do" method="POST">
                        <select name="manager" >
                            <c:forEach  var="user" items="${listM}">
                                <option   value="${user.nom}">${user.nom}</option>
                            </c:forEach>
                        </select>
                        
                        <div>
                            <label for="nomBiblio">Nom de la bibliotheque</label>
                            <input type="texte" name="nomBiblio" required>

                        </div>      
                        <div>
                            <label for="adresseBiblio ">Adresse :</label>
                            <input type="texte" name="adresseBiblio">

                        </div>   

                        <button  type="submit" value="SUBMIT" > AJOUTER</button>
                        <p style="font-size:150%;color:green;">${message}</p>
                    </form>
                </div>


                <div class="column right">
                    <h2>MODIFIER BIBLIOTHEQUE</h2>
                    <form  type="submit" action="UpdateBiblioServlet.do" method="POST">
                        <div>
                            <label for="nom">Nom</label>
                            <input type="text" name="nom" value="${biblio.nom}" required>
                        </div>
                        <div>
                            <label for="adresse">Adresse</label>
                            <input type="text" name="adresse" value="${biblio.adresse}" required>
                        </div>
                        <div>
                            <button btn btn-dark  type="submit" value="submit" > MODIFIER </button>
                        </div>
                    </form>
                    <p style="font-size:150%;color:green;">${messageU}</p>
                </div>
            </div>
        </div>  
    </body>
</html>
