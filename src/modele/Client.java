package modele;

/**
 *
 */
public class Client {

    private String nom;
    private String ville;
    private String tel;

    /**
     * Constructeur de la classe
     *
     * @param cb Client Builder
     */
    private Client(ClientBuilder cb) {
        this.nom = cb.nom;
        this.ville = cb.ville;
        this.tel = cb.tel;
    }

    /**
     * Getter permettant de récupérer le nom du client
     *
     * @return le nom du client
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter permettant de récupérer la ville du client
     *
     * @return la ville du client
     */
    public String getVille() {
        return ville;
    }

    /**
     * Getter permettant de récupérer le téléphone du client
     *
     * @return le téléphone du client
     */
    public String getTel() {
        return tel;
    }

    /**
     * Setter permettant de modifier la ville du client
     *
     * @param ville nouvelle ville du client
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

    /**
     * Setter permettant de modifier le numéro de téléphone du client
     *
     * @param tel nouveau numéro de téléphone
     */
    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Méthode permettant l'affichage des données du client
     *
     * @return les données du client
     */
    @Override
    public String toString() {
        return nom + " (" + ville + ", " + tel + ")";
    }

    /**
     * Builder de la classe client
     */
    public static class ClientBuilder {

        private String nom;
        private String ville;
        private String tel;

        public ClientBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public ClientBuilder setVille(String ville) {
            this.ville = ville;
            return this;
        }

        public ClientBuilder setTel(String tel) {
            this.tel = tel;
            return this;
        }

        public Client build() throws Exception {
            if (nom == null || ville == null) {
                throw new Exception("Informations manquantes");
            }
            if (nom.trim().equals("") || ville.trim().equals("")) {
                throw new Exception("Informations manquantes");
            }
            return new Client(this);
        }

    }
}
