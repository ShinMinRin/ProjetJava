
package modele;

/**
 * 
 * @author Tiffany De Schinckel
 * @version 1.0
 */

import projetjava18.Log;
import java.util.List;
import java.util.ArrayList;

public class ProjModele {
    protected List<Projet> mesProjets = new ArrayList<>();
    protected List<Employe> mesEmployes = new ArrayList<>();
    
    /**
     * Constructeur par défaut
     */
    public ProjModele() {
    }

    /**
     * Méthode permettant l'ajout d'un objet
     * @param o objet à ajouter
     * @return diagnostic de l'ajout
     */
    public String ajouterObjet(Object o){
        List l = null;
        String msg = "";
        
        //On détermine la liste correspondante au type d'objet
        if(o instanceof Projet){
            l = mesProjets;
            msg = "projet";
        }
        if(o instanceof Employe){
            l = mesEmployes;
            msg = "employé";
        }
        
        
        if(o==null) return msg+" null";
        if(l==null) return "Type d'objet inconnu";
        
        if(l.contains(o)){
            Log log = Log.getInstance();
            log.ajouter("Refus de l'ajout de "+o);
            return "Ajout "+msg+" refusé";
        }
        
        l.add(o);
        Log log = Log.getInstance();
        log.ajouter("Enregistrement de "+o);
        return "Ajout "+msg+" effectué";
    }
    
    /**
     * Méthode permettant de trouver un objet
     * @param o objet encapsulant les information de base
     * @return objet trouvé, ou null si introuvable
     */
    public Object getObject(Object o){
        List l = null;
        
        if(o instanceof Projet)
            l = mesProjets;
        
        if(o instanceof Employe)
            l = mesEmployes;
        
        int p = l.indexOf(o);
        
        if(p<0)
            return null;
        else
            return l.get(p);
    }
        
    
}
