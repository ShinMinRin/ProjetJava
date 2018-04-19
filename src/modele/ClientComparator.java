/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.Comparator;

/**
 *
 * @author Tiffany De Schinckel
 */
public class ClientComparator implements Comparator<Client>{

    @Override
    public int compare(Client c1, Client c2) {
        if(!c1.getNom().equals(c2.getNom())) return c1.getNom().compareTo(c2.getNom());
        if(!c1.getVille().equals(c2.getVille())) return  c1.getVille().compareTo(c2.getVille());
        return c1.getTel().compareTo(c2.getTel());
    }
    
    
    
}
