package person.roman.charac;

import clock.Clock;
import person.Person;
import person.roman.Roman;
import person.Leader;

public non-sealed class RomanPrefect extends Roman implements Leader{
    public RomanPrefect(String name, char gender, double height, int age, int strength, int endurance) {
        super(name, gender, height, age, strength, endurance);
        this.setTicBeforeAction(24);
    }

    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }


    @Override
    public void lead() {
        // Promeut un legionnaire en general
        Person replacement = null;
        replacement = new RomanGeneral(this.getTarget().getName(),
                this.getTarget().getGender(),
                this.getTarget().getHeight(),
                this.getTarget().getAge(),
                this.getTarget().getStrength(),
                this.getTarget().getEndurance()
        );

        // Supprimer l'ancienne personne
        Clock.getInstance().unsubscribe(this.getTarget());
        this.getPlace().removePerson(this.getTarget());

        // Ajouter la nouvelle personne
        this.getPlace().addPerson(replacement);
        Clock.getInstance().subscribe(replacement);
    }

    @Override
    public void ticsPassed() {
        super.ticsPassed();
    }
}
