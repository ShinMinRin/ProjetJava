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
        
        int choix;
        
        do{
            choix = vue.menu(0);
            
            switch(choix){
                case 1 :
                    
            }
            
        }while(choix != 5);
    
    }
    
    
}
