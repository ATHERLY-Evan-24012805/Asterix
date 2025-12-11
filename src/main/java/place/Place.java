package place;

import clanLeader.ClanLeader;
import food.Food;
import item.Item;
import person.Person;
import place.types.BattleField;

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
    private List<Food> food = new ArrayList<>();
    private List<Person> people = new ArrayList<>();
    private List<Item> inventory = new ArrayList<>();


    /**
     * Retourne la liste des personnes présentes dans le lieu.
     *
     * @return Liste des habitants
     */
    public List<Person> getPeople() {
        return people;
    }

    public String UIGetPeople() {
        String output = "";
        for (Person person : people) {
            output+=(person.getName()+" "+ person.getType()+", \n");
        }
        return output;
    }

    public String getNameAndWorkPeople() {
        String listOfName = " ";
        for (Person person : people) {
            listOfName += person.getName() + "("+ person.getType() +"), ";
        }
        return listOfName;
    }

    /**
     * Ajoute une instance de nourriture dans le lieu.
     *
     * @param f la nourriture à ajouter
     */
    public void addFood(Food f) {
        food.add(f);
    }

    /**
     * Retourne la liste des aliments présents dans le lieu.
     *
     * @return Liste des aliments
     */
    public List<Food> getFood() {
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
        if (person == null) {
            System.out.println("Person is null");
            return;
        }
        if (canAddPerson(person) && !people.contains(person)) {
            Place oldPlace = person.getPlace();
            if (oldPlace != null) {
                oldPlace.removePerson(person);
            }
            people.add(person);
            this.census++;
            person.setPlace(this);
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
        if (this instanceof BattleField) {
            ((BattleField) this).removeEngagedClanLeaders(person.getOwner());
        }
    }

    /**
     * Ajoute une instance de nourriture dans le lieu.
     *
     * @param f la nourriture à ajouter
     */
    public void addFood(Food f) {
        food.add(f);
    }

    /**
     * Soigne une personne présente dans le lieu.
     *
     * @param person La personne à soigner
     */
    public void healSomeone(Person person) {
        if (people.contains(person)) {
            person.heal();
            System.out.println(person.getName() + " a été soigné(e)");
        }
        else {
            System.out.println("La personne est dans un autre lieu");
        }
    }

    /**
     * Tente de consommer une unité de nourriture du lieu.
     *
     * @return true si de la nourriture a été consommée avec succès, false sinon (stock vide).
     */
    public boolean consumeFood() {
        if (!food.isEmpty()) {
            food.removeFirst(); // Retire la première unité de nourriture trouvée
            return true;
        }
        return false;
    }

    /**
     * Nourrit une personne spécifique en consommant de la nourriture du lieu.
     *
     * @param person La personne à nourrir.
     */
    public void feedSomeone(Person person) {
        if (getListOfPersons().contains(person)) {
            if (consumeFood()) {
                person.eat();
                System.out.println(person.getName() + " a été nourri(e) et se sent repu.");
            } else {
                System.out.println(person.getName() + " ne peut pas être nourri(e) : Le stock de nourriture du lieu est vide !");
            }
        } else {
            System.out.println("La personne n'est pas dans ce lieu.");
        }
    }

    /**
     * Retourne la liste des personnes présentes dans le lieu.
     *
     * @return Liste des personnes.
     */
    public List<Person> getListOfPersons(){
        return this.people;
    }

    /**
     * Retourne la liste des aliments présents dans le lieu.
     *
     * @return Liste des aliments
     */
    public List<Food> getFood() {
        return food;
    }

    /**
     * Retourne le nombre actuel de personnes dans le lieu (recensement).
     *
     * @return Le nombre d'habitants (int).
     */
    public int getCensus(){
        return this.census;
    }
    public List<Person> getListOfPersons(){
        return this.people;
    }
    public String getName(){
        return this.name;
    }
    public abstract String getType();


    // Méthodes inventaire
    /**
     * Retourne la liste des objets présents dans l'inventaire du lieu.
     *
     * @return Liste des objets ({@code List<Item>}).
     */
    public List<Item> getItems() {
        return inventory;
    }

    /**
     * Retourne l'objet présent à l'index spécifié dans l'inventaire.
     *
     * @param index L'index de l'objet dans la liste d'inventaire.
     * @return L'objet ({@link item.Item}) trouvé ou {@code null} si l'index est invalide.
     */
    public Item getItem(int index) {
        if (index < 0 || index >= inventory.size()) {
            return null;
        }
        return inventory.get(index);
    }

    /**
     * Ajoute un objet à l'inventaire du lieu.
     *
     * @param item L'objet ({@link item.Item}) à ajouter.
     */
    public void addItem(Item item){
        inventory.add(item);
    }

    /**
     * Retire un objet de l'inventaire du lieu.
     *
     * @param item L'objet ({@link item.Item}) à retirer.
     */
    public void removeItem(Item item){
        inventory.remove(item);
    }

    /**
     * Affiche le contenu complet de l'inventaire du lieu.
     */
    public void getInventory(){
        if (inventory.isEmpty()) {
            System.out.println(name + " n'a rien dans son inventaire.");
            return;
        }

        System.out.println("Inventaire de " + name + " :");
        for (Item item : inventory) {
            System.out.println("- " + item.getName());
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
