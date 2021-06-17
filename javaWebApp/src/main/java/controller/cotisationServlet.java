/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.User;

/**
 *
 * @author jknut
 */
public class cotisationServlet extends HttpServlet {

  

    
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        Boolean paye = b.cotisationPaye(u);
        req.setAttribute("paye", paye);
        req.getRequestDispatcher("cotisationPage.jsp").forward(req, resp);
    }

   
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        
        u.payeCotisation(b);
        
        req.getRequestDispatcher("cotisationPage.jsp").forward(req, resp);
        
    }

  

}
