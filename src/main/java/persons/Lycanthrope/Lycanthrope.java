package persons.Lycanthrope;


import persons.Fighter;
import persons.Person;

public class Lycanthrope extends Person implements Fighter {
    // Caractéristiques principales
    private LycanthropeAge age;
    private int impetuosity;

    //Domination
    private DominationRank dominationRank;
    private int dominationExercised;
    private int dominationSuffered;

    //meute
    private Pack pack;
    private boolean lone;

    public Lycanthrope(String name, char gender, double height, LycanthropeAge age, int strength, int endurance, DominationRank dominationRank) {

        super(name, gender, height, 0, strength, endurance);

        this.age = age;
        this.dominationRank = dominationRank;
        this.lone = true;
    }

    @Override
    public void Fighter(){

    }

    public int getDominationFactor(){
        return dominationExercised-dominationSuffered;
    }

    public boolean canDominate(Lycanthrope target){
        if(this==target){
            return false;
        }

        if(target.lone){
            return false;
        }

        if(target.gender=='F' && target.dominationRank==DominationRank.ALPHA){
            return false;
        }
        return (this.strength+impetuosity)>= target.strength;
    }
    
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

    public void Dominate(Lycanthrope lycanthrope){
        this.dominationExercised++;
        lycanthrope.dominationSuffered++;

        DominationRank tmp=this.dominationRank;
        this.dominationRank=lycanthrope.dominationRank;
        lycanthrope.dominationRank=tmp;
    }


}
