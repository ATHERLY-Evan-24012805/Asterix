package Personnage.test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import Personnage.Roman.charac.RomanGeneral;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class CharacterTest {

    Character character;
    RomanGeneral mockAdversaire; // Classe encore non implémentée → mock

    @BeforeEach
    void setUp() {
        // On crée un personnage concret via une classe anonyme 
        character = new Character("Obélix", 'M', 1.8, 35, 40, 50) {};

        // Adversaire mocké (RomanGeneral n'a pas encore de logique → mock utile)
        mockAdversaire = mock(RomanGeneral.class);

        // Simuler l'état interne private via when().thenCallRealMethod() pour fight()
        // On doit mocker le getter/setter car la santé est private
        when(mockAdversaire.health).thenReturn(100);
    }

    @Test
    void testEat() {
        character.eat();
        // hunger est private → vérification via réflexion
        int hunger = getPrivateInt(character, "hunger");
        assertEquals(110, hunger);
    }

    @Test
    void testHeal() {
        character.heal();
        int health = getPrivateInt(character, "health");
        assertEquals(110, health);
    }

    @Test
    void testDrinkPotion() {
        character.drinkPotion();
        int potion = getPrivateInt(character, "potion");
        assertEquals(10, potion);
    }

    @Test
    void testFightWithMockedAdversary() throws Exception {
        // On fabrique un adversaire "réel" via une classe anonyme plutôt qu’un mock pur
        Character adversaire = new Character("Caius", 'M', 1.7, 30, 10, 20) {};

        // Avant combat
        int healthBefore = getPrivateInt(adversaire, "health");

        // Combat
        character.fight(adversaire);

        int healthAfter = getPrivateInt(adversaire, "health");

        assertTrue(healthAfter < healthBefore,
                "L'adversaire devrait perdre de la vie lors d'un combat.");
    }

    // -------- utilitaires pour lire private fields sans getters ----------
    private int getPrivateInt(Object obj, String field) {
        try {
            var f = obj.getClass().getSuperclass().getDeclaredField(field);
            f.setAccessible(true);
            return (int) f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
