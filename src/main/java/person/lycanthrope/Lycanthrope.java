package person.lycanthrope;

import person.Fighter;
import person.Person;
import person.TransformedHuman;
import place.Place;
import place.types.Enclosure;

/**
 * Classe Lycanthrope
 * Cette classe fournit des méthodes pour dominer d'autres loup et rejoindre une meute
 */
public non-sealed class Lycanthrope extends Person implements Fighter {
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
    private double level;


    // Conxtructeur

    /**
     * Constructeur de Lycanthrope
     * @param name nom
     * @param gender genre
     * @param height taille
     * @param age age
     * @param strength force
     * @param endurance endurance
     * @param dominationRank rang de domination
     */
    public Lycanthrope(String name, char gender, double height, int age, int strength, int endurance, DominationRank dominationRank) {
        super(name, gender, height, 0, strength, endurance);
        this.age = age;
        this.dominationRank = dominationRank;
        this.lone = true;
        this.ageCategory = LycanthropeAge.YOUNG;
    }


    // Méthodes Override

    /**
     * Crée une nouvelle instance utilisé pour la potion
     * @param name
     * @param gender
     * @param height
     * @param age
     * @param strength
     * @param endurance
     * @return Nouvelle instance de lycanthrope
     */
    @Override
    public Person duplicate(String name, char gender, double height, int age, int strength, int endurance) {
        return null;
    }

    @Override
    public void fight(){    }

    @Override
    public void ticsPassed() {    }


    // Getteurs

    /**
     * Renvoie la catégorie d'age du lycanthrope
     * @return LycanthropeAge
     */
    public LycanthropeAge getAgeCategory(){
        return ageCategory;
    }

    /**
     * Retourne le facteur de domination du lycanthrope
     * @return le facteur de domination du lycanthrope
     */
    public int getDominationFactor(){
        return dominationExercised-dominationSuffered;
    }

    /**
     * Retourne le rang de domination du lycanthrope
     * @return le rang de domination du lycanthrope
     */
    public DominationRank getDominationRank() {
        return dominationRank;
    }

    /**
     * Vérifie si le lycanthrope est solitaire
     * @return Booleen de solitaire
     */
    public boolean isLone() {
        return lone;
    }

    /**
     * Retourne le niveau du lycanthrope
     * @return le niveau du lycanthrope
     */
    public double getLevel(){
        updateLevel();
        return level;
    }


    // Méthode Level

    /**
     * Méthode pour calculer et mettre à jour le niveau
     */
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

    /**
     * Méthode pour rejoindre une meute
     * @param pack
     * @param dominationRank
     */
    public void joinPack(Pack pack, DominationRank dominationRank){
        this.pack = pack;
        this.dominationRank=dominationRank;
        this.lone = false;
    }

    /**
     * Méthode pour quitter la meute
     */
    public void leavePack(){
        this.pack=null;
        this.dominationRank=null;
        this.lone = true;
    }

    /**
     * Gère les hurlements d'un lycanthrope et le type de hurlement
     * @param howl Type de hurlement
     * @param target target si DOMINATION SUBMISSION et AGGRESSIVITY
     */
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

    /**
     * Déplacement d'un lycanthrope vers un nouvel enclos
     * @param newEnclosure Enclos de destination du lycanthrope
     */
    public void moveTo(Enclosure newEnclosure) {
        if (enclosure != null) {
            enclosure.removeLycanthrope(this);
        }
        enclosure = newEnclosure;
        newEnclosure.addLycanthrope(this);
    }


    // Décroissance naturelle de rang

    /**
     * Décroissance naturelle du rang
     * @param threshold Seuil sous lequel la décroissance aura lieu
     */
    public void decreaseRank(int threshold) {
        if (lone || dominationRank == null || dominationRank == DominationRank.OMEGA) return;

        if (getDominationFactor() > threshold) return;
        if (pack != null && !pack.hasAnotherWithSameRank(this)) return;
        dominationRank = dominationRank.getLowerRank();
    }



    // Domination

    /**
     * Procédure de domination applique les effets au gagnant et au perdant
     * @param lycanthrope Cible de la domination
     */
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

        boolean dethroning = lycanthrope.getDominationRank() == DominationRank.ALPHA
                && lycanthrope.getGender() == 'M' && pack != null;
        // Echange des rang
        DominationRank tmp=this.dominationRank;
        this.dominationRank=lycanthrope.dominationRank;
        lycanthrope.dominationRank=tmp;

        if (dethroning) {
            pack.handleAlphaDomination(this, lycanthrope);
        }
    }

    // Vérification de la domination

    /**
     * Vérification que le lycanthrope peut essayer de dominer
     * @param target Cible de la domination
     * @return Un booleen afin de savoir si la domination est possible
     */
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