import clanLeader.ClanLeader;
import person.Person;
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

/**
 * Classe principale d'exécution de la simulation.
 *
 * <p>Elle gère l'initialisation du jeu (nom de la partie, nombre de joueurs/lieux,
 * choix des lieux, création des chefs de clan) et lance la boucle de jeu principale
 * avec le gestionnaire de temps ({@link clock.Clock}).
 */
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



    /**
     * Méthode principale du programme, initialisant la simulation.
     *
     * <p>Elle procède par étapes :</p>
     * <ol>
     * <li>**Initialisation :** Demande le nom de la partie et le nombre de joueurs/lieux.</li>
     * <li>**Configuration des Joueurs/Lieux :** Pour chaque joueur, demande le nom du chef, le genre, l'âge,
     * et le type de lieu initial (Village Gaulois, Camp Romain, Ville Romaine, Bourgade).</li>
     * <li>**Remplissage des Lieux :** Ajoute des personnages par défaut dans chaque lieu,
     * en fonction du type de lieu (Forgerons, Légionnaires, Druides, etc.), demandant leur nom à l'utilisateur.</li>
     * <li>**Démarrage :** Lance le {@link clock.Clock} et la boucle de jeu interactive.</li>
     * </ol>
     *
     * @param args Arguments de la ligne de commande (non utilisés).
     */
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

                case "nourrir":
                    if (TheirNames.isEmpty()) {
                        System.out.println("Aucun chef de clan n'a été créé.");
                        break;
                    }

                    // 1/ Choisir un chef/lieu
                    System.out.println("\n--- NOURRIR DES HABITANTS ---");
                    System.out.println("Quel chef de clan voulez-vous utiliser pour donner de la nourriture ?");

                    for (int i = 0; i < TheirNames.size(); i++) {
                        ClanLeader leader = TheirNames.get(i);
                        System.out.println((i + 1) + " : " + leader.getName() + " dirige " + leader.getPlace().getClass().getSimpleName());
                    }

                    System.out.print("Entrez le numéro du chef/lieu : ");
                    String leaderChoice = sc.nextLine();

                    int leaderIndex = -1;
                    try {
                        leaderIndex = Integer.parseInt(leaderChoice) - 1;
                    } catch (NumberFormatException ignored) {
                        // La vérification ci-dessous gérera l'erreur
                    }

                    if (leaderIndex < 0 || leaderIndex >= TheirNames.size()) {
                        System.out.println("Sélection de chef invalide.");
                        break;
                    }

                    ClanLeader currentLeader = TheirNames.get(leaderIndex);
                    Place currentPlace = currentLeader.getPlace();

                    // /2 Choisir l'option (Nourrir une personne ou tous)
                    System.out.println("\nOptions de Nourriture pour " + currentPlace.getClass().getSimpleName() + " :");
                    System.out.println("1 : Nourrir une personne spécifique.");
                    System.out.println("2 : Nourrir TOUS les habitants.");
                    System.out.print("Entrez votre choix (1 ou 2) : ");
                    String feedOption = sc.nextLine();

                    if (feedOption.equals("2")) {
                        // Option 2 : Nourrir tous
                        currentLeader.feedAll();
                        break;
                    }

                    if (feedOption.equals("1")) {
                        // Option 1 : Nourrir un spécifique

                        if (currentPlace.getListOfPersons().isEmpty()) {
                            System.out.println("Le lieu de " + currentLeader.getName() + " est vide. Personne à nourrir.");
                            break;
                        }

                        // /3 Afficher les habitants et choisir l'habitant à nourrir
                        System.out.println("\nHabitants disponibles :");
                        for (int i = 0; i < currentPlace.getListOfPersons().size(); i++) {
                            Person p = currentPlace.getListOfPersons().get(i);
                            // J'ai besoin de savoir s'il y a un statut de "faim" sur la Personne pour le mettre ici
                            System.out.println((i + 1) + " : " + p.getName() + " (" + p.getClass().getSimpleName() + ")");
                        }

                        System.out.print("Entrez le numéro de l'habitant à nourrir : ");
                        String personChoice = sc.nextLine();

                        int personIndex = -1;
                        try {
                            personIndex = Integer.parseInt(personChoice) - 1;
                        } catch (NumberFormatException ignored) {
                            // Géré ci-dessous
                        }

                        if (personIndex < 0 || personIndex >= currentPlace.getListOfPersons().size()) {
                            System.out.println("Sélection d'habitant invalide.");
                            break;
                        }

                        Person targetPerson = currentPlace.getListOfPersons().get(personIndex);

                        // --- ÉTAPE 4: Exécuter l'action (un spécifique) ---
                        currentLeader.feedSomeone(targetPerson);
                        break;
                    }

                    // Si l'option n'est ni 1 ni 2
                    System.out.println("Option invalide.");
                    break;

                case "soigner":
                    if (TheirNames.isEmpty()) {
                        System.out.println("Aucun chef de clan n'a été créé.");
                        break;
                    }

                    // 1/ Choisir le Chef / Lieu
                    System.out.println("\n--- 🩹 SOIGNER UN HABITANT / TOUS ---");
                    System.out.println("Quel chef de clan voulez-vous utiliser pour soigner ?");

                    for (int i = 0; i < TheirNames.size(); i++) {
                        ClanLeader leader = TheirNames.get(i);
                        System.out.println((i + 1) + " : " + leader.getName() + " dirige " + leader.getPlace().getClass().getSimpleName());
                    }

                    System.out.print("Entrez le numéro du chef/lieu : ");
                    String leaderChoiceHeal = sc.nextLine();

                    int leaderIndexHeal = -1;
                    try {
                        leaderIndexHeal = Integer.parseInt(leaderChoiceHeal) - 1;
                    } catch (NumberFormatException ignored) {
                        // Géré ci-dessous
                    }

                    if (leaderIndexHeal < 0 || leaderIndexHeal >= TheirNames.size()) {
                        System.out.println("Sélection de chef invalide.");
                        break;
                    }

                    ClanLeader currentLeaderHeal = TheirNames.get(leaderIndexHeal);
                    Place currentPlaceHeal = currentLeaderHeal.getPlace();

                    // 2/ Choisir l'option (Soigner un seul habitant ou tous)
                    System.out.println("\nOptions de Soin pour " + currentPlaceHeal.getClass().getSimpleName() + " :");
                    System.out.println("1 : Soigner une personne spécifique.");
                    System.out.println("2 : Soigner TOUS les habitants.");
                    System.out.print("Entrez votre choix (1 ou 2) : ");
                    String healOption = sc.nextLine();

                    if (healOption.equals("2")) {
                        // Option 2 : Soigner tous
                        currentLeaderHeal.healAll();
                        break;
                    }

                    if (healOption.equals("1")) {
                        // Option 1 : Soigner un spécifique
                        if (currentPlaceHeal.getListOfPersons().isEmpty()) {
                            System.out.println("Le lieu de " + currentLeaderHeal.getName() + " est vide. Personne à soigner.");
                            break;
                        }

                        // 3/ Afficher les habitants et choisir l'habitant à soigner
                        System.out.println("\nHabitants disponibles :");
                        for (int i = 0; i < currentPlaceHeal.getListOfPersons().size(); i++) {
                            Person p = currentPlaceHeal.getListOfPersons().get(i);
                            System.out.println((i + 1) + " : " + p.getName() + " (" + p.getClass().getSimpleName() + ")");
                        }

                        System.out.print("Entrez le numéro de l'habitant à soigner : ");
                        String personChoiceHeal = sc.nextLine();

                        int personIndexHeal = -1;
                        try {
                            personIndexHeal = Integer.parseInt(personChoiceHeal) - 1;
                        } catch (NumberFormatException ignored) {
                            // Géré ci-dessous
                        }

                        if (personIndexHeal < 0 || personIndexHeal >= currentPlaceHeal.getListOfPersons().size()) {
                            System.out.println("Sélection d'habitant invalide.");
                            break;
                        }

                        Person targetPersonHeal = currentPlaceHeal.getListOfPersons().get(personIndexHeal);

                        // 4/ Exécuter l'action de soin
                        currentLeaderHeal.healSomeone(targetPersonHeal);
                        break;
                    }

                    // Si l'option n'est ni 1 ni 2
                    System.out.println("Option de soin invalide.");
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
