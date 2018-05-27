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
     * @param nom Nom de la discipline
     */
    public Discipline(String nom) {
        this.nom = nom;
    }

    /**
     * Getter de la variable Nom
     * @return nom de la discipline
     */
    public String getNom() {
        return nom;
    }

   /**
    * Méthode d'affichage de la discipline
    * @return String nom de la discipline
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
