package controleur;

import modele.*;
import vue.ProjVue;

public class ProjControleur {

    private ProjModele modele = null;
    private ProjVue vue = null;

    public ProjControleur() {
    }

    public ProjControleur(ProjModele modele, ProjVue vue) {
        this.modele = modele;
        this.vue = vue;
    }

    public void gestion() {
        int ch1, ch2;

        ch1 = vue.menuPrincipal();

        switch (ch1) {
            case 1: //Ajouter
                ch2 = vue.menuAjout();
                switch (ch2) {
                    case 1:
                        vue.affMsg("Ajout d'un client");
                        ajouterClient();
                        break;
                    case 2:
                        vue.affMsg("Ajout d'un projet");
                        ajouterProjet();
                        break;
                    case 3:
                        vue.affMsg("Ajout d'un employé");
                        ajouterEmploye();
                        break;
                    case 4:
                        vue.affMsg("Ajout d'une discipline");
                        ajouterDiscipline();
                        break;
                }
                break;

            case 2: //Listes
                ch2 = vue.menuAffiche();
                switch (ch2) {
                    case 1: //clients
                        break;
                    case 2: //projets
                        break;
                    case 3: //employés
                        break;
                    case 4: //disciplines
                        break;
                    case 5: //employés sur un projet précis
                        break;
                    case 6: //projets d'un employé précis
                        break;
                    case 7: //compétences d'un employé
                        break;

                }
                break;

            case 3: //Modifier
                ch2 = vue.menuModif();
                switch (ch2) {
                    case 1: //ville cli
                        break;
                    case 2: //tel cli
                        break;
                    case 3: //proj date butoir
                        break;
                    case 4: //gsm emp
                        break;
                    case 5: //email emp
                        break;
                    case 6: //compétence emp
                        break;
                }
                break;

            case 4: //Rechercher
                ch2 = vue.menuRech();
                switch (ch2) {
                    case 1: //client
                        break;
                    case 2: //projet
                        break;
                    case 3: //Employé
                        break;
                    case 4: //Discipline
                        break;
                }
                break;

            case 5: //Supprimer
                ch2 = vue.menuSupp();
                switch (ch2) {
                    case 1: //client
                        break;
                    case 2: //projet
                        break;
                    case 3: //Employé
                        break;
                    case 4: //Discipline
                        break;
                }
                break;

            case 6: //Quitter
                System.exit(0);
                break;
        }

    }

    public void ajouterProjet() {
        Client c = null;
        //TODO rajouter regex pour le o/n
        String rep = vue.getMsg("Nouveau client ? (o/n)");
        rep.toLowerCase();
        if (rep.equals("o")) {
            ajouterClient();
            c = modele.dernierClient();
        } else {
            //TODO rechercher
        }

        Projet.ProjetBuilder pb = vue.encodeProjet(c);

        try {
            Projet p = pb.build();
            String msg = modele.ajouterObjet(p);
            vue.affMsg(msg);
        } catch (Exception exc) {
            vue.affMsg("Erreur de création " + exc);
        }

    }

    public void ajouterEmploye() {
        Employe.EmployeBuilder eb = vue.encodeEmploye();
        try {
            Employe e = eb.build();
            String msg = modele.ajouterObjet(e);
            vue.affMsg(msg);
        } catch (Exception exc) {
            vue.affMsg("Erreur de création " + exc);
        }
    }

    public void ajouterDiscipline() {
        Discipline d = vue.encodeDiscipline();
        String msg = modele.ajouterObjet(d);
        vue.affMsg(msg);
    }

    public void ajouterClient() {
        Client.ClientBuilder cb = vue.encodeClient();
        try {
            Client c = cb.build();
            String msg = modele.ajouterObjet(c);
            vue.affMsg(msg);
        } catch (Exception exc) {
            vue.affMsg("Erreur de création " + exc);
        }

    }

}
