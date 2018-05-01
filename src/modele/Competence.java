package modele;

import java.util.*;

public class Competence {

    private Employe personne;
    private Discipline discipline;
    private Niveau niveau;

    /**
     * Constructeur par défaut
     */
    public Competence() {
    }

    /**
     * Constructeur complet
     */
    public Competence(Employe personne, Discipline discipline, Niveau niveau) {
        this.personne = personne;
        this.discipline = discipline;
        this.niveau = niveau;
    }

    /**
     * Getters et setters
     */
  
    public Employe getPersonne() {
        return personne;
    }

    public void setPersonne(Employe personne) {
        this.personne = personne;
    }

    public Discipline getDiscipline() {
        return discipline;
    }

    public void setDiscipline(Discipline discipline) {
        this.discipline = discipline;
    }

    public Niveau getNiveau() {
        return niveau;
    }

    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    /**
     * Autres
     */    
    @Override
    public String toString() {
        return "Competence{" + "personne=" + personne + ", discipline=" + discipline + ", niveau=" + niveau + '}';
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 79 * hash + Objects.hashCode(this.personne);
        hash = 79 * hash + Objects.hashCode(this.discipline);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
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
