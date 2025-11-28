package org.food;
import org.food.items.*;

public class FoodFactory {
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
            case "MistelToe":
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
        }
        return null;
    }
}
