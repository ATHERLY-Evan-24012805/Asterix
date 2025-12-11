import clanLeader.ClanLeader;
import person.Fighter;
import person.Leader;
import person.Person;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static theatres.TheatreOfInvasion.createAndAddPerson;

public class Main {

    private static BattleField battleField = new BattleField();

    public static ClanLeader findLeaderByName(List<ClanLeader> players, String name) throws Exception {
        for (ClanLeader leader : players) {
            if (leader.getName().equalsIgnoreCase(name.trim())) {
                return leader;
            }
        }
        throw new Exception("Chef introuvable : " + name);
    }

    public static Person findPnjByName(List<Person> pnj, String name) throws Exception {
        for (Person PNJ : pnj) {
            if (PNJ.getName().equalsIgnoreCase(name.trim())) {
                return PNJ;
            }
        }
        throw new Exception("Personnage introuvable : " + name);
    }


    public static void main (String[] args) {

        // Initialisation du jeu
        Scanner sc = new Scanner(System.in);
        int NbOfPlayers = 0;
        System.out.println("Bienvenue dans le théatre d'envahisement !");
        System.out.println("Quel est le nom de votre partie ?");
        String GameName = sc.nextLine();
        while (NbOfPlayers <= 0) {
            System.out.println("Combien de joueur(s) etes vous ?");
            String nbOfPlaces = sc.nextLine();
            try {
                NbOfPlayers = Integer.parseInt(nbOfPlaces);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez renter un alpha numerique");
            }
            if (NbOfPlayers > 4) {
                NbOfPlayers = 0;
                System.out.println("vous ne pouvez être que 4 joueurs");
            }
        }

        // Parametre du/des joueur(s)
        ArrayList<Place> TheirPlaces = new ArrayList<>();
        TheirPlaces.add(battleField);
        ArrayList<ClanLeader> TheirNames = new ArrayList<>();


        //Boucle de creation des joueurs
        System.out.println("---------------");
        for (int i = 0; i < NbOfPlayers; i++) {
            // Partie à initialiser une fois la class ClanLeader créée
            System.out.println("Joueur " + (i+1));
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
            //selection de l'age du joueur
            int age = 0;
            while (age <= 0) {
                System.out.print("Quel est votre age ?");
                String ageInput = sc.nextLine(); // Toujours nextLine pour éviter les bugs
                try {
                    age = Integer.parseInt(ageInput);
                } catch (NumberFormatException e) {
                    System.out.println("Votre age doit être un alpha numérique.");
                }
                if (age < 0) {
                    System.out.println("Votre age doit être positif.");
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
                leader = new ClanLeader(name,gender, age,nouveauLieu);
                TheirPlaces.add(nouveauLieu);
                TheirNames.add(leader);
            }

            // village gaulois
            // 1 Forgeron
            // 1 aubergiste
            // 1 Druide
            Person newPerson = null;
            if (nouveauLieu instanceof GallicVillage) {
                System.out.println("Vous avez un lieu de Gaulois, vous disposez par défaut d'un druide, d'un forgeron et d'un aubergiste." +
                        "\nQuel nom voulez vous leurs données ?");

                //forgeron
                System.out.println("Blacksmith(Forgeron)");
                createAndAddPerson(nouveauLieu,"Blacksmith",sc,leader);

                // Aubergiste
                System.out.println("GaulishInnKeeper(Aubergiste)");
                createAndAddPerson(nouveauLieu,"GaulishInnKeeper",sc,leader);

                /* Marchand
                System.out.println("ShopKeeper(Marchand)");
                createAndAddPerson(nouveauLieu, "ShopKeeper", sc,leader);
                */

                // Druide
                createAndAddPerson(nouveauLieu,"Druide",sc,leader);

            }

            // Camp fortifié romain
            // deux legionnaires
            // un general
            if (nouveauLieu instanceof RomanFortifiedCamp) {
                System.out.println("Vous avez un lieu de Romain, vous disposez par défaut d'un Romain général et de deux Romains legionnaires." +
                        "\nQuel nom voulez vous leurs données ?");

                // Général
                System.out.println("RomanGeneral(Général)");
                createAndAddPerson(nouveauLieu,"RomanGeneral",sc,leader);

                // Legionnaire
                System.out.println("RomanLegionary(Legionnaire)");
                for (int j = 0; j < 2; j++) {
                    System.out.println("RomanLegionary"+(i+1)+" (Legionnaire)");
                    createAndAddPerson(nouveauLieu,"RomanLegionary",sc,leader);
                }
            }

            //Ville romaine
            // 1 legionnaire
            // 1 Prefet
            // 1 general
            if (nouveauLieu instanceof RomanCity) {
                System.out.println("Vous avez un lieu de Romain, vous disposez par défaut d'un Romain général, d'un Romain préfet et d'un Romain legionnaire."
                        + "\nQuel noms voulez vous leurs donner ?");

                //General
                System.out.println("RomanGeneral(Général)");
                createAndAddPerson(nouveauLieu,"RomanGeneral",sc,leader);

                //Préfet
                System.out.println("RomanPrefect(Préfet)");
                createAndAddPerson(nouveauLieu,"RomanPrefect",sc,leader);

                //Legionnaire
                System.out.println("RomanLegionary(Legionnaire)");
                createAndAddPerson(nouveauLieu,"RomanLegionary",sc,leader);

            }


            // Bourgade gallo-romaine
            // 1 Commerçant
            // 1 forgeron
            // 1 Legionnaire
            if (nouveauLieu instanceof GalloRomanVillage) {
                System.out.println("Vous avez un lieu de Gallo-Romain, vous disposez par défaut d'un Romain Legionnaire, d'un Commerçant et d'un Romain forgeron."
                        + "\n Quel noms voulez vous leurs donner ?");

                //Legionnaire
                System.out.println("RomanLegionary(Legionnaire)");
                createAndAddPerson(nouveauLieu,"RomanLegionary",sc,leader);

                //forgeron
                System.out.println("Blacksmith(Aubergiste)");
                createAndAddPerson(nouveauLieu,"Blacksmith",sc,leader);

                //Commerçant
                System.out.println("ShopKeeper(Commerçant)");
                createAndAddPerson(nouveauLieu,"ShopKeeper",sc,leader);
            }

        }




        TheatreOfInvasion theatre = new TheatreOfInvasion(GameName, TheirPlaces,TheirNames);
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
                // montrer les commandes valables
                case "info":
                    theatre.evolutionOfTheGame();
                    break;

                case "attaque":
                    //voir le champ de bataille
                    System.out.println("Qui etes vous ? ");
                    String LeaderInput = sc.nextLine();
                    ClanLeader leader = null;
                    try {
                        leader = findLeaderByName(theatre.getListOfChief(), LeaderInput);
                    } catch (Exception e) {
                        System.out.println("Le nom de chef n'est pas attribué");
                        break;
                    }
                    System.out.println("Champ de bataille : ");
                    battleField.viewBattleField();
                    Person attacker = null;
                    if (battleField.getListOfPersons().isEmpty()) {
                        System.out.println("Qui voulez envoyer au combat ?. q pour n'envoyer personne.");
                        String output = leader.getPlace().UIGetPeople();
                        System.out.print(output);
                        String option = sc.nextLine();
                        if (option.equals("q")) {
                            break;
                        }else{
                            try {
                                attacker = findPnjByName(leader.getPlace().getPeople(), option);
                            } catch (Exception e) {
                                System.out.println("Le personnage selectionné ne semble pas être présent dans votre lieu.");
                                break;
                            }
                            battleField.addPerson(attacker);
                            System.out.println(attacker.getName()+ " est partie au combat ! Souhaitez lui bonne chance.");
                        }
                    }else{
                        System.out.println("-------------\nSouhaitez-vous attaquer ou envoyer une personne au combat ? \na : attaque \ne : envoyer \nq : quitter");
                        switch (sc.nextLine()) {
                            case "q":
                                break;
                            default:
                            case "a":
                                System.out.println("Qui attaque ?(nom)");
                                boolean attackerIsGood = false;
                                while (!attackerIsGood) {
                                    String fighterInput = sc.nextLine();

                                    try {
                                        attacker = findPnjByName(battleField.getPeople(), fighterInput);
                                    } catch (Exception e) {
                                        System.out.println("L'attaquant n'est pas sur le champ de bataille.");
                                    }attackerIsGood = true;
                                    if (attacker.getOwner() == leader){
                                        attackerIsGood = false;
                                    }

                                }
                                boolean targetIsGood = false;
                                Person target = null;
                                while (!targetIsGood || attacker != null) {
                                    System.out.println("Qui est la cible ?(nom)");
                                    String targetInput = sc.nextLine();
                                    try {
                                        target = findPnjByName(battleField.getPeople(), targetInput);
                                    } catch (Exception e) {
                                        System.out.println("La cible n'est pas sur le champ de bataille.");
                                    }
                                    attacker.setTarget(target);
                                    if (attacker instanceof Fighter) {
                                        Fighter f = (Fighter) attacker;
                                        int damage = f.fight();
                                        System.out.println(attacker.getName() + " a fait " + damage + " de dommage a " + attacker.getTarget().getName() +
                                                "\n" + target.getName() + " a " + target.getHealth() + " de PV.");
                                        break;
                                    }
                                }
                                break;
                            case "e":
                                System.out.println("Qui voulez envoyer au combat ?. q pour n'envoyer personne.");
                                String output = leader.getPlace().UIGetPeople();
                                System.out.print(output);
                                String option = sc.nextLine();
                                if (option.equals("q")) {
                                    break;
                                } else {
                                    try {
                                        attacker = findPnjByName(leader.getPlace().getPeople(), option);
                                    } catch (Exception e) {
                                        System.out.println("Le personnage selectionné ne semble pas être présent dans votre lieu.");
                                        break;
                                    }
                                    battleField.addPerson(attacker);
                                    System.out.println(attacker.getName() + " est partie au combat ! Souhaitez lui bonne chance.");
                                }
                                break;
                        }
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
