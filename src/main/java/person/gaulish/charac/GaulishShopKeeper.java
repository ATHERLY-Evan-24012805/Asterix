package person.gaulish.charac;
import clock.TemporalObject;
import food.Food;
import food.FoodFactory;
import person.Person;
import person.gaulish.Gaulish;
import person.Worker;
import place.Place;

import java.util.Random;

public class GaulishShopKeeper extends Gaulish implements Worker {

    private int worktimer = 15;
    private FoodFactory factory = new FoodFactory();
    private Random random = new Random();

    public GaulishShopKeeper(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new GaulishShopKeeper(name, gender, height, age, strength, endurance);
    }

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

    @Override
    public void ticsPassed() {
        this.worktimer--;

        if (worktimer == 0) {
            work();
            worktimer = 15;
        }
    }
}
