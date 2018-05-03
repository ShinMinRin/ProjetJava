package modele;

/**
 *
 * @author Tiffany De Schinckel
 * @version 1.0
 */
import projetjava18.Log;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;

public class ProjModele {

    private static ProjModele instance = null;

    public ProjModele() {

    }

    public static ProjModele getInstance() {
        if (instance == null) {
            return instance = new ProjModele();
        } else {
            return instance;
        }
    }

    protected List<Projet> mesProjets = new ArrayList<>();
    protected List<Client> mesClients = new ArrayList<>();
    protected List<Employe> mesEmployes = new ArrayList<>();
    protected List<Travail> listeTravail = new ArrayList<>();

    /**
     * Méthode permettant l'ajout d'un objet
     *
     * @param o objet à ajouter
     * @return diagnostic de l'ajout
     */
    public String ajouterObjet(Object o) {
        List l = null;
        String msg = "";

        //On détermine la liste correspondante au type d'objet
        if (o instanceof Projet) {
            l = mesProjets;
            msg = "projet";
        }
        if (o instanceof Employe) {
            l = mesEmployes;
            msg = "employé";
        }
        if (o instanceof Client) {
            l = mesClients;
            msg = "client";
        }
        if (o instanceof Travail) {
            l = listeTravail;
            msg = "travail";
        }

        if (o == null) {
            return msg + " null";
        }
        if (l == null) {
            return "Type d'objet inconnu";
        }

        if (l.contains(o)) {
            Log log = Log.getInstance();
            log.ajouter("Refus de l'ajout de " + o);
            return "Ajout " + msg + " refusé";
        }

        l.add(o);
        Log log = Log.getInstance();
        log.ajouter("Enregistrement de " + o);
        return "Ajout " + msg + " effectué";
    }

    /**
     * Méthode permettant de trouver un objet
     *
     * @param o objet encapsulant les information de base
     * @return objet trouvé, ou null si introuvable
     */
    public Object getObject(Object o) {
        List l = null;

        //On associe l'objet à la bonne liste selon le type d'objet
        if (o instanceof Projet) {
            l = mesProjets;
        }
        if (o instanceof Employe) {
            l = mesEmployes;
        }
        if (o instanceof Client) {
            l = mesClients;
        }
        if (o instanceof Travail) {
            l = listeTravail;
        }

        int p = l.indexOf(o);

        if (p < 0) {
            return null;
        } else {
            return l.get(p);
        }
    }

    /**
     * Méthode permettant d'obtenir la liste des clients
     *
     * @return tous les clients de la liste
     */
    public List<Client> tousClients() {
        mesClients.sort(new ClientComparator());
        return mesClients;
    }

    /**
     * Méthode permettant d'obtenir la liste des employés
     *
     * @return tous les employés de la liste
     */
    public List<Employe> tousEmployes() {
        mesEmployes.sort(new EmployeComparator());
        return mesEmployes;
    }

    /**
     * Méthode permettant d'obtenir la liste des projets
     *
     * @return tous les projets de la liste
     */
    public List<Projet> tousProjets() {
        mesProjets.sort(new ProjetComparator());
        return mesProjets;
    }

    /**
     * Méthode permettant d'obtenir la liste des travaux
     *
     * @return tous les travaux (lien entre projet et employé)
     */
    public List<Travail> tousTravaux() {
        return listeTravail;
    }

    /**
     * Méthode permettant d'obtenir la liste des projets par client
     *
     * @param c Client concerné
     * @return tous les projets d'un client en particulier
     */
    public List<Projet> listeProjetsClient(Client c) {
        List<Projet> l = new ArrayList<>();

        if (c == null) {
            return l;
        }
        if (mesProjets.isEmpty()) {
            return l;
        }

        for (Projet p : mesProjets) {
            Client cl = p.getClient();
            if (cl.equals(c)) {
                l.add(p);
            }
        }

        return l;
    }

    /**
     * Méthode permettant d'obtenir la liste des projets par employé avec la
     * date d'engagement et le pourcentage de travail pour l'employé
     *
     * @param e Employé concerné
     * @return tous les détails des projets pour lesquels l'employé travaille
     */
    public List<Travail> listeTravailEmploye(Employe e) {
        List<Travail> l = new ArrayList<>();

        if (e == null) {
            return l;
        }
        if (listeTravail.isEmpty()) {
            return l;
        }

        for (Travail t : listeTravail) {
            Employe emp = t.getEmploye();
            if (emp.equals(e)) {
                l.add(t);
            }
        }

        return l;
    }

