<%-- 
    Document   : header
    Created on : 2 déc. 2020, 12:43:34
    Author     : MediaMonster
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>

<html>
    <head>
        <title>TODO supply a title</title>
        <meta charset="UTF-8">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
        <script src="bootstrap/js/jquery.js"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>   
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <style>


        </style>


    </head>
    <body>
        <!--  

    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
       
        <a class="navbar-brand" href="#">Logo</a>

        
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="#">Link 1</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">Link 2</a>
            </li>

            
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                    Dropdown link
                </a>
                <div class="dropdown-menu">
                    <a class="dropdown-item" href="#">Link 1</a>
                    <a class="dropdown-item" href="#">Link 2</a>
                    <a class="dropdown-item" href="#">Link 3</a>
                </div>
            </li>
        </ul>
    </nav>
        -->

        <nav class="navbar navbar-expand-sm bg-dark navbar-dark sticky-top">
            <div class="container-fluid" method="GET">
                <c:choose>
                    <c:when test="${user.role.nom =='lecteur'}">
                      <!--  <div class="navbar-header">
                            <a class="navbar-brand" href="homePage.jsp">Bibliotheque</a>
                        </div>
                      -->
                        <ul class="nav navbar-nav">
                            <li class="nav-item"><a href="homePage.jsp">Louer livre</a></li>
                            <li class="nav-item"><a href="reponseServlet.do">Consulter questions/réponses</a></li>
                        
                    </c:when>
                    <c:otherwise>
                     <!--   <div class="navbar-header">
                            <a class="navbar-brand" href="adminHomePage.jsp">Bibliotheque</a>
                        </div>
                     -->
                        <ul class="nav navbar-nav">
                            <li class="nav-item"><a href="adminHomePage.jsp">Louer Livre</a></li>
                            <li class="nav-item"><a href="reponseServlet.do">Gérer questions/réponses</a></li>
                        
                    </c:otherwise>
                </c:choose>
               


                    <c:if test="${user.role.nom !='lecteur'}">
                        <li class="nav-item"><a href="addEmployeeServlet.do">Gerer employers</a></li>
                        <li class="nav-item"><a href="LocationListServlet.do">Gerer locations bibliotheque</a></li>
                        </c:if>
                        <c:if test="${user.role.nom =='manager general'}">
                        <li class="nav-item"><a href="AddBiblioServlet.do" >Gerer bibliotheques</a></li>
                        <li class="nav-item"><a href="addBookPage.jsp" >Ajouter livre</a></li>
                        </c:if>

                    <li class="nav-item dropdown">
                        <a class=" dropdown-toggle"  id="navbardrop" data-toggle="dropdown">
                            Compte
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item"  href="LocationHistoryServlet.do">Gérer mes locations/réservations</a>
                            <a class="dropdown-item"  href="updaeUserServlet.do">Gérer mes données personnelles</a>
                            <a class="dropdown-item"  href="cotisationServlet.do" >Gérer cotisation</a>
                        </div>
                    </li>
                    
                </ul>

                <ul class="nav navbar-nav navbar-right">
                    <li class="nav-item"><a href="disconnectionServlet.do"><span class="glyphicon glyphicon-log-out"></span> Deconnexion</a></li>
                </ul>
            </div>
        </nav>




    </body>
</html>
