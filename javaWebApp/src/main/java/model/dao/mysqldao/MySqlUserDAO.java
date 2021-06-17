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
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import model.Bibliotheque;
import model.Exemplaire;
import model.Livre;
import model.Location;
import model.QuestRep;
import model.Role;
import model.User;
import model.dao.UserDAO;

/**
 *
 * @author MediaMonster
 */
public class MySqlUserDAO implements UserDAO {

    public MySqlUserDAO() {
    }

    private static MySqlUserDAO instance;

    static {
        instance = new MySqlUserDAO();
    }

    public static UserDAO getInstance() {
        return instance;
    }

    @Override
    public User getConnected(String login, String password) {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;
        String sql = "SELECT idUser,nom,prenom,adresse,email,telephone,role.idRole,role.nomRole,login,password,amende FROM user,role WHERE  `user`.idRole = role.idRole  AND login = ? AND password = ?  ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, login);
            st.setString(2, password);
            rs = st.executeQuery();

            if (rs.next()) {

                // String nom, String prenom, String adresse, String email, Role role,String login, String password
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10), rs.getInt(11));
                //user.setId(rs.getInt("idUser"));

            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return user;
    }

    @Override
    public User getUser(String login) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User u = null;
        String sql = "SELECT idUser,nom,prenom,adresse,telephone,email,role.idRole,role.nomRole,login,password,idUser FROM user,role WHERE  `user`.idRole = role.idRole  AND nom = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, login);
            rs = st.executeQuery();
            if (rs.next()) {
                u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return u;
    }

    @Override
    public void subscribe(User u, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO `user` ( `nom`, `prenom`,`adresse`,`telephone`,`email`,`idRole`,`login`, `password`)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        String sql2 = "INSERT INTO `inscription` (`idUser`,`idBibliotheque`) VALUES (?,?)";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, u.getNom());
            st.setString(2, u.getPrenom());
            st.setString(3, u.getAdresse());
            st.setString(4, u.getTelephone());
            st.setString(5, u.getEmail());
            st.setInt(6, u.getRole().getIdRole());
            st.setString(7, u.getLogin());
            st.setString(8, u.getPassword());
            st.executeUpdate();

            rs = st.getGeneratedKeys();

            st = c.prepareStatement(sql2);
            rs.next();
            st.setInt(1, rs.getInt(1));
            st.setInt(2, b.getId());
            //st.setInt(11,0);    
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public boolean estInscrit(User u, Bibliotheque bi) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean b = false;
        String sql = "SELECT idUser FROM inscription WHERE idUser = ? AND idBibliotheque = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, u.getId());
            st.setInt(2, bi.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                b = true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return b;
    }

    @Override
    public List<Location> getListLocations(int idU) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Location> list = new ArrayList<>();
        String sql = "SELECT exemplaire.idExemplaire,type,titre,auteur,edition,nbPage,prix,date FROM exemplaire,livre,location WHERE location.idExemplaire = exemplaire.idExemplaire AND exemplaire.idDULivre = idLivre AND  location.idUser = ?";
        //String sql = "SELECT titre from livre join exemplaire on exemplaire.idDuLivre = livre.idLivre JOIN louer on exemplaire.idExemplaire = louer.idExemplaire WHERE louer.idLecteur = ?  ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idU);

            rs = st.executeQuery();
            while (rs.next()) {

                Location l = new Location(new Exemplaire(rs.getInt(1), rs.getString(2), new Livre(rs.getString(3), rs.getString(4), rs.getString(5), rs.getInt(6), rs.getFloat(7))), rs.getDate(8));

                list.add(l);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return list;
    }

    @Override
    public void addBiblio(Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO bibliotheque  (nom,adresse,idManager) VALUES (?,?,?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, b.getNom());
            st.setString(2, b.getAdresse());
            st.setInt(3, b.getManager().getId());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void addManager(User u, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO `user` ( `nom`, `prenom`,`adresse`,`telephone`,`email`,`idRole`,`login`, `password`)"
                + "VALUES (?,?,?,?,?,?,?,?)";

        String sql2 = "INSERT INTO `inscription` (`idUser`,`idBibliotheque`) VALUES (?,?)";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            st.setString(1, u.getNom());
            st.setString(2, u.getPrenom());
            st.setString(3, u.getAdresse());
            st.setString(4, u.getTelephone());
            st.setString(5, u.getEmail());
            st.setInt(6, u.getRole().getIdRole());
            st.setString(7, u.getLogin());
            st.setString(8, u.getPassword());
            st.executeUpdate();

            rs = st.getGeneratedKeys();

            st = c.prepareStatement(sql2);
            rs.next();
            st.setInt(1, rs.getInt(1));
            st.setInt(2, b.getId());
            //st.setInt(11,0);    
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void askQuestion(QuestRep qr) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO qr (idAuteur,question) VALUES(?,?)";
        System.out.println("xcvdsfvdfgdfgdfgdfgdf");

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, qr.getIdAuteur());
            st.setString(2, qr.getQuestion());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void giveReponse(QuestRep qr) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "UPDATE qr SET idBibliotecaire = ?, reponse = ? WHERE idQuestion = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, qr.getIdAuteur());
            st.setString(2, qr.getReponse());
            st.setInt(3, qr.getIdQR());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void payeCotisation(User user, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "UPDATE inscription SET cotisationPaye = TRUE, datePayement = CURRENT_DATE WHERE idBibliotheque = ? AND idUser = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);

            st.setInt(1, b.getId());
            st.setInt(2, user.getId());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void deleteExemplaire(Exemplaire ex) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "DELETE FROM exemplaire WHERE idExemplaire = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);

            st.setInt(1, ex.getIdExemplaire());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void retourLocation(Location l) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "UPDATE exemplaire SET rendu = 'TRUE', verifié = 'FALSE'  WHERE idExemplaire = (SELECT idExemplaire FROM location WHERE idLocation = ?)";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);

            st.setInt(1, l.getIdLocation());

            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public void signalerAbimePerdu(Location l) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int idUser = 0;

        String sqlSelectIdUser = "SELECT idUser FROM location WHERE idLocation = ?";

        String sqlAmendeUser = "UPDATE user SET amende + ? WHERE idUser = ?";

        String sqlDeleteEx = "DELETE FROM exemplaire WHERE idExemplaire = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlSelectIdUser);
            st.setInt(1, l.getIdLocation());
            st.executeQuery();
            if (rs.next()) {
                idUser = rs.getInt(1);
            }

            st = c.prepareStatement(sqlAmendeUser);
            st.setFloat(1, l.getExemplaire().getLivre().getPrix());
            st.setInt(2, idUser);
            st.executeUpdate();

            st = c.prepareStatement(sqlDeleteEx);
            st.setInt(1, l.getExemplaire().getId());
            st.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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

    @Override
    public boolean updateUser(User user) {

        Connection c = null;
        ResultSet rs = null;
        PreparedStatement st = null;

        boolean boo = false;

        String sqlCheckEmail = "SELECT user.idUser FROM `user` WHERE user.email = ? AND user.idUser NOT IN (SELECT user.idUser FROM user WHERE user.idUser = ?) ";
        String sqlCheckLogin = "SELECT user.idUser FROM `user` WHERE user.login = ? AND user.idUser NOT IN (SELECT user.idUser FROM user WHERE user.idUser = ?) ";
        String sqlUpdate = "UPDATE  user SET nom = ? ,prenom = ?, email = ?, password = ?,adresse = ?  WHERE idUser = ? ";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlCheckEmail);
            st.setString(1, user.getEmail());
            st.setInt(2, user.getId());
            rs = st.executeQuery();

            if (!rs.next()) {
                st = c.prepareStatement(sqlCheckLogin);
                st.setString(1, user.getLogin());
                st.setInt(2, user.getId());
                rs = st.executeQuery();
                if (!rs.next()) {
                    boo = true;
                    st = c.prepareStatement(sqlUpdate);
                    st.setString(1, user.getNom());
                    st.setString(2, user.getPrenom());
                    st.setString(3, user.getEmail());
                    st.setString(4, user.getPassword());
                    st.setString(5, user.getAdresse());
                    st.setInt(6, user.getId());
                    st.executeUpdate();
                }

            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
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
        return boo;
    }

}
