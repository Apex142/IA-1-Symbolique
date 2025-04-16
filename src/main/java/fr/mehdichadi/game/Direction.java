package fr.mehdichadi.game;

/**
 * Enumération représentant les directions possibles pour déplacer la case vide.
 */
public enum Direction {
    UP(-1, 0),
    DOWN(1, 0),
    LEFT(0, -1),
    RIGHT(0, 1);


    private final int rowChange;
    private final int colChange;

    /**
     * Constructeur de Direction.
     * @param rowChange Modification de ligne associée à la direction.
     * @param colChange Modification de colonne associée à la direction.
     */
    Direction(int rowChange, int colChange) {
        this.rowChange = rowChange;
        this.colChange = colChange;
    }

    /**
     * Retourne le changement de ligne associé à cette direction.
     * @return Valeur de modification de ligne.
     */
    public int getRowChange() {
        return rowChange;
    }

    /**
     * Retourne le changement de colonne associé à cette direction.
     * @return Valeur de modification de colonne.
     */
    public int getColChange() {
        return colChange;
    }
}
