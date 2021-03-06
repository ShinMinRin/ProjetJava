package vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modele.*;

public class ProjVue {

    private Scanner sc = new Scanner(System.in);

    //Regex
    public String dateRegex = "^(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})([\\/])(0[1-9]{1}|1[0-2]{1})([\\/])([19|20]+[0-9]{2})$";
    public String texteRegex = "([aA-zZ]+ *-*)+";
    public String telRegex = "(0[0-9]{8,9})";
    public String mailRegex = "^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$";

    //Menus
    private List<String> menuPrincipal = new ArrayList<>(Arrays.asList("Ajouter", "Listes", "Modifier", "Rechercher", "Supprimer", "Quitter"));
    private List<String> menuAjout = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline",
            "Discipline à un projet", "Employé sur un projet", "Compétence à un employé", "Retour"));
    private List<String> menuAffiche = new ArrayList<>(Arrays.asList("Liste des clients", "Liste des projets", "Liste des employés", "Liste des disciplines",
            "Liste des disciplines d'un projet", "Liste des employés d'un projet", "Liste des projets d'un employé", "Liste des compétences d'un employé", "Retour"));
    private List<String> menuModif = new ArrayList<>(Arrays.asList("Client - Modifier la ville", "Client - Modifier le téléphone",
            "Projet - Modifier la date butoir",
            "Employé - Modifier le gsm", "Employé - Modifier l'email", "Employé - Modifier un niveau de compétence", "Retour"));
    private List<String> menuRech = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline", "Retour"));
    private List<String> menuSupp = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline",
            "Discipline à un projet", "Employé sur un projet", "Compétence à un employé", "Retour"));

    /**
     * Méthode qui affiche le menu principal et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuPrincipal() {
        affListe(menuPrincipal);
        return sc.nextInt();
    }

    /**
     * Méthode qui affiche le menu ajout et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuAjout() {
        affListe(menuAjout);
        return sc.nextInt();
    }

    /**
     * Méthode qui affiche le menu des listes et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuAffiche() {
        affListe(menuAffiche);
        return sc.nextInt();
    }

    /**
     * Méthode qui affiche le menu des modifications et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuModif() {
        affListe(menuModif);
        return sc.nextInt();
    }

    /**
     * Méthode qui affiche le menu de suppression et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuSupp() {
        affListe(menuSupp);
        return sc.nextInt();
    }

    /**
     * Méthode qui affiche le menu recherche et qui récupère le choix de
     * l'utilisateur
     *
     * @return choix de l'utilisateur
     */
    public int menuRech() {
        affListe(menuRech);
        return sc.nextInt();
    }

    /**
     * Méthode qui permet d'afficher un message
     *
     * @param msg Message à afficher
     */
    public void affMsg(Object msg) {
        System.out.println(msg);
    }

    /**
     * Méthode qui permet de récupérer une saisie
     *
     * @return String saisie récupérée
     */
    public String getMsg() {
        Scanner scan = new Scanner(System.in);
        String msg = scan.nextLine();
        System.out.println(msg);
        return msg;
    }

    /**
     * Surcharge de la classe getMsg() 
     * Permet d'afficher un message avant la saisie
     *
     * @param msg Message à afficher
     * @return String saisie récupérée
     */
    //Saisie avec l'aide de Julien Schoenaers afin d'éviter le saut d'un champs de saisie
    public String getMsg(String msg) {
        affMsg(msg);
        Scanner scan = new Scanner(System.in);
        String m = scan.nextLine();
        return m;
    }

    /**
     * Surcharge de la classe getMsg()
     * Permet d'afficher un message avant la saisie, et de vérifier la saisie avec un regex
     * 
     * @param msg Message à afficher
     * @param regex Pattern que la saisie doit respecter pour être valide
     * @return String saisie récupérée et valide
     */
    public String getMsg(String msg, String regex) {
        Scanner scan = new Scanner(System.in);
        boolean ok = false;
        String m = null;
        do {
            affMsg(msg);
            m = scan.nextLine();

            if (m.matches(regex)) {
                ok = true;
            } else {
                affMsg("Format incorrect, veuillez réessayer \n");
            }

        } while (!ok);

        return m;
    }
    
    
    /**
     * Méthode permettant d'afficher une liste numérotée
     * @param liste Liste à afficher
     */
    public void affListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affMsg((i++) + ". " + o);
        }
        affMsg("\n");
    }

    /**
     * Méthode permettant d'encoder un nouveau projet
     * @return ProjetBuilder avec les données nécessaires à sa construction
     */
    public Projet.ProjetBuilder encodeProjet() {
        affMsg("Encodage Projet");
        String titre = getMsg("Titre : ", texteRegex);
        String debut = getMsg("Date de début : ", dateRegex);
        String fin = getMsg("Date butoir : ", dateRegex);
        titre = titre.toLowerCase();

        Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
        pb.setTitre(titre).setDateButoir(fin).setDateDebut(debut);

        return pb;
    }

    /**
     * Méthode permettant d'encoder un nouveau client
     * @return ClientBuilder avec les données nécessaires à sa construction
     */
    public Client.ClientBuilder encodeClient() {
        affMsg("Encodage Client");
        String nom = getMsg("Nom : ", texteRegex);
        String ville = getMsg("Ville : ", texteRegex);
        String tel = getMsg("Tel : ", telRegex);
        nom = nom.toLowerCase();
        ville = ville.toLowerCase();

        Client.ClientBuilder cb = new Client.ClientBuilder();
        cb.setNom(nom).setTel(tel).setVille(ville);

        return cb;
    }

    /**
     * Méthode permettant d'encoder un nouvel employé
     * @return EmployeBuilder avec les données nécessaires à sa construction
     */
    public Employe.EmployeBuilder encodeEmploye() {
        affMsg("Encodage Employé");
        String nom = getMsg("Nom : ", texteRegex);
        String prenom = getMsg("Prénom : ", texteRegex);
        String gsm = getMsg("Numéro de gsm : ", telRegex);
        String email = getMsg("Adresse email : ", mailRegex);
        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();

        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        eb.setNom(nom).setPrenom(prenom).setGsm(gsm).setEmail(email);

        return eb;
    }

    /**
     * Méthode permettant d'encoder une nouvelle discipline
     * @return Discipline nouvelle discipline créée
     */
    public Discipline encodeDiscipline() {
        String nom = getMsg("Discipline : ", texteRegex);
        nom = nom.toLowerCase();
        return new Discipline(nom);
    }

}
