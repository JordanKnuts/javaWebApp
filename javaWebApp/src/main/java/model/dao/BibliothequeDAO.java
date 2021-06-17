/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;
import model.Avis;
import model.Bibliotheque;
import model.Exemplaire;
import model.Livre;
import model.Location;
import model.QuestRep;
import model.Role;
import model.User;

/**
 *
 * @author MediaMonster
 */
public interface BibliothequeDAO {

    public String getNom(int id);

    public User getUser(String login , String email);
    
    public List<Bibliotheque> getListBibliotheque();

    public List<Exemplaire> getListeDeLivre(Bibliotheque b);

    public Bibliotheque getBibliothequeByName(String b);

    public Livre getLivreById(int idL);

    public Exemplaire getExemplaireById(int idEx);

    public Location getLocationById(int idL);

    public Role getRoleByName(String name);

    public List<User> getallManager();

    public void addLivre(Livre l, Bibliotheque b, String type);

    public List<Exemplaire> getListeExemplaireDispo(int idE, Bibliotheque b);

    public List<Role> getListRole();

    public boolean checkDispoAndAddNum(Exemplaire ex, Date date, User u, Bibliotheque b);

    public boolean checkDispoAndAddPhys(Exemplaire ex, Date date, User u, Bibliotheque b);

    public List<QuestRep> getQuestRep();

    public Map<String,Integer> getListLivreContains(String s);

    public boolean cotisationPaye(User u, Bibliotheque b);

    public void addBibliotheque(Bibliotheque b);

    public void updateBiblio(Bibliotheque b);

    public List<Location> getHistoricLocationsUser(User u);

    public List<Location> getLocationsBibliotheque(Bibliotheque b);

    public void returnBook(Location l, User u);

    public void returnNumericBook(User u);

    public void updateCotisation();

    public List<Avis> getListAvis(int idL);

    public void deleteExemplaire(Location l);

    public void verifyExemplaire(Location l);

}
