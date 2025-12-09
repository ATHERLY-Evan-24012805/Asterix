package person.gaulish.charac;

import food.Food;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import place.Place;
import place.types.GallicVillage;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class GaulishShopKeeperTest {

    private GaulishShopKeeper shopKeeper;
    private Place testPlace;

    @BeforeEach
    void setUp() {
        shopKeeper = new GaulishShopKeeper("Boulangerix", 'M', 1.7, 40, 8, 7);

        // Place de test simple
        testPlace = new GallicVillage() {
            private List<Food> foods = new ArrayList<>();

            @Override
            public void addFood(Food food) {
                foods.add(food);
            }

            @Override
            public List<Food> getFood() {
                return foods;
            }
        };

        shopKeeper.setPlace(testPlace);
    }

    @Test
    void testWorkAddsFood() {
        int initialFoodCount = testPlace.getFood().size();
        shopKeeper.work();
        int newFoodCount = testPlace.getFood().size();

        assertEquals(initialFoodCount + 1, newFoodCount, "Le shopkeeper doit ajouter un aliment au lieu");
        Food produced = testPlace.getFood().get(newFoodCount - 1);
        assertNotNull(produced, "La nourriture produite ne doit pas être nulle");
    }

    @Test
    void testTicsPassedTriggersWork() {
        // On force le compteur pour qu'il déclenche le travail au prochain tic
        for (int i = 0; i < 14; i++) {
            shopKeeper.ticsPassed();
        }

        int countBefore = testPlace.getFood().size();
        // 15ème tic, work() doit être appelé
        shopKeeper.ticsPassed();
        int countAfter = testPlace.getFood().size();

        assertEquals(countBefore + 1, countAfter, "Le travail doit être effectué après 15 tics");
    }

    @Test
    void testWorkTimerResetsBehavior() {
        // Simuler 15 tics pour déclencher work()
        for (int i = 0; i < 15; i++) {
            shopKeeper.ticsPassed();
        }

        int countAfterFirstWork = testPlace.getFood().size();
        assertEquals(1, countAfterFirstWork, "Le travail doit être exécuté après 15 tics");

        // Simuler 14 tics supplémentaires : work() ne doit pas encore être appelé
        for (int i = 0; i < 14; i++) {
            shopKeeper.ticsPassed();
        }

        int countBeforeNextWork = testPlace.getFood().size();
        assertEquals(1, countBeforeNextWork, "Le travail ne doit pas être exécuté avant le prochain cycle de 15 tics");

        // Le 15ème tic déclenche à nouveau le travail
        shopKeeper.ticsPassed();
        int countAfterNextWork = testPlace.getFood().size();
        assertEquals(2, countAfterNextWork, "Le travail doit être exécuté après le second cycle de 15 tics");
    }
}
