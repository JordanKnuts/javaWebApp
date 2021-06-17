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
public class Exemplaire {

    private String type;
    private int idExemplaire;
    private Livre livre;
    private boolean rendu;
    private boolean verifie;

    public Exemplaire(int idExemplaire, String type, Livre livre) {
        this.type = type;
        this.idExemplaire = idExemplaire;
        this.livre = livre;
    }

    public Exemplaire(String type, Livre livre) {
        this.type = type;
        this.livre = livre;
    }

    public Exemplaire(int idExemplaire, String type, Livre livre, boolean rendu, boolean verifie) {
        this.idExemplaire = idExemplaire;
        this.type = type;
        this.livre = livre;
        this.rendu = rendu;
        this.verifie = verifie;
    }

    public Exemplaire() {
    }

    public String getType() {
        return type;
    }

    public boolean isRendu() {
        return rendu;
    }

    public void setRendu(boolean rendu) {
        this.rendu = rendu;
    }

    public boolean isVerifie() {
        return verifie;
    }

    public void setVerifie(boolean verifie) {
        this.verifie = verifie;
    }
    
    

    public void setType(String type) {
        this.type = type;
    }

    public int getId() {
        return idExemplaire;
    }

    public void setIdExemplaire(int idExemplaire) {
        this.idExemplaire = idExemplaire;
    }

    public int getIdExemplaire() {
        return idExemplaire;
    }

    public Livre getLivre() {
        return livre;
    }

    public void setLivre(Livre livre) {
        this.livre = livre;
    }

    @Override
    public String toString() {
        return "Exemplaire{" + "type=" + type + ", idExemplaire=" + idExemplaire + ", livre=" + livre + ", rendu=" + rendu + ", verifie=" + verifie + '}';
    }

    

}
