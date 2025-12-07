package persons.Gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import food.Food;
import food.items.*;
import persons.Gaulish.Gaulish;
import persons.Fighter;
import persons.Leader;
import persons.Worker;
import java.util.ArrayList;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    private ArrayList<Food> recette = new ArrayList<>();

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
        recette.add(new Misteltoe());
        recette.add(new Carrot());
        recette.add(new Salt());
        recette.add(new FourLeafClover());
        recette.add(new Fish());
        recette.add(new StoneOil());
        recette.add(new Honey());
        recette.add(new Mead());
        recette.add(new SecretIngredient());
        switch (effect){
            case WEREWOLF -> {
                recette.add(new IdefixHair());
                return new MagicPotion(recette);
            }
            case DUPLICATION -> {
                recette.add(new TwoHeadUnicornMilk());
                return new MagicPotion(recette);
            }
            case NOURISHING -> {
                recette.add(new Lobster());
                return new MagicPotion(recette);
            }
            default -> {
                return new MagicPotion(recette);
            }
        }
    }
}
