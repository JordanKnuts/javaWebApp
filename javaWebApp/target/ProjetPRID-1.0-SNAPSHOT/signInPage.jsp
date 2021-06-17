<%-- 
    Document   : signInPage
    Created on : 5 nov. 2020, 23:38:24
    Author     : MediaMonster
--%>

<%@page import="java.util.Enumeration"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>


<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="bootstrap/js/jquery.js"></script>
        <script src="bootstrap/js/bootstrap.js"></script>   
        <link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap.min.css" rel="stylesheet">
        <style>
            body,html{
                height: 100%;
            }



            .view {

                height: 100%;
                padding:12%;
            }

            .login-form {
                opacity: 0.8;
                background:grey !important;
                width: 340px;
                margin: auto;
                font-size: 15px;


            }
            .login-form form {
                margin-bottom: 15px;

                box-shadow: 0px 2px 2px rgba(0, 0, 0, 0.3);
                padding: 30px;
            }
            .login-form h2 {
                margin: 0 0 15px;
            }
            .form-control {
                min-height: 38px;
                border-radius: 2px;


            }
            .btn {
                min-height: 38px;
                border-radius: 2px;
                font-size: 15px;
                font-weight: bold;
            }
            h1,h2,h3{
                font-family: 'Kaushan Script', cursive;
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

    <nav class="navbar navbar-inverse">
        <div class="container-fluid" method="GET">						

            <div class="navbar-header">
                <a class="navbar-brand" href="signInPage.jsp">Bibliotheque</a>
            </div>
        </div>
    </nav>
    <div class="view" style="background-image: url('https://img.freepik.com/free-photo/blurry-background-books-library_23-2147837255.jpg?size=626&ext=jpg'); background-repeat: no-repeat; background-size: cover; background-position: center center;">

        <body>



            <div class="login-form">
                <form action = "LoginServlet.do" method = "POST">   
                    <h2 class="text-center">Login</h2>       
                    <div class="form-group">
                        <input type="text" name="login" class="form-control" placeholder="Username" required="required">
                    </div>
                    <div class="form-group">
                        <input type="password" name="password" class="form-control" placeholder="Password" required="required">
                    </div>
                    <div class="form-group">
                        <select class="form-control form-control-sm" name="biblio" >
                            <c:forEach  var="biblio" items="${listB}">
                                <option   value="${biblio.nom}">${biblio.nom}</option>
                            </c:forEach>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-dark btn-block">Log in</button>
                    </div>
                    <div class="form-group"> <p class="text-center"><a href="InscriptionServlet.do">Create an Account</a></p></div>
                </form>




                <div>

                    <p style="font-size:200%;color:red;">${error}</p>
                    <p style="font-size:200%;color:red;">${error2}</p>

                </div>
            </div>









        </body>
    </div>


</html>
