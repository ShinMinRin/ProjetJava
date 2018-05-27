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

    /**
     * Constructeur de la classe, permet de se connecter à la bdd
     */
    public ProjModeleJDBC() {
        dbconnect = DBconnect.getConnection();
        if (dbconnect == null) {
            System.err.println("erreur de connexion => arrêt du pgm");
            System.exit(1);
        }
    }

    /**
     * Permet de se déconnecter de la bdd
     */
    public void close() {
        try {
            dbconnect.close();
        } catch (Exception e) {
            System.err.println("erreur lors de la fermeture de la connexion " + e);
        }
    }

    /**
     * Réecriture de la méthode du modèle en liste Méthode permettant de peupler
     * les données, inutile en JDBC
     */
    @Override
    public void populate() {
        //ne rien faire car données déjà présentes dans DB
    }

    /**
     * Méthode ajoutant une ligne dans la table concernée par l'objet ajouté
     *
     * @param o objet à ajouter à la bdd
     * @return msg diagnostic de l'ajout
     */
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
            query = "insert into PROJ_PROJET(TITRE_PROJ,CLIENT_PROJ,DATE_DEBUT,DATE_BUTOIR) VALUES (?,"
                    + "(SELECT ID_CLI FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ?)"
                    + ",?,?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Projet) o).getTitre());
                pstm.setString(2, ((Projet) o).getClient().getNom());
                pstm.setString(3, ((Projet) o).getClient().getVille());
                pstm.setString(4, ((Projet) o).getDateDebut().replace("/", "-"));
                pstm.setString(5, ((Projet) o).getDateButoir().replace("/", "-"));
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
            query = "insert into PROJ_TRAVAIL(ID_PROJ, ID_EMP, DATE_ENGAGEMENT, POURCENTAGE) VALUES ("
                    + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?), "
                    + "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ? AND GSM_EMP = ?), ?, ?)";
            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Travail) o).getProjet().getTitre());
                pstm.setString(2, ((Travail) o).getEmploye().getNom());
                pstm.setString(3, ((Travail) o).getEmploye().getPrenom());
                pstm.setString(4, ((Travail) o).getEmploye().getGsm());
                pstm.setString(5, ((Travail) o).getDateEngagement().replace("/", "-"));
                pstm.setFloat(6, ((Travail) o).getPourcentage());

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

        if (o instanceof Temps) {
            query = "INSERT INTO PROJ_TEMPS(NB_JH, ID_DISC, ID_PROJ) VALUES(?, "
                    + "(SELECT ID_DISC FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?), "
                    + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?))";

            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setInt(1, ((Temps) o).getNb_jh());
                pstm.setString(2, ((Temps) o).getDiscipline().getNom());
                pstm.setString(3, ((Temps) o).getProjet().getTitre());

                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout temps effectué";
                } else {
                    msg = "Ajout temps non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout du temps " + e;
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

        if (o instanceof Competence) {
            query = "INSERT INTO PROJ_COMPETENCE(ID_EMP, ID_DISC, ID_NIV) VALUES("
                    + "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?), "
                    + "(SELECT ID_DISC FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?), "
                    + "(SELECT ID_NIV FROM PROJ_NIVEAU WHERE DESC_NIV = ?))";

            PreparedStatement pstm = null;

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Competence) o).getPersonne().getNom());
                pstm.setString(2, ((Competence) o).getPersonne().getPrenom());
                pstm.setString(3, ((Competence) o).getDiscipline().getNom());
                pstm.setString(4, ((Competence) o).getNiveau().getSignification());
                int n = pstm.executeUpdate();

                if (n == 1) {
                    msg = "Ajout compétence effectué";
                } else {
                    msg = "Ajout compétence non effectué";
                }
            } catch (SQLException e) {
                msg = "Erreur lors de l'ajout du compétence " + e;
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

    /**
     * Méthode récupérant une ligne dans la bdd
     *
     * @param o objet avec des informations partielles à retrouver dans la bdd
     * @return o1 objet avec les informations complètes
     */
    @Override
    public Object getObject(Object o) {
        String query;
        PreparedStatement pstm = null;
        Object o1 = new Object();
        o1 = null;
        ResultSet rs = null;

        //On vérifie que l'objet n'est pas null
        if (o == null) {
            return "Objet null. Recherche impossible";
        }

        //On détermine la table correspondante au type d'objet
        if (o instanceof Projet) {
            query = "SELECT * FROM PROJ_PROJET P "
                    + "INNER JOIN PROJ_CLIENT CL ON P.CLIENT_PROJ = CL.ID_CLI "
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
            query = "SELECT * FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?";

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Employe) o).getNom());
                pstm.setString(2, ((Employe) o).getPrenom());
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
                    String nom = rs.getString("NOM_CLI");
                    String ville = rs.getString("VILLE_CLI");
                    String tel = rs.getString("TEL_CLI");

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
                    System.err.println("Erreur de fermeture du ResulSet " + e);
                }
            }
        }

        if (o instanceof Discipline) {
            query = "SELECT * FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?";

            try {
                pstm = dbconnect.prepareStatement(query);
                pstm.setString(1, ((Discipline) o).getNom());
                rs = pstm.executeQuery();

                if (rs.next()) {
                    String nom = rs.getString("NOM_DISC");
                    o1 = new Discipline(nom);
                }

            } catch (SQLException e) {
                System.err.println("Erreur lors de la recherche discipline " + e);
            } finally {
                try {
                    if (rs != null) {
                        rs.close();
                    }
                } catch (SQLException e) {
                    System.err.println("Erreur de fermeture du ResultSet");
                }
            }
        }

        return o1;
    }

    /**
     * Méthode retournant toutes les lignes de la table PROJ_DISCIPLINE
     * @return ArrayList avec les informations sur les différentes disciplines encodées
     */
    public List<Discipline> toutesDisciplines() {
        String critere = "ORDER BY NOM_DISC";

        String query = "SELECT * FROM PROJ_DISCIPLINE " + critere;
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

    /**
     * Méthode retournant toutes les lignes de la table PROJ_CLIENT
     * @return ArrayList avec les informations des clients encodés
     */
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

    /**
     * Méthode retournant toutes les lignes de la table PROJ_EMPLOYE
     * @return ArrayList avec les informations des employés encodés
     */
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

    /**
     * Méthode retournant toutes les lignes de la table PROJ_PROJET
     * @return ArrayList avec les informations des projets encodés
     */
    @Override
    public List<Projet> tousProjets() {
        String critere = "ORDER BY CLIENT_PROJ, TITRE_PROJ";

        String query = "SELECT * FROM PROJ_PROJET P "
                + "INNER JOIN PROJ_CLIENT CL ON P.CLIENT_PROJ = CL.ID_CLI "
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

    /**
     * Méthode retournant les différents niveaux de compétences possibles
     * @return ArrayList avec les informations des niveaux encodés
     */
    public List<Niveau> tousNiveaux() {
        List<Niveau> ln = new ArrayList<>();
        String query = "SELECT * FROM PROJ_NIVEAU ORDER BY ID_NIV";
        Statement stm = null;
        ResultSet rs = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            while (rs.next()) {
                int degre = rs.getInt("ID_NIV");
                String desc = rs.getString("DESC_NIV");
                Niveau n = new Niveau(degre, desc);
                ln.add(n);
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

        return ln;
    }

    /**
     * Méthode retournant tous les projets d'un client donné
     * @param c Client concerné
     * @return ArrayList avec les informations sur les projets du client c
     */
    @Override
    public List<Projet> listeProjetsClient(Client c) {
        String critere = "ORDER BY TITRE";
        String query = "SELECT * FROM PROJ_PROJET WHERE CLIENT_PROJ = (SELECT ID_CLI FROM PROJ_CLIENT "
                + "WHERE CL.NOM_CLI = ? AND CL.VILLE_CLI = ?"
                + ") " + critere;

        List<Projet> lp = new ArrayList<>();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, c.getNom());
            pstm.setString(2, c.getVille());
            rs = pstm.executeQuery();

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
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                System.err.println("Erreur de fermeture du preparedStatement " + e);
            }
        }

        return lp;
    }

    /**
     * Méthode retournant toutes les disciplines présentes sur un projet donné
     * @param p Projet concerné
     * @return ArrayList avec les informations sur les disciplines du projet p
     */
    public List<Discipline> listeDiscProj(Projet p) {
        String query = "SELECT * FROM PROJ_DISCIPLINE WHERE ID_DISC IN ("
                + "SELECT ID_DISC FROM PROJ_TEMPS WHERE ID_PROJ =("
                + "SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?))";
        List<Discipline> ld = new ArrayList<>();

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            rs = pstm.executeQuery();

            while (rs.next()) {
                String nom = rs.getString("NOM_DISC");

                Discipline d = new Discipline(nom);
                ld.add(d);

            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des disciplines du projet " + e);
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
                System.err.println("Erreur de fermeture du PreparedStatement " + e);
            }
        }

        return ld;
    }

    /**
     * Méthode retournant tous les employés travaillant sur un projet donné
     * @param p Projet concerné
     * @return ArrayList avec les informations sur les employés du projet p
     */
    public List<Employe> listeEmployeDuProjet(Projet p) {
        String query = "SELECT * FROM PROJ_EMPLOYE WHERE ID_EMP IN("
                + "SELECT ID_EMP FROM PROJ_TRAVAIL WHERE ID_PROJ ="
                + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?))";

        List<Employe> le = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            rs = pstm.executeQuery();

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
            System.err.println("Erreur lors de la recherche des employés sur un projet " + e);
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

        return le;
    }

    /**
     * Méthode retournant tous les projets sur lesquels un employé travaille
     * @param emp Employé concerné
     * @return ArrayList avec les informations des projets de l'employé emp
     */
    public List<Projet> listeProjetParEmploye(Employe emp) {
        String query = "SELECT * FROM PROJ_PROJET P "
                + "INNER JOIN PROJ_CLIENT CL ON CL.ID_CLI = P.CLIENT_PROJ "
                + "WHERE P.ID_PROJ IN ("
                + "SELECT T.ID_PROJ FROM PROJ_TRAVAIL T WHERE T.ID_EMP ="
                + "(SELECT E.ID_EMP FROM PROJ_EMPLOYE E "
                + "WHERE E.NOM_EMP = ? AND E.PRENOM_EMP = ?))";

        List<Projet> lp = new ArrayList<>();
        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, emp.getNom());
            pstm.setString(2, emp.getPrenom());
            rs = pstm.executeQuery();

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
            System.err.println("Erreur lors de la recherche des projets d'un employé " + e);
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

        return lp;
    }

    /**
     * Méthode retournant toutes les compétences attribuées à un employé
     * @param emp Employé concerné
     * @return ArrayList avec les compétences de l'employé emp
     */
    public List<Competence> listeCompEmp(Employe emp) {
        List<Competence> lc = new ArrayList<>();

        String query = "SELECT * FROM PROJ_COMPETENCE C "
                + "INNER JOIN PROJ_DISCIPLINE D ON C.ID_DISC = D.ID_DISC "
                + "INNER JOIN PROJ_NIVEAU N ON C.ID_NIV = N.ID_NIV "
                + "WHERE ID_EMP = "
                + "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?)";

        PreparedStatement pstm = null;
        ResultSet rs = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, emp.getNom());
            pstm.setString(2, emp.getPrenom());
            rs = pstm.executeQuery();

            while (rs.next()) {
                Discipline d = new Discipline(rs.getString("NOM_DISC"));
                Niveau n = new Niveau(rs.getInt("ID_NIV"), rs.getString("DESC_NIV"));
                Competence c = new Competence(emp, d, n);
                lc.add(c);
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche des compétences d'un employé " + e);
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

        return lc;
    }

    /**
     * Méthode modifiant la ville d'un client
     * @param c Client concerné
     * @param v Nouvelle ville
     * @return msg Diagnostic de la modification
     */
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
                msg = "Changement de ville effectué";
            } else {
                msg = "Changement de ville non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement de ville " + e;
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

    /**
     * Méthode modifiant le numéro de téléphone d'un client
     * @param c Client concerné
     * @param t Nouveau numéro de téléphone
     * @return msg Diagnostic de la modification
     */
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

    /**
     * Méthode modifiant le numéro de gsm d'un employé
     * @param emp Employé concerné
     * @param gsm Nouveau numéro de gsm
     * @return msg Diagnostic de la modification
     */
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

    /**
     * Méthode modifiant l'adresse email d'un employé
     * @param emp Employé concerné
     * @param email Nouvelle adresse email
     * @return msg Diagnostic de la modification
     */
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

    /**
     * Méthode modifiant le niveau d'une compétence d'un employé
     * @param emp Employé concerné
     * @param d Discipline concernée
     * @param niv Nouveau niveau de compétence dans la discipline pour cet employé
     * @return msg Diagnostic de la modification
     */
    public String changeNivComp(Employe emp, Discipline d, Niveau niv) {
        String msg;
        String query = "UPDATE PROJ_COMPETENCE SET ID_NIV ="
                + "(SELECT ID_NIV FROM PROJ_NIVEAU WHERE DESC_NIV = ?) "
                + "WHERE ID_EMP = "
                + "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?) "
                + "AND ID_DISC = "
                + "(SELECT ID_DISC FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?)";

        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, niv.getSignification());
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());
            pstm.setString(4, d.getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Changement de niveau effectué";
            } else {
                msg = "Changement de niveau non effectué";
            }
        } catch (SQLException e) {
            msg = "Erreur lors du changement de niveau " + e;
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

    /**
     * Méthode modifiant la date butoir d'un projet
     * @param p Projet concerné
     * @param dateB Nouvelle date butoir
     * @return msg Diagnotic de la modification
     */
    public String changeDateButoirProj(Projet p, String dateB) {
        String msg;
        String query = "UPDATE PROJ_PROJET SET DATE_BUTOIR = ? WHERE TITRE_PROJ = ?";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, dateB);
            pstm.setString(2, p.getTitre());

            int n = pstm.executeUpdate();

            if (n == 1) {
                msg = "Changement de la date butoir effectué";
            } else {
                msg = "Changement de la date butoir non effectué";
            }
        } catch (SQLException e) {
            msg = "Errreur lors du changement de la date butoir " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture du preparedStatement " + e;
            }
        }

        return msg;
    }

    /**
     * Méthode modifiant le pourcentage de temps consacré par un employé sur un projet
     * @param t Travail concerné (lien entre le projet et l'employé)
     * @param p Nouveau pourcentage
     * @return msg Diagnostic de la modification
     */
    @Override
    public String changePourcentage(Travail t, float p) {
        String msg;
        String query = "UPDATE PROJ_TRAVAIL SET POURCENTAGE = ? "
                + "WHERE ID_PROJ = (SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?) "
                + "AND ID_EMP = (SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ? )";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setFloat(1, p);
            pstm.setString(2, t.getProjet().getTitre());
            pstm.setString(3, t.getEmploye().getNom());
            pstm.setString(4, t.getEmploye().getPrenom());

            int n = pstm.executeUpdate();

            if (n == 1) {
                msg = "Changement du pourcentage effectué";
            } else {
                msg = "Changement du pourcentage non effectué";
            }
        } catch (SQLException e) {
            msg = "Errreur lors du changement du pourcentage " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg = "Erreur de fermeture du preparedStatement " + e;
            }
        }

        return msg;
    }

    /**
     * Méthode supprimant un client de la bdd
     * @param c Client à supprimer
     * @return msg Diagnostic de la suppression
     */
    @Override
    public String suppClient(Client c) {
        String msg;
        String query = "DELETE FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ? AND TEL_CLI = ?";
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

    /**
     * Méthode supprimant un projet et les travaux associés de la bdd
     * @param p Projet à supprimer
     * @return msg Diagnostic de la suppression
     */
    @Override
    public String suppProjet(Projet p) {
        String msg;

        //On supprime d'abord les travaux liés au projet
        String query = "DELETE FROM PROJ_TRAVAIL WHERE ID_PROJ = (SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?)";
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

        //On supprime les lignes de la table PROJ_TEMPS concernées
        query = "DELETE FROM PROJ_TEMPS WHERE ID_PROJ = "
                + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?)";
        
        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());

            int n = pstm.executeUpdate();

            if (n > 0) {
                msg += "\nSuppression des temps liés au projet effectuée";
            } else {
                msg += "\nAucun temps associé au projet";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression des temps liés au projet " + e;
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
        query = "DELETE FROM PROJ_PROJET WHERE TITRE_PROJ = ? AND CLIENT_PROJ = "
                + "(SELECT ID_CLI FROM PROJ_CLIENT WHERE NOM_CLI = ? AND VILLE_CLI = ?)";

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            pstm.setString(2, p.getClient().getNom());
            pstm.setString(3, p.getClient().getVille());

            int n = pstm.executeUpdate();

            if (n == 1) {
                msg += "\nSuppression du projet effectuée";
            } else {
                msg += "\nSuppression du projet non effectuée";
            }

        } catch (SQLException e) {
            msg += "\nErreur lors de la suppression du projet " + e;
        } finally {
            try {
                if (pstm != null) {
                    pstm.close();
                }
            } catch (SQLException e) {
                msg += "\nErreur de fermeture de preparedStatement " + e;
            }
        }

        return msg;
    }

    /**
     * Méthode supprimant un employé de la bdd
     * @param emp Employé  supprimer
     * @return msg Diagnostic de la suppression
     */
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

    /**
     * Méthode supprimant une compétence à un employé donné
     * @param emp Employé concerné
     * @param c Compétence de l'employé à supprimer
     * @return msg Diagnostic de la suppression
     */
    public String suppCompEmp(Employe emp, Competence c) {
        String msg;
        String query = "DELETE FROM PROJ_COMPETENCE WHERE ID_EMP = "
                + "(SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?) "
                + "AND ID_DISC = (SELECT ID_DISC FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?)";

        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, emp.getNom());
            pstm.setString(2, emp.getPrenom());
            pstm.setString(3, c.getDiscipline().getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression de la compétence de l'employé effectuée";
            } else {
                msg = "Suppression de la compétence de l'employé non effectuée";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression de la compétence de l'employé " + e;
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

    /**
     * Méthode supprimant un employé d'un projet donné
     * @param p Projet concerné
     * @param emp Employé à supprimer du projet
     * @return msg Diagnostic de la suppression
     */
    public String suppEmpProj(Projet p, Employe emp) {
        String msg;
        String query = "DELETE FROM PROJ_TRAVAIL WHERE ID_PROJ ="
                + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?) "
                + "AND ID_EMP = (SELECT ID_EMP FROM PROJ_EMPLOYE WHERE NOM_EMP = ? AND PRENOM_EMP = ?)";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            pstm.setString(2, emp.getNom());
            pstm.setString(3, emp.getPrenom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression de l'employé du projet effectuée";
            } else {
                msg = "Suppression de l'employé du projet non effectuée";
            }

        } catch (SQLException e) {
            msg = "Erreur lors de la suppression de l'employé du projet " + e;
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

    /**
     * Méthode supprimant une discipline de la bdd
     * @param d Discipline à supprimer
     * @return msg Diagnostic de la suppression
     */
    public String suppDiscipline(Discipline d) {
        String msg;
        String query = "DELETE FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?";
        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, d.getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression de la discipline effectuée";
            } else {
                msg = "Suppression de la discipline non effectuée";
            }
        } catch (SQLException e) {
            msg = "Erreur lors de la suppression de la discipline " + e;
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

    /**
     * Méthode supprimant une discipline d'un projet
     * @param p Projet concerné
     * @param d Discipline à supprimer du projet
     * @return msg Diagnostic de la suppression
     */
    public String suppDiscProj(Projet p, Discipline d) {
        String msg;
        String query = "DELETE FROM PROJ_TEMPS WHERE ID_PROJ ="
                + "(SELECT ID_PROJ FROM PROJ_PROJET WHERE TITRE_PROJ = ?)"
                + "AND ID_DISC ="
                + "(SELECT ID_DISC FROM PROJ_DISCIPLINE WHERE NOM_DISC = ?)";

        PreparedStatement pstm = null;

        try {
            pstm = dbconnect.prepareStatement(query);
            pstm.setString(1, p.getTitre());
            pstm.setString(2, d.getNom());

            int n = pstm.executeUpdate();
            if (n == 1) {
                msg = "Suppression de la discipline effectuée";
            } else {
                msg = "Suppression de la discipline non effectuée";
            }
        } catch (SQLException e) {
            msg = "Erreur lors de la suppression de la discipline " + e;
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

    /**
     * Méthode retournant le dernier client encodé dans la bdd
     * @return dernier client ajouté
     */
    @Override
    public Client dernierClient() {
        String query = "SELECT * FROM PROJ_CLIENT WHERE ID_CLI = (SELECT MAX(ID_CLI) FROM PROJ_CLIENT)";

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

    /**
     * Méthode retournant la dernière discipline encodée dans la bdd
     * @return Dernière discipline ajoutée
     */
    public Discipline derniereDiscipline() {
        String query = "SELECT * FROM PROJ_DISCIPLINE WHERE ID_DISC = (SELECT MAX(ID_DISC) FROM PROJ_DISCIPLINE)";

        Statement stm = null;
        ResultSet rs = null;
        Discipline d = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                String nom = rs.getString("NOM_DISC");
                d = new Discipline(nom);
            }
        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche de la dernière discipline encodée " + e);
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

        return d;
    }

    /**
     * Méthode retournant le dernier employé encodé dans la bdd
     * @return Dernier employé ajouté
     */
    public Employe dernierEmploye() {
        String query = "SELECT * FROM PROJ_EMPLOYE WHERE ID_EMP = (SELECT MAX(ID_EMP) FROM PROJ_EMPLOYE)";

        Statement stm = null;
        ResultSet rs = null;
        Employe emp = null;

        try {
            stm = dbconnect.createStatement();
            rs = stm.executeQuery(query);

            if (rs.next()) {
                String nom = rs.getString("NOM_EMP");
                String prenom = rs.getString("PRENOM_EMP");
                String gsm = rs.getString("GSM_EMP");
                String email = rs.getString("EMAIL_EMP");

                Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
                try {
                    emp = eb.setEmail(email).setGsm(gsm).setNom(nom).setPrenom(prenom).build();
                } catch (Exception e) {
                    System.err.println("Erreur employé " + e);
                }
            }

        } catch (SQLException e) {
            System.err.println("Erreur lors de la recherche du dernier employé encodé " + e);
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

        return emp;
    }
}
