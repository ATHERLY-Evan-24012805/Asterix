package theatres;

import clock.Clock;
import person.types.Gaulish.charac.GaulishBlacksmith;
import place.types.*;
import place.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class TheatreOfInvasion {
    private String name;
    private int numberOfPlaces;
    private ArrayList<Place> listOfPlaces;
    private List listOfChief;

    //Constructeur
    public TheatreOfInvasion(String nameOfTheGame, ArrayList<Place> listOfPlaces /*, List listOfChief*/) {

        this.name = name;
        this.listOfPlaces = listOfPlaces;
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
            System.out.println("i " + i + " input " + input);

            /* Partie à initialiser une fois la class ClanLeader créée
            System.out.println("Tout bon chef a un nom, quel est le vôtre ? ");
            String name = sc.nextLine();
            ClanLeader leader = new ClanLeader(name)
            TheirName = leader;
             */

            System.out.println("Quel est le type de votre premier lieu");
            System.out.println(" 1 : Village Gaullois \n 2 : camp retranché romain \n 3 : ville romaine \n 4 : bourgade gallo-romaine ");
            int numPlace = sc.nextInt();
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
            if (p instanceof GallicVillage) {
                System.out.println("Vous avez un lieu de Gaullois, vous disposez par défaut de deux forgerons et d'un aubergiste." +
                        "Quel nom voulez vous leurs données ?");
                System.out.print("Forgeron 1 :");
                String f1 = sc.nextLine();
                char gender = ' '; // On initialise la variable
                boolean saisieValide = false;
                while (!saisieValide) {
                    System.out.print("Quel sera son genre M, F ou X ? ");
                    String inputGender = sc.next(); // On lit le texte

                    // On prend le premier caractère et on le met en MAJUSCULE pour accepter 'm' ou 'M'
                    if (inputGender.length() > 0) {
                        gender = Character.toUpperCase(inputGender.charAt(0));
                    }

                    // Vérification
                    if (gender == 'M' || gender == 'F' || gender == 'X') {
                        saisieValide = true; // C'est bon, on pourra sortir de la boucle !
                    } else {
                        System.out.println("Erreur : Veuillez entrer uniquement M, F ou X.");
                    }
                }
                p.addPerson(new GaulishBlacksmith(f1,gender));
            }
        }
        System.out.println("Vous avez par defaut 5 Guerriers");



        TheatreOfInvasion theatre = new TheatreOfInvasion(GameName, TheirPlaces);

        Clock clock = new Clock();
        while (true) {
            clock.notifySubscribers();

        }

    }

}
