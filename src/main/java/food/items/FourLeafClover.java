package food.items;

import clock.TemporalObject;
import food.Food;

/**
 * Représente un trèfle à quatre feuilles, un type d'aliment de base dans la simulation.
 *
 * <p>Le trèfle a un attribut de fraîcheur qui décroit au fil du temps.
 */
public class FourLeafClover extends Food implements TemporalObject {

    //Attributs
    private boolean peremption = true;
    private int isFreshFor = 48;

    //Constructeur
    /**
     * Construit un nouvel objet Trèfle à quatre feuilles.
     * <p>Le nom de l'aliment est initialisé à "Four Leaf Clover" et la fraîcheur à 48 tics.
     */
    public FourLeafClover() {
        super("Four Leaf Clover");
    }

    //Methodes
    /**
     * Gère l'écoulement du temps pour le trèfle.
     *
     * <p>Cette méthode, implémentée depuis {@link TemporalObject}, est appelée à chaque
     * tic de l'horloge. Elle décrémente le compteur de fraîcheur.
     * Si le compteur atteint une valeur négative, le trèfle est marqué comme périmé
     */
    public void ticsPassed() {
        this.isFreshFor--;

        if (this.isFreshFor < 0) {
            this.peremption = false;
        }
    }

    //Getters
    /**
     * Vérifie si le trèfle est périmé.
     *
     * @return true si le trèfle est frais (non périmé), false sinon.
     */
    public boolean getPeremption() {
        return peremption;
    }

    /**
     * Récupère le nombre de tics restants avant que le trèfle ne soit périmé.
     *
     * @return Le nombre de tics restants avant la péremption.
     */
    public int getIsFreshFor() {
        return isFreshFor;
    }
}
