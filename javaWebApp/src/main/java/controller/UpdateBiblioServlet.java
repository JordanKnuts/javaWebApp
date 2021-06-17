/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;

/**
 *
 * @author jknut
 */
public class UpdateBiblioServlet extends HttpServlet {

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        String nom = req.getParameter("nom");
        String adresse = req.getParameter("adresse");

        Bibliotheque biblio = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        
        biblio.setAdresse(adresse);
        biblio.setNom(nom);
        
        biblio.updateBiblio(biblio);
        
        String messageU = "Bibliotheque modifiée !";
        
        req.setAttribute("messageU", messageU);
        
   
         this.getServletContext().getRequestDispatcher("/addBiblioPage.jsp").forward(req, resp);

        
    }

}
