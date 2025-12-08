package person.Gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import food.Food;
import food.items.*;
import person.Gaulish.Gaulish;
import person.Fighter;
import person.Leader;
import person.Worker;
import java.util.ArrayList;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    private ArrayList<Food> recipe = new ArrayList<>();

    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public void Fighter() {
    }

    @Override
    public void lead(){

    }

    @Override
    public void work(){

    }

    public MagicPotion createMagicPotion(MagicEffect effect, int Quantity){
        recipe.add(new Misteltoe());
        recipe.add(new Carrot());
        recipe.add(new Salt());
        recipe.add(new FourLeafClover());
        recipe.add(new Fish());
        recipe.add(new StoneOil());
        recipe.add(new Honey());
        recipe.add(new Mead());
        recipe.add(new SecretIngredient());
        switch (effect){
            case WEREWOLF : {
                recipe.add(new IdefixHair());
                return new MagicPotion(recipe, 4);
            }
            case DUPLICATION : {
                recipe.add(new TwoHeadUnicornMilk());
                return new MagicPotion(recipe, 4);
            }
            case NOURISHING : {
                recipe.add(new Lobster());
                return new MagicPotion(recipe, 4);
            }
            default : {
                return new MagicPotion(recipe, 4);
            }
        }
    }
}
