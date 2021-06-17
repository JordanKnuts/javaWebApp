/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysqldao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.dao.AbstractDAOFactory;
import model.dao.BibliothequeDAO;
import model.dao.LivreDAO;
import model.dao.UserDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlDAOFactory extends AbstractDAOFactory {

    private static MySqlDAOFactory instance;
    //public String dburl = "jdbc:mysql://localhost:3306/biblio?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC";

    private MySqlDAOFactory() {
    }
    
    public static MySqlDAOFactory getInstance() {
        if (instance == null) {
            instance = new MySqlDAOFactory();
        }
        return instance;
    }

    public Connection getConnection() {
        Connection c = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
             c = DriverManager.getConnection("jdbc:mysql://localhost:3306/bibliotheque?zeroDateTimeBehavior=CONVERT_TO_NULL&serverTimezone=UTC", "root", "");
            

        } catch (ClassNotFoundException ex) {
            Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(MySqlDAOFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
                    return c;
    }

    @Override
    public UserDAO createUserDAO() {
        return MySqlUserDAO.getInstance();
    }

    @Override
    public BibliothequeDAO createBibliothequeDAO() {
        return MySqlBibliothequeDAO.getInstance();
    }

    @Override
    public LivreDAO createLivreDAO() {
        return MySqlLivreDAO.getInstance();
    }
}
