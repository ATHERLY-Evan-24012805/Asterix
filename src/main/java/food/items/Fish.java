package food.items;

import clock.TemporalObject;
import food.Food;

/**
 * Représente un poisson, un type d'aliment de base dans la simulation.
 *
 * <p>Le poisson a un attribut de fraîcheur qui décroit au fil du temps.
 */
public class Fish extends Food implements TemporalObject {

    //Attributs
    private boolean peremption = true;
    private int isFreshFor = 24;

    //Constructeur
    /**
     * Construit un nouvel objet Fish.
     * <p>Le nom de l'aliment est initialisé à "Fish" et la fraîcheur à 24 tics.
     */
    public Fish(){
        super("Fish");
    }

    //Methodes

    /**
     * Gère l'écoulement du temps pour le poisson
     *
     * <p>Cette méthode, implémentée depuis {@link TemporalObject}, est appelée à chaque
     * tic de l'horloge. Elle décrémente le compteur de fraîcheur.
     * Si le compteur atteint une valeur négative, le poisson est marqué comme périmé
     */
    public void ticsPassed() {
        this.isFreshFor--;

        if (this.isFreshFor < 0) {
            this.peremption = false;
        }
    }

    //Getters
    /**
     * Vérifie si le poisson est périmé.
     *
     * @return true si le poisson est frais (non périmé), false sinon.
     */
    public boolean getPeremption() {
        return peremption;
    }

    /**
     * Récupère le nombre de tics restants avant que le poisson ne soit périmé.
     *
     * @return Le nombre de tics restants avant la péremption.
     */
    public int getIsFreshFor() {
        return isFreshFor;
    }
}
