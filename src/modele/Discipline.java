package modele;

import java.util.*;

public class Discipline {

    private String nom;

    /**
     * Constructeur par défaut
     */
    public Discipline() {
    }

    /**
     * Constructeur complet
     */
    public Discipline(String nom) {
        this.nom = nom;
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

    /**
     * Autre
     */
    @Override
    public String toString() {
        return nom;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 71 * hash + Objects.hashCode(this.nom);
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
        final Discipline other = (Discipline) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    
}
