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
                        case 5: //employés sur un projet 
                            vue.affMsg("Liste des employés sur un projet");
                            listeEmpProj();
                            break;
                        case 6: //projets d'un employé précis
                            vue.affMsg("Liste des projets d'un employé");
                            listeProjEmp();
                            break;
                        case 7: //TODO compétences d'un employé
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
                        case 6: //TODO compétence emp
                            break;
                    }
                    break;

                case 4: //Rechercher
                    ch2 = vue.menuRech();
                    switch (ch2) {
                        case 1: //client
                            vue.affMsg("Rechercher un client");
                            Client cli = rechClient();
                            vue.affMsg(cli.toString());
                            break;
                        case 2: //projet
                            vue.affMsg("Rechercher un projet");
                            Projet p = rechProjet();
                            vue.affMsg(p.toString());
                            break;
                        case 3: //Employé
                            vue.affMsg("Rechercher un employé");
                            Employe emp = rechEmploye();
                            vue.affMsg(emp.toString());
                            break;
                        case 4: //Discipline
                            vue.affMsg("Rechercher une discipline");
                            Discipline d = rechDiscipline();
                            vue.affMsg(d.toString());
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
                    }
                    break;

                case 6: //Quitter
                    vue.affMsg("Fin du programme");
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
    
    public void listeEmpProj(){
        Projet p = rechProjet();
        List<Employe> l = modele.listeEmployeDuProjet(p);
        vue.affListe(l);
    }
    
    public void listeProjEmp(){
        Employe emp = rechEmploye();
        List<Projet> l = modele.listeProjetParEmploye(emp);
        vue.affListe(l);
    }

    public void modifVilleCli() {
        Client cli = rechClient();
        String newVille = vue.getMsg("Nouvelle ville : ");
        vue.affMsg(modele.changeVilleClient(cli, newVille));
    }

    public void modifTelCli() {
        Client cli = rechClient();
        String newTel = vue.getMsg("Nouveau numéro de téléphone : ");
        vue.affMsg(modele.changeTelClient(cli, newTel));
    }

    public void modifGsmEmp() {
        Employe emp = rechEmploye();
        String newGsm = vue.getMsg("Nouveau numéro de téléphone : ");
        vue.affMsg(modele.changeGsmEmploye(emp, newGsm));
    }

    public void modifEmailEmp() {
        Employe emp = rechEmploye();
        String newMail = vue.getMsg("Nouvelle adresse e-mail : ");
        vue.affMsg(modele.changeEmailEmploye(emp, newMail));
    }
    
    public void modifDateButoirProj(){
        Projet p = rechProjet();
        String newDate = vue.getMsg("Nouvelle date butoir : ");
        vue.affMsg(modele.changeDateButoirProj(p, newDate));
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
