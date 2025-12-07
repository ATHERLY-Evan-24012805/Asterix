package MagicPotion;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import persons.Person;

import static org.junit.jupiter.api.Assertions.*;

class MagicPotionTest {

    Person character;

    @BeforeEach
    void setup() {
        character = new Person("Obélix", 'M', 1.8, 35, 40, 50) {};
    }


    @Test
    void testConstructorMagicPotionNormal() {
        MagicPotion potion = new MagicPotion(MagicEffect.NORMAL);
        assertEquals(3, potion.getDoses());
        assertEquals(MagicEffect.NORMAL, potion.getEffects());
    }

    @Test
    void testConstructorMagicPotionPermanant() {
        MagicPotion potion = new MagicPotion(MagicEffect.PERMANENT);
        assertEquals(2, potion.getDoses());
        assertEquals(MagicEffect.PERMANENT, potion.getEffects());
    }

    @Test
    void testConstructorMagicPotionStone() {
        MagicPotion potion = new MagicPotion(MagicEffect.TURN_TO_STONE);
        assertEquals(1, potion.getDoses());
        assertEquals(MagicEffect.TURN_TO_STONE, potion.getEffects());
    }

    @Test
    void testConstructorMagicPotionDuplicate() {
        MagicPotion potion = new MagicPotion(MagicEffect.DUPLICATION);
        assertEquals(10, potion.getDoses());
        assertEquals(MagicEffect.DUPLICATION, potion.getEffects());
    }

    @Test
    void testConstructorMagicPotionWerewolf() {
        MagicPotion potion = new MagicPotion(5, MagicEffect.WEREWOLF);
        assertEquals(5, potion.getDoses());
        assertEquals(MagicEffect.WEREWOLF, potion.getEffects());
    }
    //test

}