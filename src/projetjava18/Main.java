
package projetjava18;

import modele.ProjModeleJDBC;
import controleur.ProjControleur;
import vue.ProjVue;

/**
 *
 * @author Tiffany
 */
public class Main {
    
    private ProjControleur pc;
    private ProjVue pv;
    private ProjModeleJDBC pm;
    
    public Main(){
        pv = new ProjVue();
        pm = new ProjModeleJDBC();
        pm.populate();
        pc = new ProjControleur(pm,pv);
        pc.gestion();  
    }
    
    public static void main(String[] args) {
        Main projet = new Main();
    }
    
}
