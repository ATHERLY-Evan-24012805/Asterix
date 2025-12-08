package place;

import clanLeader.ClanLeader;
import Foods.Foods;
import persons.Person;

import java.util.ArrayList;
import java.util.List;

/**
 * Classe abstraite représentant un lieu dans le jeu.
 *
 * <p>Chaque lieu possède un nom, une superficie, un chef de clan,
 * un recensement de ses habitants, ainsi qu'une liste de personnes
 * et d'aliments présents.</p>
 *
 * <p>Cette classe fournit des méthodes pour ajouter et retirer
 * des personnes, nourrir et soigner quelqu'un, et obtenir la liste
 * des habitants.</p>
 */
public abstract class Place {
    private String name;
    private int surface;
    private ClanLeader chief;
    private int census;

    protected List<Foods> food = new ArrayList<>();
    protected List<Person> people = new ArrayList<>();


    /**
     * Retourne la liste des personnes présentes dans le lieu.
     *
     * @return Liste des habitants
     */
    public List<Person> getPeople() {
        return people;
    }

    /**
     * Retourne la liste des aliments présents dans le lieu.
     *
     * @return Liste des aliments
     */
    public List<Foods> getFood() {
        return food;
    }

    /**
     * Vérifie si une personne peut être ajoutée dans ce lieu.
     *
     * @param person La personne à tester
     * @return true si la personne peut être ajoutée, false sinon
     */
    public abstract boolean canAddPerson(Person person);

    /**
     * Ajoute une personne dans le lieu si elle y est autorisée et si elle n'y est pas déjà.
     *
     * @param person La personne à ajouter
     */
    public void addPerson(Person person) {
        if (canAddPerson(person) && !people.contains(person)) {
            people.add(person);
        } else {
            System.out.println("Impossible d'ajouter cette personne ici : " + person);
        }
    }

    /**
     * Retire une personne du lieu.
     *
     * @param person La personne à retirer
     */
    public void removePerson(Person person) {
        people.remove(person);
    }

    /**
     * Soigne une personne présente dans le lieu.
     *
     * @param person La personne à soigner
     */
    public void healSomeone(Person person) {
        if (people.contains(person)) {
            person.heal();
        }
        else {
            System.out.println("La personne est dans un autre lieu");
        }
    }

    /**
     * Nourrit une personne présente dans le lieu.
     *
     * @param person La personne à nourrir
     */
    public void feedSomeone(Person person) {
        if (people.contains(person)) {
            person.eat();
        }
        else {
            System.out.println("La personne est dans un autre lieu");
        }
    }

    /**
     * Retourne une représentation textuelle du lieu, incluant
     * son nom, sa superficie, son chef, son recensement, ainsi
     * que les listes de personnes et d'aliments présents.
     *
     * @return Description complète du lieu
     */
    @Override
    public String toString() {
        return "Nom : " + name + "\n" +
                "Superficie : " + surface + "\n" +
                "Chef de clan : " + chief + "\n" +
                "Recensement : " + census + "\n" +
                "Liste de personnes : " + people + "\n" +
                "Liste d'aliments : " + food;
    }
}
