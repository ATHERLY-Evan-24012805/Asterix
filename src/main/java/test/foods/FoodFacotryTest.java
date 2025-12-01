package test.foods;

import food.FoodFactory;
import food.items.*;
import food.Food;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.jupiter.api.Assertions.*; // Notez le "jupiter"

public class FoodFacotryTest {

    private FoodFactory factory ;

    @BeforeEach
    void init(){
        this.factory = new FoodFactory();
    }

    //verifie que la factory creee bien le bon objet carrot (carotte)
    @Test
    void testCreateFoodFactoryCarrot(){
        Food result = factory.createFood("Carrot");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Carrot.class, result, "L'objet devrait etre de type Carrot");
    }

    //Verifie la creation de Wine (vin)
    @Test
    void testCreateFoodFactoryWine(){
        Food result = factory.createFood("Wine");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Wine.class, result, "L'objet devrait etre de type Wine");
    }

    // Verifie la creation de Salt (sel)
    @Test
    void testCreateFoodFactorySalt(){
        Food result = factory.createFood("Salt");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Salt.class, result, "L'objet devrait etre de type sel");
    }

    //Verifie la creation de BeetJuice (jus de betrave)
    @Test
    void testCreateFoodFactoryBeetJuice(){
        Food result = factory.createFood("BeetJuice");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(BeetJuice.class, result, "L'objet devrait etre de type BeetJuice");
    }

    //Verifie la creation de IdefixHair (cheveux d'Idefix)
    @Test
    void testCreateFoodFactoryIdefixHair(){
        Food result = factory.createFood("IdefixHair");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(IdefixHair.class, result, "L'objet devrait etre de type IdefixHair");
    }

    //Verifie la creation de Honey (miel)
    @Test
    void testCreateFoodFactoryHoney(){
        Food result = factory.createFood("Honey");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Honey.class, result, "L'objet devrait etre de type Honey");
    }

    //Verifie la creation de StoneOil (huile de roche)
    @Test
    void testCreateFoodFactoryStoneOil(){
        Food result = factory.createFood("StoneOil");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(StoneOil.class, result, "L'objet devrait etre de type StoneOil");
    }

    //Verifie la creation de Fish (poisson)
    @Test
    void testCreateFoodFactoryFish(){
        Food result = factory.createFood("Fish");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Fish.class, result, "L'objet devrait etre de type Fish");
    }

    //Verifie la creation de Misteltoe (gui)
    @Test
    void testCreateFoodFactoryMisteltoe(){
        Food result = factory.createFood("Misteltoe");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Misteltoe.class, result, "L'objet devrait etre de type Misteltoe");
    }

    //Verifie la creation de Strawberry (fraise)
    @Test
    void testCreateFoodFactoryStrawberry(){
        Food result = factory.createFood("Strawberry");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Strawberry.class, result, "L'objet devrait etre de type Strawberry");
    }

    //Verifie la creation de FourleafClover (Trèfle a quatre feuille)
    @Test
    void testCreateFoodFactoryFourLeafClover(){
        Food result = factory.createFood("FourLeafClover");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(FourLeafClover.class, result, "L'objet devrait etre de type FourLeafClover");
    }

    //Verifie la creation de TwoHeadUnivornMilk ( Lait de licorne a deux tetes)
    @Test
    void testCreateFoodFactoryTwoHeadUnicornMilk(){
        Food result = factory.createFood("TwoHeadUnicornMilk");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(TwoHeadUnicornMilk.class, result, "L'objet devrait etre de type TwoHeadUnicornMilk");
    }

    //Verifie la creation de Mead (hydromel)
    @Test
    void testCreateFoodFactoryMead(){
        Food result = factory.createFood("Mead");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Mead.class, result, "L'objet devrait etre de type Mead");
    }

    //Verifie de la creation de Lobster (omar)
    @Test
    void testCreateFoodFactoryLobster(){
        Food result = factory.createFood("Lobster");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(Lobster.class, result, "L'objet devrait etre de type Lobster");
    }

    //Verifie de la creation de SecretIngredient (secret ingredient)
    @Test
    void testCreateFoodFactorySecretIngredient(){
        Food result = factory.createFood("SecretIngredient");

        assertNotNull(result, "la factory ne doit pas renvoyer null");
        assertInstanceOf(SecretIngredient.class, result, "L'objet devrait etre de type SecretIngredient");
    }
}
