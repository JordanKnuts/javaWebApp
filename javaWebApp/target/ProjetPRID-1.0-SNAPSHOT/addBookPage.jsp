<%-- 
    Document   : addBookPage
    Created on : 20 déc. 2020, 12:21:01
    Author     : jknut
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ include file = "header/header.jsp" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.js"></script>
        <script src="https://rawgit.com/enyo/dropzone/master/dist/dropzone.css"></script>


        <link rel="stylesheet" href="dropzone.css">

        <title>AddBook Page</title>
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
    </head>
    <body>
        <h1>Hello World!</h1>
        <div class="row">
            <div class="column left">
                
                <form id="my-awesome-dropzone"  action="AdBookServlet.do" class="dropzone" enctype="multipart/form-data" method="POST" ></form>
                
            </div>
            <div class="column right">
                <h2>AJOUT D'UN LIVRE</h2>
                <form  type="submit" action="AdBookServlet.do" method="POST">
                    <div>
                        <label for="titre">Titre</label>
                        <input type="text" name="titre" required>
                    </div>
                    <div>
                        <label for="auteur">Auteur</label>
                        <input type="text" name="auteur" required>
                    </div>
                    <div>
                        <label for="edition">Edition</label>
                        <input type="text" name="edition" required>
                    </div>
                    <div>
                        <label for="nbPage">nbPage</label>
                        <input type="number" name="nbPage" required>
                    </div>
                    <div>
                        <label for="prix">Prix</label>
                        <input type="float" name="prix" required>
                    </div>
                    <div>
                        <label  for="type">Type</label>
                        <select name="type">
                            <option name ="type" value="P">P</option>
                            <option name ="type" value="N">N</option>
                        </select>
                    </div>



                    <div>
                        <button  type="submit" value="submit" > AJOUTER </button>
                    </div>

                </form>

            </div>
            <p style="font-size:150%;color:green;">${message}</p>


        </div> 

    </div>


</body>
</html>
