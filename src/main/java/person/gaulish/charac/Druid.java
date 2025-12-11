package person.gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import clock.Clock;
import food.Food;
import food.items.*;
import item.Item;
import person.Person;
import person.gaulish.Gaulish;
import person.Fighter;
import person.Leader;
import person.Worker;
import place.Place;
import place.types.BattleField;

import java.util.ArrayList;
import java.util.List;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    private ArrayList<Food> recipe = new ArrayList<>();

    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    public String getType(){
        return "Druide";
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new Druid(name, gender, height, age, strength, endurance);
    }

    // à voir si on ne fait pas plutôt choisir au clan leader qui il choisit d'attaquer.
    protected Person findWeakestRoman(List<Person> persons) {
        Person target = null;
        for (Person p : persons) {
            if (isRoman(p)) {
                if (target == null || p.getHealth() < target.getHealth()) {
                    target = p;
                }
            }
        }
        return target;
    }

    protected boolean isRoman(Person person) {
        return person.getClass().getPackageName().contains("person.roman");
    }

    @Override
    public int fight() {

        if (this.getTarget() == null) {
            return 0;
        }
        // Pas de combat en dehors d'un BattleField
        Place place = this.getPlace();
        if (!(place instanceof BattleField)) {
            return 0;
        }

        List<Person> persons = place.getPeople();
        Person target = findWeakestRoman(persons);

        if (target == null) {
            return 0;
        }

        int damage = this.hit(target);

        return damage;
    }

    @Override
    public void lead() {
        System.out.println(getName() + "Attend l'ordre de changer un métier");
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

    public void changeRole(Person target, String newRole) {
        if (target == null || newRole == null) return;

        Place place = target.getPlace();
        if (place == null) return;

        Person replacement = null;

        switch (newRole.toUpperCase()) {
            case "BLACKSMITH":
                replacement = new GaulishBlacksmith(
                        target.getName(),
                        target.getGender(),
                        target.getHeight(),
                        target.getAge(),
                        target.getStrength(),
                        target.getEndurance()
                );
                break;

            case "SHOPKEEPER":
                replacement = new GaulishShopKeeper(
                        target.getName(),
                        target.getGender(),
                        target.getHeight(),
                        target.getAge(),
                        target.getStrength(),
                        target.getEndurance()
                );
                break;

            case "INNKEEPER":
                replacement = new GaulishInnKeeper(
                        target.getName(),
                        target.getGender(),
                        target.getHeight(),
                        target.getAge(),
                        target.getStrength(),
                        target.getEndurance()
                );
                break;

            default:
                System.out.println("Rôle inconnu : " + newRole);
                return;
        }

        // Supprimer l'ancienne personne
        Clock.getInstance().unsubscribe(target);
        place.removePerson(target);

        // Ajouter la nouvelle personne
        place.addPerson(replacement);
        Clock.getInstance().subscribe(replacement);

        System.out.println("Le druide assigne " + target.getName() +
                " au métier " + newRole.toLowerCase());
    }

    @Override
    public void ticsPassed() {

    }
}
