package person.lycanthrope;

import person.Fighter;
import person.Person;
import place.types.Enclosure;

public class Lycanthrope extends Person implements Fighter {
    // Caractéristiques principales
    private LycanthropeAge ageCategory;
    private int age;
    private int impetuosity;

    //Domination
    private DominationRank dominationRank;
    private int dominationExercised;
    private int dominationSuffered;

    //meute
    private Pack pack;
    private boolean lone;
    private Enclosure enclosure;


    // Conxtructeur
    public Lycanthrope(String name, char gender, double height, int age, int strength, int endurance, DominationRank dominationRank) {
        super(name, gender, height, 0, strength, endurance);
        this.age = age;
        this.dominationRank = dominationRank;
        this.lone = true;
    }


    // Méthodes Override
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }
    @Override
    public void fight(){    }

    @Override
    public void ticsPassed() {    }


    // Getteurs
    public LycanthropeAge getAgeCategory(){
        return ageCategory;
    }

    public int getDominationFactor(){
        return dominationExercised-dominationSuffered;
    }

    public DominationRank getDominationRank() {
        return dominationRank;
    }

    public boolean isLone() {
        return lone;
    }


    // Meute
    public void joinPack(Pack pack, DominationRank dominationRank){
        this.pack = pack;
        this.dominationRank=dominationRank;
        this.lone = false;
    }

    public void leavePack(){
        this.pack=null;
        this.dominationRank=null;
        this.lone = true;
    }

    public void howl(){
        if(lone || pack == null){
            System.out.println(getName() + " est solitaire et hurle dans le vide...");
            return;
        }
        System.out.println(getName() + "hurle ! ");
        pack.getHowl().display();
    }


    // Enclos
    public void moveTo(Enclosure newEnclosure) {
        if (enclosure != null) {
            enclosure.removeLycanthrope(this);
        }
        enclosure = newEnclosure;
        newEnclosure.addLycanthrope(this);
    }


    // Décroissance naturelle de rang
    public void decreaseRank(int threshold) {
        if (lone || dominationRank == null || dominationRank == DominationRank.OMEGA) return;

        if (getDominationFactor() < threshold) {
            dominationRank = dominationRank.getLowerRank();
        }
    }


    // Domination
    public void Dominate(Lycanthrope lycanthrope){

        if (!canDominate(lycanthrope)) {
            // Echec donc sanction de l'agresseur
            this.dominationSuffered++;

            // Cas où le loup OMEGA échoue la domination face à un male ALPHA
            if (lycanthrope.getDominationRank() == DominationRank.ALPHA
                    && lycanthrope.getGender() == 'M'
                    && this.getGender() == 'M' && this.getDominationRank() == DominationRank.OMEGA) {
                // Le loup devient solitaire
                this.pack.removeLycanthrope(this);
            }

            // Cas où le loup échoue la domination face à un male ALPHA
            else if (lycanthrope.getDominationRank() == DominationRank.ALPHA
                    && lycanthrope.getGender() == 'M'
                    && this.getGender() == 'M'){
                // Le loup perd un rang
                this.dominationRank = this.dominationRank.getLowerRank();
            }
            return;
        }

        //Réussite de la domination
        this.dominationExercised++;
        lycanthrope.dominationSuffered++;

        // Echange des rang
        DominationRank tmp=this.dominationRank;
        this.dominationRank=lycanthrope.dominationRank;
        lycanthrope.dominationRank=tmp;
    }

    // Vérification de la domination
    public boolean canDominate(Lycanthrope target){
        if(this==target){
            return false;
        }

        if(target.lone){
            return false;
        }

        if(target.getGender() =='F' && target.dominationRank==DominationRank.ALPHA){
            return false;
        }

        return (this.getStrength() + impetuosity)>= target.getStrength();
    }
}