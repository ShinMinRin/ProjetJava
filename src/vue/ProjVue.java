package vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modele.*;

public class ProjVue {

    private Scanner sc = new Scanner(System.in);

    private List<String> menuPrincipal = new ArrayList<>(Arrays.asList("Ajouter", "Listes", "Modifier", "Rechercher", "Supprimer", "Quitter"));
    private List<String> menuAjout = new ArrayList<>(Arrays.asList("Client","Projet","Employé", "Discipline"));
    private List<String> menuAffiche = new ArrayList<>(Arrays.asList("Liste des clients", "Liste des projets", "Liste des employés", "Liste des disciplines",
                                        "Liste des employés d'un projet", "Liste des projets d'un employé", "Liste des compétences d'un employé"));
    private List<String> menuModif = new ArrayList<>(Arrays.asList("Client - Modifier la ville", "Client - Modifier le téléphone",
                                                                    "Projet - Modifier la date butoir",
                                                                    "Employé - Modifier le gsm", "Employé - Modifier l'email", "Employé - Modifier une compétence"));
    private List<String> menuRech = new ArrayList<>(Arrays.asList("Client","Projet","Employé", "Discipline"));
    private List<String> menuSupp = new ArrayList<>(Arrays.asList("Client","Projet","Employé", "Discipline"));
    
    
    public int menuPrincipal() {
        affListe(menuPrincipal);
        return sc.nextInt();
    }
    
    public int menuAjout(){
        affListe(menuAjout);
        return sc.nextInt();
    }
    
    public int menuAffiche(){
        affListe(menuAffiche);
        return sc.nextInt();
    }
    
    public int menuModif(){
        affListe(menuModif);
        return sc.nextInt();
    }
    
    public int menuSupp(){
        affListe(menuSupp);
        return sc.nextInt();
    }
    
    public int menuRech(){
        affListe(menuRech);
        return sc.nextInt();
    }
    
    
    
    public void affMsg(Object msg) {
        System.out.println(msg);
    }

    public String getMsg() {
        String msg = sc.nextLine();
        System.out.println(msg);
        return msg;
    }

    /**
     * Saisie avec l'aide de Julien Schoenaers afin d'éviter le saut d'un champs de saisie
     */
    public String getMsg(String msg) { //surcharge de la méthode getMsg
        affMsg(msg);
        Scanner scan = new Scanner(System.in);
        String m = scan.nextLine();
        //System.out.println(m);
        return m;
    }
    
    public void affListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affMsg((i++) + "." + o);
        }
    }
    
    
    public Projet.ProjetBuilder encodeProjet(){
        String titre = getMsg("Titre : ");
        String debut = getMsg("Date de début : ");
        String fin = getMsg("Date butoir : ");
        titre.toLowerCase();
        
        Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
        pb.setTitre(titre).setDateButoir(fin).setDateDebut(debut);
        
        return pb;
    }
    
    public Client.ClientBuilder encodeClient(){
        String nom = getMsg("Nom : ");
        String ville = getMsg("Ville : ");
        String tel = getMsg("Tel : ");
        nom.toLowerCase();
        ville.toLowerCase();
        
        Client.ClientBuilder cb = new Client.ClientBuilder();
        cb.setNom(nom).setTel(tel).setVille(ville);
        
        return cb;
    }
    
    public Employe.EmployeBuilder encodeEmploye(){
        String nom = getMsg("Nom : ");
        String prenom = getMsg("Prénom : ");
        String gsm = getMsg("Numéro de gsm : ");
        String email = getMsg("Adresse email : ");
        nom.toLowerCase();
        prenom.toLowerCase();
        
        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        eb.setNom(nom).setPrenom(prenom).setGsm(gsm).setEmail(email);
        
        return eb;        
    }

    public Discipline encodeDiscipline() {
        String nom = getMsg("Nom de la discipline : ");
        nom.toLowerCase();
        return new Discipline(nom);
    }
    
}