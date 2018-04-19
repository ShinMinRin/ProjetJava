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
public class EmployeComparator implements Comparator<Employe>{

    @Override
    public int compare(Employe o1, Employe o2) {
        if(!o1.getNom().equals(o2.getNom())) return o1.getNom().compareTo(o2.getNom());
        if(!o1.getPrenom().equals(o2.getPrenom())) return o1.getPrenom().compareTo(o2.getPrenom());
        return o1.getEmail().compareTo(o2.getEmail());
    }
    
}
