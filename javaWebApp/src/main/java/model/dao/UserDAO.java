/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.List;
import model.Bibliotheque;
import model.Exemplaire;
import model.Location;
import model.QuestRep;
import model.User;

/**
 *
 * @author MediaMonster
 */
public interface UserDAO {

    
    
    public User getConnected(String login, String password);
    
    public void subscribe(User u,Bibliotheque b);
    
    public boolean estInscrit(User u, Bibliotheque b);
    
    //public void loue(int idExemplaire , User u, Date date);
    
    public List<Location> getListLocations(int id);
    
    public void addBiblio(Bibliotheque b);
    
    public void addManager(User m, Bibliotheque b);
    
    public void askQuestion(QuestRep qr);
    
    public void giveReponse(QuestRep qr);
    
    public void payeCotisation(User u, Bibliotheque b);
    
    public User getUser(String login);
    
    public boolean updateUser(User user);
    
    public void deleteExemplaire(Exemplaire e);
    
    public void retourLocation(Location l);
    //permet au bibliothecaire de valider le retour avec ou sans amende et p-e delete
    
    public void signalerAbimePerdu(Location l);
    
    
    
    
    
    
    //-----------------------------------------------
    
//    public int getIdUser(String login);
//
//    public void setLogin(String login);
//
//    public void setRole(String role);
//    
//    
//    
//    public String getPassword(String login);
    
    
    
    
    
}
