package theatres;

import clock.Clock;
import person.Person;
import person.types.Gaulish.charac.*;
import person.types.Roman.charac.*;
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

    private static void createAndAddPerson(Place p, String roleName, Scanner sc) {
        // 1. Demande du Nom
        System.out.print(roleName + " : ");
        String name = sc.nextLine();

        // 2. Validation du Genre (Factorisée ici)
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
                    System.out.println("Erreur : Veuillez entrer uniquement M, F ou X.");
                }
            }
        }

        // 3. Génération Aléatoire (Factorisée ici)
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
            newPerson = new Druide(name, gender, height, age, 60, 20);
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
        System.out.println(roleName + " créé avec succès !"); // toString et getname pour avoir le nom du perso qui s'affiche
    }




    public static void main (String[] args) {

        // Initialisation du jeu
        Scanner sc = new Scanner(System.in);
        int input = 0;
        System.out.println("Bienvenue dans le théatre d'envahisement !");
        System.out.println("Quel est le nom de votre partie ?");
        String GameName = sc.nextLine();
        while (input <= 0) {
            System.out.println("Combien de joueur(s) etes vous ?");
            String nbOfPlaces = sc.nextLine();
            try {
                input = Integer.parseInt(nbOfPlaces);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez renter un alpha numerique");
            }
        }
        // Parametre du/des joueur(s)
        ArrayList<Place> TheirPlaces = new ArrayList<>();
        ArrayList<Place> TheirNames = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            /* Partie à initialiser une fois la class ClanLeader créée
            System.out.println("Tout bon chef a un nom, quel est le vôtre ? ");
            String name = sc.nextLine();
            ClanLeader leader = new ClanLeader(name)
            TheirName = leader;
             */

            System.out.println("Quel est le type de votre premier lieu");
            System.out.println(" 1 : Village Gaullois \n 2 : camp retranché romain \n 3 : ville romaine \n 4 : bourgade gallo-romaine ");
            int numPlace = sc.nextInt();
            sc.nextLine();
            Place nouveauLieu = null;
            switch (numPlace) {
                case 1:
                    nouveauLieu = new GallicVillage();
                    System.out.println("Village Gaullois créé");
                    break;
                case 2:
                    nouveauLieu = new RomanFortifiedCamp();
                    System.out.println("Camp retranché romain créé");
                    break;
                case 3:
                    nouveauLieu = new RomanCity();
                    System.out.println("Ville romaine créée");
                    break;
                case 4:
                    nouveauLieu = new GalloRomanVillage();
                    System.out.println("Bourgade gallo-romaine Créée");
                    break;
                default:
                    System.out.println("Veuillez choisir une option valide!");
                    i--;
                    break;
            }
            if (nouveauLieu != null) {
                TheirPlaces.add(nouveauLieu);
            }
        }
        for (Place p : TheirPlaces) {

            // village gaulois
            // 1 Forgeron
            // 1 aubergiste
            // 1 Druide
            if (p instanceof GallicVillage) {
                System.out.println("Vous avez un lieu de Gaulois, vous disposez par défaut d'un druide, d'un forgerons et d'un aubergiste." +
                        "\nQuel nom voulez vous leurs données ?");

                //forgeron
                System.out.println("Blacksmith(Aubergiste)");
                createAndAddPerson(p,"Blacksmith",sc);

                // Aubergiste
                System.out.println("GaulishInnKeeper(Aubergiste)");
                createAndAddPerson(p,"GaulishInnKeeper",sc);

                // Druide
                createAndAddPerson(p,"Druide",sc);

            }

            // Camp fortifié romain
            // deux legionnaire
            // un general
            if (p instanceof RomanFortifiedCamp) {
                System.out.println("Vous avez un lieu de Romain, vous disposez par défaut d'un Romain général et de deux Romains legionnaires." +
                        "\nQuel nom voulez vous leurs données ?");

                // Général
                System.out.print("RomanGeneral(Général)");
                createAndAddPerson(p,"RomanGeneral",sc);

                // Legionnaire
                System.out.print("RomanLegionary(Legionnaire)");
                for (int i = 0; i < 2; i++) {
                    createAndAddPerson(p,"RomanLegionary",sc);
                }
            }

            //Ville romaine
            // 1 legionnaire
            // 1 Prefet
            // 1 general
            if (p instanceof RomanCity) {
                System.out.println("Vous avez un lieu de Romain, vous disposez par défaut d'un Romain général, d'un Romain préfet et d'un Romain legionnaire."
                + "\n Quel noms voulez vous leurs donner ?");

                //General
                System.out.print("RomanGeneral(Général)");
                createAndAddPerson(p,"RomanGeneral",sc);

                //Préfet
                System.out.print("RomanPrefect(Préfet)");
                createAndAddPerson(p,"RomanPrefect",sc);

                //Legionnaire
                System.out.print("RomanLegionary(Legionnaire)");
                createAndAddPerson(p,"RomanLegionary",sc);

            }


            // Bourgade gallo-romaine
            // 1 Commerçant
            // 1 forgeron
            // 1 Legionnaire
            if (p instanceof GalloRomanVillage) {
                System.out.println("Vous avez un lieu de Gallo-Romain, vous disposez par défaut d'un Romain Legionnaire, d'un Commerçant et d'un Romain forgeron."
                        + "\n Quel noms voulez vous leurs donner ?");

                //Legionnaire
                System.out.print("RomanLegionary(Legionnaire)");
                createAndAddPerson(p,"RomanLegionary",sc);

                //forgeron
                System.out.println("Blacksmith(Aubergiste)");
                createAndAddPerson(p,"Blacksmith",sc);

                //Commerçant
                System.out.println("ShopKeeper(Commerçant)");
                createAndAddPerson(p,"ShopKeeper",sc);
            }

        }




        TheatreOfInvasion theatre = new TheatreOfInvasion(GameName, TheirPlaces);
        System.out.println("Lancement de la partie. A partir de maintenant le temps defile.");
        clock.start(5);

        boolean gameIsRunning = true;

        while (gameIsRunning) {
            // Le thread principal est bloqué ici tant que l'utilisateur n'écrit pas.
            // Mais le thread de l'horloge continue de tourner derrière !
            System.out.println("\n--- À vous chef ! (Tapez 'info', 'attaque', ou 'quitter') ---");
            String command = sc.nextLine();

            switch (command.toLowerCase()) {
                case "info":
                    System.out.println("Il y a " + theatre.howManyPeople() + " personnes en vie.");
                    break;

                case "attaque":
                    System.out.println("Bataille lancée ! (Logique à implémenter)");
                    break;

                case "quitter":
                    System.out.println("Fermeture du jeu...");
                    gameIsRunning = false;
                    System.exit(0); // Tue aussi le thread de l'horloge
                    break;

                default:
                    System.out.println("Ordre inconnu.");
            }
        }

    }

}
