package person.gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import food.Food;
import food.items.*;
import item.Item;
import person.Person;
import person.gaulish.Gaulish;
import person.Fighter;
import person.Leader;
import person.Worker;
import place.Place;

import java.util.ArrayList;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    private ArrayList<Food> recipe = new ArrayList<>();

    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }

    @Override
    public void fight() {
        //fait des degats modérés
    }

    @Override
    public void lead(){
        // changes les roles en fonction de la demande du joueurs
    }

    @Override
    public void work(){
        // fais des potions
    }

    public MagicPotion createMagicPotion(MagicEffect effect, int Quantity, Place place){
        ArrayList<Food> recipe = getBaseRecipe();

        switch (effect){
            case WEREWOLF : {
                recipe.add(new IdefixHair());
                break;
            }
            case DUPLICATION : {
                recipe.add(new TwoHeadUnicornMilk());
                break;
            }
            case NOURISHING : {
                recipe.add(new Lobster());
                break;
            }
            default : {
                break;
            }
        }

        ArrayList<Item> inventory = new ArrayList<>(place.getItems());
        ArrayList<Food> usedIngredients = new ArrayList<>();


        // Parcours de la recette
        for (Food needed : recipe) {

            boolean found = false;

            // Parcours de l'inventaire
            for (Item item : inventory) {
                // Vérification si c'est un aliment
                if (item instanceof Food) {
                    Food food = (Food) item;
                    //Vérification qu'on attend cet aliment et qu'on ne l'a pas déjà utilisé
                    if (food.getClass() == needed.getClass() && !usedIngredients.contains(food)) {
                        usedIngredients.add(food);
                        found = true;
                        break;
                    }
                }
            }

            // Si on n'a pas trouvé l'aliment on renvoie une erreur
            if (!found) {
                System.err.println("La potion explose : ingrédient manquant ("
                        + needed.getClass().getSimpleName() + ")");
                return null;
            }
        }


        if (usedIngredients.size() != recipe.size()) {
            System.err.println("La potion explose : mauvais ingrédients ou mauvais ordre !");
            return null;
        }

        // Enlever les ingrédients de l'inventaire de l'endroit
        for (Food f : usedIngredients) {
            place.removeItem(f);
        }

        MagicPotion potion = new MagicPotion(usedIngredients, Quantity);
        place.addItem(potion);
        return potion;
    }

    // Fonction pour avoir la potion magique
    private ArrayList<Food> getBaseRecipe() {
        ArrayList<Food> recipe = new ArrayList<>();
        recipe.add(new Misteltoe());
        recipe.add(new Carrot());
        recipe.add(new Salt());
        recipe.add(new FourLeafClover());
        recipe.add(new Fish());
        recipe.add(new StoneOil());
        recipe.add(new Honey());
        recipe.add(new Mead());
        recipe.add(new SecretIngredient());
        return recipe;
    }

    @Override
    public void ticsPassed() {

    }
}
