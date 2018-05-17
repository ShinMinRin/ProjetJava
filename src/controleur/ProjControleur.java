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
                            modifVilleCli();
                            break;
                        case 2: //tel cli
                            modifTelCli();
                            break;
                        case 3: //proj date butoir
                            break;
                        case 4: //gsm emp
                            modifGsmEmp();
                            break;
                        case 5: //email emp
                            modifEmailEmp();
                            break;
                        case 6: //compétence emp
                            break;
                    }
                    break;

                case 4: //Rechercher
                    ch2 = vue.menuRech();
                    switch (ch2) {
                        case 1: //client
                            rechClient();
                            break;
                        case 2: //projet
                            rechProjet();
                            break;
                        case 3: //Employé
                            rechEmploye();
                            break;
                        case 4: //Discipline
                            rechDiscipline();
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
            //TODO rechercher
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
    
    public void listeClients(){
        List<Client> l = modele.tousClients();
        vue.affListe(l);
    }
    
    public void listeProjets(){
        List<Projet> l = modele.tousProjets();
        vue.affListe(l);
    }
    
    public void listeEmployes(){
        List<Employe> l = modele.tousEmployes();
        vue.affListe(l);
    }
    
    public void listeDisciplines(){
        List<Discipline> l = modele.toutesDisciplines();
        vue.affListe(l);
    }
    
    
    public void modifVilleCli(){
        //TODO recherche du client
        
        String newVille = vue.getMsg("Nouvelle ville : ");
        //modele.changeVilleClient(cli, newVille);
    }
    
    public void modifTelCli(){
        //TODO recherche du client
        
        String newTel = vue.getMsg("Nouveau numéro de téléphone : ");
        //modele.changeTelClient(cli, newTel);
    }
    
    public void modifGsmEmp(){
        //TODO recherche de l'employé
        
        String newGsm = vue.getMsg("Nouveau numéro de téléphone : ");
        //modele.changeGsmEmploye(emp, newGsm);
    }
    
    public void modifEmailEmp(){
        //TODO recherche de l'employé
        
        String newMail = vue.getMsg("Nouvelle adresse e-mail : ");
        //modele.changeEmailEmploye(emp, newMail);
    }
    
    public void rechClient(){
        Client cli = null;
        Client.ClientBuilder cb = vue.encodeClient();
        
        try{
            cli = cb.build();
        } catch(Exception e){
            vue.affMsg("Erreur client "+e);
        }
        
        modele.getObject(cli);
    }
    
    public void rechProjet(){
        Projet p = null;
        String titre = vue.getMsg("Titre du projet : ");
        
        Projet.ProjetBuilder pb = new Projet.ProjetBuilder();
        try{
            p = pb.setTitre(titre).build();
        }catch(Exception e){
            vue.affMsg("Erreur projet "+e);
        }
        
        modele.getObject(p);
    }
    
    public void rechEmploye(){
        Employe emp = null;
        
        String nom = vue.getMsg("Nom : ");
        String prenom = vue.getMsg("Prénom : ");
        String email = vue.getMsg("Email : ");
        
        Employe.EmployeBuilder eb = new Employe.EmployeBuilder();
        try {
            emp = eb.setEmail(email).setNom(nom).setPrenom(prenom).build();
        } catch (Exception e) {
            vue.affMsg("Erreur employé "+e);
        }
        
        modele.getObject(emp);
    }
    
    public void rechDiscipline(){
        String nom = vue.getMsg("Nom : ");
        Discipline d = new Discipline(nom);
        modele.getObject(d);
    }

}
