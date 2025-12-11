package food;

import clock.TemporalObject;
import item.Item;

/**
 * Classe abstraite représentant tout aliment ou ingrédient dans la simulation.
 */
public abstract class Food extends Item {

    //Attribut
    private int bonus;

    //constructeur

    /**
     * Construit un objet Food avec un nom et une valeur de bonus spécifiques.
     *
     * @param name Le nom de l'aliment.
     * @param bonus La valeur du bonus de l'aliment
     */
    public Food(String name, int bonus) {
        super(name);
        this.bonus = bonus;
    }

    /**
     * Construit un objet Food avec un nom spécifique et une valeur de bonus par défaut.
     *
     * @param name Le nom de l'aliment.
     */
    public Food(String name)
    {
        super(name);
        this.bonus = 15;
    }

    //Methodes
    /**
     * Retourne la valeur de bonus associée à cet aliment.
     *
     * @return La valeur entière du bonus.
     */
    public int getBonus(){
        return bonus;
    }

    /**
     * Méthode de gestion du temps, implémentée par défaut.
     *
     * <p>Par défaut, la majorité des aliments ne réagissent pas à l'écoulement du temps,
     * et cette méthode est laissée vide. Les sous-classes périssables (comme {@code Fish})
     * doivent surcharger cette méthode pour implémenter une logique de péremption.
     */
    public void ticsPassed(){
        // Implémentation par défaut : aucun effet sur la majorité des aliments.
    }

}