package places;

import clanLeader.ClanLeader;
import Foods.Foods;
import persons.Person;

import java.util.ArrayList;
import java.util.List;

public abstract class Place {
    private String name;
    private int surface;
    private ClanLeader chief;
    private int census;
    protected List<Foods> food = new ArrayList<>();
    protected List<Person> people = new ArrayList<>();

    public abstract boolean canAddPerson(Person person);

    public void addPerson(Person person) {
        if (canAddPerson(person)) {
            people.add(person);
        } else {
            System.out.println("Impossible d'ajouter cette personne ici : " + person);
        }
    }

    public void removePerson(Person person) {
        people.remove(person);
    }
    public List<Person> getPeople() {
        return people;
    }

    public void healSomeone(Person person) {
        if (people.contains(person)) {
            person.heal();
        }
        else {
            System.out.println("La personne est dans un autre lieu");
        }
    }

    public void feedSomeone(Person person) {
        if (people.contains(person)) {
            person.eat();
        }
        else {
            System.out.println("La personne est dans un autre lieu");
        }
    }

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
