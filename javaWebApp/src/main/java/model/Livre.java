 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.util.List;
import model.dao.AbstractDAOFactory;
import model.dao.BibliothequeDAO;
import model.dao.LivreDAO;

/**
 *
 * @author MediaMonster
 */
public class Livre {
    private int idLivre,nbPage;
    private float note ;
    private String titre,auteur,edition;
    private float prix;
    private List<Avis> listAvis;
    

    public Livre( String titre, String auteur, String edition,int nbPage, float prix) {
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.prix = prix;
    }

    public Livre(int idLivre, String titre, String auteur, String edition,int nbPage, float prix, float note) {
        this.idLivre = idLivre;
        this.note = note;
        this.titre = titre;
        this.auteur = auteur;
        this.edition = edition;
        this.prix = prix;
    }


    public Livre() {
    }

    public int getIdLivre() {
        return idLivre;
    }

    public void setIdLivre(int idLivre) {
        this.idLivre = idLivre;
    }

    public float getNote() {
        return note;
    }

    public void setNote(float note) {
        this.note = note;
    }

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public String getAuteur() {
        return auteur;
    }

    public void setAuteur(String auteur) {
        this.auteur = auteur;
    }

    public String getEdition() {
        return edition;
    }

    public void setEdition(String edition) {
        this.edition = edition;
    }

    public float getPrix() {
        return prix;
    }

    public int getNbPage() {
        return nbPage;
    }

    public void setNbPage(int nbPage) {
        this.nbPage = nbPage;
    }
    
    

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public List<Avis> getListAvis() {
        
        //ABSTRACTDAO
        return listAvis;
    }

    public void setListAvis(List<Avis> listAvis) {
        this.listAvis = listAvis;
    }
    
    
    
    public void commenter(int idL,int idU,int note,String com){
        AbstractDAOFactory factory = AbstractDAOFactory.getFactory();
        LivreDAO livreDAO = factory.createLivreDAO();
        livreDAO.commenter(idL, idU, note, com);
    }

    @Override
    public String toString() {
        return "Livre{" + "idLivre=" + idLivre + ", nbPage=" + nbPage + ", note=" + note + ", titre=" + titre + ", auteur=" + auteur + ", edition=" + edition + ", prix=" + prix + ", listAvis=" + listAvis + '}';
    }

    
   
    
    

    
    
    
    
    
}
