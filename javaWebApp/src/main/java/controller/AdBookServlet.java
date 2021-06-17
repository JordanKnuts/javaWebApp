/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Bibliotheque;
import model.Livre;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author MediaMonster
 */
public class AdBookServlet extends HttpServlet {



    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
    }

 
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        
        
        
        
        if (!ServletFileUpload.isMultipartContent(request)) {
         throw new IllegalArgumentException("Request is not multipart, please 'multipart/form-data' enctype for your form.");
     }

     ServletFileUpload uploadHandler = new ServletFileUpload(new DiskFileItemFactory());
     PrintWriter writer = response.getWriter();
   
     System.out.println(new File(request.getServletContext().getRealPath("/")));
     try {
         List<FileItem> items = uploadHandler.parseRequest(request);
         
         for (FileItem item : items) {
             if (!item.isFormField()) {
                     File file = new File(request.getServletContext().getRealPath("C:/Users/jknut/OneDrive/Documents/IG/com.mycompany_ProjetPRID/drop/"), item.getName());
                     item.write(file);
                     
                     System.out.println("uploaded");
             }
         }
     } catch (FileUploadException e) {
             throw new RuntimeException(e);
     } catch (Exception e) {
             throw new RuntimeException(e);
     } finally {
        
         writer.close();
     }
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        
        //Livre( String titre, String auteur, String edition,int nbPage, float prix);
        Bibliotheque b = (Bibliotheque) request.getSession().getAttribute("bibliotheque");
        String titre = request.getParameter("titre");
        String auteur = request.getParameter("auteur");
        String edition = request.getParameter("edition");
        String type = request.getParameter("type");
        int nbPage = Integer.parseInt(request.getParameter("nbPage"));
        float prix = Float.valueOf(request.getParameter("prix"));
       
        Livre l  = new Livre();
        l.setTitre(titre);
        l.setAuteur(auteur);
        l.setEdition(edition);
        l.setNbPage(nbPage);
        l.setPrix(prix);
        
        b.addLivre(l, b, type);
        
        String message = "Ajout effectué !";
        
        request.setAttribute("message", message);
        this.getServletContext().getRequestDispatcher("/addBookPage.jsp").forward(request, response);
        
        
        
        
        
    }

    

}
