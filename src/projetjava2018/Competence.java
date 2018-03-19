package projetjava2018;

import java.util.*;

public class Competence {

    private Personnel personne;
    private Discipline discipline;
    private int degre;

    /**
     * Constructeur par défaut
     */
    public Competence() {
    }

    /**
     * Constructeur complet
     */
    public Competence(Personnel personne, Discipline discipline, int degre) {
        this.personne = personne;
        this.discipline = discipline;
        this.degre = degre;
    }

    /**
     * Getters et setters
     */
    public Personnel getPersonne() {
        return personne;
    }

    public void setPersonne(Personnel personne) {
        this.personne = personne;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public int getDegre() {
        return degre;
    }

    public void setDegre(int degre) {
        this.degre = degre;
    }

    /**
     * Autre
     */
    @Override
    public String toString() {
        return "Competence{" + "personne=" + personne + ", discipline=" + discipline + ", degre=" + degre + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.personne);
        hash = 47 * hash + Objects.hashCode(this.discipline);
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
        final Competence other = (Competence) obj;
        if (!Objects.equals(this.personne, other.personne)) {
            return false;
        }
        if (!Objects.equals(this.discipline, other.discipline)) {
            return false;
        }
        return true;
    }

    
}
