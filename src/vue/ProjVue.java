package vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import modele.*;

public class ProjVue {

    private Scanner sc = new Scanner(System.in);

    private List<String> menuPrincipal = new ArrayList<>(Arrays.asList("Ajouter", "Modifier", "Rechercher", "Supprimer", "Quitter"));
    private List<String> sousMenu = new ArrayList<>(Arrays.asList("Projet", "Employe", "Discipline"));

    public int menu() {
        affListe(menuPrincipal);
        return sc.nextInt();
    }
    
    public int sousMenu(String nomMenu){
        affMsg(nomMenu);
        affListe(sousMenu);
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
    
    public Employe encodeEmploye(){
        String nom = getMsg("Nom : ");
        String prenom = getMsg("Prénom : ");
        String gsm = getMsg("Numéro de gsm : ");
        String email = getMsg("Adresse email : ");
        Employe e = new Employe(nom, prenom, gsm, email);
        return e;
    }

}
