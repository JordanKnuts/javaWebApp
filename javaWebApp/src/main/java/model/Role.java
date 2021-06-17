/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author MediaMonster
 */
public class Role {

    private int idRole;
    private String nom;

    public Role(int idRole, String nom) {
        this.idRole = idRole;
        this.nom = nom;
    }

    public Role(String nom) {
        this.nom = nom;
    }
    
    public Role(){};

    
    public int getIdRole() {
        return idRole;
    }

    public void setIdRole(int idRole) {
        this.idRole = idRole;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    @Override
    public String toString() {
        return "Role{" + "idRole=" + idRole + ", nom=" + nom + '}';
    }
    
    

}
