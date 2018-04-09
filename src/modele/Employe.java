package modele;
import java.util.*;

/**
 * 
 */
public class Employe {

    private String nom;
    private String prenom;
    private String gsm;
    private String email;
    
    /**
     * Constructeur par défaut
     */
    public Employe() {
    }

    /**
     * Constructeur complet
     */
    public Employe(String nom, String prenom, String gsm, String email) {
        this.nom = nom;
        this.prenom = prenom;
        this.gsm = gsm;
        this.email = email;
    }
    
    /**
     * Getters et setters
     */
    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getGsm() {
        return gsm;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Autres
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.nom);
        hash = 89 * hash + Objects.hashCode(this.prenom);
        hash = 89 * hash + Objects.hashCode(this.gsm);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Employe other = (Employe) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        if (!Objects.equals(this.prenom, other.prenom)) {
            return false;
        }
        if (!Objects.equals(this.gsm, other.gsm)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Employe{" + "nom=" + nom + ", prenom=" + prenom + ", gsm=" + gsm + ", email=" + email + '}';
    }
    
    
    
    
    

    



}