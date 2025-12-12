package place.types;

import person.Person;

import person.lycanthrope.Lycanthrope;
import person.lycanthrope.Pack;
import place.Place;
import person.Person;
import person.lycanthrope.Lycanthrope;
import place.Place;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Classe représentant un enclos
 *
 * <p>Un enclos ne peut contenir que des créatures fantastiques.
 *
 * <p>Hérite de Place et bénéficie donc des méthodes pour
 * gérer les habitants et leur alimentation/soin.
 */
public class Enclosure extends Place {

    private String name;
    private Pack pack;
    private List<Lycanthrope> lycanthropes;

    public Enclosure(String name) {
        this.name = name;
        this.lycanthropes = new ArrayList<>();
    }

    public boolean hasPack() {
        return pack != null;
    }

    public void addLycanthrope(Lycanthrope l) {
        lycanthropes.add(l);
    }

    public void removeLycanthrope(Lycanthrope l) {
        lycanthropes.remove(l);
    }

    public List<Lycanthrope> getSolitaryLycanthropes() {
        List<Lycanthrope> solitaryLycanthropes = new ArrayList<>();
        for (Lycanthrope l : lycanthropes) {
            if(l.isLone()){
                solitaryLycanthropes.add(l);
            }
        }
        return solitaryLycanthropes;
    }

    /**
     * Vérifie si une personne peut entrer dans ce lieu
     *
     * @param person La personne à tester
     * @return true si la personne peut entrer, false sinon
     */
    @Override
    public boolean canAddPerson(Person person) {
        return person instanceof Lycanthrope;
    }
    @Override
    public String getType() {
        return "Enclos";
    }

    public void tryCreatePackIfPossible(Scanner scanner) {

        if (hasPack()) return;

        Lycanthrope male = null;
        Lycanthrope female = null;

        for (Lycanthrope l : lycanthropes) {
            if (!l.isLone()) continue;

            if (l.getGender() == 'M' && male == null) {
                male = l;
            }
            if (l.getGender() == 'F' && female == null) {
                female = l;
            }
        }

        if (male != null && female != null) {

            System.out.print("Nom de la nouvelle meute : ");
            String packName = scanner.nextLine();

            System.out.print("Cri de la meute : ");
            String howl = scanner.nextLine();

            pack = new Pack(packName, howl, male, female);
        }

    }
}
