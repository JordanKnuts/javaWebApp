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
import model.Centre;
import model.Location;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.mysqldao.MySqlDAOFactory;

/**
 *
 * @author MediaMonster
 */
public class ServletLocation extends HttpServlet {

    Centre centre = new Centre();

    @Override
    public void init() throws ServletException {
        if (AbstractDAOFactory.getFactory() == null) {
            AbstractDAOFactory.setFactory(MySqlDAOFactory.getInstance());
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        RequestDispatcher requestDispatcher = null;
        
        
        User u = (User)req.getSession().getAttribute("user");
        List<Location> listL = u.getLocations();
        int idExemplaire = (Integer)(req.getSession().getAttribute("idExemplaire"));
        String mess = (String)req.getSession().getAttribute("message");
        
        req.setAttribute("listL", listL);
        req.setAttribute("userName", u.getNom());
        req.setAttribute("idEx", idExemplaire);
        req.setAttribute("message", mess);
        if(u.getRole().getNom() =="lecteur"){
             requestDispatcher = req.getRequestDispatcher("test.jsp");
        }else{
             requestDispatcher = req.getRequestDispatcher("test.jsp");
        }
       

        requestDispatcher.forward(req, resp);
        
        
       
    }

}
