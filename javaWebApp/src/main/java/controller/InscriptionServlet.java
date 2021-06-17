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
import model.dao.AbstractDAOFactory;
import model.dao.mysqldao.MySqlDAOFactory;
import model.dao.mysqldao.MySqlUserDAO;

/**
 *
 * @author MediaMonster
 */
public class InscriptionServlet extends HttpServlet {

    Centre centre = new Centre();
    
    @Override
    public void init() throws ServletException {
        if (AbstractDAOFactory.getFactory() == null) {
            AbstractDAOFactory.setFactory(MySqlDAOFactory.getInstance());
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
      List<Bibliotheque> listB = (List<Bibliotheque>)req.getServletContext().getAttribute("listB");
      req.setAttribute("listB",listB);
      RequestDispatcher dispatcher  = req.getRequestDispatcher("inscriptionPage.jsp");
      dispatcher.forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String b = req.getParameter("biblio");
        Bibliotheque biblio = centre.getBibliothequeByName(b);
        User user = new User();
        User user2 = biblio.getUser(req.getParameter("login"), req.getParameter("mail"));
        RequestDispatcher dispatcher = null;
        String error = "";
        if (user2 == null) {
            user.setLogin(req.getParameter("login"));
            user.setAdresse(req.getParameter("adresse"));
            user.setEmail(req.getParameter("mail"));
            user.setNom(req.getParameter("nom"));
            user.setPrenom(req.getParameter("prenom"));
            user.setTelephone(req.getParameter("telephone"));
            user.setRole(new Role(4, "lecteur"));
            user.setPassword(req.getParameter("password"));
            user.subscribe(biblio);
            dispatcher = req.getRequestDispatcher("signInPage.jsp");
            dispatcher.forward(req, resp);

        } else {
            error = "UN UTILISATEUR AVEC CE LOGIN OU CET EMAIL EXISTE DEJA";
            req.setAttribute("error", error);
            this.getServletContext().getRequestDispatcher("/inscriptionPage.jsp").forward(req, resp);
            //dispatcher = req.getRequestDispatcher("inscriptionPage.jsp");

        }

        

    }

}
