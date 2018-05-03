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
    
    public void gestion(){
        int ch1, ch2;
        
        ch1 = vue.menuPrincipal();
        
        switch(ch1){
            case 1 : //Ajouter
                ch2 = vue.menuAjout();
                switch(ch2){
                    case 1 :
                        vue.affMsg("Ajout d'un client");
                        ajouterClient();
                        break;
                    case 2 : 
                        vue.affMsg("Ajout d'un projet");
                        ajouterProjet();
                        break;
                    case 3 : 
                        vue.affMsg("Ajout d'un employé");
                        ajouterEmploye();
                        break;
                    case 4 :
                        vue.affMsg("Ajout d'une discipline");
                        ajouterDiscipline();
                        break;
                }
                break;
                
            case 2 : //Listes
                ch2 = vue.menuAffiche();
                switch(ch2){
                    
                }
                break;
                
            case 3 : //Modifier
                ch2 = vue.menuModif();
                switch(ch2){
                    case 1 :
                        break;
                    case 2 :
                        break;
                    case 3 :
                        break;
                    case 4 : 
                        break;
                    case 5 :
                        break;
                    case 6:
                        break;
                }
                break;
                

            case 4 : //Rechercher
                ch2 = vue.menuRech();
                switch(ch2){
                   
                }
                break;
                
            case 5 : //Supprimer
                ch2 = vue.menuSupp();
                switch(ch2){
                    
                }
                break;
                
            case 6 : //Quitter
                System.exit(0);
                break;
        }
        
    }
    
    public void ajouterProjet(){
        Projet p = vue.encodeProjet();
        String msg = modele.ajouterObjet(p);
        vue.affMsg(msg);
    }
    
    public void ajouterEmploye(){
        Employe e = vue.encodeEmploye();
        String msg = modele.ajouterObjet(e);
        vue.affMsg(msg);
    }
    
    public void ajouterDiscipline(){
        Discipline d = vue.encodeDiscipline();
        String msg = modele.ajouterObjet(d);
        vue.affMsg(msg);
    }

    private void ajouterClient() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
}
