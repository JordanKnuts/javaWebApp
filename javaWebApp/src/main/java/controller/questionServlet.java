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
import model.QuestRep;
import model.User;

/**
 *
 * @author jknut
 */
public class questionServlet extends HttpServlet {

  
  


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        String question = req.getParameter("question");
        String message = "Question envoyée !";  
        QuestRep q = new QuestRep();    
        q.setIdAuteur(u.getId());
        q.setQuestion(question);
        u.askQuestion(q);
        req.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/homePage.jsp").forward(req, resp);
    }

 
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        
    }


    
}
