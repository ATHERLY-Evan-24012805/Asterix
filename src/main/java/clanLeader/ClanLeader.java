package clanLeader;

import MagicPotion.MagicPotion;
import MagicPotion.MagicEffect;
import person.gaulish.charac.Druid;
import person.Person;
import place.Place;


/**
 * Représente le chef d'un clan, responsable de la gestion d'un lieu et des personnes qui s'y trouvent.
 * Le ClanLeader peut examiner un lieu, créer ou transférer des personnages, ainsi que
 * nourrir ou soigner des individus.
 */
public class ClanLeader {

    private String name;
    private char gender;
    private int age;
    private Place place;

    /**
     * Constructeur permettant d'initialiser un chef de clan.
     *
     * @param name   nom du chef
     * @param gender genre du chef
     * @param age    age du chef
     * @param place  lieu que le chef dirige
     */
    public ClanLeader(String name, char gender, int age, Place place) {
        this.name = name;
        this.gender = gender;
        this.age = age;
        this.place = place;
    }

    /**
     * Affiche les informations du lieu dirigé par le chef.
     */
    public void examinePlace() {
        System.out.println(place.toString());
    }

    /**
     * Ajoute une personne au lieu dirigé.
     *
     * @param person la personne à ajouter
     */
    public void createPerson(Person person) {
        place.addPerson(person);
    }


    /**
     * Soigne une personne spécifique dans le lieu.
     *
     * @param person la personne à soigner
     */
    public void healSomeone(Person person) {
        place.healSomeone(person);
    }

    /**
     * Soigne toutes les personnes présentes dans le lieu.
     */
    public void healAll() {
        for (Person p : place.getListOfPersons()) {
            p.heal();
        }
    }

    /**
     * Nourrit une personne spécifique dans le lieu.
     *
     * @param person la personne à nourrir
     */
    public void feedSomeone(Person person) {
        place.feedSomeone(person);
    }

    /**
     * Nourrit toutes les personnes présentes dans le lieu.
     */
    public void feedAll() {
        for (Person p : place.getListOfPersons()) {
            p.eat();
        }
    }

    /**
     * Demande à un Druide présent dans le lieu actuel de fabriquer une potion magique.
     *
     * <p>Le chef de clan délègue la tâche de création de la potion au Druide spécifié,
     * en lui indiquant l'effet désiré et la quantité de recettes à préparer.
     *
     * @param druid Le {@link Druid} présent dans le lieu qui fabriquera la potion.
     * @param desiredEffect L'effet magique ({@link MagicEffect}) désiré pour la potion.
     * @param quantity La quantité de recettes à préparer (chaque unité donne quatre doses).
     * @return La {@link MagicPotion} créée, ou null si la fabrication échoue.
     */
    public MagicPotion askDruidForPotion(Druid druid, MagicEffect desiredEffect, int quantity) {
        // Vérifie si le Druide est bien présent dans le lieu
        if (place.getListOfPersons().contains(druid)) {
            System.out.println(name + " demande à " + druid.getName() + " de préparer une potion avec l'effet " + desiredEffect.name() + ".");
            MagicPotion newPotion = druid.createMagicPotion(desiredEffect, quantity, place);

            if (newPotion != null) {
                System.out.println(druid.getName() + " a réussi à créer une " + newPotion.getName() + " !");
            } else {
                System.out.println(druid.getName() + " a échoué ! La potion a explosé.");
            }
            return newPotion;
        } else {
            System.out.println(name + " ne peut pas demander la potion : " + druid.getName() + " n'est pas ici.");
            return null;
        }
    }

    /**
     * Donne une potion spécifique à une personne présente dans le lieu.
     *
     * <p>Cette méthode transfère la potion depuis l'inventaire du {@link Place} vers l'individu
     * (simulé par l'application des effets, car la personne boit la potion).
     *
     * @param person La personne qui va boire la potion (doit être dans le lieu du chef).
     * @param potion La {@link MagicPotion} à administrer.
     * @param doses La quantité de doses à consommer.
     */
    public void givePotion(Person person, MagicPotion potion, int doses) {
        // Vérifie si la personne est présente
        if (!place.getListOfPersons().contains(person)) {
            System.out.println(name + " ne peut pas donner de potion à " + person.getName() + " car il/elle n'est pas ici.");
            return;
        }

        // Vérifie si la potion est dans l'inventaire du lieu
        if (!place.getItems().contains(potion)) {
            System.out.println(name + " ne possède pas cette potion dans l'inventaire du lieu.");
            return;
        }

        System.out.println(name + " donne " + doses + " doses de " + potion.getName() + " à " + person.getName() + ".");

        // Simule la consommation de la potion par la personne.
        // On doit trouver l'index de la potion dans l'inventaire du lieu pour appeler drinkPotion(index, doses)
        int potionIndex = place.getItems().indexOf(potion);

        if (potionIndex != -1) {
            // Utiliser la surcharge drinkPotion(index, doses) de Person
            person.drinkPotion(potionIndex, doses);
        } else {
            System.err.println("Potion non trouvée dans l'inventaire");
        }
    }

    /**
     * Transfère une personne du lieu actuel vers une destination.
     *
     * <p>La méthode vérifie d'abord que la personne est bien présente dans le lieu actuel, puis
     * s'assure que la destination autorise cette personne. Si c'est le cas, elle est retirée
     * du lieu actuel puis ajoutée à la destination.
     *
     * @param person      la personne à transférer
     * @param destination le lieu de destination
     */
    public void transferPerson(Person person, Place destination) {

        // Vérifie si la personne à déplacer est bien présente dans ce lieu.
        if (!place.getListOfPersons().contains(person)) {
            System.out.println("La personne n'est pas dans ce lieu.");
            return;
        }

        // Vérifie que la personne puisse bien être ajoutée vers la destination.
        if (destination.canAddPerson(person)) {
            place.removePerson(person);
            person.setPlace(destination);
            destination.addPerson(person);
        }
        else {
            System.out.println("Cette personne n'est pas autorisée vers cette destination.");
        }
    }
}
