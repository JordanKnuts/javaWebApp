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
import model.User;

/**
 *
 * @author jknut
 */
public class AddBiblioServlet extends HttpServlet {

    Centre centre = new Centre();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        List<User> listM = centre.getAllManager();
        //req.setAttribute("listM", listM);
        Bibliotheque biblio =(Bibliotheque) req.getSession().getAttribute("bibliotheque");
        req.getSession().setAttribute("listM", listM);
        req.setAttribute("biblio", biblio);
        RequestDispatcher requestDispatcher = req.getRequestDispatcher("addBiblioPage.jsp");

        requestDispatcher.forward(req, resp);
        

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bibliotheque b = new Bibliotheque();
        User u = new User();

        b.setAdresse(req.getParameter("adresseBiblio"));
        b.setNom(req.getParameter("nomBiblio"));
        b.setManager(u.getUserByLogin(req.getParameter("manager")));

        centre.addBiblio(b);

        String message = "Bibliotheque " + b.getNom() + " a bien été ajoutée !";

        req.setAttribute("message", message);

        doGet(req, resp);
    }

}
