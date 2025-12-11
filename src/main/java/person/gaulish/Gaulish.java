package person.gaulish;

import person.Person;

/**
 * Classe abstraite de base représentant un personnage gaulois.
 *
 * <p>Cette classe étend {@link person.Person} et sert de classe parent pour toutes
 * les implémentations spécifiques des rôles gaulois (Druide, Forgeron, etc.).
 * Elle fournit la structure de base pour les attributs et comportements communs
 * à tous les Gaulois.
 */
public abstract class Gaulish extends Person {

    /**
     * Construit un nouveau personnage Gaulois.
     *
     * @param name Le nom du Gaulois.
     * @param gender Le genre du Gaulois ('M', 'F' ou 'X').
     * @param height La taille du Gaulois.
     * @param age L'âge du Gaulois.
     * @param strength La force de base du gaulois.
     * @param endurance L'endurance de base du gaulois.
     */
    public Gaulish(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
    }
}
