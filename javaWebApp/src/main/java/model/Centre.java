/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebService;

import model.dao.AbstractDAOFactory;
import model.dao.BibliothequeDAO;

/**
 *
 * @author MediaMonster
 */

//@WebService
public class Centre {

    private List<Exemplaire> listE = new ArrayList<>();
    private List<Bibliotheque> list = new ArrayList<>();

    public Centre() {
    }

    public List<Bibliotheque> getListB() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        list = bibliothequeDAO.getListBibliotheque();
        return list;
    }

    public Bibliotheque getBibliothequeByName(String s) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getBibliothequeByName(s);
    }

    public List<Role> getListRole() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getListRole();
    }

    public List<Exemplaire> getListE(int idL, Bibliotheque b) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getListeExemplaireDispo(idL, b);

    }

    public boolean checkDispoAndAddNum(Exemplaire ex, Date date, User u, Bibliotheque b) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.checkDispoAndAddNum(ex, date, u, b );
    }
    
    public boolean checkDispoAndAddPhys(Exemplaire ex, Date date, User u, Bibliotheque b) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.checkDispoAndAddPhys(ex, date, u, b);
    }
    
    public Exemplaire getExemplaireById(int idEx){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getExemplaireById(idEx);
    }
    //@WebMethod(operationName="listLivre")
    //@WebParam(name = "name")
    public Map<String,Integer> getListLivreContains(String s){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getListLivreContains(s);
    }
    
    public void addBiblio(Bibliotheque b){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        bibliothequeDAO.addBibliotheque(b);
    }
    
    public List<User> getAllManager(){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        BibliothequeDAO bibliothequeDAO = factory.createBibliothequeDAO();
        return bibliothequeDAO.getallManager();
    }
   

//    public User getConnected(String login,String password){
//        
//    }
}
