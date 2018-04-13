package modele;

import java.util.*;

public class Travail {

    private Projet projet;
    private Employe employe;
    private String dateEngagement;
    private float pourcentage;

    /**
     * Constructeur par défaut
     */
    public Travail() {
    }

    /**
     * Constructeur sans pourcentage
     */
    public Travail(Projet projet, Employe personne, String dateEngagement) {
        this.projet = projet;
        this.employe = personne;
        this.dateEngagement = dateEngagement;
    }

    /**
     * Constructeur complet
     */
    public Travail(Projet projet, Employe personne, String dateEngagement, float pourcentage) {
        this.projet = projet;
        this.employe = personne;
        this.dateEngagement = dateEngagement;
        this.pourcentage = pourcentage;
    }

    /**
     * Getters et setters
     */
    public Projet getProjet() {
        return projet;
    }

    public Employe getEmploye() {
        return employe;
    }

    public String getDateEngagement() {
        return dateEngagement;
    }

    public float getPourcentage() {
        return pourcentage;
    }
    
    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Autre
     */
    @Override
    public String toString() {
        return "Travail{" + "projet=" + projet + ", personne=" + employe + ", dateEngagement=" + dateEngagement + ", pourcentage=" + pourcentage + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.projet);
        hash = 23 * hash + Objects.hashCode(this.employe);
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
        final Travail other = (Travail) obj;
        if (!Objects.equals(this.projet, other.projet)) {
            return false;
        }
        if (!Objects.equals(this.employe, other.employe)) {
            return false;
        }
        return true;
    }

}
