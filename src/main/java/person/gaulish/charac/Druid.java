package person.gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import clock.Clock;
import food.Food;
import food.items.*;
import person.Person;
import person.gaulish.Gaulish;
import person.Fighter;
import person.Leader;
import person.Worker;
import place.Place;
import place.types.BattleField;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.List;

public class Druid extends Gaulish implements Fighter, Leader, Worker{
    private ArrayList<Food> recipe = new ArrayList<>();

    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

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
    public void fight() {
        // Pas de combat en dehors d'un BattleField
        Place place = this.getPlace();
        if (!(place instanceof BattleField)) {
            return;
        }

        List<Person> persons = place.getPeople();
        Person target = findWeakestRoman(persons);

        if (target == null) {
            return;
        }

        this.fight(target);

        System.out.println(
                this.getName() + " attaque " + target.getName() +
                        " dans le champ de bataille ! Il lui reste " + target.getHealth() + " PV."
        );
    }

    @Override
    public void lead() {
        System.out.println(getName() + "Attend l'ordre de changer un métier");
    }

    @Override
    public void work(){
        // fais des potions
    }

    public MagicPotion createMagicPotion(MagicEffect effect, int Quantity){
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

    public void changeRole(Person target, String newRole, Clock clock) {
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
        clock.unsubscribe(target);
        place.removePerson(target);

        // Ajouter la nouvelle personne
        place.addPerson(replacement);
        clock.subscribe(replacement);

        System.out.println("Le druide assigne " + target.getName() +
                " au métier " + newRole.toLowerCase());
    }

    @Override
    public void ticsPassed() {

    }
}
