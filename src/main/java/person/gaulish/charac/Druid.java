package person.gaulish.charac;
import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import clock.Clock;
import food.Food;
import food.items.*;
import item.Item;
import person.Person;
import person.Person;
import person.gaulish.Gaulish;
import person.Fighter;
import person.Leader;
import person.Worker;
import place.Place;
import place.types.BattleField;

import place.Place;

import java.util.ArrayList;
import java.util.List;

/**
 * Représente un Druide, un personnage gaulois polyvalent qui joue le rôle de
 * Combattant ({@link Fighter}), Chef ({@link Leader}) et Travailleur ({@link Worker}).
 *
 * <p>Le Druide est unique par sa capacité à préparer des potions magiques
 * et à réassigner les rôles des autres Gaulois.
 */
public class Druid extends Gaulish implements Fighter, Leader, Worker{

    private ArrayList<Food> recipe = new ArrayList<>();

    /**
     * Construit un nouveau Druide avec les caractéristiques spécifiées.
     *
     * @param name Le nom du Druide.
     * @param gender Le genre du Druide ('M', 'F' ou 'X').
     * @param height La taille du Druide.
     * @param age L'âge du Druide.
     * @param strength La force de base du Druide.
     * @param endurance L'endurance de base du Druide.
     */
    public Druid(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }

    /**
     * Crée une nouvelle instance de {@code Druid} avec les caractéristiques données.
     * Utilisé pour la potion de duplication.
     *
     * @param name Le nom de la nouvelle instance.
     * @param gender Le genre de la nouvelle instance.
     * @param height La taille de la nouvelle instance.
     * @param age L'âge de la nouvelle instance.
     * @param strength La force de la nouvelle instance.
     * @param endurance L'endurance de la nouvelle instance.
     * @return Une nouvelle instance de Druid.
     */
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return new Druid(name, gender, height, age, strength, endurance);
    }

    /**
     * Implémentation du rôle Fighter.
     * Le Druide cherche et attaque le Romain le plus faible sur un {@link BattleField}.
     * L'attaque n'a lieu que si le Druide se trouve dans un BattleField.
     */
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

    /**
     * Implémentation du rôle Leader.
     * Le Druide attend un ordre explicite pour changer le métier d'un autre personnage,
     * mais cette méthode sert de point d'attente dans le cycle de vie du leader.
     */
    @Override
    public void lead() {
        System.out.println(getName() + "Attend l'ordre de changer un métier");
    }

    /**
     * Implémentation du rôle Worker.
     * Le Druide travaille en préparant des potions. La logique concrète de création
     * se trouve dans {@link #createMagicPotion(MagicEffect, int, Place)}.
     */
    @Override
    public void work(){
        // fais des potions
    }

    /**
     * Crée une nouvelle {@link MagicPotion} en utilisant les ingrédients disponibles dans le {@link Place} spécifié.
     *
     * <p>La méthode vérifie si les ingrédients de base nécessaires à la recette sont présents dans l'inventaire du
     * lieu, et ajoute des ingrédients spéciaux en fonction de l'effet désiré.
     *
     * <p>Si la recette est incomplète ou incorrecte, la potion "explose" et retourne null.
     * Si elle est réussie, la potion est ajoutée au lieu, et les ingrédients sont retirés.
     *
     * @param effect L'effet magique principal désiré pour la potion (influence les ingrédients spéciaux).
     * @param Quantity Le nombre de recettes à préparer (chaque unité donne quatre doses).
     * @param place Le lieu où le Druide tente de créer la potion (source des ingrédients et destination de la potion).
     * @return La MagicPotion nouvellement créée, ou null en cas d'échec.
     */
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

    /**
     * Permet au Druide de changer le rôle (la classe concrète) d'une autre personne gauloise.
     *
     * <p>La personne cible est retirée du lieu et du système d'abonnement de Clock,
     * puis une nouvelle instance du rôle spécifié est créée avec les anciennes caractéristiques
     * et ajoutée au lieu et à la Clock.
     *
     * @param target La {@link Person} dont le rôle doit être changé.
     * @param newRole Le nouveau rôle désiré (ex: "BLACKSMITH", "SHOPKEEPER", "INNKEEPER").
     */
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

    /**
     * Est appelé à chaque tic de l'horloge.
     * Appelle l'implémentation de la classe parente {@link Gaulish#ticsPassed()}.
     */
    @Override
    public void ticsPassed() {
        super.ticsPassed();
    }

    /**
     * Recherche le personnage Romain le plus faible (celui avec le moins de points de vie)
     * parmi une liste donnée de personnes.
     *
     * @param persons La liste des personnes à inspecter.
     * @return Le {@link Person} Romain le plus faible, ou null s'il n'y a pas de Romain.
     */
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

    /**
     * Détermine si une personne donnée est un Romain en vérifiant le nom de son package.
     *
     * @param person La {@link Person} à vérifier.
     * @return true si la personne appartient au package person.roman, false sinon.
     */
    protected boolean isRoman(Person person) {
        return person.getClass().getPackageName().contains("person.roman");
    }

    /**
     * Définit et retourne la recette standard de base requise pour la création de la potion magique.
     *
     * @return Une ArrayList ordonnée des {@link Food} requis pour la recette de base.
     */
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
}
