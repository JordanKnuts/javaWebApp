/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model.dao.mysqldao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import model.Livre;
import model.dao.LivreDAO;


public class MySqlLivreDAO implements LivreDAO {

    public MySqlLivreDAO() {
    }
    ;
    
    private static MySqlLivreDAO instance;

    public static LivreDAO getInstance() {
        if (instance == null) {
            instance = new MySqlLivreDAO();
        }
        return instance;
    }
    

/*TODO   update actif une seule fois*/
    @Override
    public void commenter(int idL, int idU, int note, String com) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        
        String sql = "INSERT INTO avis ( idLivre, idLecteur,note,commentaire) VALUES(?,?,?,?)";
        String sql2 = "UPDATE livre SET livre.note =(SELECT AVG(avis.note) FROM avis WHERE avis.idLivre = ?) WHERE livre.idLivre = avis.idLivre";
        
        try {
             c = MySqlDAOFactory.getInstance().getConnection();
             st = c.prepareStatement(sql);
             st.setInt(1, idL);
             st.setInt(2,idU );
             st.setInt(3, note);
             st.setString(4,com);
             st.executeUpdate();
             st = c.prepareStatement(sql2);
             st.setInt(1, idL);
             
             
        } catch (Exception e) {
             System.out.println("Probleme sql commenter" + e);
        }finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (st != null) {
                    st.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
            try {
                if (c != null) {
                    c.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }


}
