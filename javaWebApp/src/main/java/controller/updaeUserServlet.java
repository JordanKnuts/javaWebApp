/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Role;
import model.User;

/**
 *
 * @author jknut
 */
public class updaeUserServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + "/updateUserPage.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        User user = (User) req.getSession().getAttribute("user");
        //Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        String error = "";
        String msg = "";

        user.setLogin(req.getParameter("login"));
        user.setAdresse(req.getParameter("adresse"));
        user.setEmail(req.getParameter("email"));
        user.setNom(req.getParameter("nom"));
        user.setPrenom(req.getParameter("prenom"));
        user.setTelephone(req.getParameter("telephone"));
        user.setPassword(req.getParameter("password"));
        boolean exists = user.updateUser();
        if (exists == false) {
            error = "Un utilisateur avec ce login ou cet email existe déjà";
            req.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher("/updateUserPage.jsp").forward(req, resp);
        } else {
            msg ="Modification(s) effectuée(s) !" ;
            
            req.setAttribute("msg", msg);
            req.setAttribute("user", user);
            req.getRequestDispatcher("/updateUserPage.jsp").forward(req, resp);
            
        }

    }

}
