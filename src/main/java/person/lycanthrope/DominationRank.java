package person.lycanthrope;

/**
 * Rang de domination des lycanthropes
 */
public enum DominationRank {
    ALPHA('α',0),
    BETA('β', 1),
    GAMMA('γ', 2),
    DELTA('δ', 3),
    EPSILON('ε', 4),
    OMEGA('ω', Integer.MAX_VALUE);

    private final char symbol;
    private final int rank;

    /**
     * Constructeur de DominationRank
     * @param symbol
     * @param rank
     */
    DominationRank(char symbol, int rank) {
        this.symbol = symbol;
        this.rank = rank;
    }

    /**
     * Retourne le symbole du rang
     * @return symbole
     */
    public char getSymbol() {
        return symbol;
    }

    /**
     * Retourne le rang
     * @return rang
     */
    public int getRank() {
        return rank;
    }


    /**
     * Renvoie le rang inférieur
     * @return rang inférieur
     */
    public DominationRank getLowerRank() {
        switch (this) {
            case ALPHA:   return BETA;
            case BETA:    return GAMMA;
            case GAMMA:   return DELTA;
            case DELTA:   return EPSILON;
            case EPSILON: return OMEGA;
            default:      return OMEGA;
        }
    }
}
