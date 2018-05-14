package modele;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import connections.DBconnect;

/**
 *
 * @author Tiffany De Schinckel
 */
public class ProjModeleJDBC extends ProjModele {

    Connection dbconnect;

    public ProjModeleJDBC() {
        dbconnect = DBconnect.getConnection();
        if (dbconnect == null) {
            System.err.println("erreur de connexion => arrêt du pgm");
            System.exit(1);
        }
    }

    public void close() {
        try {
            dbconnect.close();
        } catch (Exception e) {
            System.err.println("erreur lors de la fermeture de la connexion " + e);
        }
    }

    @Override
    public void populate() {
        //ne rien faire car données déjà présentes dans DB
    }

    @Override
    public String ajouterObjet(Object o) {
        String msg = "";
        String query;

        //On vérifie que l'objet n'est pas null
        if (o == null) {
            return "Objet null. Ajout impossible";
        }

        //On détermine la table correspondante au type d'objet
        if (o instanceof Projet) {
            query = "insert into PROJ_PROJET(TITRE_PROJ,CLIENT_PROJ,DATE_DEBUT,DATE_BUTOIR) VALUES (?,?,?,?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Projet) o).getTitre());
                //TODO Récupérer l'id du client dans la BDD
                //TODO Récupérer les dates en format Date
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout projet effectué";
                } else {
                    msg = "Ajout projet non effectué";
                }
            } catch (Exception e) {
                msg = "Erreur lors de l'ajout du projet " + e;
            } finally {
                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (Exception e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }

        }

        if (o instanceof Employe) {
            query = "insert into PROJ_EMPLOYE(NOM_EMP, PRENOM_EMP, GSM_EMP, EMAIL_EMP) VALUES (?,?,?,?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Employe) o).getNom());
                pstm.setString(2, ((Employe) o).getPrenom());
                pstm.setString(3, ((Employe) o).getGsm());
                pstm.setString(4, ((Employe) o).getEmail());
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout employé effectué";
                } else {
                    msg = "Ajout employé non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout de l'employé " + e;
            } finally {
                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }
        }

        if (o instanceof Client) {
            query = "insert into PROJ_CLIENT(NOM_CLI, VILLE_CLI, TEL_CLI) VALUES (?,?,?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Client) o).getNom());
                pstm.setString(2, ((Client) o).getVille());
                pstm.setString(3, ((Client) o).getTel());
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout client effectué";
                } else {
                    msg = "Ajout client non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout du client " + e;
            } finally {
                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }
        }

        if (o instanceof Discipline) {
            query = "insert into PROJ_DISCIPLINE(NOM_DISC) VALUES (?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Discipline) o).getNom());
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout discipline effectué";
                } else {
                    msg = "Ajout discipline non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout de la discipline " + e;
            } finally {
                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }
        }

        if (o instanceof Travail) {
            //TODO modifier la query en fonction de la table travail
            query = "insert into PROJ_() VALUES ()";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                //TODO ajouter les données dans la query
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout projet effectué";
                } else {
                    msg = "Ajout projet non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout du projet " + e;
            } finally {
                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }
        }

        return msg;
    }

    @Override
    public Object getObject(Object o) {
        String query;
        PreparedStatement pstm = null;
        Object o1 = null;
        ResultSet rs = null;

        //On vérifie que l'objet n'est pas null
        if (o == null) {
            return "Objet null. Recherche impossible";
        }

        //On détermine la table correspondante au type d'objet
        if (o instanceof Projet) {
            query = "SELECT * FROM PROJ_PROJET WHERE TITRE = ?";
            //TODO modifier la query avec une jointure pour éviter la 2e query
            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Projet) o).getTitre());
                rs = pstm.executeQuery();
                Client cli = null;

                if (rs.next()) {
                    int idcli = rs.getInt(1);
                    String titre = rs.getString(2);
                    String debut = (rs.getDate(4)).toString();
                    String butoir = (rs.getDate(5)).toString();

                    query = "SELECT * FROM PROJ_CLIENT WHERE ID_CLI = ?";
                    pstm = dbconnect.prepareStatement(query);
                    pstm.setInt(1, idcli);
                    ResultSet rsCli = pstm.executeQuery();

                    if (rsCli.next()) {
                        String nom = rsCli.getString(2);
                        String ville = rsCli.getString(3);
                        String tel = rsCli.getString(4);

                        Client.ClientBuilder cb = new Client.ClientBuilder();
                        try {
                            cli = cb.setNom(nom).setTel(tel).setVille(ville).build();
                        } catch (Exception e) {
                            System.err.println("Erreur client " + e);
                        } finally {
                            try {
                                if (rsCli != null) {
                                    rsCli.close();
                                }
                            } catch (SQLException e) {
                                System.err.println("erreur de fermeture de resultset client " + e);
                            }
                        }

                    }

                    Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
                    try {
                        o1 = pb.setTitre(titre).setClient(cli).setDateDebut(debut).setDateButoir(butoir).build();
                    } catch (Exception e) {
                        System.err.println("Erreur projet " + e);
                    }
                }

            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche de projet" + e);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture de resultset " + e);
                }

                try {
                    if (pstm != null) {
                        pstm.close();
                    }
                } catch (SQLException e) {
                    System.err.println("erreur de fermeture du preparedStatement " + e);
                }

            }

        }
        
        if(o instanceof Employe){
            query = "SELECT * FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ? AND GSM_EMP = ?";
            
            
            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Employe) o).getNom());
                pstm.setString(2, ((Employe) o).getPrenom());
                pstm.setString(3, ((Employe) o).getGsm());
                rs = pstm.executeQuery();
                
                if(rs.next()){
                    String nom = rs.getString(2);
                    String prenom = rs.getString(3);
                    String gsm = rs.getString(4);
                    String email = rs.getString(5);
                    
                    Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
                    
                    try {
                        o1 = eb.setNom(nom).setEmail(email).setGsm(gsm).setPrenom(prenom).build();
                    } catch (Exception e) {
                        System.err.println("Erreur employé "+ e);
                    }
                    
                }
                
            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche employé "+e);
            } finally{
                try {
                    if(rs != null){
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur de fermeture du preparedStatement "+e);
                }
            }
        }
        
        if(o instanceof Client){
            query = "SELECT * FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ?";
            
            
            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Client) o).getNom());
                pstm.setString(2, ((Client) o).getVille());
                rs = pstm.executeQuery();
                
                if(rs.next()){
                    String nom = rs.getString(2);
                    String ville = rs.getString(3);
                    String tel = rs.getString(4);
                    
                    Client.ClientBuilder cb = new Client.ClientBuilder();
                    
                    try {
                        o1 = cb.setNom(nom).setTel(tel).setVille(ville).build();
                    } catch (Exception e) {
                        System.err.println("Erreur client "+ e);
                    }
                    
                }
                
            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche client "+e);
            } finally{
                try {
                    if(rs != null){
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur de fermeture du preparedStatement "+e);
                }
            }
        }

        return o1;
    }
}
