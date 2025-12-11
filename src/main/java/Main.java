import clanLeader.ClanLeader;
import person.Person;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static theatres.TheatreOfInvasion.createAndAddPerson;

public class Main {

    public static ClanLeader findLeaderByName(List<ClanLeader> players, String name) {
        for (ClanLeader leader : players) {
            if (leader.getName().equalsIgnoreCase(name.trim())) {
                return leader;
            }
        }
        return null;
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
        TheirPlaces.add(new BattleField());
        ArrayList<ClanLeader> TheirNames = new ArrayList<>();
        for (int i = 0; i < input; i++) {
            // Partie à initialiser une fois la class ClanLeader créée
            System.out.println("Tout bon chef a un nom, quel est le vôtre ? ");
            String name = sc.nextLine();

            char gender = ' ';
            boolean saisieValide = false;
            while (!saisieValide) {
                System.out.print("Quel est votre genre M, F ou X ? ");
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
            char age = ' ';
            saisieValide = false;
            while (!saisieValide) {
                System.out.print("Quel est votre age ?");
                String ageInput = sc.nextLine(); // Toujours nextLine pour éviter les bugs

                if (!ageInput.isEmpty()) {
                    int ageInt = Integer.parseInt(ageInput);
                    if (ageInt > 0) {
                        saisieValide = true;
                    } else {
                        System.out.println("Veuillez saisir un age valable.");
                    }
                }
            }
            System.out.println("Quel est le type de votre premier lieu");
            System.out.println(" 1 : Village Gaullois \n 2 : camp retranché romain \n 3 : ville romaine \n 4 : bourgade gallo-romaine ");
            int numPlace = sc.nextInt();
            sc.nextLine();
            Place nouveauLieu = null;
            ClanLeader leader = null;
            switch (numPlace) {
                case 1:
                    nouveauLieu = new GallicVillage();
                    System.out.println("Village Gaullois créé");
                    leader = new ClanLeader(name,gender, age,nouveauLieu);
                    break;
                case 2:
                    nouveauLieu = new RomanFortifiedCamp();
                    System.out.println("Camp retranché romain créé");
                    leader = new ClanLeader(name,gender, age,nouveauLieu);
                    break;
                case 3:
                    nouveauLieu = new RomanCity();
                    System.out.println("Ville romaine créée");
                    leader = new ClanLeader(name,gender, age,nouveauLieu);
                    break;
                case 4:
                    nouveauLieu = new GalloRomanVillage();
                    System.out.println("Bourgade gallo-romaine Créée");
                    leader = new ClanLeader(name,gender, age,nouveauLieu);
                    break;
                default:
                    System.out.println("Veuillez choisir une option valide!");
                    i--;
                    break;
            }
            if (nouveauLieu != null) {
                TheirPlaces.add(nouveauLieu);
                TheirNames.add(leader);
            }
        }
        for (Place p : TheirPlaces) {

            // village gaulois
            // 1 Forgeron
            // 1 aubergiste
            // 1 Druide
            if (p instanceof GallicVillage) {
                System.out.println("Vous avez un lieu de Gaulois, vous disposez par défaut d'un druide, d'un forgeron, " +
                                    "d'un marchand et d'un aubergiste." +
                        "\nQuel nom voulez vous leurs données ?");

                //forgeron
                System.out.println("Blacksmith(Forgeron)");
                createAndAddPerson(p,"Blacksmith",sc);

                // Aubergiste
                System.out.println("GaulishInnKeeper(Aubergiste)");
                createAndAddPerson(p,"GaulishInnKeeper",sc);

                // Marchand
                System.out.println("ShopKeeper(Marchand)");
                createAndAddPerson(p, "ShopKeeper", sc);

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
                System.out.println("RomanLegionary(Legionnaire)");
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
            // Le thread de l'horloge continue de tourner derrière
            System.out.println("\n--- Quels sont vos ordres ! ---");
            String command = sc.nextLine();

            // sequence de jeu
            // A chaque tic, il faut que les joueurs aient leurs stats et leurs options de combat.
            // Il faut utiliser la methode dans theatreOfInvasion evolutionOfTheGame
            switch (command.toLowerCase()) {
                case "info":
                    theatre.evolutionOfTheGame();
                    break;

                case "attaque":
                    System.out.println("Déclarez la guerre (Ex: 'Evan attaque Romain') :");
                    String commandInput = sc.nextLine();

                    // 1. Analyse de la phrase
                    // On sépare sur le mot clé " attaque " (avec les espaces autour !)
                    String[] parts = commandInput.split(" attaque ");

                    if (parts.length < 2) {
                        System.out.println("Format incorrect. Utilisez : [NomJoueur] attaque [NomAdversaire]");
                        break;
                    }

                    String nomAttaquant = parts[0];
                    String nomDefenseur = parts[1];

                    // 2. Recherche des objets ClanLeader
                    // (Supposons que 'listOfChief' contient tous vos joueurs)
                    ClanLeader attaquant = findLeaderByName(TheirNames, nomAttaquant);
                    ClanLeader defenseur = findLeaderByName(TheirNames, nomDefenseur);

                    // 3. Vérification et Affichage des Troupes
                    if (attaquant != null && defenseur != null) {
                        System.out.println("\n GUERRE DÉCLARÉE : " + attaquant.getName() + " VS " + defenseur.getName());

                        // --- AFFICHER LES TROUPES DE L'ATTAQUANT ---
                        System.out.println("\n--- Troupes disponibles de " + attaquant.getName() + " ---");
                        // On suppose que le ClanLeader possède une liste de ses lieux (getPlaces())
                        boolean aDesTroupes = false;

                        for (Person person : attaquant.getPlace().getListOfPersons()) {
                            // On affiche index ou nom pour faciliter la sélection
                            System.out.println("-> " + person.getName() + " (" + person.getClass().getSimpleName() + ") situé à " + attaquant.getName());
                            aDesTroupes = true;
                        }

                        if (!aDesTroupes) {
                            System.out.println(attaquant.getName()+" n'a plus de soldats !");
                            break;
                        }

                        // --- SUITE LOGIQUE : CHOIX DU SOLDAT ---
                        System.out.println("\nQuel soldat de " + attaquant.getName() + " envoyez-vous ? (Entrez son nom)");
                        String soldatName = sc.nextLine();


                    } else {
                        System.out.println("Erreur : Impossible de trouver l'un des deux chefs de clan.");
                    }
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
