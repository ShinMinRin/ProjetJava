package modele;

/**
 *
 */
public class Client {

    private String nom;
    private String ville;
    private String tel;

    private Client(ClientBuilder cb) {
        this.nom = cb.nom;
        this.ville = cb.ville;
        this.tel = cb.tel;
    }

    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public String getTel() {
        return tel;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    @Override
    public String toString() {
        return nom + " (" + ville + ", " + tel + ")";
    }

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
        
        public Client build() throws Exception{
            if(nom==null || ville==null)
                throw new Exception("Informations manquantes");
            if(nom.trim().equals("") || ville.trim().equals(""))
                throw new Exception("Informations manquantes");
            return new Client(this);
        }
        
        
    }
}
