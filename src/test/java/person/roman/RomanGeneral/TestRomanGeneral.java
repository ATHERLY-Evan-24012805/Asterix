package person.roman.RomanGeneral;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import person.Person;
import person.gaulish.charac.Druid;
import person.roman.charac.RomanGeneral;
import person.roman.charac.RomanLegionary;
import place.types.BattleField;
import place.types.GallicVillage;

class RomanGeneralTest {

    private RomanGeneral general;
    private BattleField battlefield;
    private Person targetGaulois;

    @BeforeEach
    void setUp() {
        // Initialisation avec de vrais objets et des stats fixes
        // General: Force 50, Endurance 50
        general = new RomanGeneral("César", 'M', 1.80, 50, 50, 50);

        // Cible: Force 10, Endurance 10, PV 100
        targetGaulois = new Druid("Asterix", 'M', 1.60, 30, 10, 10);
        targetGaulois.setHealth(100);

        battlefield = new BattleField();

        // Configuration de base : le général vise le gaulois
        general.setTarget(targetGaulois);
    }

    @Test
    void testFight_SoloInBattleField() {
        // Cas : Le général est seul romain, il doit se battre lui-même
        battlefield.addPerson(general);
        battlefield.addPerson(targetGaulois);
        general.setPlace(battlefield);

        double initialHealth = targetGaulois.getHealth();

        // Action
        general.fight();

        // Vérification : La cible a perdu des PV (car le général a frappé)
        assertTrue(targetGaulois.getHealth() < initialHealth, "Le général seul doit frapper la cible");
    }

    @Test
    void testFight_WithAllies_ShouldNotFight() {
        // Cas : Le général est avec un ami, il ne se salit pas les mains
        Person legionary = new RomanLegionary("Légionnaire", 'M', 1.8, 25, 50, 50);

        battlefield.addPerson(general);
        battlefield.addPerson(legionary);
        battlefield.addPerson(targetGaulois);
        general.setPlace(battlefield);

        double initialHealth = targetGaulois.getHealth();

        // Action
        general.fight();

        // Vérification : La cible a TOUJOURS ses PV (le général n'a rien fait)
        assertEquals(initialHealth, targetGaulois.getHealth(), "Le général ne doit pas frapper s'il a des alliés");
    }

    @Test
    void testLead_WithTwoLegionaries_ShouldCommandAttack() {
        // Cas : 2 Légionnaires présents -> Ils doivent taper tous les deux
        Person l1 = new RomanLegionary("L1", 'M', 1.8, 25, 20, 20); // Force 20
        Person l2 = new RomanLegionary("L2", 'M', 1.8, 25, 20, 20); // Force 20

        battlefield.addPerson(general);
        battlefield.addPerson(l1);
        battlefield.addPerson(l2);
        battlefield.addPerson(targetGaulois);
        general.setPlace(battlefield);

        // On s'assure que le cooldown permet l'action
        // (Supposons que ticBeforeAction est à 0 par défaut ou accessible)
        // general.setTicBeforeAction(0);

        double initialHealth = targetGaulois.getHealth();

        // Action
        general.lead();

        // Vérification : La cible a dû prendre 2 coups
        // Avec Force 20, ça fait des dégâts. On vérifie juste que ça a baissé significativement
        assertTrue(targetGaulois.getHealth() < initialHealth, "Les légionnaires auraient dû attaquer sous ordre");
    }

    @Test
    void testLead_WithOneLegionary_ShouldNotCommand() {
        // Cas : Pas assez de troupes (seulement 1)
        Person l1 = new RomanLegionary("L1", 'M', 1.8, 25, 20, 20);

        battlefield.addPerson(general);
        battlefield.addPerson(l1);
        battlefield.addPerson(targetGaulois);
        general.setPlace(battlefield);

        double initialHealth = targetGaulois.getHealth();

        // Action
        general.lead();

        // Vérification : Rien ne se passe
        assertEquals(initialHealth, targetGaulois.getHealth(), "Pas assez de légionnaires pour commander");
    }

    @Test
    void testNotInBattleField() {
        // Cas : Dans un village paisible
        GallicVillage village = new GallicVillage();
        village.addPerson(general);
        village.addPerson(targetGaulois); // Même s'il y a un ennemi
        general.setPlace(village);

        double initialHealth = targetGaulois.getHealth();

        // Action
        general.fight();
        general.lead();

        // Vérification : Aucune violence dans le village
        assertEquals(initialHealth, targetGaulois.getHealth(), "Pas de combat hors du champ de bataille");
    }
}