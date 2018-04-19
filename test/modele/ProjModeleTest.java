package modele;

import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Tiffany De Schinckel
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
        System.out.println("ajouterObjet client");
        ProjModele instance = new ProjModele();
        Client c = new Client("Mecano", "La Louvière", "064545258");
        String result = instance.ajouterObjet(c);
        String expResult = "Ajout client effectué";
        assertEquals(expResult, result);

        System.out.println("ajouterObjet projet");
        Object o = new Projet("Site internet Mecano", c, "01/05/2018", "02/06/2018");
        expResult = "Ajout projet effectué";
        result = instance.ajouterObjet(o);
        assertEquals(expResult, result);

        System.out.println("ajouterObjet projet doublon");
        expResult = "Ajout projet refusé";
        result = instance.ajouterObjet(o);
        assertEquals(expResult, result);
        instance.suppProjet((Projet) o);
        instance.suppClient(c);
    }

    /**
     * Test of getObject method, of class ProjModele.
     */
    @Test
    public void testGetObject() {
        System.out.println("getObject");
        Object o = new Client("Nishi drinks", "Waterloo", "026451320");
        ProjModele instance = new ProjModele();
        instance.ajouterObjet(o);
        Object expResult = o;
        Object result = instance.getObject(o);
        assertEquals(expResult, result);
        instance.suppClient((Client) o);
    }

    /**
     * Test of tousClients method, of class ProjModele.
     */
    @Test
    public void testTousClients() {
        System.out.println("tousClients");
        ProjModele instance = new ProjModele();
        List<Client> expResult = new ArrayList<>();
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        instance.ajouterObjet(c);
        expResult.add(c);
        c = new Client("Le petit boulanger", "le Roeulx", "064/848294");
        instance.ajouterObjet(c);
        expResult.add(c);

        List<Client> result = instance.tousClients();
        for (Client cTrouve : expResult) {
            if (!result.contains(cTrouve)) {
                fail("client trouvé absent de la liste " + cTrouve);
            }
        }

        for (Client cTrouve : expResult) {
            instance.suppClient(cTrouve);
        }
    }

    /**
     * Test of tousEmployes method, of class ProjModele.
     */
    @Test
    public void testTousEmployes() {
        System.out.println("tousEmployes");
        ProjModele instance = new ProjModele();
        List<Employe> expResult = new ArrayList<>();
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        instance.ajouterObjet(e);
        expResult.add(e);
        e = new Employe("Chevalier", "Marlène", "0494141518", "marlene@hotmail.be");
        instance.ajouterObjet(e);
        expResult.add(e);

        List<Employe> result = instance.tousEmployes();
        for (Employe eTrouve : expResult) {
            if (!result.contains(eTrouve)) {
                fail("Employé trouvé absent de la liste " + eTrouve);
            }
        }

        for (Employe eTrouve : expResult) {
            instance.suppEmploye(eTrouve);
        }
    }

    /**
     * Test of tousProjets method, of class ProjModele.
     */
    @Test
    public void testTousProjets() {
        System.out.println("tousProjets");
        ProjModele instance = new ProjModele();
        List<Projet> expResult = new ArrayList<>();
        Client c = new Client("Mecano", "La Louvière", "064545258");
        Projet p = new Projet("Site internet Mecano", c, "01/05/2018", "02/06/2018");
        instance.ajouterObjet(c);
        instance.ajouterObjet(p);
        expResult.add(p);

        p = new Projet("Logiciel gestion garage Mecano", c, "01/05/2018", "30/06/2018");
        instance.ajouterObjet(p);
        expResult.add(p);

        List<Projet> result = instance.tousProjets();
        for (Projet pTrouve : expResult) {
            if (!result.contains(pTrouve)) {
                fail("Projet trouvé absent de la liste " + pTrouve);
            }
        }

        for (Projet pTrouve : expResult) {
            instance.suppProjet(pTrouve);
        }
        instance.suppClient(c);
    }

    /**
     * Test of tousTravaux method, of class ProjModele.
     */
    @Test
    public void testTousTravaux() {
        System.out.println("tousTravaux");
        ProjModele instance = new ProjModele();
        List<Travail> expResult = new ArrayList<>();
        Client c = new Client("Mecano", "La Louvière", "064545258");
        Projet p = new Projet("Site internet Mecano", c, "01/05/2018", "02/06/2018");
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        instance.ajouterObjet(c);
        instance.ajouterObjet(p);
        instance.ajouterObjet(e);

        Travail t = new Travail(p, e, "01/05/2018");
        instance.ajouterObjet(t);
        expResult.add(t);

        e = new Employe("Chevalier", "Marlène", "0494141518", "marlene@hotmail.be");
        instance.ajouterObjet(e);
        t = new Travail(p, e, "05/05/2018");
        instance.ajouterObjet(t);
        expResult.add(t);

        List<Travail> result = instance.tousTravaux();
        for (Travail tTrouve : expResult) {
            if (!result.contains(tTrouve)) {
                fail("Travail trouvé absent de la liste " + tTrouve);
            }
        }

        instance.suppProjet(p);
        instance.suppEmploye(e);
        instance.suppClient(c);
    }

    /**
     * Test of listeProjetsClient method, of class ProjModele.
     */
    @Test
    public void testListeProjetsClient() {
        System.out.println("listeProjetsClient");
        ProjModele instance = new ProjModele();
        List<Projet> expResult = new ArrayList<>();
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        instance.ajouterObjet(c);

        Projet p = new Projet("Logistique Nishi drinks", c, "02/06/2018", "01/07/2018");
        instance.ajouterObjet(p);
        expResult.add(p);


        p = new Projet("Caisse scan Nishi drinks", c, "02/06/2018", "02/07/2018");
        instance.ajouterObjet(p);
        expResult.add(p);

        List<Projet> result = instance.listeProjetsClient(c);

        for (Projet pTrouve : expResult) {
            if (!result.contains(pTrouve)) {
                fail("Projet non trouvé dans la liste de projet client " + pTrouve);
            }
        }

        for (Projet pTrouve : result) {
            if (!expResult.contains(pTrouve)) {
                fail("Projet incorrectement retourné dans le résultat " + pTrouve);
            }
        }

        for (Projet pTrouve : expResult) {
            instance.suppProjet(pTrouve);
        }
        instance.suppClient(c);
    }

    /**
     * Test of listeTravailEmploye method, of class ProjModele.
     */
    @Test
    public void testListeTravailEmploye() {
        System.out.println("listeTravailEmploye");
        ProjModele instance = new ProjModele();
        List<Travail> expResult = new ArrayList<>();
        
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        instance.ajouterObjet(e);
        
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        instance.ajouterObjet(c);
        Projet p = new Projet("Logistique Nishi drinks", c, "02/06/2018", "01/07/2018");
        instance.ajouterObjet(p);
        Travail t = new Travail(p,e,"12/06/2018",25);
        instance.ajouterObjet(t);
        expResult.add(t);
        
        Projet p2 = new Projet("Caisse scan Nishi drinks", c, "02/06/2018", "02/07/2018");
        instance.ajouterObjet(p);
        t = new Travail(p2,e,"05/06/2018");
        instance.ajouterObjet(t);
        expResult.add(t);
        
        List<Travail> result = instance.listeTravailEmploye(e);
        
        for(Travail tTrouve : expResult){
            if(!result.contains(tTrouve)){
                fail("Travail non trouvé dans la liste " + tTrouve);
            }
        }
        
        for(Travail tTrouve : result){
            if(!expResult.contains(tTrouve)){
                fail("Travail incorrectement retourné dans le résultat "+tTrouve);
            }
        }
        
        instance.suppProjet(p);
        instance.suppProjet(p2);
        instance.suppClient(c);
    }

    /**
     * Test of changeVilleClient method, of class ProjModele.
     */
    @Test
    public void testChangeVilleClient() {
        System.out.println("changeVilleClient");
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        String v = "Ecaussines";
        ProjModele instance = new ProjModele();
        String expResult = "Ecaussines";
        instance.changeVilleClient(c, v);
        String result = c.getVille();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeTelClient method, of class ProjModele.
     */
    @Test
    public void testChangeTelClient() {
        System.out.println("changeTelClient");
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        String t = "025279624";
        ProjModele instance = new ProjModele();
        String expResult = "025279624";
        instance.changeTelClient(c, t);
        String result = c.getTel();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeGsmEmploye method, of class ProjModele.
     */
    @Test
    public void testChangeGsmEmploye() {
        System.out.println("changeGsmEmploye");
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        String gsm = "0475010203";
        ProjModele instance = new ProjModele();
        String expResult = "0475010203";
        instance.changeGsmEmploye(e, gsm);
        String result = e.getGsm();
        assertEquals(expResult, result);
    }

    /**
     * Test of changeEmailEmploye method, of class ProjModele.
     */
    @Test
    public void testChangeEmailEmploye() {
        System.out.println("changeEmailEmploye");
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        String email = "dupont.nicolas@gmail.com";
        ProjModele instance = new ProjModele();
        String expResult = "dupont.nicolas@gmail.com";
        instance.changeEmailEmploye(e, email);
        String result = e.getEmail();
        assertEquals(expResult, result);
    }

    /**
     * Test of changePourcentage method, of class ProjModele.
     */
    @Test
    public void testChangePourcentage() {
        System.out.println("changePourcentage");
        Client c = new Client("Mecano", "La Louvière", "064545258");
        Projet p = new Projet("Site internet Mecano", c, "01/05/2018", "02/06/2018");
        Employe e = new Employe("Dupont", "Nicolas", "0472895607", "nicolas.dupont@gmail.com");
        Travail t = new Travail(p, e, "01/05/2018", 0);
        float pc = 100;
        ProjModele instance = new ProjModele();
        String expResult = Float.toString(100);
        instance.changePourcentage(t, pc);
        String result = Float.toString(t.getPourcentage());
        assertEquals(expResult, result);
    }

    /**
     * Test of suppClient method, of class ProjModele.
     */
    @Test
    public void testSuppClient() {
        System.out.println("suppClient");
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        ProjModele instance = new ProjModele();
        instance.ajouterObjet(c);
        instance.suppClient(c);
        Client result = (Client) instance.getObject(c);
        assertNull(result);
        
        c = new Client("Nishi drinks", "Waterloo", "026451320");
        instance.ajouterObjet(c);
        Projet p = new Projet("Logistique Nishi drinks", c, "02/06/2018", "01/07/2018");
        instance.ajouterObjet(p);
        String expResult2 ="Suppression impossible car le client possède des projets";
        String result2 = instance.suppClient(c);
        assertEquals(expResult2, result2);
        
        instance.suppProjet(p);
        instance.suppClient(c);      
        
    }

    /**
     * Test of suppProjet method, of class ProjModele.
     */
    @Test
    public void testSuppProjet() {
        System.out.println("suppProjet");
        ProjModele instance = new ProjModele();
        Client c = new Client("Nishi drinks", "Waterloo", "026451320");
        instance.ajouterObjet(c);
        Projet p = new Projet("Logistique Nishi drinks", c, "02/06/2018", "01/07/2018");
        instance.ajouterObjet(p);
        String expResult = "Projet supprimé";
        String result = instance.suppProjet(p);
        assertEquals(expResult, result);
    }

    /**
     * Test of populate method, of class ProjModele.
     */
    //@Test
    public void testPopulate() {
        System.out.println("populate");
        ProjModele instance = new ProjModele();
        instance.populate();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
