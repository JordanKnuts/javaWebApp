/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Centre;

/**
 *
 * @author jknut
 */
public class disconnectionServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {

        RequestDispatcher  requestDispatcher = req.getRequestDispatcher("test.jsp");
        requestDispatcher.forward(req, resp);

//        HttpSession session = req.getSession();
//        session.invalidate();
//        req.getRequestDispatcher("LoginServlet.do").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Centre c = new Centre();
        String s = req.getParameter("titre");
        Map<String, Integer> map = new HashMap<>();
        map = c.getListLivreContains(s);
        //MAP VIDE ?????
        req.setAttribute("titre", s);
        req.setAttribute("map", map);
        RequestDispatcher  requestDispatcher = req.getRequestDispatcher("test.jsp");
        requestDispatcher.forward(req, resp);
    }

}
