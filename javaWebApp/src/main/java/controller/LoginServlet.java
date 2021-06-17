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
import model.Exemplaire;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.mysqldao.MySqlDAOFactory;
import model.dao.mysqldao.MySqlUserDAO;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author MediaMonster
 */
public class LoginServlet extends HttpServlet {

    Centre centre = new Centre();

    @Override
    public void init() throws ServletException {
        if (AbstractDAOFactory.getFactory() == null) {
            AbstractDAOFactory.setFactory(MySqlDAOFactory.getInstance());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        String login = req.getParameter("login");
        String password = req.getParameter("password");
        String bi = req.getParameter("biblio");

        //Bibliotheque bibliotheque =(Bibliotheque) req.getAttribute("biblio");
        Bibliotheque bibliotheque = centre.getBibliothequeByName(bi);
        //String bi = bibliotheque.getNom();

        List<Exemplaire> listE = bibliotheque.getListLivre();

        String error = null;
        boolean inscrit = true;
        //MySqlUserDAO msuDAO = new MySqlUserDAO();

        //User user = null;
        User user = new User().getConnected(login, password);

        RequestDispatcher requestDispatcher = null;
        if (user != null) {
            if (user.getRole().getNom().equals("lecteur")) {
                inscrit = user.estInscrit(bibliotheque);
            } else {
                inscrit = true;
            }
            if (inscrit) {

                req.getSession().setAttribute("user", user);
                req.getSession().setAttribute("bibliotheque", bibliotheque);
                bibliotheque.updateCotisation();
                bibliotheque.returnNumericBook(user);
                if (!user.getRole().getNom().equals("lecteur")) {
                    req.getServletContext().setAttribute("listE", listE);
                    requestDispatcher = req.getRequestDispatcher("adminHomePage.jsp");

                } else {
                    req.getServletContext().setAttribute("listE", listE);
                    requestDispatcher = req.getRequestDispatcher("homePage.jsp");
                }
            } else {
                error = "Vous n'êtes pas inscrit dans cette bibliotheque";
                req.setAttribute("error", error);
                req.setAttribute("user", user);
                req.setAttribute("bibli", bibliotheque);
                requestDispatcher = req.getRequestDispatcher("signInPage.jsp");
            }

        } else if (login != null) {
            error = "Mot de passe ou login erroné";
            req.setAttribute("error", error);
            requestDispatcher = req.getRequestDispatcher("signInPage.jsp");
        }

        requestDispatcher.forward(req, resp);

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<Bibliotheque> listB = centre.getListB();
        req.getServletContext().setAttribute("listB", listB);
        req.getRequestDispatcher("signInPage.jsp").forward(req, resp);

    }

}
