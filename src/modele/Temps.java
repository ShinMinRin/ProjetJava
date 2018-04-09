package modele;

import java.util.*;

/**
 *
 */
public class Temps {

    private Discipline discipline;
    private Projet projet;
    private int nb_jh;

    /**
     * Constructeur par défaut
     */
    public Temps() {
    }

    /**
     * Constructeur complet
     */
    public Temps(Discipline discipline, Projet projet, int nb_jh) {
        this.discipline = discipline;
        this.projet = projet;
        this.nb_jh = nb_jh;
    }

    /**
     * Getters et setters
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    public Projet getProjet() {
        return projet;
    }

    public int getNb_jh() {
        return nb_jh;
    }

    public void setNb_jh(int nb_jh) {
        this.nb_jh = nb_jh;
    }

    
    /**
     * Autres 
     */
    @Override
    public int hashCode() {
        int hash = 3;
        hash = 59 * hash + Objects.hashCode(this.discipline);
        hash = 59 * hash + Objects.hashCode(this.projet);
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
        final Temps other = (Temps) obj;
        if (!Objects.equals(this.discipline, other.discipline)) {
            return false;
        }
        if (!Objects.equals(this.projet, other.projet)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Temps{" + "discipline=" + discipline + ", projet=" + projet + ", nb_jh=" + nb_jh + '}';
    }
    
    

}
