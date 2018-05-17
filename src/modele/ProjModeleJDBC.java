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

    public int recupIdCli(Client c) {
        int idCli = -1;

        String query = "SELECT ID_CLI FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ? AND TEL_CLI = ?";
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, c.getNom());
            pstm.setString(2, c.getVille());
            pstm.setString(3, c.getTel());
            rs = pstm.executeQuery();

            if (rs.next()) {
                idCli = rs.getInt("ID_CLI");
            }

        } catch (SQLException e) {
            System.err.println("Erreur dans la récupération de l'id client " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du preparedStatement " + e);
            }
        }

        return idCli;
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
                pstm.setInt(2, recupIdCli(((Projet) o).getClient()));
                pstm.setString(3, ((Projet) o).getDateDebut().replace("/", "-"));
                pstm.setString(4, ((Projet) o).getDateButoir().replace("/", "-"));
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
            query = "insert into PROJ_TRAVAIL(ID_PROJ, ID_EMP, DATE_ENGAGEMENT) VALUES (?,?,?)";
            String idProj = "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = " + ((Travail) o).getProjet().getTitre();
            String idEmp = "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = " + ((Travail) o).getEmploye().getNom()
                    + " AND PRENOM_EMP = " + ((Travail) o).getEmploye().getPrenom() + " AND GSM_EMP = " + ((Travail) o).getEmploye().getGsm();
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, idProj);
                pstm.setString(2, idEmp);
                pstm.setString(3, ((Travail) o).getDateEngagement().replace("/", "-"));

                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout travail effectué";
                } else {
                    msg = "Ajout travail non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout du travail " + e;
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
            query = "SELECT * FROM PROJ_PROJET P\n"
                    + "INNER JOIN PROJ_CLIENT CL ON P.CLIENT_PROJ = CL.ID_CLI\n"
                    + "WHERE P.TITRE_PROJ = ?";
            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Projet) o).getTitre());
                rs = pstm.executeQuery();
                Client cli = null;

                if (rs.next()) {
                    String titre = rs.getString("TITRE_PROJ");
                    String debut = (rs.getDate("DATE_DEBUT")).toString();
                    String butoir = (rs.getDate("DATE_BUTOIR")).toString();
                    String nom = rs.getString("NOM_CLI");
                    String ville = rs.getString("VILLE_CLI");
                    String tel = rs.getString("TEL_CLI");

                    Client.ClientBuilder cb = new Client.ClientBuilder();
                    try {
                        cli = cb.setNom(nom).setTel(tel).setVille(ville).build();
                    } catch (Exception e) {
                        System.err.println("Erreur client " + e);
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

        if (o instanceof Employe) {
            query = "SELECT * FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ? AND EMAIL_EMP = ?";

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Employe) o).getNom());
                pstm.setString(2, ((Employe) o).getPrenom());
                pstm.setString(3, ((Employe) o).getEmail());
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String nom = rs.getString(2);
                    String prenom = rs.getString(3);
                    String gsm = rs.getString(4);
                    String email = rs.getString(5);

                    Employe.EmployeBuilder eb = new Employe.EmployeBuilder();

                    try {
                        o1 = eb.setNom(nom).setEmail(email).setGsm(gsm).setPrenom(prenom).build();
                    } catch (Exception e) {
                        System.err.println("Erreur employé " + e);
                    }

                }

            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche employé " + e);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur de fermeture du preparedStatement " + e);
                }
            }
        }

        if (o instanceof Client) {
            query = "SELECT * FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ?";

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Client) o).getNom());
                pstm.setString(2, ((Client) o).getVille());
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String nom = rs.getString(2);
                    String ville = rs.getString(3);
                    String tel = rs.getString(4);

                    Client.ClientBuilder cb = new Client.ClientBuilder();

                    try {
                        o1 = cb.setNom(nom).setTel(tel).setVille(ville).build();
                    } catch (Exception e) {
                        System.err.println("Erreur client " + e);
                    }

                }

            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche client " + e);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur de fermeture du preparedStatement " + e);
                }
            }
        }

        return o1;
    }

    public List<Discipline> toutesDisciplines(){
        String critere = "ORDER BY NOM_DISC";
        
        String query = "SELECT * FROM PROJ_DISCIPLINE "+critere;
        List<Discipline> ld = new ArrayList<>();
        
        Statement stm = null;
        ResultSet rs = null;
        
        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nom = rs.getString("NOM_DISC");
                
                Discipline d = new Discipline(nom);
                ld.add(d);
                
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des disciplines " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return ld;
    }
    
    
    @Override
    public List<Client> tousClients() {
        String critere = "ORDER BY NOM_CLI";

        String query = "SELECT * FROM PROJ_CLIENT " + critere;
        List<Client> lc = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nom = rs.getString("NOM_CLI");
                String ville = rs.getString("VILLE_CLI");
                String tel = rs.getString("TEL_CLI");
                Client cli = null;

                Client.ClientBuilder cb = new Client.ClientBuilder();
                try {
                    cli = cb.setNom(nom).setTel(tel).setVille(ville).build();
                } catch (Exception e) {
                    System.err.println("Erreur client " + e);
                }

                lc.add(cli);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des clients " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return lc;
    }

    @Override
    public List<Employe> tousEmployes() {
        String critere = "ORDER BY NOM_EMP,PRENOM_EMP,GSM_EMP";

        String query = "SELECT * FROM PROJ_EMPLOYE " + critere;
        List<Employe> le = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String nom = rs.getString("NOM_EMP");
                String prenom = rs.getString("PRENOM_EMP");
                String gsm = rs.getString("GSM_EMP");
                String email = rs.getString("EMAIL_EMP");
                Employe emp = null;

                Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
                try {
                    emp = eb.setEmail(email).setGsm(gsm).setNom(nom).setPrenom(prenom).build();
                } catch (Exception e) {
                    System.err.println("Erreur employé " + e);
                }

                le.add(emp);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des employés " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return le;
    }

    @Override
    public List<Projet> tousProjets() {
        String critere = "ORDER BY CLIENT_PROJ, TITRE";

        String query = "SELECT * FROM PROJ_PROJET P\n"
                + "INNER JOIN PROJ_CLIENT CL ON P.CLIENT_PROJ = CL.ID_CLI\n"
                + critere;
        List<Projet> lp = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String titre = rs.getString("TITRE_PROJ");
                String debut = (rs.getDate("DATE_DEBUT")).toString();
                String butoir = (rs.getDate("DATE_BUTOIR")).toString();
                String nom = rs.getString("NOM_CLI");
                String ville = rs.getString("VILLE_CLI");
                String tel = rs.getString("TEL_CLI");

                Client cli = null;
                Projet p = null;

                Client.ClientBuilder cb = new Client.ClientBuilder();
                try {
                    cli = cb.setNom(nom).setTel(tel).setVille(ville).build();
                } catch (Exception e) {
                    System.err.println("Erreur client " + e);
                }

                Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
                try {
                    p = pb.setClient(cli).setDateButoir(butoir).setDateDebut(debut).setTitre(titre).build();
                } catch (Exception e) {
                    System.err.println("Erreur projet " + e);
                }

                lp.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des projets " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return lp;
    }

    //TODO Modifier le code pour les travaux
    @Override
    public List<Travail> tousTravaux() {
        String critere = "ORDER BY ";

        String query = "SELECT * FROM PROJ_TRAVAIL" + critere;
        List<Travail> lt = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                //Recupérer les données
                //Ajouter à la liste
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des travaux " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return lt;
    }

    @Override
    public List<Projet> listeProjetsClient(Client c) {
        String critere = "ORDER BY TITRE";

        String query = "SELECT * FROM PROJ_PROJET WHERE CLIENT_PROJ = (SELECT ID_CLI FROM PROJ_CLIENT "
                + "WHERE CL.NOM_CLI = " + c.getNom() + "AND CL.VILLE_CLI = " + c.getVille()
                + ")\n" + critere;
        List<Projet> lp = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                String titre = rs.getString("TITRE_PROJ");
                String debut = (rs.getDate("DATE_DEBUT")).toString();
                String butoir = (rs.getDate("DATE_BUTOIR")).toString();

                Projet p = null;

                Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
                try {
                    p = pb.setClient(c).setDateButoir(butoir).setDateDebut(debut).setTitre(titre).build();
                } catch (Exception e) {
                    System.err.println("Erreur projet " + e);
                }

                lp.add(p);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des projets " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return lp;
    }

    @Override
    //TODO compléter le code
    public List<Travail> listeTravailEmploye(Employe emp) {
        String critere = "ORDER BY ";

        String query = "SELECT * FROM PROJ_TRAVAIL" + critere;
        List<Travail> lt = new ArrayList<>();

        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                //Récupérer les données de la requête
                //Ajouter à la liste
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des travaux " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return lt;
    }

    @Override
    public String changeVilleClient(Client c, String v) {
        String query = "UPDATE PROJ_CLIENT SET VILLE_CLI = ? WHERE NOM_CLI = ?";
        PreparedStatement pstm = null;
        String msg;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, v);
            pstm.setString(2, c.getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Changement de gsm effectué";
            } else {
                msg = "Changement d'adresse non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement d'adresse " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String changeTelClient(Client c, String t) {
        String query = "UPDATE PROJ_CLIENT SET TEL_CLI = ? WHERE NOM_CLI = ?";
        PreparedStatement pstm = null;
        String msg;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, t);
            pstm.setString(2, c.getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Changement de téléphone effectué";
            } else {
                msg = "Changement de téléphone non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement de téléphone " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String changeGsmEmploye(Employe emp, String gsm) {
        String query = "UPDATE PROJ_EMPLOYE SET GSM_EMP = ? WHERE NOM_EMP = ? AND PRENOM_EMP = ? AND EMAIL_EMP = ?";
        PreparedStatement pstm = null;
        String msg;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, gsm);
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());
            pstm.setString(4, emp.getEmail());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Changement de gsm effectué";
            } else {
                msg = "Changement de gsm non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement de gsm " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String changeEmailEmploye(Employe emp, String email) {
        String query = "UPDATE PROJ_EMPLOYE SET EMAIL_EMP = ? WHERE NOM_EMP = ? AND PRENOM_EMP = ? AND GSM_EMP = ?";
        PreparedStatement pstm = null;
        String msg;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, email);
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());
            pstm.setString(4, emp.getGsm());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Changement d'email effectué";
            } else {
                msg = "Changement d'email non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement d'email " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String changePourcentage(Travail t, float p) {
        //TODO prévoir un trigger pour ça
        return null;
    }

    @Override
    public String suppClient(Client c) {
        String msg;
        String query = "DELETE FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ? AND TEL = ?";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, c.getNom());
            pstm.setString(2, c.getVille());
            pstm.setString(3, c.getTel());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression du client effectuée";
            } else {
                msg = "Suppression du client non effectuée (impossible ?)";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression du client " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String suppProjet(Projet p) {
        String msg;

        //On supprime d'abord les travaux liés au projet
        String query = "DELETE FROM PROJ_TRAVAIL WHERE ID_PROJ = (SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());

            int n = pstm.executeUpdate();

            if (n > 0) {
                msg = "Suppression des travaux liés au projet effectuée";
            } else {
                msg = "Aucun travail associé au projet";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression des travaux liés au projet " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }
        
        //On supprime ensuite le projet
        query = "DELETE FROM PROJ_PROJET WHERE TITRE_PROJ = ? AND CLIENT = ?";
       
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            pstm.setInt(2, recupIdCli(p.getClient()));            

            int n = pstm.executeUpdate();

            if (n == 1) {
                msg += "Suppression du projet effectuée";
            } else {
                msg += "Suppression du projet non effectuée";
            }

        } catch (SQLException e) {
            msg += "Erreur lors de la suppression du projet " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg += "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public String suppEmploye(Employe emp) {
        String msg;
        String query = "DELETE FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ? "
                + "AND GSM_EMP = ? AND EMAIL_EMP = ?";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, emp.getNom());
            pstm.setString(2, emp.getPrenom());
            pstm.setString(3, emp.getGsm());
            pstm.setString(4, emp.getEmail());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression de l'employé effectuée";
            } else {
                msg = "Suppression de l'employé non effectuée";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression de l'employé " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    @Override
    public Client dernierClient() {
        String query = "SELECT * FROM PROJ_CLIENT WHERE ID_CLI = LAST_INSERT_ID()";

        Statement stm = null;
        ResultSet rs = null;
        Client cli = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                String nom = rs.getString("NOM_CLI");
                String ville = rs.getString("VILLE_CLI");
                String tel = rs.getString("TEL_CLI");

                Client.ClientBuilder cb = new Client.ClientBuilder();

                try {
                    cli = cb.setNom(nom).setTel(tel).setVille(ville).build();
                } catch (Exception e) {
                    System.err.println("Erreur client " + e);
                }
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du dernier client encodé " + e);
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du ResultSet " + e);
            }

            try {
                if (stm != null) {
                    stm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du Statement " + e);
            }
        }

        return cli;
    }
}
