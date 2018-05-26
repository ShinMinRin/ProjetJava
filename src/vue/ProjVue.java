package vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modele.*;

public class ProjVue {

    private Scanner sc = new Scanner(System.in);
    
    public String dateRegex = "^(0[1-9]{1}|[1-2]{1}[0-9]{1}|3[0-1]{1})([\\/])(0[1-9]{1}|1[0-2]{1})([\\/])([19|20]+[0-9]{2})$";
    public String texteRegex = "([aA-zZ]+ *-*)+";
    public String telRegex = "(0[0-9]{8,9})";
    public String mailRegex = "^\\w+[\\w-\\.]*\\@\\w+((-\\w+)|(\\w*))\\.[a-z]{2,3}$";

    private List<String> menuPrincipal = new ArrayList<>(Arrays.asList("Ajouter", "Listes", "Modifier", "Rechercher", "Supprimer", "Quitter"));
    private List<String> menuAjout = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline", 
            "Discipline à un projet", "Employé sur un projet", "Compétence à un employé"));
    private List<String> menuAffiche = new ArrayList<>(Arrays.asList("Liste des clients", "Liste des projets", "Liste des employés", "Liste des disciplines",
            "Liste des employés d'un projet", "Liste des projets d'un employé", "Liste des compétences d'un employé"));
    private List<String> menuModif = new ArrayList<>(Arrays.asList("Client - Modifier la ville", "Client - Modifier le téléphone",
            "Projet - Modifier la date butoir",
            "Employé - Modifier le gsm", "Employé - Modifier l'email", "Employé - Modifier un niveau de compétence"));
    private List<String> menuRech = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline"));
    private List<String> menuSupp = new ArrayList<>(Arrays.asList("Client", "Projet", "Employé", "Discipline"));

    public int menuPrincipal() {
        affListe(menuPrincipal);
        return sc.nextInt();
    }

    public int menuAjout() {
        affListe(menuAjout);
        return sc.nextInt();
    }

    public int menuAffiche() {
        affListe(menuAffiche);
        return sc.nextInt();
    }

    public int menuModif() {
        affListe(menuModif);
        return sc.nextInt();
    }

    public int menuSupp() {
        affListe(menuSupp);
        return sc.nextInt();
    }

    public int menuRech() {
        affListe(menuRech);
        return sc.nextInt();
    }

    public void affMsg(Object msg) {
        System.out.println(msg);
    }

    public String getMsg() {
        Scanner scan = new Scanner(System.in);
        String msg = scan.nextLine();
        System.out.println(msg);
        return msg;
    }

    /**
     * Saisie avec l'aide de Julien Schoenaers afin d'éviter le saut d'un champs
     * de saisie
     */
    public String getMsg(String msg) {
        affMsg(msg);
        Scanner scan = new Scanner(System.in);
        String m = scan.nextLine();
        return m;
    }

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

    public void affListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affMsg((i++) + ". " + o);
        }
    }
   
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

    public Employe.EmployeBuilder encodeEmploye() {
        affMsg("Encodage Employé");
        String nom = getMsg("Nom : ", texteRegex);
        String prenom = getMsg("Prénom : ",texteRegex);
        String gsm = getMsg("Numéro de gsm : ", telRegex);
        String email = getMsg("Adresse email : ", mailRegex);
        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();

        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        eb.setNom(nom).setPrenom(prenom).setGsm(gsm).setEmail(email);

        return eb;
    }

    public Discipline encodeDiscipline() {
        String nom = getMsg("Discipline : ", texteRegex);
        nom = nom.toLowerCase();
        return new Discipline(nom);
    }
   
    

}
