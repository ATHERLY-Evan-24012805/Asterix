package person.roman.RomanGeneral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import person.Person;
import person.roman.charac.RomanGeneral;
import person.roman.charac.RomanLegionary;
import person.roman.charac.RomanPrefect;
import place.types.RomanCity; // Ou n'importe quel Place

import java.util.List;

class RomanPrefectTest {

    private RomanPrefect prefect;
    private RomanCity city;
    private RomanLegionary targetLegionary;

    @BeforeEach
    void setUp() {
        // 2. Création des vrais objets
        // Prefect: Force 30, Endurance 30
        prefect = new RomanPrefect("Prefectus", 'M', 1.80, 40, 30, 30);

        // La cible : Un légionnaire nommé Titus
        targetLegionary = new RomanLegionary("Titus", 'M', 1.75, 25, 50, 50);

        // 3. Configuration du lieu
        city = new RomanCity();
        city.addPerson(prefect);
        city.addPerson(targetLegionary);

        // 4. Mise en place des références
        prefect.setPlace(city);
    }

    @Test
    void testInitialization() {
        // Vérifie que le constructeur a bien défini le cooldown spécifique au Préfet
        // (Supposons que vous ayez un getter pour ticBeforeAction, sinon ce test est optionnel)
        // assertEquals(24, prefect.getTicBeforeAction(), "Le préfet doit attendre 24 tics");
    }

    @Test
    void testLead_PromotesLegionaryToGeneral() {
        // ARRANGE
        prefect.setTarget(targetLegionary); // Le préfet vise Titus

        // ACT
        prefect.lead();

        // ASSERT
        List<Person> population = city.getListOfPersons();

        // 1. Vérifier la taille de la population (Toujours 2 : Le préfet + le nouveau)
        assertEquals(2, population.size(), "La population doit rester stable (1 mort, 1 naissance)");

        // 2. Vérifier que l'ancien Légionnaire a disparu
        assertFalse(population.contains(targetLegionary), "L'ancien légionnaire doit être retiré de la ville");

        // 3. Trouver la nouvelle personne (celle qui n'est pas le préfet)
        Person newGeneral = population.stream()
                .filter(p -> p != prefect)
                .findFirst()
                .orElse(null);

        assertNotNull(newGeneral, "Il doit y avoir une nouvelle personne dans la ville");

        // 4. Vérifier que c'est bien un Général
        assertInstanceOf(RomanGeneral.class, newGeneral, "La nouvelle personne doit être un RomanGeneral");

        // 5. Vérifier que les caractéristiques ont été copiées
        assertEquals("Titus", newGeneral.getName(), "Le nom doit être conservé");
        assertEquals(targetLegionary.getHeight(), newGeneral.getHeight(), "La taille doit être conservée");
        assertEquals(targetLegionary.getAge(), newGeneral.getAge(), "L'âge doit être conservé");

        // 6. Vérifier que les stats de combat sont celles d'origine (selon votre code actuel)
        // Note: Votre code copie la force/endurance de la cible.
        assertEquals(targetLegionary.getStrength(), newGeneral.getStrength());
    }

    @Test
    void testTicsPassed_DecreasesHunger() {
        // ARRANGE
        int initialFood = prefect.getHunger(); // Supposons 100

        // ACT
        prefect.ticsPassed();

        // ASSERT
        // On vérifie que le super.ticsPassed() a bien été appelé en regardant l'effet sur la faim
        assertTrue(prefect.getHunger() < initialFood, "Le temps qui passe doit donner faim (appel super)");
    }
}