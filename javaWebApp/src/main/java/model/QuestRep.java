/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

/**
 *
 * @author jknut
 */
public class QuestRep {

    private int idQR, idAuteur;
    private String question, reponse;

    public QuestRep(int idQR, String question, String reponse) {
        this.idQR = idQR;
        this.question = question;
        this.reponse = reponse;
    }

    public QuestRep(int idAuteur, String questRep) {
        this.idAuteur = idAuteur;
        this.question = questRep;
    }

    

    public QuestRep() {
    }

    public int getIdQR() {
        return idQR;
    }

    public void setIdQR(int idQR) {
        this.idQR = idQR;
    }

    public int getIdAuteur() {
        return idAuteur;
    }

    public void setIdAuteur(int idAuteur) {
        this.idAuteur = idAuteur;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public String getReponse() {
        return reponse;
    }

    public void setReponse(String reponse) {
        this.reponse = reponse;
    }

}
