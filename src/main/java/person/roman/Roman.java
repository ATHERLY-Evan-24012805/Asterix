package person.roman;

import person.Person;

/**
 * Classe abstraite de base représentant un personnage Romain.
 *
 * <p>Cette classe étend {@link person.Person} et sert de classe parent pour toutes
 * les implémentations spécifiques des rôles romains (Légionnaire, Général, etc.).
 * Elle fournit la structure de base pour les attributs et comportements communs
 * à tous les Romains.
 */
public abstract class Roman extends Person {

    /**
     * Construit un nouveau personnage Romain.
     *
     * @param name Le nom du romain
     * @param gender Le genre du Romain ('M', 'F' ou 'X').
     * @param height La taille du romain
     * @param age L'âge du romain
     * @param strength La force du romain
     * @param endurance L'endurance du romain
     */
    public Roman(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }
}


