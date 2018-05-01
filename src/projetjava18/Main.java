
package projetjava18;

import modele.ProjModele;
import controleur.ProjControleur;
import vue.ProjVue;

/**
 *
 * @author Tiffany
 */
public class Main {
    
    private ProjControleur pc;
    private ProjVue pv;
    private ProjModele pm;
    
    public Main(){
        pv = new ProjVue();
        pm = new ProjModele();
        pm.populate();
        pc = new ProjControleur(pm,pv);
        pc.gestion();  
    }
    
    public static void main(String[] args) {
        Main projet = new Main();
    }
    
}
