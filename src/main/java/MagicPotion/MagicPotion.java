package MagicPotion;

import food.Food;
import food.items.*;
import item.Item;

import java.util.ArrayList;

public class MagicPotion extends Item{
    private int doses;
    private int nutrition = 0;
    private ArrayList<MagicEffect> effect = new ArrayList<MagicEffect>();
    private ArrayList<Food> Ingredients;
    private ArrayList<Food> Recipe = new ArrayList<Food>();

    public MagicPotion(ArrayList<Food> Ingredients, int quantity)  {
        super("Magic Potion");
        this.doses = quantity * 4;
        this.Ingredients = Ingredients;
        Recipe.add(new Misteltoe());
        Recipe.add(new Carrot());
        Recipe.add(new Salt());
        Recipe.add(new FourLeafClover());
        Recipe.add(new Fish());
        Recipe.add(new StoneOil());
        Recipe.add(new Honey());
        Recipe.add(new Mead());
        Recipe.add(new SecretIngredient());
        if (containsAllIngredients(Recipe)){
            ArrayList<Food> extras = getExtraIngredients();
            for (Food f : extras){
                if (f instanceof Lobster || f instanceof Strawberry || f instanceof BeetJuice){
                    this.nutrition +=25;
                }

                if (f instanceof TwoHeadUnicornMilk){
                    effect.add(MagicEffect.DUPLICATION);
                }

                if(f instanceof IdefixHair){
                    effect.add(MagicEffect.WEREWOLF);
                }
            }
        }
        else {
            System.err.println("La potion a explosée! Essayez de suivre la recette dans l'ordre.");
        }
    }


    public ArrayList<MagicEffect> getEffects(){
        return effect;
    }

    public boolean isEmpty(){
        return doses == 0;
    }

    public int getDoses() {
        return doses;
    }

    public void consumeDoses(int doses) {

        // Vérification qu'il y a assez de doses dans la potion
        if (doses > this.doses) {
            System.err.println("Erreur : pas assez de doses dans la potion.");
            return;
        }

        this.doses -= doses;

        // Appliquer les effets
        if (doses == 4) {
            effect.add(MagicEffect.PERMANENT);
        }
        if (doses > 8){
            effect.add(MagicEffect.TURN_TO_STONE);
        }
    }

    public boolean containsAllIngredients(ArrayList<Food> check) {
        if (check.size()>Ingredients.size()){
            return false;
        }
        for(int i=0; i< check.size();++i){
            Food expected = check.get(i);
            Food actual = Ingredients.get(i);
            if (expected instanceof StoneOil && actual instanceof BeetJuice){
                continue;
            }

            if(Ingredients.get(i).getClass()!=check.get(i).getClass()){
                return false;
            }
        }
        return true;
    }

    public ArrayList<Food> getExtraIngredients() {
        ArrayList<Food> extras = new ArrayList<Food>();
        for (int i = Recipe.size(); i < Ingredients.size(); i++) {
            extras.add(Ingredients.get(i));
        }
        return extras;
    }

}
