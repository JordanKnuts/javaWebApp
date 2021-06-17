/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Centre;
import model.Exemplaire;
import model.User;

/**
 *
 * @author MediaMonster
 */
public class rentServlet extends HttpServlet {

    Centre centre = new Centre();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        String messageE = "";
        
        if (!b.cotisationPaye(u)) {
            messageE = "Vous n'avez pas payé la cotisation";
        } else if (u.getAmende() > 0) {
            messageE = "Vous avec une amende impayée";
        }
        
        req.setAttribute("messageE", messageE);
        if (u.getRole().getNom().equals("lecteur")) {
            this.getServletContext().getRequestDispatcher("/homePage.jsp").forward(req, resp);
        } else {
            this.getServletContext().getRequestDispatcher("/adminHomePage.jsp").forward(req, resp);
        }

        if (u.getAmende() < 0 && b.cotisationPaye(u)) {

            String l = req.getParameter("livreID");
            List<Exemplaire> listExe = centre.getListE(Integer.parseInt(l), (Bibliotheque) req.getSession().getAttribute("bibliotheque"));
            Exemplaire e = listExe.get(0);
            req.getSession().setAttribute("listExe", listExe);
            req.getSession().setAttribute("exemplaire", e);
            resp.sendRedirect(req.getContextPath() + "/rentPage.jsp");
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String idExe = req.getParameter("idExe");
        String dateS = req.getParameter("calendar");
        // String type = req.getParameter("type");
        String message = "MESSAGE";
        String error = "ERROR";

        User u = (User) req.getSession().getAttribute("user");
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");

        int idExemp = Integer.parseInt(idExe);
        req.setAttribute("idExe", idExemp);
        req.setAttribute("date", dateS);
        // req.setAttribute("type", type);
        //req.getRequestDispatcher("/test.jsp").forward(req, resp);

        Exemplaire exe = centre.getExemplaireById(idExemp);
        boolean getLoc = true;

        Date date = null;

        try {
            date = new SimpleDateFormat("MM/dd/yyyy").parse(dateS);
            if (exe.getType().contains("N")) {
                message = "LOCATION Num";
                // getLoc = centre.checkDispoAndAddNum(exe, date, u, b);
            } else if (exe.getType().contains("P")) {
                message = "LOCATION Phys";
                //getLoc = centre.checkDispoAndAddPhys(exe, date, u, b);
            }

            if (getLoc == true) {
//                message = "LOCATION OK";
                req.getSession().setAttribute("idExemplaire", idExe);
                req.setAttribute("message", message);
                resp.sendRedirect(req.getContextPath() + "/ServletLocation.do");

            } else if (getLoc == false) {

                message = "PAS DISPONIBLE, SELECTIONNEZ UNE AUTRE DATE SVP";
                req.setAttribute("message", message);
                this.getServletContext().getRequestDispatcher("/rentPage.jsp").forward(req, resp);

            }

        } catch (ParseException ex) {
            message = "VEUILLEZ SELECTIONNER UNE DATE VALIDE";
            req.setAttribute("message", message);
            this.getServletContext().getRequestDispatcher("/rentPage.jsp").forward(req, resp);
        }
    }

}
