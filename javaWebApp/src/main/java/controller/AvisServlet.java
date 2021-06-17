/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Avis;
import model.Bibliotheque;
import model.Livre;
import model.User;
import model.dao.AbstractDAOFactory;
import model.dao.mysqldao.MySqlDAOFactory;

/**
 *
 * @author MediaMonster
 */
public class AvisServlet extends HttpServlet {
    
    @Override
    public void init() throws ServletException {
        if (AbstractDAOFactory.getFactory() == null) {
            AbstractDAOFactory.setFactory(MySqlDAOFactory.getInstance());
        }
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        
        int note = Integer.parseInt(req.getParameter("note"));
        String commentaire = req.getParameter("commentaire");
        
        int idLivre =Integer.parseInt((String)req.getSession().getAttribute("idL"));
        User u = (User)req.getSession().getAttribute("user");
        Bibliotheque bi = (Bibliotheque)req.getSession().getAttribute("bibliotheque");
        Livre l = bi.getLivreById(idLivre);
        l.commenter(idLivre, u.getId(), note, commentaire);
        req.getRequestDispatcher("homePage.jsp").forward(req, resp);
        
        
    }
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Bibliotheque bi = (Bibliotheque)req.getSession().getAttribute("bibliotheque");
        String exem = req.getParameter("livreID");
        int idL = Integer.parseInt(exem);
        List<Avis> lAv = bi.listAvisLivre(idL);
        
        req.getServletContext().setAttribute("lAv", lAv); 
        req.getServletContext().setAttribute("idL", idL); 
        req.getRequestDispatcher("avisPage.jsp").forward(req, resp);
        
        
    }
    
}
