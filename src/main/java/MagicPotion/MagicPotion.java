package MagicPotion;

import food.Food;
import food.items.*;
import item.Item;

import java.util.ArrayList;

/**
 * Représente une Potion Magique unique dans la simulation, héritant de la classe Item.
 *
 * <p>La création de la potion est basée sur une recette spécifique.
 * La présence d'ingrédients supplémentaires influence les propriétés de la potion,
 * notamment sa valeur nutritive et les effets magiques ajoutés.
 */
public class MagicPotion extends Item{

    private int doses;
    private int nutrition = 0;
    private ArrayList<MagicEffect> effect = new ArrayList<MagicEffect>();
    private ArrayList<Food> Ingredients;
    private ArrayList<Food> Recipe = new ArrayList<Food>();


    /**
     * Construit un objet MagicPotion, validant la recette et calculant les effets et la nutrition.
     *
     * <p>Si la liste des ingredients commence par la recette dans le bon ordre,
     * la potion est créée avec succès. Sinon, une erreur est affichée.
     *
     * @param Ingredients La liste ordonnée des ingrédients fournis pour la fabrication.
     * @param quantity La quantité de recettes utilisées (chaque unité donne quatre doses).
     */
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

    /**
     * Retourne la liste des effets magiques que cette potion confère.
     *
     * @return ArrayList des {@link MagicEffect} contenus dans la potion.
     */
    public ArrayList<MagicEffect> getEffects(){
        return effect;
    }

    /**
     * Vérifie si la potion ne contient plus aucune dose.
     *
     * @return true si doses est égal à 0, false sinon.
     */
    public boolean isEmpty(){
        return doses == 0;
    }

    /**
     * Retourne le nombre de doses restantes dans la potion.
     *
     * @return Le nombre de doses.
     */
    public int getDoses() {
        return doses;
    }

    /**
     * Consomme un certain nombre de doses de la potion et applique des effets spéciaux
     * en fonction de la quantité consommée.
     *
     * @param doses La quantité de doses à consommer.
     */
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

    /**
     * Vérifie si la liste d'ingrédients actuelle commence par la liste d'ingrédients attendue
     * dans le bon ordre.
     *
     * @param check La liste d'ingrédients à vérifier.
     * @return true si la recette est respectée jusqu'à la taille de check, false sinon.
     */
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

    /**
     * Isole les ingrédients qui ont été ajoutés en plus de la recette standard ({@link #Recipe}).
     *
     * @return Une ArrayList contenant les ingrédients supplémentaires.
     */
    public ArrayList<Food> getExtraIngredients() {
        ArrayList<Food> extras = new ArrayList<Food>();
        for (int i = Recipe.size(); i < Ingredients.size(); i++) {
            extras.add(Ingredients.get(i));
        }
        return extras;
    }

}
