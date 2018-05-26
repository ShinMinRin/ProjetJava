package modele;

import java.util.*;

/**
 *
 */
public class Niveau {

    private int degre;
    private String signification;

    /**
     * Constructeur par défaut
     */
    public Niveau() {
    }

    /**
     * Constructeur complet
     */
    public Niveau(int degre, String signification) {
        this.degre = degre;
        this.signification = signification;
    }

    /**
     * Getters et setters
     */
    public int getDegre() {
        return degre;
    }

    public void setDegre(int degre) {
        this.degre = degre;
    }

    public String getSignification() {
        return signification;
    }

    public void setSignification(String signification) {
        this.signification = signification;
    }

    /**
     * Autre
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
