/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import model.dao.AbstractDAOFactory;
import model.dao.LivreDAO;

/**
 *
 * @author MediaMonster
 */
public class Avis {
    
    private User user;
    private double note;
    private String commentaire;

    public Avis(User u, double note, String commentaire) {
        this.user = u;
        this.note = note;
        this.commentaire = commentaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
   
    

    public double getNote() {
        return note;
    }

    public void setNote(double note) {
        this.note = note;
    }

    public String getCommentaire() {
        return commentaire;
    }

    public void setCommentaire(String commentaire) {
        this.commentaire = commentaire;
    }
    
    public void commenter(int idL , int idU, int note, String com){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        LivreDAO livreDAO = factory.createLivreDAO();
        livreDAO.commenter(idL, idU, note, com);
    }

    @Override
    public String toString() {
        return "Avis{" + "user=" + user + ", note=" + note + ", commentaire=" + commentaire + '}';
    }
    
    
    
    
}
