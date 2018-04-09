package modele;

import java.util.*;

public class Travail {

    private Projet projet;
    private Employe personne;
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
        this.personne = personne;
        this.dateEngagement = dateEngagement;
    }

    /**
     * Constructeur complet
     */
    public Travail(Projet projet, Employe personne, String dateEngagement, float pourcentage) {
        this.projet = projet;
        this.personne = personne;
        this.dateEngagement = dateEngagement;
        this.pourcentage = pourcentage;
    }

    /**
     * Getters et setters
     */
    public Projet getProjet() {
        return projet;
    }

    public Employe getPersonne() {
        return personne;
    }

    public String getDateEngagement() {
        return dateEngagement;
    }

    public float getPourcentage() {
        return pourcentage;
    }

    public void setDateEngagement(String dateEngagement) {
        this.dateEngagement = dateEngagement;
    }

    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Autre
     */
    @Override
    public String toString() {
        return "Travail{" + "projet=" + projet + ", personne=" + personne + ", dateEngagement=" + dateEngagement + ", pourcentage=" + pourcentage + '}';
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 23 * hash + Objects.hashCode(this.projet);
        hash = 23 * hash + Objects.hashCode(this.personne);
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
        if (!Objects.equals(this.personne, other.personne)) {
            return false;
        }
        return true;
    }

}
