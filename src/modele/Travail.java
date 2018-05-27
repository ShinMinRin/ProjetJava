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
     * Constructeur complet
     * @param projet Projet concerné
     * @param personne Employé concerné
     * @param dateEngagement Date d'engagement de l'employé sur le projet concerné
     * @param pourcentage Pourcentage de travail que représente le projet pour l'employé
     */
    public Travail(Projet projet, Employe personne, String dateEngagement, float pourcentage) {
        this.projet = projet;
        this.employe = personne;
        this.dateEngagement = dateEngagement;
        this.pourcentage = pourcentage;
    }

    /**
     * Getter de la variable Projet
     * @return Projet concerné
     */
    public Projet getProjet() {
        return projet;
    }

    /**
     * Getter de la variable Employé
     * @return Employé concerné
     */
    public Employe getEmploye() {
        return employe;
    }

    /**
     * Getter de la variable dateEngagement
     * @return date d'engagement de l'employé sur le projet
     */
    public String getDateEngagement() {
        return dateEngagement;
    }

    /**
     * Getter de la variable pourcentage
     * @return Pourcentage de travail que ce projet représente pour l'employé
     */
    public float getPourcentage() {
        return pourcentage;
    }
    
    /**
     * Setter de la variable pourcentage
     * @param pourcentage Nouveau taux de pourcentage
     */
    public void setPourcentage(float pourcentage) {
        this.pourcentage = pourcentage;
    }

    /**
     * Méthode d'affichage de la classe Travail
     * @return String Informations du travail d'un employé sur un projet
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
