/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.BibliothequeDAO;

/**
 *
 * @author MediaMonster
 */
public class Bibliotheque {

    private String nom, adresse;
    private User manager;
    private int id;

    private List<Exemplaire> listLivre;
    private List<User> listUser;

    public Bibliotheque(int id, String nom, String adresse, User manager) {
        this.nom = nom;
        this.adresse = adresse;
        this.manager = manager;
        this.id = id;
    }

    public Bibliotheque(String nom, String adresse, User manager) {
        this.nom = nom;
        this.adresse = adresse;
        this.manager = manager;
    }

    public Bibliotheque(int id, String nom) {
        this.nom = nom;
        this.id = id;
    }

    public Bibliotheque() {

    }

    public String getNom() {
        return nom;
    }

    public String getAdresse() {
        return adresse;
    }

    public User getManager() {
        return manager;
    }

    public int getId() {
        return id;
    }

   

    public List<User> getListUser() {
        //ABSTRACT
        return listUser;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setManager(User manager) {
        this.manager = manager;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public User getUser(String login,String email){
         AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getUser(login, email);
    }
    
     public List<Exemplaire> getListLivre() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        listLivre = bibliothequeDAO.getListeDeLivre(this);
        return listLivre;
    }

    public Livre getLivreById(int idL) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getLivreById(idL);

    }

    public Role getRoleByName(String s) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getRoleByName(s);

    }

    public void addLivre(Livre l, Bibliotheque b, String type) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.addLivre(l, b, type);
    }

    public List<Location> getLocationHistoryUser(User u) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getHistoricLocationsUser(u);

    }

   

    public List<Location> getLocationsBibliotheque() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getLocationsBibliotheque(this);

    }

    public List<QuestRep> getListQuesRep() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getQuestRep();
    }

    public boolean cotisationPaye(User u) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.cotisationPaye(u, this);
    }

    public void updateBiblio(Bibliotheque b) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.updateBiblio(b);
    }

    public Location getLocationById(int idL) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getLocationById(idL);
    }

    public void returnLocation(Location l, User u) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.returnBook(l, u);
    }

    public void returnNumericBook(User us) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.returnNumericBook(us);
    }

    public void updateCotisation() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.updateCotisation();
    }

    public List<Avis> listAvisLivre(int idL) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getListAvis(idL);
    }

    public void deleteExemplaire(Location l) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.deleteExemplaire(l);

    }

    public void verifyExemplaire(Location l) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.verifyExemplaire(l);
    }

    @Override
    public String toString() {
        return "Bibliotheque{" + "nom=" + nom + ", adresse=" + adresse + ", manager=" + manager + ", id=" + id + ", listLivre=" + listLivre + ", listUser=" + listUser + '}';
    }

}
