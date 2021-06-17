/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Centre;
import model.Role;
import model.User;

/**
 *
 * @author MediaMonster
 */
public class addEmployeeServlet extends HttpServlet {

    Centre centre = new Centre();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<Role> listRole = centre.getListRole();
        req.setAttribute("ListRo", listRole);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addEmployeePage.jsp");

        requestDispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bibliotheque biblio = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        //int idRole = Integer.parseInt(req.getParameter("idRole"));
        
        User user = new User();
        User user2 = biblio.getUser(req.getParameter("login"), req.getParameter("mail"));
        if (null == user2) {
            user.setLogin(req.getParameter("login"));
            user.setAdresse(req.getParameter("adresse"));
            user.setEmail(req.getParameter("mail"));
            user.setNom(req.getParameter("nom"));
            user.setPrenom(req.getParameter("prenom"));
            user.setTelephone(req.getParameter("telephone"));
            user.setRole(biblio.getRoleByName(req.getParameter("role")));
            user.setPassword(req.getParameter("password"));
            user.subscribe(biblio);
            String message = "Le " + req.getParameter("role") + " a bien été ajouté";
            req.setAttribute("message", message);

        }else{
           String messageE = "Un utilisateur avec cet email ou ce mot de passe existe déjà ";
           req.setAttribute("message", messageE);
        }
            
          
        doGet(req, resp);

    }

}
