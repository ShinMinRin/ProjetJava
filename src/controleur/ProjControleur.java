package controleur;

import java.util.List;
import modele.*;
import vue.ProjVue;

public class ProjControleur {

    private ProjModeleJDBC modele = null;
    private ProjVue vue = null;

    public ProjControleur() {
    }

    public ProjControleur(ProjModeleJDBC modele, ProjVue vue) {
        this.modele = modele;
        this.vue = vue;
    }

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
                        case 5: //TODO employés sur un projet 
                            
                            break;
                        case 6: //TODO projets d'un employé précis
                            break;
                        case 7: //TODO compétences d'un employé
                            break;

                    }
                    break;

                case 3: //Modifier
                    ch2 = vue.menuModif();
                    switch (ch2) {
                        case 1: //ville cli
                            modifVilleCli();
                            break;
                        case 2: //tel cli
                            modifTelCli();
                            break;
                        case 3: //TODO proj date butoir
                            break;
                        case 4: //gsm emp
                            modifGsmEmp();
                            break;
                        case 5: //email emp
                            modifEmailEmp();
                            break;
                        case 6: //TODO compétence emp
                            break;
                    }
                    break;

                case 4: //Rechercher
                    ch2 = vue.menuRech();
                    switch (ch2) {
                        case 1: //client
                            Client cli = rechClient();
                            vue.affMsg(cli.toString());
                            break;
                        case 2: //projet
                            Projet p = rechProjet();
                            vue.affMsg(p.toString());
                            break;
                        case 3: //Employé
                            Employe emp = rechEmploye();
                            vue.affMsg(emp.toString());
                            break;
                        case 4: //Discipline
                            Discipline d = rechDiscipline();
                            vue.affMsg(d.toString());
                            break;
                    }
                    break;

                case 5: //Supprimer
                    ch2 = vue.menuSupp();
                    switch (ch2) {
                        case 1: //client
                            suppClient();
                            break;
                        case 2: //projet
                            suppProjet();
                            break;
                        case 3: //Employé
                            suppEmploye();
                            break;
                        case 4: //Discipline
                            suppDiscipline();
                            break;
                    }
                    break;

                case 6: //Quitter
                    System.exit(0);
                    break;
            }
        } while (ch1 < 6);

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
            c = rechClient();
        }

        Projet.ProjetBuilder pb = vue.encodeProjet();

        try {
            Projet p = pb.setClient(c).build();
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

    public void listeClients() {
        List<Client> l = modele.tousClients();
        vue.affListe(l);
    }

    public void listeProjets() {
        List<Projet> l = modele.tousProjets();
        vue.affListe(l);
    }

    public void listeEmployes() {
        List<Employe> l = modele.tousEmployes();
        vue.affListe(l);
    }

    public void listeDisciplines() {
        List<Discipline> l = modele.toutesDisciplines();
        vue.affListe(l);
    }

    public void modifVilleCli() {
        Client cli = rechClient();
        String newVille = vue.getMsg("Nouvelle ville : ");
        modele.changeVilleClient(cli, newVille);
    }

    public void modifTelCli() {
        Client cli = rechClient();
        String newTel = vue.getMsg("Nouveau numéro de téléphone : ");
        modele.changeTelClient(cli, newTel);
    }

    public void modifGsmEmp() {
        Employe emp = rechEmploye();
        String newGsm = vue.getMsg("Nouveau numéro de téléphone : ");
        modele.changeGsmEmploye(emp, newGsm);
    }

    public void modifEmailEmp() {
        Employe emp = rechEmploye();
        String newMail = vue.getMsg("Nouvelle adresse e-mail : ");
        modele.changeEmailEmploye(emp, newMail);
    }

    public Client rechClient() {
        Client cli = null;
        String nom = vue.getMsg("Nom du client : ");
        String ville = vue.getMsg("Ville : ");
        Client.ClientBuilder cb = new Client.ClientBuilder();

        try {
            cli = cb.setNom(nom).setVille(ville).build();
        } catch (Exception e) {
            vue.affMsg("Erreur client " + e);
        }

        Object o = modele.getObject(cli);
        return (Client) o;
    }

    public Projet rechProjet() {
        Projet p = null;
        String titre = vue.getMsg("Titre du projet : ");

        Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
        try {
            p = pb.setTitre(titre).build();
        } catch (Exception e) {
            vue.affMsg("Erreur projet " + e);
        }

        Object o = modele.getObject(p);
        return (Projet) o;
    }

    public Employe rechEmploye() {
        Employe emp = null;

        String nom = vue.getMsg("Nom : ");
        String prenom = vue.getMsg("Prénom : ");

        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        try {
            emp = eb.setNom(nom).setPrenom(prenom).build();
        } catch (Exception e) {
            vue.affMsg("Erreur employé " + e);
        }

        Object o = modele.getObject(emp);
        return (Employe) o;
    }

    public Discipline rechDiscipline() {
        String nom = vue.getMsg("Nom : ");
        Discipline d = new Discipline(nom);
        Object o = modele.getObject(d);
        return (Discipline) o;
    }

    public void suppClient() {
        Client cli = rechClient();
        String msg = modele.suppClient(cli);
        vue.affMsg(msg);
    }

    public void suppProjet() {
        Projet p = rechProjet();
        String msg = modele.suppProjet(p);
        vue.affMsg(msg);
    }

    public void suppEmploye() {
        Employe emp = rechEmploye();
        String msg = modele.suppEmploye(emp);
        vue.affMsg(msg);
    }

    public void suppDiscipline() {
        Discipline d = rechDiscipline();
        String msg = modele.suppDiscipline(d);
        vue.affMsg(msg);
    }
}
