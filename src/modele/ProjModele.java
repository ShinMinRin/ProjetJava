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
     * Méthode permettant de récupérer le dernier client ajouté
     */
    public Client dernierClient(){
        return mesClients.get(mesClients.size()-1);
    }
    
    /**
     * Méthode permettant de peupler artificiellement les listes de base
     */
    public void populate() {
        
        //CLIENTS
        Client.ClientBuilder cb = new Client.ClientBuilder();
        cb.setNom("Ledoux").setVille("Soignies").setTel("064/556365");
        try{
            Client cli = cb.build();
            mesClients.add(cli);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }
        
        cb.setNom("Carrefour").setVille("Bruxelles").setTel("02/5247845");
        try{
            Client cli = cb.build();
            mesClients.add(cli);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }
        
        cb.setNom("Dupuis").setVille("Namur").setTel("081/521478");
        try{
            Client cli = cb.build();
            mesClients.add(cli);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }
        
        //EMPLOYE
        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        eb.setNom("Shin").setPrenom("Anna").setGsm("0475/859674").setEmail("shin.anna@gmail.com");
        try{
            Employe emp = eb.build();
            mesEmployes.add(emp);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }
        
        eb.setNom("Montagner").setPrenom("Fabrice").setGsm("0472/121214").setEmail("f.montagner@yahoo.fr");
        try{
            Employe emp = eb.build();
            mesEmployes.add(emp);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }
        
        eb.setNom("Lemoine").setPrenom("Patrick").setGsm("0492/748558").setEmail("lemoine.p@hotmail.com");
        try{
            Employe emp = eb.build();
            mesEmployes.add(emp);
        }catch(Exception exc){
            System.out.println("Erreur de création "+exc);
        }

      
       //PROJET
       try{
           Projet p = new Projet.ProjetBuilder().
                   setTitre("Logiciel de caisse Ledoux").
                   setClient(mesClients.get(0)).
                   setDateDebut("20/04/2018").
                   setDateButoir("20/06/2018").
                   build();
           mesProjets.add(p);
       }catch(Exception exc){
           System.out.println("Erreur de création "+exc);
       }

       //ASSOCIATION PROJET - EMPLOYE
        listeTravail.addAll(Arrays.asList(
                new Travail(mesProjets.get(0), mesEmployes.get(0), "20/04/2018", 100),
                new Travail(mesProjets.get(0), mesEmployes.get(1), "20/04/2018", 100),
                new Travail(mesProjets.get(0), mesEmployes.get(2), "08/05/2018", 100)));
    }

}
