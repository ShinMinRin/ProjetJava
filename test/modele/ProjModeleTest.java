/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rikak
 */
public class ProjModeleTest {
    
    public ProjModeleTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of ajouterObjet method, of class ProjModele.
     */
    @Test
    public void testAjouterObjetProjet() {
        System.out.println("ajouterObjetProjet");
        Object o = new Projet();
        ProjModele instance = new ProjModele();
        String expResult = "Ajout projet effectué";
        String result = instance.ajouterObjet(o);
        assertEquals(expResult, result);
    }

    /**
     * Test of getObject method, of class ProjModele.
     
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        Object o = null;
        ProjModele instance = new ProjModele();
        Object expResult = null;
        Object result = instance.getObject(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    */
}
