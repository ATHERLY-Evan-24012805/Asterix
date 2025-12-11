package theatres;

import clanLeader.ClanLeader;
import clock.Clock;
import person.Person;
import person.gaulish.charac.*;
import person.lycanthrope.Lycanthrope;
import person.roman.charac.*;
import place.types.*;
import place.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TheatreOfInvasion {
    private String name;
    private int numberOfPlaces;
    private ArrayList<Place> listOfPlaces;
    private List<ClanLeader> listOfChief;
    private static Clock clock = new Clock();

    //Constructeur
    public TheatreOfInvasion(String nameOfTheGame, ArrayList<Place> listOfPlaces , List listOfChief) {

        this.name = name;
        this.listOfPlaces = listOfPlaces;
        this.numberOfPlaces = listOfPlaces.size();
        this.listOfChief = listOfChief;
    }


    public ArrayList<Place> showPlaces() {
        return listOfPlaces;
    }

    public List<ClanLeader> getListOfChief() {
        return listOfChief;
    }

    public int howManyPeople() {
        int compt = 0;
        for (Place p : listOfPlaces) {
            compt+=p.getCensus();
        }
        return compt;
    }

    public List PeopleByPlace(Place place){
        List people = new ArrayList();
        people = place.getListOfPersons();
        return people;
    }

    public static Clock getClock() {
        return clock;
    }

    public static void createAndAddPerson(Place p, String roleName, Scanner sc, ClanLeader leader) {
        // 1. Demande du Nom
        System.out.print(roleName + " : ");
        String name = sc.nextLine();

        // 2. Validation du Genre
        char gender = ' ';
        boolean saisieValide = false;
        while (!saisieValide) {
            System.out.print("Quel sera son genre M, F ou X ? ");
            String inputGender = sc.nextLine(); // Toujours nextLine pour éviter les bugs

            if (!inputGender.isEmpty()) {
                gender = Character.toUpperCase(inputGender.charAt(0));
                if (gender == 'M' || gender == 'F' || gender == 'X') {
                    saisieValide = true;
                } else {
                    System.out.println("Veuillez entrer uniquement M, F ou X.");
                }
            }
        }

        // 3. Génération Aléatoire
        Random random = new Random();

        // Taille entre 80cm et 250cm
        double height = (random.nextInt((250 - 80) + 1) + 80) / 100.0; // Division par 100.0 pour avoir un double (ex: 1.85)

        // Age entre 15 et 80
        int age = random.nextInt((80 - 15) + 1) + 15;


        Person newPerson = null;
        // Gaulois
        if (roleName.equals("Blacksmith")) {
            // on fixe l'endurance et la force des blacksmith a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // Force 70
            // Endurance 30
            newPerson = new GaulishBlacksmith(name.trim(), gender, height, age, 70, 30 );
        }
        if (roleName.equals("GaulishInnKeeper")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 20
            // Endurance 80
            newPerson = new GaulishInnKeeper(name.trim(), gender, height, age, 20, 80);
        }
        if (roleName.equals("Druide")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 60
            // Endurance 20
            newPerson = new Druid(name.trim(), gender, height, age, 60, 20);
        }
        if (roleName.equals("ShopKeeper")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 30
            // Endurance 20
            newPerson = new GaulishShopKeeper(name.trim(), gender, height, age, 30, 20);
        }


        //Romain
        if (roleName.equals("RomanGeneral")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 50
            // endurance 50.
            newPerson = new RomanGeneral(name.trim(), gender, height, age, 50, 50);
            //ToString personnage
        }

        if (roleName.equals("RomanLegionary")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 90
            // endurance 40.
            newPerson = new RomanLegionary(name.trim(), gender, height, age, 90, 40);
            //ToString personnage
        }

        if (roleName.equals("RomanPrefect")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 30
            // endurance 30.
            newPerson = new RomanPrefect(name.trim(), gender, height, age, 30, 30);
            //ToString personnage
        }

        assert newPerson != null;
        newPerson.setOwner(leader);
        p.addPerson(newPerson);
        clock.subscribe(newPerson);
        System.out.println(newPerson.getName().toUpperCase()+" taille :"+newPerson.getHeight()+", age :"+newPerson.getAge()+", Force :"+ newPerson.getStrength()+", Endurance : "+newPerson.getEndurance() + " créé avec succès !" +
                "\n---------------------------------");
    }

    public void evolutionOfTheGame() {
        for (Place p : listOfPlaces) {
            String people = p.getNameAndWorkPeople();
            System.out.println("Lieux : " + p.getName() +", " +p.getType());
            System.out.println("Population : "+people +
                    "\n Inventaire : " +p.getItems()
            );
        }
    }

    // Méthode pour transformation en Lycanthrope
    public static void createWerewolfFrom(Person former, Place place) {

        // Création du loup-garou
        Person werewolf = new Lycanthrope(
                former.getName() + " le Loup-Garou",
                former.getGender(),
                former.getHeight(),
                former.getAge(),
                former.getStrength(),
                former.getEndurance()
        );

        // Tuer le personnage se transformant
        former.die();

        // Ajout du nouveau
        place.addPerson(werewolf);
        clock.subscribe(werewolf);

        System.out.println("Vous vous êtes transformé en Lycanthrope !");
    }
}
