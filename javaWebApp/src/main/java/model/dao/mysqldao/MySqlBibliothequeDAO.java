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
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
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
import model.dao.BibliothequeDAO;

/*
 * TODO *
 */
public class MySqlBibliothequeDAO implements BibliothequeDAO {

    public MySqlBibliothequeDAO() {

    }

    private static MySqlBibliothequeDAO instance;

//    static {
//        instance = new MySqlBibliothequeDAO();
//    }
    public static BibliothequeDAO getInstance() {
        if (instance == null) {
            instance = new MySqlBibliothequeDAO();
        }
        return instance;
    }

    @Override
    public String getNom(int id) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String nomB = "";

        String sql = "SELECT nom FROM bibliotheque WHERE id = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, id);
            rs = st.executeQuery();
            if (rs.next()) {
                nomB = rs.getString("nom");
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
        return nomB;
    }

    @Override
    public User getUser(String login, String email) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        User user = null;


        String sqlCheckLogin = "SELECT idUser,nom,prenom,adresse,telephone,email,role.idRole,role.nomRole,login,password,idUser FROM user,role WHERE login = ? ";
        String sqlCheckEmail = "SELECT idUser,nom,prenom,adresse,telephone,email,role.idRole,role.nomRole,login,password,idUser FROM user,role WHERE email = ? ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlCheckLogin);
            st.setString(1, login);
            rs = st.executeQuery();
            if (!rs.next()) {
                st = c.prepareStatement(sqlCheckEmail);
                st.setString(1, email);
                rs = st.executeQuery();
                if (rs.next()) {
                    user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10));
                }
            } else {
                user = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10));
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
    public List<Bibliotheque> getListBibliotheque() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Bibliotheque> listB;
        listB = new ArrayList<>();

        String sql = "SELECT bibliotheque.idBibliotheque, bibliotheque.nom ,bibliotheque.adresse, user.nom,user.prenom, user.adresse,user.telephone,user.email,role.idRole,role.nomRole,user.login,user.password FROM bibliotheque,role,user WHERE bibliotheque.idManager = user.idUser AND user.idRole = role.idRole   ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                //String nom, String prenom, String adresse, String email, Role role,String login, String password
                Bibliotheque biblio = new Bibliotheque(rs.getInt(1), rs.getString(2), rs.getString(3), new User(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), new Role(rs.getInt(9), rs.getString(10)), rs.getString(11), rs.getString(12)));

                // new Bibliotheque(rs.getInt(11),rs.getString(1),rs.getString(2),new User(rs.getString(3),rs.getString(4),rs.getString(5),rs.getString(6),new Role(rs.getInt(7),rs.getString(8)),rs.getString(9),rs.getString(10)));
                listB.add(biblio);
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
        return listB;
    }

    @Override
    public List<Exemplaire> getListeDeLivre(Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Exemplaire> listL;
        listL = new ArrayList<>();

        String sql = "SELECT e.idExemplaire,e.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.prix,livre.note "
                + " FROM exemplaire as e, livre,possede "
                + " WHERE e.idDuLivre = livre.idLivre AND e.idExemplaire = possede.idExemplaire AND possede.idBibliotheque = ? GROUP BY titre";
        // "SELECT livre.titre as Titre ,bibliotheque.idBibliotheque as idB ,bibliotheque.nom  as nomB, exemplaire.idExemplaire as IdE, exemplaire.type as Type  FROM livre JOIN exemplaire ON exemplaire.idDuLivre = livre.idLivre JOIN possede ON possede.idEx = exemplaire.idExemplaire JOIN bibliotheque ON bibliotheque.idBibliotheque = possede.idBi GROUP BY livre.idLivre";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, b.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                Exemplaire livre = new Exemplaire(rs.getInt(1), rs.getString(2), new Livre(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getFloat(8), rs.getFloat(9)));

                listL.add(livre);
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
        return listL;
    }

    @Override
    public Bibliotheque getBibliothequeByName(String nom) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Bibliotheque b = null;
        String sql = "SELECT bibliotheque.idBibliotheque, bibliotheque.nom ,bibliotheque.adresse, user.nom,user.prenom, user.adresse,user.email,user.telephone,role.idRole,role.nomRole,user.login,user.password FROM bibliotheque,role,user WHERE bibliotheque.idManager = user.idUser AND user.idRole = role.idRole  AND bibliotheque.nom = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, nom);
            rs = st.executeQuery();
            if (rs.next()) {
                b = new Bibliotheque(rs.getInt(1), rs.getString(2), rs.getString(3), new User(rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), new Role(rs.getInt(9), rs.getString(10)), rs.getString(11), rs.getString(12)));
                //        User u = new User(nom, nom, sql, sql, sql, role, nom, sql)
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
    public Livre getLivreById(int i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Livre l = null;
        String sql = "SELECT idLivre, titre, auteur, edition, nbPage,prix, note FROM livre WHERE idLivre = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, i);
            rs = st.executeQuery();
            if (rs.next()) {
                l = new Livre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7));
            }
            //String titre, String auteur, String edition,int nbPage, float prix

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
        return l;

    }

    @Override
    public Exemplaire getExemplaireById(int i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Exemplaire ex = null;
        String sql = "SELECT idExemplaire,type,idLivre,titre,auteur,edition,nbPage,prix,note FROM exemplaire,livre WHERE  exemplaire.idExemplaire = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, i);
            rs = st.executeQuery();
            if (rs.next()) {
                ex = new Exemplaire(rs.getInt(1), rs.getString(2), new Livre(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getFloat(8), rs.getFloat(9)));
                //e = new Livre(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getInt(5), rs.getFloat(6), rs.getInt(7));
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
        return ex;

    }

    @Override
    public List<User> getallManager() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<User> listManager;
        listManager = new ArrayList<>();

        String sql = "SELECT idUser,nom,prenom,adresse,telephone,email,role.idRole,role.nomRole,login,`password` FROM `user`,role WHERE `user`.idRole = role.idRole AND `user`.idRole = 2 AND idUser NOT IN (SELECT user.idUser FROM inscription,`user` WHERE inscription.idUser = `user`.idUser AND `user`.idRole = 2) ";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                User manager = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10));
                listManager.add(manager);
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
        return listManager;
    }

    //TODO if exists
    @Override
    public void addLivre(Livre l, Bibliotheque b, String type) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sqlExist = "SELECT titre,auteur,edition,nbPage,prix FROM livre WHERE titre=? AND auteur=? AND edition=?";
        String sqlInsertLivre = " INSERT INTO livre (titre,auteur,edition,nbPage,note,prix) VALUES(?,?,?,?,null,?) ";
        String sqlInsertExemp = " INSERT INTO exemplaire(idDuLivre,type) VALUES((SELECT idLivre FROM livre WHERE titre=?   AND auteur=? AND edition =? ),?) ";
        String sqlInsertPossede = " INSERT INTO possede (idExemplaire,idBibliotheque) VALUES ((SELECT MAX(idExemplaire) FROM exemplaire ),?)";

