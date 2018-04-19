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
public class ProjetComparator implements Comparator<Projet>{

    @Override
    public int compare(Projet o1, Projet o2) {
        return o1.getTitre().compareTo(o2.getTitre());
    }
    
}
