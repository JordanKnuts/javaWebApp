/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Location;
import model.User;

public class LocationHistoryServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Bibliotheque biblio = (Bibliotheque) req.getSession().getAttribute("bibliotheque");

        List<Location> listL = biblio.getLocationHistoryUser(user);
        Date date = new Date();
        
        req.setAttribute("user", user);
        req.setAttribute("biblio", biblio);
        req.setAttribute("listL", listL);
        req.setAttribute("date", date);
        
        req.getRequestDispatcher("locationHistoryPage.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Bibliotheque biblio = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        User u = (User) req.getSession().getAttribute("user");
        String idLoc = req.getParameter("idLoc");
        String message = " ";

        req.setAttribute("idLoc", idLoc);
        Location l = biblio.getLocationById(Integer.parseInt(idLoc));
        biblio.returnLocation(l, u);
        message = l.getExemplaire().getLivre().getTitre() + " a bien été rendu ";

        //req.getSession().setAttribute("message", message);
       // req.getRequestDispatcher("/LocationHistoryServlet.do").forward(req, resp);
        resp.sendRedirect(req.getContextPath() + "/LocationHistoryServlet.do");

    }
}
