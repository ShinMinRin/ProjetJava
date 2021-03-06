package controleur;

import java.util.List;
import modele.*;
import vue.ProjVue;

public class ProjControleur {

    private ProjModeleJDBC modele = null;
    private ProjVue vue = null;

    /**
     * Constructeur par défaut
     */
    public ProjControleur() {
    }

    /**
     * Constructeur complet
     * @param modele version JDBC
     * @param vue classe Vue
     */
    public ProjControleur(ProjModeleJDBC modele, ProjVue vue) {
        this.modele = modele;
        this.vue = vue;
    }

    /**
     * Gestion des différents menus du programme
     */
    public void gestion() {
        int ch1 = 0, ch2;

        do {
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
                        case 5:
                            vue.affMsg("Associer une discipline à un projet");
                            ajouterDiscToProj();
                            break;
                        case 6:
                            vue.affMsg("Associer un employé à un projet");
                            ajouterTravail();
                            break;
                        case 7:
                            vue.affMsg("Ajouter une compétence à un employé");
                            ajouterCompetence();
                            break;
                        case 8 :
                            break;
                    }
                    break;

                case 2: //Listes
                    ch2 = vue.menuAffiche();
                    switch (ch2) {
                        case 1: //clients
                            vue.affMsg("Liste des clients");
                            listeClients();
                            break;
                        case 2: //projets
                            vue.affMsg("Liste des projets");
                            listeProjets();
                            break;
                        case 3: //employés
                            vue.affMsg("Liste des employés");
                            listeEmployes();
                            break;
                        case 4: //disciplines
                            vue.affMsg("Liste des disciplines");
                            listeDisciplines();
                            break;
                        case 5: //discipline d'un projet
                            vue.affMsg("Liste des disciplines d'un projet");
                            listeDiscProj();
                            break;
                        case 6: //employés sur un projet 
                            vue.affMsg("Liste des employés sur un projet");
                            listeEmpProj();
                            break;
                        case 7: //projets d'un employé précis
                            vue.affMsg("Liste des projets d'un employé");
                            listeProjEmp();
                            break;
                        case 8: //compétences d'un employé
                            vue.affMsg("Liste des compétences d'un employé");
                            listeCompEmp();
                            break;
                        case 9 :
                            break;

                    }
                    break;

                case 3: //Modifier
                    ch2 = vue.menuModif();
                    switch (ch2) {
                        case 1: //ville cli
                            vue.affMsg("Modifier la ville d'un client");
                            modifVilleCli();
                            break;
                        case 2: //tel cli
                            vue.affMsg("Modifier le téléphone d'un client");
                            modifTelCli();
                            break;
                        case 3: //proj date butoir
                            vue.affMsg("Modifier la date butoir d'un projet");
                            modifDateButoirProj();
                            break;
                        case 4: //gsm emp
                            vue.affMsg("Modifier le gsm d'un employé");
                            modifGsmEmp();
                            break;
                        case 5: //email emp
                            vue.affMsg("Modifier l'email d'un employé");
                            modifEmailEmp();
                            break;
                        case 6: //compétence emp
                            vue.affMsg("Modifier le niveau de compétence d'un employé");
                            modifCompEmp();
                            break;
                        case 7 :
                            break;
                    }
                    break;

                case 4: //Rechercher
                    ch2 = vue.menuRech();
                    switch (ch2) {
                        case 1: //client
                            vue.affMsg("Rechercher un client");
                            Client cli = (Client) rechClient();
                            if (cli == null) {
                                vue.affMsg("Client introuvable");
                            } else {
                                vue.affMsg(cli.toString());
                            }
                            break;
                        case 2: //projet
                            vue.affMsg("Rechercher un projet");
                            Projet p = (Projet) rechProjet();
                            if (p == null) {
                                vue.affMsg("Projet introuvable");
                            } else {
                                vue.affMsg(p.toString());
                            }
                            break;
                        case 3: //Employé
                            vue.affMsg("Rechercher un employé");
                            Employe emp = (Employe) rechEmploye();
                            if (emp == null) {
                                vue.affMsg("Employé introuvable");
                            } else {
                                vue.affMsg(emp.toString());
                            }
                            break;
                        case 4: //Discipline
                            vue.affMsg("Rechercher une discipline");
                            Discipline d = (Discipline) rechDiscipline();
                            if (d == null) {
                                vue.affMsg("Discipline introuvable");
                            } else {
                                vue.affMsg(d.toString());
                            }
                            break;
                        case 5 :
                            break;
                    }
                    break;

                case 5: //Supprimer
                    ch2 = vue.menuSupp();
                    switch (ch2) {
                        case 1: //client
                            vue.affMsg("Supprimer un client");
                            suppClient();
                            break;
                        case 2: //projet
                            vue.affMsg("Supprimer un projet");
                            suppProjet();
                            break;
                        case 3: //Employé
                            vue.affMsg("Supprimer un employé");
                            suppEmploye();
                            break;
                        case 4: //Discipline
                            vue.affMsg("Supprimer une discipline");
                            suppDiscipline();
                            break;
                        case 5:
                            vue.affMsg("Supprimer une discipline d'un projet");
                            suppDiscProj();
                            break;
                        case 6:
                            vue.affMsg("Supprimer un employé sur un projet");
                            suppEmpProj();
                            break;
                        case 7:
                            vue.affMsg("Supprimer une compétence à un employé");
                            suppCompEmp();
                            break;
                        case 8 :
                            break;
                    }
                    break;

                case 6: //Quitter
                    vue.affMsg("Fin du programme");
                    System.exit(0);
                    break;
            }
        } while (ch1 < 6);

    }

    /**
     * Méthode permettant d'ajouter un projet dans la bdd
     */
    public void ajouterProjet() {
        Client c = null;
        String rep;
        rep = vue.getMsg("Nouveau client ? (o/n)", "[oOnN]");

        if (rep.equalsIgnoreCase("o")) {
            ajouterClient();
            c = modele.dernierClient();
        }
        if (rep.equalsIgnoreCase("n")) {
            c = (Client) rechClient();
            if (c == null) {
                vue.affMsg("Client introuvable");
                return;
            } else {
                vue.affMsg("Client trouvé :\n" + c);
                rep = vue.getMsg("Est-ce le bon client ? (o/n)", "[oOnN]");

                if (rep.equalsIgnoreCase("n")) {
                    return;
                }
            }

        }

        Projet.ProjetBuilder pb = vue.encodeProjet();
        Projet p;

        try {
            p = pb.setClient(c).build();
            String msg = modele.ajouterObjet(p);
            vue.affMsg(msg);
        } catch (Exception exc) {
            vue.affMsg("Erreur de création " + exc);
        }
    }

    /**
     * Méthode permettant d'ajouter un employé dans la bdd
     */
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

    /**
     * Méthode permettant d'ajouter une compétence à un employé dans la bdd
     */
    public void ajouterCompetence() {
        Employe emp = (Employe) rechEmploye();
        Discipline d = (Discipline) rechDiscipline();
        List<Niveau> ln = modele.tousNiveaux();
        vue.affListe(ln);
        int ch = Integer.parseInt(vue.getMsg("Quel niveau ? ", "[1-3]{1}"));
        Niveau niv = ln.get(ch - 1);
        Competence c = new Competence(emp, d, niv);
        String msg = modele.ajouterObjet(c);
        vue.affMsg(msg);
    }

    /**
     * Méthode permettant d'ajouter un employé sur un projet dans la bdd
     */
    public void ajouterTravail() {
        Projet p = (Projet) rechProjet();
        Employe emp = (Employe) rechEmploye();
        String date = vue.getMsg("Date d'engagement sur le projet : ", vue.dateRegex);
        float pc = Float.parseFloat(vue.getMsg("Pourcentage : ", "^100(\\.0{0,2}?)?$|^\\d{0,2}(\\.\\d{0,2})?$"));
        Travail tv = new Travail(p, emp, date, pc);
        String msg = modele.ajouterObjet(tv);
        vue.affMsg(msg);
    }

    /**
     * Méthode permettant d'ajouter une discipline dans la bdd
     */
    public void ajouterDiscipline() {
        Discipline d = vue.encodeDiscipline();
        String msg = modele.ajouterObjet(d);
        vue.affMsg(msg);
    }

    /**
     * Méthode permettant d'ajouter une discipline à un projet dans la bdd
     */
    public void ajouterDiscToProj() {
        Projet p = (Projet) rechProjet();
        Boolean encore = true;
        do {
            List<Discipline> ld = modele.toutesDisciplines();
            vue.affListe(ld);
            int ch = Integer.parseInt(vue.getMsg("Votre choix (0 si absent de la liste) : ", "[0-9]+"));
            Discipline d;
            if (ch != 0) {
                d = ld.get(ch - 1);
            } else {
                ajouterDiscipline();
                d = modele.derniereDiscipline();
            }
            
            ajouterTemps(p, d);

            String rep = vue.getMsg("Voulez-vous ajouter une autre discipline pour ce projet ? (o/n)", "[oOnN]");
            if (rep.equalsIgnoreCase("n")) {
                encore = false;
            }
        } while (encore);
    }

    /**
     * Méthode permettant d'ajouter un client dans la bdd
     */
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

    /**
     * Méthode permettant d'ajouter le temps prévu pour une discipline sur un projet 
     * @param p projet concerné
     * @param d discipline concernée
     */
    public void ajouterTemps(Projet p, Discipline d) {
        int jh = Integer.parseInt(vue.getMsg("Nombre de journée/homme prévue pour cette discipline (" + d.getNom() + ") : ", "[0-9]+"));
        Temps t = new Temps(d, p, jh);
        String msg = modele.ajouterObjet(t);
        vue.affMsg(msg);
    }

    /**
     * Méthode permettant d'afficher la liste des clients encodés dans la bdd
     */
    public void listeClients() {
        List<Client> l = modele.tousClients();
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des projets encodés dans la bdd
     */
    public void listeProjets() {
        List<Projet> l = modele.tousProjets();
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des employés encodés dans la bdd
     */
    public void listeEmployes() {
        List<Employe> l = modele.tousEmployes();
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des disciplines encodées dans la bdd
     */
    public void listeDisciplines() {
        List<Discipline> l = modele.toutesDisciplines();
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des disciplines associées à un projet
     */
    public void listeDiscProj() {
        Projet p = (Projet) rechProjet();
        if (p == null) {
            vue.affMsg("Projet introuvable");
        } else {
            List<Discipline> l = modele.listeDiscProj(p);
            if (l.isEmpty()) {
                vue.affMsg("Aucune discipline associée à ce projet");
            } else {
                vue.affListe(l);
            }
        }
    }

    /**
     * Méthode permettant d'afficher la liste des employés travaillant sur un projet
     */
    public void listeEmpProj() {
        Projet p = (Projet) rechProjet();
        List<Employe> l = modele.listeEmployeDuProjet(p);
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des projets sur lesquels travaille un employé
     */
    public void listeProjEmp() {
        Employe emp = (Employe) rechEmploye();
        List<Projet> l = modele.listeProjetParEmploye(emp);
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant d'afficher la liste des compétences d'un employé
     */
    public void listeCompEmp() {
        Employe emp = (Employe) rechEmploye();
        List<Competence> l = modele.listeCompEmp(emp);
        if (l.isEmpty()) {
            vue.affMsg("Aucune donnée à afficher");
        } else {
            vue.affListe(l);
        }

    }

    /**
     * Méthode permettant de modifier la ville d'un client
     */
    public void modifVilleCli() {
        Client cli = (Client) rechClient();
        if (cli == null) {
            vue.affMsg("Client introuvable");
        } else {
            String newVille = vue.getMsg("Nouvelle ville : ");
            newVille = newVille.toLowerCase();
            vue.affMsg(modele.changeVilleClient(cli, newVille) + "\n");
        }

    }

    /**
     * Méthode permettant de modifier le téléphone d'un client
     */
    public void modifTelCli() {
        Client cli = (Client) rechClient();
        if (cli == null) {
            vue.affMsg("Client introuvable");
        } else {
            String newTel = vue.getMsg("Nouveau numéro de téléphone : ");
            vue.affMsg(modele.changeTelClient(cli, newTel) + "\n");
        }
    }

    /**
     * Méthode permettant de modifier le gsm d'un employé
     */
    public void modifGsmEmp() {
        Employe emp = (Employe) rechEmploye();
        if (emp == null) {
            vue.affMsg("Employé introuvable");
        } else {
            String newGsm = vue.getMsg("Nouveau numéro de téléphone : ");
            vue.affMsg(modele.changeGsmEmploye(emp, newGsm) + "\n");
        }
    }

    /**
     * Méthode permettant de modifier l'email d'un employé
     */
    public void modifEmailEmp() {
        Employe emp = (Employe) rechEmploye();
        if (emp == null) {
            vue.affMsg("Employé introuvable");
        } else {
            String newMail = vue.getMsg("Nouvelle adresse e-mail : ");
            newMail = newMail.toLowerCase();
            vue.affMsg(modele.changeEmailEmploye(emp, newMail + "\n"));
        }
    }

    /**
     * Méthode permettant de modifier la compétence d'un employé
     */
    public void modifCompEmp() {
        Employe emp = (Employe) rechEmploye();
        if (emp == null) {
            vue.affMsg("Employé introuvable");
        } else {
            List<Competence> l = modele.listeCompEmp(emp);
            if (l.isEmpty()) {
                vue.affMsg("Aucune compétence attribuée à cet employé");
                return;
            } else {
                vue.affListe(l);
                boolean ok = true;
                do {
                    int ch = Integer.parseInt(vue.getMsg("Compétence à modifier (0 pour quitter) : ", "[0-9]+"));
                    if (ch == 0) {
                        return;
                    } else if (ch > l.size()) {
                        vue.affMsg("Réponse invalide, veuillez recommencer");
                        ok = false;
                    } else {
                        Competence c = l.get(ch - 1);
                        List<Niveau> ln = modele.tousNiveaux();
                        vue.affListe(ln);
                        ch = Integer.parseInt(vue.getMsg("Quel niveau ? ", "[1-3]{1}"));
                        Niveau niv = ln.get(ch - 1);
                        String msg = modele.changeNivComp(emp, c.getDiscipline(), niv);
                        vue.affMsg(msg);
                    }
                } while (!ok);

            }
        }
    }

    /**
     * Méthode permettant de modifier la date butoir d'un projet
     */
    public void modifDateButoirProj() {
        Projet p = (Projet) rechProjet();
        if (p == null) {
            vue.affMsg("Projet introuvable");
        } else {
            String newDate = vue.getMsg("Nouvelle date butoir : ");
            vue.affMsg(modele.changeDateButoirProj(p, newDate) + "\n");
        }
    }

    /**
     * Méthode de recherche d'un client
     * @return Object client trouvé ou null si non trouvé
     */
    public Object rechClient() {
        Client cli = null;
        String nom = vue.getMsg("Nom du client : ");
        String ville = vue.getMsg("Ville : ");
        nom = nom.toLowerCase();
        ville = ville.toLowerCase();
        Client.ClientBuilder cb = new Client.ClientBuilder();

        try {
            cli = cb.setNom(nom).setVille(ville).build();
        } catch (Exception e) {
            vue.affMsg("Erreur client " + e);
        }

        Object o = modele.getObject(cli);
        return o;
    }

    /**
     * Méthode de recherche d'un projet
     * @return Object projet trouvé ou null si non trouvé
     */
    public Object rechProjet() {
        Projet p = null;
        String titre = vue.getMsg("Titre du projet : ");
        titre = titre.toLowerCase();

        Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
        try {
            p = pb.setTitre(titre).build();
        } catch (Exception e) {
            vue.affMsg("Erreur projet " + e);
        }

        Object o = modele.getObject(p);
        return o;
    }

    /**
     * Méthode de recherche d'un employé
     * @return Object employé trouvé ou null si non trouvé
     */
    public Object rechEmploye() {
        Employe emp = null;

        String nom = vue.getMsg("Nom de l'employé : ");
        String prenom = vue.getMsg("Prénom de l'employé : ");
        nom = nom.toLowerCase();
        prenom = prenom.toLowerCase();

        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        try {
            emp = eb.setNom(nom).setPrenom(prenom).build();
        } catch (Exception e) {
            vue.affMsg("Erreur employé " + e);
        }

        Object o = modele.getObject(emp);
        return o;
    }

    /**
     * Méthode de recherche d'une discipline
     * @return Object discipline trouvée ou null si non trouvé
     */
    public Object rechDiscipline() {
        String nom = vue.getMsg("Discipline : ");
        nom = nom.toLowerCase();
        Discipline d = new Discipline(nom);
        Object o = modele.getObject(d);
        return o;
    }

    /**
     * Méthode de suppression d'un client
     */
    public void suppClient() {
        Client cli = (Client) rechClient();
        if (cli == null) {
            vue.affMsg("Client introuvable");
        } else {
            String msg = modele.suppClient(cli);
            vue.affMsg(msg);
        }
    }

    /**
     * Méthode de suppression d'un projet
     */
    public void suppProjet() {
        Projet p = (Projet) rechProjet();
        if (p == null) {
            vue.affMsg("Projet introuvable");
        } else {
            String msg = modele.suppProjet(p);
            vue.affMsg(msg);
        }
    }

    /**
     * Méthode de suppression d'un employé
     */
    public void suppEmploye() {
        Employe emp = (Employe) rechEmploye();
        if (emp == null) {
            vue.affMsg("Employé introuvable");
        } else {
            String msg = modele.suppEmploye(emp);
            vue.affMsg(msg);
        }
    }
    
    /**
     * Méthode de suppression d'une compétence d'un employé
     */
    public void suppCompEmp(){
        Employe emp = (Employe) rechEmploye();
        if (emp == null) {
            vue.affMsg("Employé introuvable");
        } else {
            List<Competence> l = modele.listeCompEmp(emp);
            if (l.isEmpty()) {
                vue.affMsg("Aucune compétence attribuée à cet employé");
                return;
            } else {
                vue.affListe(l);
                boolean ok = true;
                do {
                    int ch = Integer.parseInt(vue.getMsg("Compétence à supprimer (0 pour quitter) : ", "[0-9]+"));
                    if (ch == 0) {
                        return;
                    } else if (ch > l.size()) {
                        vue.affMsg("Réponse invalide, veuillez recommencer");
                        ok = false;
                    } else {
                        Competence c = l.get(ch - 1);
                        String msg = modele.suppCompEmp(emp, c);
                        vue.affMsg(msg);
                    }
                } while (!ok);

            }
        }
    }
    
    /**
     * Méthode de suppression d'un employé d'un projet
     */
    public void suppEmpProj(){
        Projet p = (Projet) rechProjet();
        if(p == null){
            vue.affMsg("Projet introuvable");
        } else{
            Employe emp = (Employe) rechEmploye();
            if(emp == null){
                vue.affMsg("Employé introuvable");
            } else{
                String msg = modele.suppEmpProj(p,emp);
                vue.affMsg(msg);
            }
        }
    }

    /**
     * Méthode de suppression d'une discipline
     */
    public void suppDiscipline() {
        Discipline d = (Discipline) rechDiscipline();
        if (d == null) {
            vue.affMsg("Discipline introuvable");
        } else {
            String msg = modele.suppDiscipline(d);
            vue.affMsg(msg);
        }
    }

    /**
     * Méthode de suppression d'une discipline d'un projet
     */
    public void suppDiscProj() {
        Projet p = (Projet) rechProjet();
        if (p == null) {
            vue.affMsg("Projet introuvable");
        } else {
            Discipline d = (Discipline) rechDiscipline();
            if (d == null) {
                vue.affMsg("Discipline introuvable");
            } else {
                String msg = modele.suppDiscProj(p, d);
                vue.affMsg(msg);
            }
        }

    }
}
