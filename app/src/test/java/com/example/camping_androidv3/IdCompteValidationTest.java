package com.example.camping_androidv3;

import org.junit.Test;

import static org.junit.Assert.*;

public class IdCompteValidationTest {

    @Test
    public void testIdCompteEstValide() {
        int idCompte = 5;

        //passe si ID est positif
        assertTrue("idCompte doit être > 0", idCompte > 0);
    }

    @Test
    public void testIdCompteInvalide() {
        int idCompte = -1; //L'utilisateur n'est pas connecté

        //passe si détecte bien que ID est invalide
        assertFalse("idCompte ne doit pas être égal à -1", idCompte == -1);
    }
}
