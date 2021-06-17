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
import model.Bibliotheque;
import model.QuestRep;
import model.User;

/**
 *
 * @author jknut
 */
public class reponseServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        Bibliotheque b = (Bibliotheque) req.getSession().getAttribute("bibliotheque");
        List<QuestRep> listQR = b.getListQuesRep();
        req.setAttribute("listQR", listQR);
        req.setAttribute("bi", b);
        req.getRequestDispatcher("/questionReponse.jsp").forward(req, resp);

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        User u = (User) req.getSession().getAttribute("user");
        String reponse = req.getParameter("reponse");
        String idQR = req.getParameter("idQR");
        QuestRep q = new QuestRep();
        q.setIdAuteur(u.getId());
        q.setReponse(reponse);
        q.setIdQR(Integer.parseInt(idQR));
        u.giveReponse(q);

        req.setAttribute("idQR", idQR);
        req.setAttribute("idU", u.getId());
        req.setAttribute("user", u);

        req.setAttribute("reponse", reponse);
        doGet(req,resp);
 
    }

}