    /**
     * Méthode permettant de modifier la ville du client
     *
     * @param c client dont on veut modifier l'adresse
     * @param v nouvelle ville
     * @return diagnostic du changement
     */
    public String changeVilleClient(Client c, String v) {
        c.setVille(v);
        return "Changement d'adresse effectué";
    }

    /**
     * Méthode permettant de modifier le numéro de téléphone du client
     *
     * @param c client dont on veut modifier le numéro de téléphone
     * @param t nouveau numéro de téléphone
     * @return diagnostic du changement
     */
    public String changeTelClient(Client c, String t) {
        c.setTel(t);
        return "Changement de téléphone effectué";
    }

    /**
     * Méthode permettant de modifier le numéro de GSM de l'employé
     *
     * @param e employé dont on veut modifier le gsm
     * @param gsm nouveau numéro de gsm
     * @return diagnostic du changement
     */
    public String changeGsmEmploye(Employe e, String gsm) {
        e.setGsm(gsm);
        return "Changement de numéro de gsm effectué";
    }

    /**
     * Méthode permettant de modifier l'adresse email de l'employé
     *
     * @param e employé dont on veut modifier l'adresse email
     * @param email nouvelle adresse email
     * @return diagnostic du changement
     */
    public String changeEmailEmploye(Employe e, String email) {
        e.setEmail(email);
        return "Changement d'email effectué";
    }

    /**
     * Méthode permettant de modifier le pourcentage de temps consacré au projet
     * par l'employé concerné
     *
     * @param t travail concerné (lien entre projet et employé)
     * @param p nouveau pourcentage
     * @return diagnostic du changement
     */
    public String changePourcentage(Travail t, float p) {
        t.setPourcentage(p);
        return "Changement de pourcentage effectué";
    }

    /**
     * Méthode permettant de supprimer un client
     *
     * @param c client à supprimer
     * @return diagnostic de la suppression
     */
    public String suppClient(Client c) {
        List<Projet> lp = listeProjetsClient(c);
        if (!lp.isEmpty()) {
            return "Suppression impossible car le client possède des projets";
        }

        boolean ok = mesClients.remove(c);
        if (!ok) {
            return "Client introuvable ou suppression impossible";
        } else {
            return "Client supprimé";
        }
    }

    /**
     * Méthode permettant de supprimer un projet
     *
     * @param p projet à supprimer
     * @return diagnostic de la suppression
     */
    public String suppProjet(Projet p) {
        boolean ok = mesProjets.remove(p);

        if (ok) {
            //On supprime les travaux liés au projet
            for (Travail t : listeTravail) {
                Projet projet = t.getProjet();
                if (projet.equals(p)) {
                    listeTravail.remove(t);
                }
            }

            return "Projet supprimé";
        } else {
            return "Projet introuvable ou suppression impossible";
        }
    }

    /**
     * Méthode permettant de supprimer un employé
     *
     * @param e employé à supprimer
     * @return diagnostic de la suppression
     */
    public String suppEmploye(Employe e) {
        boolean ok = mesEmployes.remove(e);
        if (!ok) {
            return "Employé introuvable ou suppression impossible";
        } else {
            return "Employé supprimé";
        }
    }

    /**
     * Méthode permettant de peupler artificiellement les listes de base
     */
    public void populate() {
        mesClients.addAll(Arrays.asList(
                new Client("Ledoux", "Soignies", "064/556365"),
                new Client("Carrefour", "Bruxelles", "02/5247845"),
                new Client("Dupuis", "Namur", "081/521478")));

        mesEmployes.addAll(Arrays.asList(
                new Employe("Shin", "Anna", "0475/859674", "shin.anna@gmail.com"),
                new Employe("Montagner", "Fabrice", "0472/121214", "f.montagner@yahoo.fr"),
                new Employe("Lemoine", "Patrice", "0492/748558", "lemoine.p@hotmail.com")));

        mesProjets.add(new Projet("Logiciel de caisse Ledoux", mesClients.get(0), "20/04/2018", "20/06/2018"));

        listeTravail.addAll(Arrays.asList(
                new Travail(mesProjets.get(0), mesEmployes.get(0), "20/04/2018", 100),
                new Travail(mesProjets.get(0), mesEmployes.get(1), "20/04/2018", 100),
                new Travail(mesProjets.get(0), mesEmployes.get(2), "08/05/2018", 100)));
    }

}
