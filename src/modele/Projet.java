package modele;

import java.util.*;

public class Projet {

    private String titre;
    private Client client;
    private String dateDebut;
    private String dateButoir;

    /**
     * Constructeur de la classe Projet
     * @param pb ProjetBuilder permettant de créer un projet
     */
    private Projet(ProjetBuilder pb) {
        this.titre = pb.titre;
        this.client = pb.client;
        this.dateDebut = pb.dateDebut;
        this.dateButoir = pb.dateButoir;
    }

    /**
     * Getter de la variable titre
     * @return String titre du projet
     */
    public String getTitre() {
        return titre;
    }

    /**
     * Getter de la variable client
     * @return Client client du projet
     */
    public Client getClient() {
        return client;
    }

    /**
     * Getter de la variable dateDebut
     * @return date de début du projet
     */
    public String getDateDebut() {
        return dateDebut;
    }

    /**
     * Getter de la variable dateButoir
     * @return date butoir du projet
     */
    public String getDateButoir() {
        return dateButoir;
    }

    /**
     * Setter de la variable dateButoir
     * @param dateButoir Nouvelle date butoir du projet
     */
    public void setDateButoir(String dateButoir) {
        this.dateButoir = dateButoir;
    }

    /**
     * Méthode d'affichage de la classe Projet
     * @return String Informations sur le projet
     */
    @Override
    public String toString() {
        return titre + "\nClient : " + client + "\nDébut : " + dateDebut + "\nDate butoir : " + dateButoir + "\n";
    }

    /**
     * Classe de construction de l'objet Projet
     */
    public static class ProjetBuilder {

        private String titre;
        private Client client;
        private String dateDebut;
        private String dateButoir;

        public ProjetBuilder() {
        }

        public ProjetBuilder setTitre(String titre) {
            this.titre = titre;
            return this;
        }

        public ProjetBuilder setClient(Client client) {
            this.client = client;
            return this;
        }

        public ProjetBuilder setDateDebut(String dateDebut) {
            this.dateDebut = dateDebut;
            return this;
        }

        public ProjetBuilder setDateButoir(String dateButoir) {
            this.dateButoir = dateButoir;
            return this;
        }

        public Projet build() throws Exception {
            if (titre == null) {
                throw new Exception("Informations manquantes");
            }
            if (titre.trim().equals("")) {
                throw new Exception("Informations manquantes");
            }
            return new Projet(this);
        }

    }

}
