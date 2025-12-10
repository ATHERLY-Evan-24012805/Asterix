package person.roman.charac;

import person.Person;
import person.roman.Roman;
import person.Fighter;
import person.Leader;
import place.Place;
import place.types.BattleField;

import java.util.ArrayList;
import java.util.List;

public non-sealed class RomanGeneral extends Roman implements Fighter, Leader {

    //constructeur
    public RomanGeneral(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
        this.setTicBeforeAction(5);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }

    // se bat seulement s'il n'a plus d'allier avec lui sur le champ de bataille
    @Override
    public void fight() {
        Place place = this.getPlace();

        // Pas de combat en dehors d'un BattleField
        if (!(place instanceof BattleField)) {
            return;
        }

        // S'il lui reste des alliés, il ne peut se battre
        for (Person person : place.getPeople()){
            if (person == this) continue;

            if (person instanceof Roman){
                return;
            }
        }
        this.fight(this.getTarget());


    }

    @Override
    public void lead() {
    // fait en sorte que deux personnages puissent taper en meme temps
        Place place = this.getPlace();

        // Pas de combat en dehors d'un BattleField
        if (!(place instanceof BattleField)) {
            return;
        }

        // Instanciation de la liste de legionnaire
        ArrayList<Person> legionnaries = new ArrayList<>();
        for (Person person : place.getPeople()){
            if (person instanceof RomanLegionary){
                legionnaries.add(person);
            }
        }

        //S'il a plus de deux alliés legionnaires
        if (legionnaries.size()>=2 || this.getTicBeforeAction() == 0){
            //faire agir le general
            legionnaries.get(0).fight(this.getTarget());
            legionnaries.get(1).fight(this.getTarget());
            this.setTicBeforeAction(5);
        }
    }

    @Override
    public void ticsPassed() {
        super.ticsPassed();
        this.setTicBeforeAction(this.getTicBeforeAction()-1);
        this.lead();
    }
}

