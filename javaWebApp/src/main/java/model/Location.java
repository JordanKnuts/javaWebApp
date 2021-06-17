/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.Date;

/**
 *
 * @author MediaMonster
 */
public class Location {
    private int idLocation;
    private Exemplaire exemplaire;
    private Date dateDeLocation;
    private User user;
  

    public Location(int idLocation, Exemplaire exemplaire, Date dateDeLocation) {
        this.idLocation = idLocation;
        this.exemplaire = exemplaire;
        this.dateDeLocation = dateDeLocation;
    }
    public Location(int idLocation, Exemplaire exemplaire, Date dateDeLocation,User user) {
        this.idLocation = idLocation;
        this.exemplaire = exemplaire;
        this.dateDeLocation = dateDeLocation;
        this.user= user;
    }

    public Location(Exemplaire exemplaire, Date dateDeLocation) {
        this.exemplaire = exemplaire;
        this.dateDeLocation = dateDeLocation;
    }


    public Location() {
    }

    public int getIdLocation() {
        return idLocation;
    }

    public void setIdLocation(int idLocation) {
        this.idLocation = idLocation;
    }

    public Exemplaire getExemplaire() {
        return exemplaire;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    
    

    public void setExemplaire(Exemplaire exemplaire) {
        this.exemplaire = exemplaire;
    }

    public Date getDateDeLocation() {
        return dateDeLocation;
    }

    public void setDateDeLocation(Date dateDeLocation) {
        this.dateDeLocation = dateDeLocation;
    }

    @Override
    public String toString() {
        return "Location{" + "idLocation=" + idLocation + ", exemplaire=" + exemplaire + ", dateDeLocation=" + dateDeLocation + '}';
    }

    

    
    
    
    

   
    
    
}
