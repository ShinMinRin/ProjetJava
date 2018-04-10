package modele;
import java.util.*;

/**
 *
 */
public class Client {

    private String nom;
    private String ville;
    private String tel;
    
    /**
     * Constructeur par défaut
     */
    public Client() {
    }
    
    
    /**
     * Constructeur complet
     */
    public Client(String nom, String ville, String tel){
        
    }

    /**
     * Getters et setters
     */
    public String getNom() {
        return nom;
    }

    public String getVille() {
        return ville;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public String getTel() {
        return tel;
    }

    public void setTel(String tel) {
        this.tel = tel;
    }

    /**
     * Autre
     */
    @Override
    public String toString() {
        return "Client{" + "nom=" + nom + ", ville=" + ville + ", tel=" + tel + '}';
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 47 * hash + Objects.hashCode(this.nom);
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
        final Client other = (Client) obj;
        if (!Objects.equals(this.nom, other.nom)) {
            return false;
        }
        return true;
    }

    

    
}
