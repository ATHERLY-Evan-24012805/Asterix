import person.Person;
import person.gaulish.charac.*;
import person.roman.charac.*;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

import static theatres.TheatreOfInvasion.createAndAddPerson;

public class Main {
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
                System.out.println("Blacksmith(Forgeron)");
                createAndAddPerson(p,"Blacksmith",sc);

                // Aubergiste
                System.out.println("GaulishInnKeeper(Aubergiste)");
                createAndAddPerson(p,"GaulishInnKeeper",sc);

                // Druide
                createAndAddPerson(p,"Druide",sc);

            }

            // Camp fortifié romain
            // deux legionnaires
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
        theatre.getClock().start(5);

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
