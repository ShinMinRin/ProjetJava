package vue;

import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;

public class ProjVue {

    private Scanner sc = new Scanner(System.in);

    private List<String> menuPrincipal = new ArrayList<>(Arrays.asList("Ajouter", "Modifier", "Supprimer", "Rechercher", "Quitter"));
    private List<String> sousMenu = new ArrayList<>(Arrays.asList("Client", "Competence", "Discipline", "Employé", "Projet"));

    public int menu(int mode) {

        List<String> liste = null;
        String msg = "";

        switch (mode) {
            case 0: //Menu principal
                liste = menuPrincipal;
                msg = "Menu principal";
                break;
                
            case 1: //Ajouter
                liste = sousMenu;
                msg = "Ajouter";
                break;
                
            case 2: //Modifier
                liste = sousMenu;
                msg = "Modifier";
                break;
                
            case 3: //Supprimer
                liste = sousMenu;
                msg = "Supprimer";
                break;
                
            case 4: //Rechercher
                liste = sousMenu;
                msg = "Rechercher";
                break;
                
            case 5: //Quitter
                affMsg("Merci d'avoir utilisé le programme");
                System.exit(0);
                break;
        }

        affMsg(msg);
        affListe(liste);

        int ch;
        do {
            String chs = getMsg("votre choix");
            ch = Integer.parseInt(chs);
            if (ch > 0 && ch <= liste.size()) {
                break;
            }
            affMsg("choix incorrect");
        } while (true);
        return ch;
    }

    public String getMsg() {
        String msg = sc.nextLine();
        return msg;
    }

    public void affMsg(Object msg) {
        System.out.println(msg);
    }

    public String getMsg(String msg) {//surcharge de la méthode getMsg
        affMsg(msg);
        return getMsg();
    }

    public void affListe(Collection liste) {
        int i = 1;
        for (Object o : liste) {
            affMsg((i++) + "." + o);
        }
    }

}
