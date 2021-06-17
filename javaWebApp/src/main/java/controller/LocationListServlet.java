package controller;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Location;
import model.User;

/**
 *
 * @author jknut
 */
public class LocationListServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        Bibliotheque biblio = (Bibliotheque) req.getSession().getAttribute("bibliotheque");

        List<Location> listL = biblio.getLocationsBibliotheque();
        Date date = new Date();
        req.setAttribute("user", user);
        req.setAttribute("biblio", biblio);
        req.getSession().setAttribute("listL", listL);
        req.setAttribute("date", date);
        req.setAttribute("message",(String) req.getSession().getAttribute("message"));

        req.getRequestDispatcher("locationPage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String dLoc = req.getParameter("deleteLoc");
        String vLoc = req.getParameter("verifyLoc");
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        String message = " ";



        if (null != dLoc) {
            int idLocToDel = Integer.parseInt(dLoc);
            Location l = b.getLocationById(idLocToDel);
            b.deleteExemplaire(l);
            message = "L'exemplaire du livre " + dLoc +"  --- " + l.getExemplaire().getLivre().getTitre()+ " a bien été supprimé";

        } else {
            int idLocToVerif = Integer.parseInt(vLoc);
            Location l = b.getLocationById(idLocToVerif);
            b.verifyExemplaire(l);
            message = "Le retour de l'exemplaire du livre " + l.getExemplaire().getLivre().getTitre() + " a bien été vérifié";

        }
        //req.getSession().setAttribute("message", message);
        //req.getRequestDispatcher("locationPage.jsp");
        resp.sendRedirect(req.getContextPath() + "/LocationListServlet.do");
        //this.getServletContext().getRequestDispatcher("/locationPage.jsp").forward(req, resp);
    }

}
