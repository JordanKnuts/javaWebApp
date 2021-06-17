/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;
import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.UserDAO;

/**
 *
 * @author MediaMonster
 */
public class User {

    private String login,password,nom,prenom,adresse,email,telephone;
    private Role role;
    private int id;
    private int amende;
    private List<Location> locations;

    public User() {
    }

    public User(int id, String nom, String prenom, String adresse, String email,String telephone, Role role,String login, String password) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.password = password;
        this.id = id;
    }
    
    public User(int id, String nom, String prenom, String adresse, String email,String telephone, Role role,String login, String password, int amende) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.password = password;
        this.id = id;
        this.amende = amende;
    }
    
    

    public User( String nom, String prenom, String adresse, String email,String telephone, Role role,String login, String password) {
        this.login = login;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.telephone = telephone;
        this.email = email;
        this.role = role;
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public int getAmende() {
        return amende;
    }

    public void setAmende(int amende) {
        this.amende = amende;
    }
    
    

    public int getId() {
        return id;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }
    
    

    public String getLogin() {
        return login;
    }


    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    
    

    public void setLogin(String login) {
        this.login = login;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    


    public User getConnected(String login, String password) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return userDAO.getConnected(login, password);

    }

//    public int getID() {
//        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
//        UserDAO userDAO = factory.createUserDAO();
//        return userDAO.getIdUser(getLogin());
//    }

    public User getUserByLogin(String login) {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return userDAO.getUser(getLogin());
    }

    public List<Location> getLocations() {
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return locations = userDAO.getListLocations(getId());
    }

    public void askQuestion(QuestRep qr){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        userDAO.askQuestion(qr);
    }
    public void giveReponse(QuestRep qr){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        userDAO.giveReponse(qr);
    }
    
    public void payeCotisation(Bibliotheque b){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        userDAO.payeCotisation(this, b);
    }
    
    public void subscribe( Bibliotheque b){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        userDAO.subscribe(this, b);
    }
    
    public boolean updateUser(){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return userDAO.updateUser(this);
    }
    public boolean estInscrit(Bibliotheque b){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        UserDAO userDAO = factory.createUserDAO();
        return userDAO.estInscrit(this, b);
    }
    
   

    @Override
    public String toString() {
        return "User{" + "login=" + login + ", password=" + password + ", nom=" + nom + ", prenom=" + prenom + ", adresse=" + adresse + ", email=" + email + ", telephone=" + telephone + ", role=" + role + ", id=" + id + ", amende=" + amende + ", locations=" + locations + '}';
    }

    
    
    

}
