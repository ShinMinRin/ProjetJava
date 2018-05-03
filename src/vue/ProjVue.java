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
        return msg;
    }

    public String getMsg(String msg) { //surcharge de la méthode getMsg
        affMsg(msg);
        return getMsg();
    }

    public void affListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affMsg((i++) + "." + o);
        }
    }
    
    
    public Projet encodeProjet(){
        String titre = getMsg("Titre : ");
        String debut = getMsg("Date de début : ");
        String fin = getMsg("Date butoir : ");
        Projet p = new Projet(titre, debut, fin);
        return p;
    }
    
    public Client encodeClient(){
        String nom = getMsg("Nom : ");
        String ville = getMsg("Ville : ");
        String tel = getMsg("Tel : ");
        Client c = new Client(nom,ville,tel);
        return c;
    }
    
    public Employe encodeEmploye(){
        String nom = getMsg("Nom : ");
        String prenom = getMsg("Prénom : ");
        String gsm = getMsg("Numéro de gsm : ");
        String email = getMsg("Adresse email : ");
        Employe e = new Employe(nom, prenom, gsm, email);
        return e;
    }
    
    public Discipline encodeDiscipline(){
        String nom = getMsg("Discipline : ");
        Discipline d = new Discipline(nom);
        return d;
    }

}
