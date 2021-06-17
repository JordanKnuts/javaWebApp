/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
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
public class LocationReturnServlet extends HttpServlet {




    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        int idLoc = Integer.parseInt(req.getParameter("idLocation"));
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        User u = (User) req.getSession().getAttribute("user");
        Location l = b.getLocationById(idLoc);
        b.returnLocation(l, u);
        req.setAttribute("user", u);
        req.setAttribute("biblio", b);
        req.setAttribute("loca", l);
        req.setAttribute("idL", idLoc);
        
        req.getRequestDispatcher("test.jsp").forward(req, resp);
    }

    
    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
