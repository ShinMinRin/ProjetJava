package modele;

import java.util.*;

public class Niveau {

    private int degre;
    private String signification;

    /**
     * Constructeur par défaut
     */
    public Niveau() {
    }

    /**
     * Constructeur de la classe Niveau
     * @param degre Degré du niveau de maîtrise
     * @param signification Description du niveau de maîtrise
     */
    public Niveau(int degre, String signification) {
        this.degre = degre;
        this.signification = signification;
    }

    /**
     * Getter de la variable degré
     * @return degré du niveau de maîtrise
     */
    public int getDegre() {
        return degre;
    }

    /**
     * Setter de la variable degré
     * @param degre Nouveau degré de maîtrise
     */
    public void setDegre(int degre) {
        this.degre = degre;
    }

    /**
     * Getter de la variable signification
     * @return String signification
     */
    public String getSignification() {
        return signification;
    }

    /**
     * Méthode d'affichage d'un objet Niveau
     * @return String informations sur le niveau de maîtrise
     */
    @Override
    public String toString() {
        return signification;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 73 * hash + this.degre;
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
        final Niveau other = (Niveau) obj;
        if (this.degre != other.degre) {
            return false;
        }
        return true;
    }

}
