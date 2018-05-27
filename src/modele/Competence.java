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
     * @param personne Employé qui a la compétence
     * @param discipline Discipline concernée
     * @param niveau Niveau possédé pour la discipline
     */
    public Competence(Employe personne, Discipline discipline, Niveau niveau) {
        this.personne = personne;
        this.discipline = discipline;
        this.niveau = niveau;
    }

    /**
     * Getter permettant de récupérer l'employé concerné
     * @return Employé concerné
     */  
    public Employe getPersonne() {
        return personne;
    }

    
    /**
     * Getter permettant de récupérer la discipline concernée
     * @return Discipline concernée
     */
    public Discipline getDiscipline() {
        return discipline;
    }

   
    /**
     * Getter permettant de récupérer le niveau de maîtrise de la discipline
     * @return Niveau de maîtrise de la discipline
     */
    public Niveau getNiveau() {
        return niveau;
    }

    /**
     * Setter permettant de modifier le niveau de maîtrise de la discipline
     * @param Niveau de maîtrise de la discipline
     */
    public void setNiveau(Niveau niveau) {
        this.niveau = niveau;
    }

    /**
     * Méthode d'affichage de la compétence
     * @return String Informations sur la compétence maîtrisée par l'employé
     */ 
    @Override
    public String toString() {
        return discipline.getNom() + " (" + niveau.getSignification() +")"; 
    }

    /**
     * Méthode permettant de vérifier l'égalité de deux Compétences
     * @param obj Competence avec laquelle on compare celle-ci
     * @return boolean true si les deux compétences sont égales, sinon false
     */
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
