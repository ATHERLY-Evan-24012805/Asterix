package persons;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import person.Person;
import person.types.*;
import person.types.Roman.charac.RomanGeneral;


import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class PersonTest {
/*
    //Création instance personnages
    Person character;
    RomanGeneral mockAdversaire;

    @BeforeEach
    void setUp() {

        character = new Person("Obélix", 'M', 1.8, 35, 40, 50) {};
        //mock de la classe RomanGeneral
        mockAdversaire = mock(RomanGeneral.class);
        when(mockAdversaire.getHealth()).thenReturn(100);
    }

    @Test
    void testEat() {
        character.eat();
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
    void testFightWithMockedAdversary() {
        Person adversaire = new Person("Caius", 'M', 1.7, 30, 10, 20) {};

        int healthBefore = getPrivateInt(adversaire, "health");

        character.fight(adversaire);

        int healthAfter = getPrivateInt(adversaire, "health");

        assertTrue(healthAfter < healthBefore);
    }
/*
    private int getPrivateInt(Object obj, String field) {
        try {
            var f = obj.getClass().getSuperclass().getDeclaredField(field);
            f.setAccessible(true);
            return (int) f.get(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

 */
}
