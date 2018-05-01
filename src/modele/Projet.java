package modele;

import java.util.*;

public class Projet {

    private String titre;
    private Client client;
    private String dateDebut;
    private String dateButoir;

    /**
     * Constructeur par défaut
     */
    public Projet() {
    }
    
    /**
     * Constructeur partiel
     */
     public Projet(String titre, String dateDebut, String dateButoir) {
        this.titre = titre;
        this.dateDebut = dateDebut;
        this.dateButoir = dateButoir;
    }

    /**
     * Constructeur complet
     */
    public Projet(String titre, Client c, String dateDebut, String dateButoir) {
        this.titre = titre;
        this.client = c;
        this.dateDebut = dateDebut;
        this.dateButoir = dateButoir;
    }
    
    /**
     * Getters et setters
     */

    public String getTitre() {
        return titre;
    }

    public void setTitre(String titre) {
        this.titre = titre;
    }

    public Client getClient() {
        return client;
    }

    public void setClient(Client client) {
        this.client = client;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public void setDateDebut(String dateDebut) {
        this.dateDebut = dateDebut;
    }

    public String getDateButoir() {
        return dateButoir;
    }

    public void setDateButoir(String dateButoir) {
        this.dateButoir = dateButoir;
    }

    /**
     * Autres
     */

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 89 * hash + Objects.hashCode(this.titre);
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
        final Projet other = (Projet) obj;
        if (!Objects.equals(this.titre, other.titre)) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Projet{" + "titre=" + titre + ", client=" + client + ", dateDebut=" + dateDebut + ", dateButoir=" + dateButoir + '}';
    }

}
