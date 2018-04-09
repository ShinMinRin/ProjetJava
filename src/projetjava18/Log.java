package projetjava18;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Administrateur
 */
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.*;

public class Log {

    private List<String> lignes = null;
    private static Log instance = null;

    private Log() {
        lignes = new ArrayList<>();
    }

    public static Log getInstance() {
        if (instance == null) {
            instance = new Log();
        }
        return instance;
    }

    public void vider() {
        lignes.clear();
    }

    public void ajouter(String l) {
        try {
            File fout = new File("c:/tempo/fic.txt");
            FileWriter fw = new FileWriter(fout, true);
            PrintWriter pr = new PrintWriter(fw);
            pr.println(l);
            pr.close();
        } catch (Exception e) {
            System.out.println("erreur de fichier");
        }
    }

    public void afficher() {
        for (String l : lignes) {
            System.out.println(l);
        }
    }

}
