/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modele;

import java.util.List;
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
    public void testAjouterObjet() {
        System.out.println("ajouterObjet");
        Object o = null;
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.ajouterObjet(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObject method, of class ProjModele.
     */
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

    /**
     * Test of tousClients method, of class ProjModele.
     */
    @Test
    public void testTousClients() {
        System.out.println("tousClients");
        ProjModele instance = new ProjModele();
        List<Client> expResult = null;
        List<Client> result = instance.tousClients();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tousEmployes method, of class ProjModele.
     */
    @Test
    public void testTousEmployes() {
        System.out.println("tousEmployes");
        ProjModele instance = new ProjModele();
        List<Employe> expResult = null;
        List<Employe> result = instance.tousEmployes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tousProjets method, of class ProjModele.
     */
    @Test
    public void testTousProjets() {
        System.out.println("tousProjets");
        ProjModele instance = new ProjModele();
        List<Projet> expResult = null;
        List<Projet> result = instance.tousProjets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of tousTravaux method, of class ProjModele.
     */
    @Test
    public void testTousTravaux() {
        System.out.println("tousTravaux");
        ProjModele instance = new ProjModele();
        List<Travail> expResult = null;
        List<Travail> result = instance.tousTravaux();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeProjetsClient method, of class ProjModele.
     */
    @Test
    public void testListeProjetsClient() {
        System.out.println("listeProjetsClient");
        Client c = null;
        ProjModele instance = new ProjModele();
        List<Projet> expResult = null;
        List<Projet> result = instance.listeProjetsClient(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listeTravailEmploye method, of class ProjModele.
     */
    @Test
    public void testListeTravailEmploye() {
        System.out.println("listeTravailEmploye");
        Employe e = null;
        ProjModele instance = new ProjModele();
        List<Travail> expResult = null;
        List<Travail> result = instance.listeTravailEmploye(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeVilleClient method, of class ProjModele.
     */
    @Test
    public void testChangeVilleClient() {
        System.out.println("changeVilleClient");
        Client c = null;
        String v = "";
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.changeVilleClient(c, v);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeTelClient method, of class ProjModele.
     */
    @Test
    public void testChangeTelClient() {
        System.out.println("changeTelClient");
        Client c = null;
        String t = "";
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.changeTelClient(c, t);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeGsmEmploye method, of class ProjModele.
     */
    @Test
    public void testChangeGsmEmploye() {
        System.out.println("changeGsmEmploye");
        Employe e = null;
        String gsm = "";
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.changeGsmEmploye(e, gsm);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeEmailEmploye method, of class ProjModele.
     */
    @Test
    public void testChangeEmailEmploye() {
        System.out.println("changeEmailEmploye");
        Employe e = null;
        String email = "";
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.changeEmailEmploye(e, email);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changePourcentage method, of class ProjModele.
     */
    @Test
    public void testChangePourcentage() {
        System.out.println("changePourcentage");
        Travail t = null;
        float p = 0.0F;
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.changePourcentage(t, p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suppClient method, of class ProjModele.
     */
    @Test
    public void testSuppClient() {
        System.out.println("suppClient");
        Client c = null;
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.suppClient(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of suppProjet method, of class ProjModele.
     */
    @Test
    public void testSuppProjet() {
        System.out.println("suppProjet");
        Projet p = null;
        ProjModele instance = new ProjModele();
        String expResult = "";
        String result = instance.suppProjet(p);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of populate method, of class ProjModele.
     */
    @Test
    public void testPopulate() {
        System.out.println("populate");
        ProjModele instance = new ProjModele();
        instance.populate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
    
}
