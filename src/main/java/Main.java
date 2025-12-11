import clanLeader.ClanLeader;
import person.Person;
import place.Place;
import place.types.*;
import theatres.TheatreOfInvasion;

import java.util.ArrayList;
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
            // Le thread de l'horloge continue de tourner derrière
            System.out.println("\n--- Quels sont vos ordres ! ---");
            String command = sc.nextLine();

            // sequence de jeu
            // A chaque tic, il faut que les joueurs aient leurs stats et leurs options de combat.
            // Il faut utiliser la methode dans theatreOfInvasion evolutionOfTheGame
            switch (command.toLowerCase()) {
                case "info":
                    System.out.println("Il y a " + theatre.howManyPeople() + " personnes en vie.");
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
