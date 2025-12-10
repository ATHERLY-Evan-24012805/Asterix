package person.gaulish.charac;
import food.Food;
import food.FoodFactory;
import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

import java.util.Random;

/**
 * Représente un Marchand Gaulois (ShopKeeper).
 *
 * <p>Un Marchand est un type de Gaulois qui implémente le rôle de Travailleur.
 * Son rôle principal est de produire aléatoirement de la nourriture** et de l'ajouter
 * à l'inventaire du lieu ({@link place.Place}) où il se trouve.
 */
public class GaulishShopKeeper extends Gaulish implements Worker {

    private int worktimer = 15;
    private FoodFactory factory = new FoodFactory();
    private Random random = new Random();

    /**
     * Construit une nouvelle instance de GaulishShopKeeper.
     *
     * @param name Le nom du Marchand.
     * @param gender Le genre du Marchand ('M', 'F' ou 'X').
     * @param height La taille du Marchand.
     * @param age L'âge du Marchand.
     * @param strength La force de base du Marchand.
     * @param endurance L'endurance de base du Marchand.
     */
    public GaulishShopKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Crée une nouvelle instance de GaulishShopKeeper avec les caractéristiques données.
     * Utilisé pour la potion Duplicate.
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de {@code GaulishShopKeeper}.
     */
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishShopKeeper(name, gender, height, age, strength, endurance);
    }

    /**
     * La fonction work pour le Marchand consiste à produire un aliment aléatoire
     * et à l'ajouter à l'inventaire de son lieu actuel.
     *
     * <p>Elle choisit un aliment parmi une liste prédéfinie de types, crée l'objet
     * via la {@link food.FoodFactory} et l'ajoute au {@link place.Place}.
     */
    @Override
    public void work() {
        String[] possibleFoods = {
                "BeetJuice", "Carrot", "Fish", "FourLeafClover", "Honey", "IdefixHair", "Lobster", "Mead",
                "Mistletoe", "Salt", "SecretIngredient", "StoneOil", "Strawberry", "TwoHeadUnicornMilk", "Wine"
        };

        String chosenFood = possibleFoods[random.nextInt(possibleFoods.length)];
        Food food = factory.createFood(chosenFood);

        Place place = this.getPlace();
        place.addFood(food);

        System.out.println(getName() + "a produit : " + chosenFood);
    }

    /**
     * Appelé à chaque tic de l'horloge. Gère le compteur de travail.
     *
     * <p>Décrémente le worktimer. Lorsque le compteur atteint 0,
     * la méthode work() est appelée et le compteur est réinitialisé.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();

        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 15;
        }
    }
}
