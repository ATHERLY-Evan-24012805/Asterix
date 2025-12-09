package theatres;

import clock.Clock;
import person.Person;
import person.gaulish.charac.*;
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
    private List listOfChief;
    private static Clock clock = new Clock();

    //Constructeur
    public TheatreOfInvasion(String nameOfTheGame, ArrayList<Place> listOfPlaces /*, List listOfChief*/) {

        this.name = name;
        this.listOfPlaces = listOfPlaces;
        this.numberOfPlaces = listOfPlaces.size();
        //this.listOfChief = listOfChief;
    }


    public ArrayList<Place> showPlaces() {
        return listOfPlaces;
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
        return null;
    }

    public static Clock getClock() {
        return clock;
    }

    public static void createAndAddPerson(Place p, String roleName, Scanner sc) {
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

        // Age entre 15 et 80 (ou paramétrable si besoin)
        int age = random.nextInt((80 - 15) + 1) + 15;


        Person newPerson = null;
        // Gaulois
        if (roleName.equals("Blacksmith")) {
            // on fixe l'endurance et la force des blacksmith a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // Force 70
            // Endurance 30
            newPerson = new GaulishBlacksmith(name, gender, height, age, 70, 30 );
        }
        if (roleName.equals("GaulishInnKeeper")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 20
            // Endurance 80
            newPerson = new GaulishInnKeeper(name, gender, height, age, 20, 80);
        }
        if (roleName.equals("Druide")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 60
            // Endurance 20
            newPerson = new Druid(name, gender, height, age, 60, 20);
        }
        if (roleName.equals("ShopKeeper")) {
            // Pareil les aubergistes ont des valeurs par défaut moins de force plus d'endurance.
            // Force 30
            // Endurance 20
            newPerson = new GaulishShopKeeper(name, gender, height, age, 30, 20);
        }


        //Romain
        if (roleName.equals("RomanGeneral")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 50
            // endurance 50.
            newPerson = new RomanGeneral(name, gender, height, age, 50, 50);
            //ToString personnage
        }

        if (roleName.equals("RomanLegionary")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 90
            // endurance 40.
            newPerson = new RomanLegionary(name, gender, height, age, 90, 40);
            //ToString personnage
        }

        if (roleName.equals("RomanPrefect")) {
            // on fixe l'endurance et la force des généraux a une valeur fixe, car c'est spécifique à leurs role, ici forgeron
            // force 30
            // endurance 30.
            newPerson = new RomanPrefect(name, gender, height, age, 30, 30);
            //ToString personnage
        }

        p.addPerson(newPerson);
        clock.subscribe(newPerson);
        assert newPerson != null;
        System.out.println(newPerson.toString() + " créé avec succès !");
    }

    // a implementer pour le deroulement du jeu
    public void evolutionOfTheGame() {
        return;
    }
}
