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

    private Employe(EmployeBuilder eb) {
        this.nom = eb.nom;
        this.prenom = eb.prenom;
        this.gsm = eb.gsm;
        this.email = eb.email;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getGsm() {
        return gsm;
    }

    public String getEmail() {
        return email;
    }

    public void setGsm(String gsm) {
        this.gsm = gsm;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "EmployeB{" + "nom=" + nom + ", prenom=" + prenom + ", gsm=" + gsm + ", email=" + email + '}';
    }

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
            if(nom==null || prenom==null || email==null)throw new Exception("informations de base manquantes");
            if(nom.trim().equals("") || prenom.trim().equals("") || email.trim().equals("")) throw new Exception("informations de base manquantes");
            return new Employe(this);
        }
        
        
    }

}
