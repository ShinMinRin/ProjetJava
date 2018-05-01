package controleur;

import modele.ProjModele;
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
        
        ch1 = vue.menu();
        
        switch(ch1){
            case 1 : //Ajouter
                ch2 = vue.sousMenu("Ajouter");
                switch(ch2){
                    case 1 : 
                        vue.affMsg("Ajout d'un projet");
                        break;
                    case 2 : 
                        vue.affMsg("Ajout d'un employé");
                        break;
                    case 3 :
                        vue.affMsg("Ajout d'une discipline");
                        break;
                }
                break;
                
            case 2 : //Modifier
                ch2 = vue.sousMenu("Modifier");
                switch(ch2){
                    case 1 : 
                        vue.affMsg("Modification d'un projet");
                        break;
                    case 2 : 
                        vue.affMsg("Modification d'un employé");
                        break;
                    case 3 :
                        vue.affMsg("Modification d'une discipline");
                        break;
                }
                break;
                
            case 3 : //Rechercher
                ch2 = vue.sousMenu("Rechercher");
                switch(ch2){
                    case 1 : 
                        vue.affMsg("Recherche d'un projet");
                        break;
                    case 2 : 
                        vue.affMsg("Recherche d'un employé");
                        break;
                    case 3 :
                        vue.affMsg("Recherche d'une discipline");
                        break;
                }
                break;
                
            case 4 : //Supprimer
                ch2 = vue.sousMenu("Supprimer");
                switch(ch2){
                    case 1 : 
                        vue.affMsg("Suppression d'un projet");
                        break;
                    case 2 : 
                        vue.affMsg("Suppression d'un employé");
                        break;
                    case 3 :
                        vue.affMsg("Suppression d'une discipline");
                        break;
                }
                break;
                
            case 5 : //Quitter
                System.exit(0);
                break;
        }
        
    }
    
    
}
