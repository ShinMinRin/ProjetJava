package modele;

import java.util.*;

public class Projet {

    private String titre;
    private Client client;
    private String dateDebut;
    private String dateButoir;

    private Projet(ProjetBuilder pb) {
        this.titre = pb.titre;
        this.client = pb.client;
        this.dateDebut = pb.dateDebut;
        this.dateButoir = pb.dateButoir;
    }

    public String getTitre() {
        return titre;
    }

    public Client getClient() {
        return client;
    }

    public String getDateDebut() {
        return dateDebut;
    }

    public String getDateButoir() {
        return dateButoir;
    }

    public void setDateButoir(String dateButoir) {
        this.dateButoir = dateButoir;
    }

    @Override
    public String toString() {
        return titre + "\nClient : " + client + "\nDébut : " + dateDebut + "\nDate butoir : " + dateButoir;
    }

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