//        String sql = " IF EXISTS "
//                + " (SELECT titre,auteur,edition,nbPage,prix FROM livre WHERE titre=? AND auteur=? AND edition=?) "
//                + " BEGIN "
//                + " INSERT INTO exemplaire(idDuLivre,type,disponible) VALUES((SELECT idLivre FROM livre WHERE titre=?   AND auteur=? AND edition =? ),?,?) "
//                + " END; "
//                + " ELSE "
//                + " INSERT INTO livre (titre,auteur,edition,nbPage,prix) VALUES(?,?,?,?,?);";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlExist);
            st.setString(1, l.getTitre());
            st.setString(2, l.getAuteur());
            st.setString(3, l.getEdition());

            rs = st.executeQuery();
            if (rs.next()) {
                st = c.prepareStatement(sqlInsertExemp);
                st.setString(1, l.getTitre());
                st.setString(2, l.getAuteur());
                st.setString(3, l.getEdition());
                st.setString(4, type);

                st.executeUpdate();

                st = c.prepareStatement(sqlInsertPossede);
                st.setInt(1, b.getId());

                st.executeUpdate();
            } else {
                st = c.prepareStatement(sqlInsertLivre);
                st.setString(1, l.getTitre());
                st.setString(2, l.getAuteur());
                st.setString(3, l.getEdition());
                st.setInt(4, l.getNbPage());
                st.setFloat(5, l.getPrix());

                st.executeUpdate();

                st = c.prepareStatement(sqlInsertExemp);
                st.setString(1, l.getTitre());
                st.setString(2, l.getAuteur());
                st.setString(3, l.getEdition());
                st.setString(4, type);

                st.executeUpdate();

                st = c.prepareStatement(sqlInsertPossede);
                st.setInt(1, b.getId());

                st.executeUpdate();

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
    }

    @Override
    public List<Exemplaire> getListeExemplaireDispo(int idL, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Exemplaire> listL;
        listL = new ArrayList<>();
        String sql = "SELECT exemplaire.idExemplaire,exemplaire.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.prix,livre.note, possede.idBibliotheque "
                + " FROM exemplaire,livre,possede "
                + " WHERE "
                + " exemplaire.idDuLivre = livre.idLivre "
                + " AND disponible = true "
                + " AND possede.idExemplaire = exemplaire.idExemplaire "
                + " AND idLivre = ?  "
                + " GROUP BY "
                + " type "
                + " ORDER BY possede.idBibliotheque = ? ASC";
        // "SELECT livre.titre as Titre ,bibliotheque.idBibliotheque as idB ,bibliotheque.nom  as nomB, exemplaire.idExemplaire as IdE, exemplaire.type as Type  FROM livre JOIN exemplaire ON exemplaire.idDuLivre = livre.idLivre JOIN possede ON possede.idEx = exemplaire.idExemplaire JOIN bibliotheque ON bibliotheque.idBibliotheque = possede.idBi GROUP BY livre.idLivre";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idL);
            st.setInt(2, b.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                Exemplaire livre = new Exemplaire(rs.getInt(1), rs.getString(2), new Livre(rs.getInt(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getInt(7), rs.getFloat(8), rs.getFloat(9)));

                //Exemplaire: int idExemplaire, char type,  Livre livre
                //Livre :int idLivre, String titre, String auteur, String edition,int nbPage, float prix, float note
                listL.add(livre);
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
        return listL;
    }

    @Override
    public List<Role> getListRole() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Role> listR = new ArrayList();
        String sql = " SELECT idRole, nomRole FROM role";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            rs.next();
            while (rs.next()) {
                Role r = new Role();
                r.setIdRole(rs.getInt("idRole"));
                r.setNom(rs.getString("nomRole"));
                if (!rs.getString("nomRole").contains("lecteur")) {
                    listR.add(r);
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

        return listR;
    }

    @Override
    public boolean checkDispoAndAddNum(Exemplaire ex, Date date, User u, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean exe = false;
        c = MySqlDAOFactory.getInstance().getConnection();
        String sqlDispo = "SELECT exemplaire.idExemplaire,exemplaire.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.note,livre.prix "
                + " FROM exemplaire,livre,possede "
                + " WHERE livre.idLivre = ? AND type = ? "
                + " AND exemplaire.idDuLivre = livre.idLivre "
                + " AND possede.idExemplaire = exemplaire.idExemplaire "
                + " AND exemplaire.idExemplaire NOT IN "
                + " (SELECT location.idExemplaire FROM location WHERE disponible = true AND location.date BETWEEN DATE_SUB( ? ,INTERVAL 30 DAY)AND DATE_ADD( ? , INTERVAL 30 DAY)) "
                + " GROUP BY exemplaire.idExemplaire "
                + " ORDER BY possede.idBibliotheque = ? DESC "
                + " LIMIT 1 ";
        String sqlAddLoc = "INSERT INTO `location` ( `idExemplaire`, `idUser`, `date`) VALUES (?,?,?)";

        String sqlUpdateEx = "UPDATE exemplaire SET rendu = false , vérifié = false WHERE idExemplaire = ?";

        try {

            st = c.prepareStatement(sqlDispo);
            st.setInt(1, ex.getLivre().getIdLivre());
            st.setString(2, ex.getType());
            st.setDate(3, new java.sql.Date(date.getTime()));
            st.setDate(4, new java.sql.Date(date.getTime()));
            st.setInt(5, b.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                exe = true;
                int idExe = rs.getInt("idExemplaire");
                st = c.prepareStatement(sqlAddLoc);
                st.setInt(1, idExe);
                st.setInt(2, u.getId());
                st.setDate(3, new java.sql.Date(date.getTime()));
                st.executeUpdate();

                st = c.prepareStatement(sqlUpdateEx);
                st.setInt(1, idExe);
                st.executeUpdate();

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

        return exe;
    }

    @Override
    public boolean checkDispoAndAddPhys(Exemplaire ex, Date date, User u, Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        boolean exe = false;
        c = MySqlDAOFactory.getInstance().getConnection();

        String sqlDispo = "SELECT exemplaire.idExemplaire,exemplaire.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.note,livre.prix "
                + " FROM exemplaire,livre,possede "
                + " WHERE livre.idLivre = ? AND type = ? AND idBibliotheque = ?"
                + " AND exemplaire.idDuLivre = livre.idLivre "
                + " AND possede.idExemplaire = exemplaire.idExemplaire "
                + " AND exemplaire.idExemplaire NOT IN "
                + " (SELECT location.idExemplaire FROM location WHERE disponible = true AND location.date BETWEEN DATE_SUB( ? ,INTERVAL 30 DAY)AND DATE_ADD( ? , INTERVAL 30 DAY)) "
                + " LIMIT 1 ";

        String sqlDispoOther = "SELECT exemplaire.idExemplaire,exemplaire.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.note,livre.prix "
                + " FROM exemplaire,livre,possede "
                + " WHERE livre.idLivre = ? AND type = ?"
                + " AND exemplaire.idDuLivre = livre.idLivre "
                + " AND possede.idExemplaire = exemplaire.idExemplaire "
                + " AND exemplaire.idExemplaire NOT IN "
                + " (SELECT location.idExemplaire FROM location WHERE disponible = true AND location.date BETWEEN DATE_SUB( ? ,INTERVAL 32 DAY)AND DATE_ADD( ? , INTERVAL 32 DAY)) "
                + " LIMIT 1 ";

        String sqlAddLoc = "INSERT INTO `location` ( `idExemplaire`, `idUser`, `date`) VALUES (?,?,?)";

        String sqlUpdateEx = "UPDATE exemplaire SET rendu = false , vérifié = false WHERE idExemplaire = ?";

        try {

            st = c.prepareStatement(sqlDispo);
            st.setInt(1, ex.getLivre().getIdLivre());
            st.setString(2, ex.getType());
            st.setInt(3, b.getId());
            st.setDate(4, new java.sql.Date(date.getTime()));
            st.setDate(5, new java.sql.Date(date.getTime()));
            rs = st.executeQuery();
            if (rs.next()) {
                exe = true;
                int idExe = rs.getInt("idExemplaire");
                st = c.prepareStatement(sqlAddLoc);
                st.setInt(1, idExe);
                st.setInt(2, u.getId());
                st.setDate(3, new java.sql.Date(date.getTime()));
                st.executeUpdate();

                st = c.prepareStatement(sqlUpdateEx);
                st.setInt(1, idExe);
                st.executeUpdate();
            } else if (!rs.next()) {
                st = c.prepareStatement(sqlDispoOther);
                st.setInt(1, ex.getLivre().getIdLivre());
                st.setString(2, ex.getType());
                st.setDate(3, new java.sql.Date(date.getTime()));
                st.setDate(4, new java.sql.Date(date.getTime()));
                rs = st.executeQuery();
                if (rs.next()) {
                    exe = true;
                    int idExe = rs.getInt("idExemplaire");
                    st = c.prepareStatement(sqlAddLoc);
                    st.setInt(1, idExe);
                    st.setInt(2, u.getId());
                    st.setDate(3, new java.sql.Date(date.getTime()));
                    st.executeUpdate();

                    st = c.prepareStatement(sqlUpdateEx);
                    st.setInt(1, idExe);
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

        return exe;
    }

    @Override
    public List<QuestRep> getQuestRep() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<QuestRep> qrList = new ArrayList();
        c = MySqlDAOFactory.getInstance().getConnection();
        String sql = " SELECT idQuestion,question,reponse from qr";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            rs = st.executeQuery();
            while (rs.next()) {
                QuestRep qr = new QuestRep();
                qr.setIdQR(rs.getInt("idQuestion"));
                qr.setQuestion(rs.getString("question"));
                if (null != rs.getString("reponse")) {
                    qr.setReponse(rs.getString("reponse"));
                }
                qrList.add(qr);

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
        return qrList;

    }

    @Override
    public Map<String,Integer> getListLivreContains(String string) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
       
        Map<String,Integer> map = new HashMap<>();

        String sql = "SELECT livre.titre,COUNT(*) as nombre FROM exemplaire as e, livre  WHERE e.disponible=true AND livre.idLivre = e.idDuLivre  AND livre.titre LIKE ? GROUP BY titre";
        // "SELECT livre.titre as Titre ,bibliotheque.idBibliotheque as idB ,bibliotheque.nom  as nomB, exemplaire.idExemplaire as IdE, exemplaire.type as Type  FROM livre JOIN exemplaire ON exemplaire.idDuLivre = livre.idLivre JOIN possede ON possede.idEx = exemplaire.idExemplaire JOIN bibliotheque ON bibliotheque.idBibliotheque = possede.idBi GROUP BY livre.idLivre";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1,  "%" + string+ "%");
            rs = st.executeQuery();
            
            while (rs.next()) {
                
                map.put(rs.getString("titre"),rs.getInt("nombre"));
               
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
        return map;
    }

    @Override
    public boolean cotisationPaye(User user, Bibliotheque b) {

        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Boolean aPaye = false;

        String sql = "SELECT idUser FROM inscription WHERE idUser = ? AND idBibliotheque = ? AND cotisationPaye = TRUE";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, user.getId());
            st.setInt(2, b.getId());
            rs = st.executeQuery();
            if (rs.next()) {
                aPaye = true;
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
        return aPaye;

    }

    @Override
    public Role getRoleByName(String string) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Role r = new Role();
        String sql = "SELECT idRole,nomRole FROM role WHERE nomRole=?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, string);
            rs = st.executeQuery();
            if (rs.next()) {
                r.setIdRole(rs.getInt("idRole"));
                r.setNom(rs.getString("nomRole"));

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
        return r;
    }

    @Override
    public void addBibliotheque(Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "INSERT INTO bibliotheque (nom,adresse,idManager) VALUES(?,?,?)";

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
    public void updateBiblio(Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sql = "UPDATE bibliotheque SET nom = ? , adresse = ? WHERE idBibliotheque = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setString(1, b.getNom());
            st.setString(2, b.getAdresse());
            st.setInt(3, b.getId());

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
    public List<Location> getHistoricLocationsUser(User user) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Location> r = new ArrayList();
        String sql = "SELECT DISTINCT idLocation, e.idExemplaire,e.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.prix,livre.note,e.rendu,e.vérifié ,loc.date FROM exemplaire as e, livre,location as loc,possede as po WHERE livre.idLivre = e.idDuLivre AND e.idExemplaire = loc.idExemplaire AND po.idExemplaire = loc.idExemplaire  AND loc.idUser = ? GROUP BY idLocation";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, user.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                Location l = new Location(rs.getInt(1), new Exemplaire(rs.getInt(2), rs.getString(3), new Livre(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getFloat(9), rs.getFloat(10)), rs.getBoolean(11), rs.getBoolean(12)), rs.getDate(13));
                r.add(l);
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
        return r;
    }

    @Override
    public List<Location> getLocationsBibliotheque(Bibliotheque b) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Location> r = new ArrayList();
        //SI L'EXEMPLAIRE VIENT D'UNE AUTRE BIBLIO ?? LE LIEN DES TABLES
        String sql = "SELECT DISTINCT idLocation, e.idExemplaire,e.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.prix,livre.note,e.rendu,e.vérifié, loc.date FROM exemplaire as e, livre,location as loc,possede as po WHERE livre.idLivre = e.idDuLivre AND e.idExemplaire = loc.idExemplaire AND po.idExemplaire = loc.idExemplaire AND po.idBibliotheque = ?";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, b.getId());
            rs = st.executeQuery();
            while (rs.next()) {
                Location l = new Location(rs.getInt(1), new Exemplaire(rs.getInt(2), rs.getString(3), new Livre(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getFloat(9), rs.getFloat(10)), rs.getBoolean(11), rs.getBoolean(12)), rs.getDate(13));
                r.add(l);
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
        return r;
    }

    @Override
    public Location getLocationById(int idL) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        Location l = null;
        String sql = "SELECT idLocation,e.idExemplaire,e.type,livre.idLivre,livre.titre,livre.auteur,livre.edition,livre.nbPage,livre.prix,livre.note, location.date FROM exemplaire e, livre,location,possede WHERE location.idExemplaire = e.idExemplaire AND e.idDuLivre = livre.idLivre AND location.idLocation = ? GROUP BY idExemplaire";
        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idL);
            rs = st.executeQuery();
            if (rs.next()) {
                l = new Location(rs.getInt(1), new Exemplaire(rs.getInt(2), rs.getString(3), new Livre(rs.getInt(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getInt(8), rs.getFloat(9), rs.getFloat(10))), rs.getDate(11));

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
        return l;
    }

    @Override
    public void returnBook(Location location, User user) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        float amende;

        String sql = "UPDATE exemplaire SET rendu= true  WHERE IdExemplaire = ? ";
        String sqlCalculAmende = "SELECT DATEDIFF(CURRENT_DATE, date ) from location where location.IdUser = ? and idLocation = ?";

        String sqlAddAmende = "UPDATE user SET Amende= user.amende + ?  WHERE idUser = ? ";
        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, location.getExemplaire().getIdExemplaire());
            st.executeUpdate();

            st = c.prepareStatement(sqlCalculAmende);
            st.setInt(1, user.getId());
            st.setInt(2, location.getIdLocation());
            rs = st.executeQuery();

            if (rs.next()) {
                amende = rs.getInt(1) - 30.0f;
                if ((amende) > 0) {
                    amende = amende * 0.2f;
                    st = c.prepareStatement(sqlAddAmende);
                    st.setFloat(1, amende);
                    st.setInt(2, user.getId());
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

    }

    @Override
    public void updateCotisation() {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE `inscription` SET `cotisationPaye`= 0  WHERE DATEDIFF(CURRENT_DATE,datePayement) > 365 ";

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
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
    public void returnNumericBook(User user) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;

        String sqlCalcul = "SELECT DATEDIFF(CURRENT_DATE, date ) as days,exemplaire.idExemplaire from location,exemplaire where location.idExemplaire = exemplaire.idExemplaire AND exemplaire.type = 'N' AND location.IdUser = ? ";
        String sql = "UPDATE exemplaire SET rendu=true  WHERE IdExemplaire = ? ";

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sqlCalcul);
            st.setInt(1, user.getId());
            rs = st.executeQuery();

            if (rs.next()) {
                int idE = rs.getInt("idExemplaire");
                if (rs.getInt("days") > 30) {
                    st = c.prepareStatement(sql);
                    st.setInt(1, idE);
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
    }

    @Override
    public List<Avis> getListAvis(int i) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        List<Avis> a = new ArrayList();

        String sql = "SELECT idUser,nom,prenom,adresse,telephone,email,role.idRole,role.nomRole,login,password,note,commentaire FROM user,role, avis where role.idRole = user.idRole and avis.idLecteur = user.idUser and avis.idLivre = ?";

        try {

            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, i);
            rs = st.executeQuery();
            while (rs.next()) {
                Avis l = new Avis(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), new Role(rs.getInt(7), rs.getString(8)), rs.getString(9), rs.getString(10)), rs.getDouble(11), rs.getString(12));

                a.add(l);
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
        return a;

    }

    @Override
    public void deleteExemplaire(Location l) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        int idEx = l.getExemplaire().getId();

        String sql = "UPDATE exemplaire SET disponible = false ,  vérifié = true WHERE idExemplaire = ? ";

        String sqlAmende = "UPDATE user SET amende = amende + (SELECT prix FROM livre,exemplaire WHERE exemplaire.idDuLivre = livre.idLivre AND idExemplaire = ? )  WHERE idUser = (SELECT idUser from location WHERE idLocation = ?)";
        //si l'exemplaire.rendu = false actionné le return book

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
            st.setInt(1, idEx);
            st.executeUpdate();

            st = c.prepareStatement(sqlAmende);
            st.setInt(1, idEx);
            st.setInt(2, l.getIdLocation());
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
    public void verifyExemplaire(Location l) {
        Connection c = null;
        PreparedStatement st = null;
        ResultSet rs = null;
        String sql = "UPDATE exemplaire SET vérifié = true WHERE idExemplaire = ? ";

        try {
            c = MySqlDAOFactory.getInstance().getConnection();
            st = c.prepareStatement(sql);
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

}
