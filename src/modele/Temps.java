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
    * @param discipline Discipline concernée
    * @param projet Projet concerné
    * @param nb_jh Nombre de journée/homme nécessaires dans cette discipline pour ce projet
    */
    public Temps(Discipline discipline, Projet projet, int nb_jh) {
        this.discipline = discipline;
        this.projet = projet;
        this.nb_jh = nb_jh;
    }

    /**
     * Getter de la variable Discipline
     * @return Discipline concernée
     */
    public Discipline getDiscipline() {
        return discipline;
    }

    /**
     * Getter de la variable Projet
     * @return Projet concerné
     */
    public Projet getProjet() {
        return projet;
    }

    /**
     * Getter de la variable nb_jh
     * @return le nombre de journée/homme nécessaire dans la discipline
     */
    public int getNb_jh() {
        return nb_jh;
    }

    /**
     * Setter de la variable nb_jh
     * @param nb_jh Nouveau nombre de journée/homme nécessaire dans la discipline
     */
    public void setNb_jh(int nb_jh) {
        this.nb_jh = nb_jh;
    }

    
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

    /**
     * Méthode d'affichage de la classe Temps
     * @return String Informations du temps nécessaires pour une discipline sur un projet
     */
    @Override
    public String toString() {
        return nb_jh + " journées/homme prévues pour la discipline : " + discipline + "\n sur le projet : " + projet;
    }

}
