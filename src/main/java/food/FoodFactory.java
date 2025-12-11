package food;
import food.items.*;

/**
 * Une factory responsable de l'instanciation des différents objets alimentaires
 * Food en fonction d'une chaîne de caractères donnée.
 */
public class FoodFactory {

    /**
     * Crée et retourne une instance de Food correspondant au type spécifié.
     * <p>Utilise une structure switch pour déterminer quelle sous-classe
     * de Food instancier. Les types valides sont définis par les classes
     * concrètes existantes dans le package food.items.
     *
     * @param type_of_food La chaîne de caractère représentant le type d'aliment à créer.
     * @return Une nouvelle instance de la classe Food correspondante ou null si le type n'est pas reconnu.
     */
    public Food createFood(String type_of_food) {
        switch (type_of_food) {
            case "Wine":
                return new Wine();
            case "Carrot":
                return new Carrot();
                case "Salt":
                    return new Salt();
            case "BeetJuice":
                return new BeetJuice();
            case "IdefixHair":
                return new IdefixHair();
            case "Honey":
                return new Honey();
            case "StoneOil":
                return new StoneOil();
            case "Fish":
                return new Fish();
            case "Misteltoe":
                return new Misteltoe();
            case "FourLeafClover":
                return new FourLeafClover();
            case "TwoHeadUnicornMilk":
                return new TwoHeadUnicornMilk();
            case "Mead":
                return new Mead();
            case "Lobster":
                return new Lobster();
            case "SecretIngredient":
                return new SecretIngredient();
                case "Strawberry":
                    return new Strawberry();
        }
        return null;
    }
}
