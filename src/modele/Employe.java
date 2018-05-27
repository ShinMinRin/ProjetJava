package modele;

/**
 *
 * @author tiffany.deschinckel
 */
public class Employe {

    private String nom;
    private String prenom;
    private String gsm;
    private String email;

    /**
     * Constructeur de la classe Employe
     * @param eb EmployeBuilder permettant de créer l'employé
     */
    private Employe(EmployeBuilder eb) {
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.gsm = eb.gsm;
        this.email = eb.email;
    }

    /**
     * Getter de la variable nom
     * @return nom de l'employé
     */
    public String getNom() {
        return nom;
    }

    /**
     * Getter de la variable prenom
     * @return Prénom de l'employé
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Getter de la variable gsm
     * @return GSM de l'employé
     */
    public String getGsm() {
        return gsm;
    }

    /**
     * Getter de la variable email
     * @return EMail de l'employé
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter de la variable gsm
     * @param gsm Nouveau GSM de l'employé
     */
    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    /**
     * Setter de la variable Email
     * @param email Nouvel email de l'employé
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Méthode d'affichage de l'employé
     * @return String informations sur l'employé
     */
    @Override
    public String toString() {
        return nom + " " + prenom + " gsm : " + gsm + " mail : " + email;
    }

    /**
     * Classe de constructeur de l'employé
     */
    public static class EmployeBuilder {

        private String nom;
        private String prenom;
        private String gsm;
        private String email;

        public EmployeBuilder() {
        }

        public EmployeBuilder setNom(String nom) {
            this.nom = nom;
            return this;
        }

        public EmployeBuilder setPrenom(String prenom) {
            this.prenom = prenom;
            return this;
        }

        public EmployeBuilder setGsm(String gsm) {
            this.gsm = gsm;
            return this;
        }

        public EmployeBuilder setEmail(String email) {
            this.email = email;
            return this;
        }
        
        public Employe build() throws Exception{
            if(nom==null || prenom==null)throw new Exception("informations de base manquantes");
            if(nom.trim().equals("") || prenom.trim().equals("")) throw new Exception("informations de base manquantes");
            return new Employe(this);
        }
        
        
    }

}
