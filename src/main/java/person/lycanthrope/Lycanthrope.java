package person.lycanthrope;

import person.Fighter;
import person.Person;
import person.TransformedHuman;
import place.Place;
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

    //Caractéristique secondaire
    private int level;


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

    public int getLevel(){
        updateLevel();
        return level;
    }


    // Méthode Level
    public void updateLevel(){
        int newLevel = 0;
        if (ageCategory != null){
            switch (ageCategory){
                case YOUNG: level += 2; break;
                case ADULT: level += 3; break;
                case OLD: level += 1; break;
            }
        }
        level += getStrength();
        level += getDominationFactor();

        switch (dominationRank) {
            case ALPHA:   level *= 1.5; break;
            case BETA:    level *= 1.3; break;
            case GAMMA:   level *= 1.1; break;
            case DELTA:   level *= 1.0; break;
            case EPSILON: level *= 0.9; break;
            case OMEGA:   level *= 0.8; break;
            default:      level *= 1.0; break;
        }
        level = Math.max(level, 0);
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

    public void howl(TypeHowl howl, Lycanthrope target){
        String msg = "";

        switch(howl) {
            case PACK:
                if (pack != null) {
                    msg = "Hurlement de meute : " + pack.getHowl().getMessage();
                } else {
                    msg = "Hurlement de meute : solitaire";
                }
                break;
            case DOMINATION:
                msg = "Hurlement de domination contre " + target.getName();
                break;
            case SUBMISSION:
                msg = "Hurlement de soumission à " + target.getName();
                break;
            case AGGRESSIVITY:
                msg = "Hurlement agressif envers " + target.getName();
                break;
        }
        System.out.println(getName() + " : " + msg);

        // Réponse des membres de la meute
        if(howl == TypeHowl.PACK && pack != null) {
            for(Lycanthrope member : pack.getMembers()) {
                if(member != this) {
                    System.out.println(member.getName() + " répond au hurlement de meute !");
                }
            }
        }
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
        if (!canDominate(lycanthrope)){
            return;
        }

        if(this.getLevel() <= lycanthrope.getLevel()){
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

        return (this.getLevel())>= target.getLevel();
    }

    // Transformation en humain
    public void transformToHuman(Place place){
        double chance = 0.0;
        if (dominationRank != null) {
            switch (dominationRank) {
                case ALPHA: chance = 0.01; break;
                case BETA:  chance = 0.05; break;
                case GAMMA: chance = 0.1; break;
                case DELTA: chance = 0.2; break;
                case EPSILON: chance = 0.3; break;
                case OMEGA: chance = 0.5; break;
            }
        }

        if (Math.random() < chance) {
            System.out.println(getName() + " quitte la meute et l'enclos !");

            this.pack.removeLycanthrope(this);
            this.enclosure.removeLycanthrope(this);
            Person newPerson = new TransformedHuman(this.getName(), this.getGender(), this.getHeight(), this.getAge(), this.getStrength(), this.getEndurance());
            place.addPerson(newPerson);
        }

    }
}