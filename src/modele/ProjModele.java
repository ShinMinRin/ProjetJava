
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
    protected List<Client> mesClients = new ArrayList<>();
    protected List<Employe> mesEmployes = new ArrayList<>();
    protected List<Travail> listeTravail = new ArrayList<>();
    
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
        if(o instanceof Client){
            l = mesClients;
            msg = "client";
        }
        if(o instanceof Travail){
            l = listeTravail;
            msg = "travail";
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
        
        //On associe l'objet à la bonne liste selon le type d'objet
        if(o instanceof Projet)
            l = mesProjets;
        if(o instanceof Employe)
            l = mesEmployes;
        if(o instanceof Client)
            l = mesClients;
        if(o instanceof Travail)
            l = listeTravail;
        
        int p = l.indexOf(o);
        
        if(p<0)
            return null;
        else
            return l.get(p);
    }
        
    
    /**
     * Méthode permettant d'obtenir la liste des clients
     * @return tous les clients de la liste
     */
    public List<Client> tousClients(){
        //TODO critère de tri
        return mesClients;
    }
    
    /**
     * Méthode permettant d'obtenir la liste des employés
     * @return tous les employés de la liste
     */
    public List<Employe> tousEmployes(){
        //TODO critère de tri
        return mesEmployes;
    }
    
    /**
     * Méthode permettant d'obtenir la liste des projets
     * @return tous les projets de la liste
     */
    public List<Projet> tousProjets(){
        //TODO critère de tri
        return mesProjets;
    }
    
    /**
     * Méthode permettant d'obtenir la liste des travaux
     * @return tous les travaux (lien entre projet et employé)
     */
    public List<Travail> tousTravaux(){
        //TODO critère de tri
        return listeTravail;
    }
    
    /**
     * Méthode permettant d'obtenir la liste des projets par client
     * @param c Client concerné
     * @return tous les projets d'un client en particulier
     */
    public List<Projet> listeProjetsClient(Client c){
        List<Projet> l = new ArrayList<>();
        //TODO ajouter le code nécessaire au tri des projets
        return l;
    }
    
    /**
     * Méthode permettant d'obtenir la liste des projets par employé
     * avec la date d'engagement et le pourcentage de travail pour l'employé
     * @param e Employé concerné
     * @return tous les détails des projets pour lesquels l'employé travaille
     */
    public List<Travail> listeTravailEmploye(Employe e){
        List<Travail> l = new ArrayList<>();
        //TODO ajouter le code nécessaire au tri des projets
        return l;
    }
    
    /**
     * Méthode permettant de modifier la ville du client
     * @param c client dont on veut modifier l'adresse
     * @param v nouvelle ville
     * @return diagnostic du changement
     */
    public String changeVilleClient(Client c, String v){
        c.setVille(v);
        return "Changement d'adresse effectué";
    }
    
    /**
     * Méthode permettant de modifier le numéro de téléphone du client
     * @param c client dont on veut modifier le numéro de téléphone
     * @param t nouveau numéro de téléphone
     * @return diagnostic du changement
     */
    public String changeTelClient(Client c, String t){
        c.setTel(t);
        return "Changement de téléphone effectué";
    }
    
    /**
     * Méthode permettant de modifier le numéro de GSM de l'employé
     * @param e employé dont on veut modifier le gsm
     * @param gsm nouveau numéro de gsm
     * @return diagnostic du changement
     */
    public String changeGsmEmploye(Employe e, String gsm){
        e.setGsm(gsm);
        return "Changement de numéro de gsm effectué";
    }
    
    /**
     * Méthode permettant de modifier l'adresse email de l'employé
     * @param e employé dont on veut modifier l'adresse email
     * @param email nouvelle adresse email
     * @return diagnostic du changement
     */
    public String changeEmailEmploye(Employe e, String email){
        e.setEmail(email);
        return "Changement d'email effectué";
    }
    
    /**
     * Méthode permettant de modifier le pourcentage de temps consacré au projet
     * par l'employé concerné
     * @param t travail concerné (lien entre projet et employé)
     * @param p nouveau pourcentage
     * @return diagnostic du changement
     */
    public String changePourcentage(Travail t, float p){
        t.setPourcentage(p);
        return "Changement de pourcentage effectué";
    }
    
}
