package Theatres;

import clock.Clock;
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
    public TheatreOfInvasion(String nameOfTheGame, ArrayList<Place> listOfPlaces) {

        this.name = name;
        this.listOfPlaces = listOfPlaces;
    }


    public ArrayList<Place> showPlaces() {
        return listOfPlaces;
    }

    public int howManyPeople() {
        int compt = 0;
        for (Place p : listOfPlaces) {
            compt+=p.getNbpersons();
        }
        return compt;
    }

    public List PeopleByPlace(Place place){
        List people = new ArrayList();
        people = place.getListOfPersonnage();
        return null;
    }
    public static void main (String[] args) {
        Scanner sc = new Scanner(System.in);
        int input = 0;
        System.out.println("Bienvenue dans le théatre d'envahisement !" );
        System.out.println("Quel est le nom de votre partie");
        String name = sc.nextLine();
        while (input<=0) {
            System.out.println("Combien de joueur(s) etes vous ?");
            String nbOfPlaces = sc.nextLine();
            try {
                input = Integer.parseInt(nbOfPlaces);
            } catch (NumberFormatException e) {
                System.out.println("Veuillez renter un alpha numerique");
            }
        }
        for (int i = 0; i < input; i++) {
            System.out.println("Quel est le type de votre premier lieu");
            System.out.println(
                    "1 : Village Gaullois \n 2 :  camp retranché romain \n 3 : ville romaine \n 4 : bourgade gallo-romaine \n 5 : enclos \n 6 : champ de bataille"
            );
            input = sc.nextInt();
            switch (input) {
                case 1:

            }


        }
        //TheatreOfInvasion theatre = new TheatreOfInvasion(name, input);

        Clock clock = new Clock();
        while (true){
            clock.notifySubscribers();

        }

    }

}
