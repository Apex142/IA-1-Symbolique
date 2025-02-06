package fr.mehdichadi.game;

public class Move {
    private final Direction direction;

    public Move(Direction direction) {
        this.direction = direction;
    }

    public Direction getDirection() {
        return direction;
    }

    /**
     * Applique le mouvement à un état donné et retourne le nouvel état.
     * @param state L'état actuel du jeu.
     * @return Le nouvel état après application du mouvement ou null si le mouvement est impossible.
     */
    public State apply(State state) {
        if (state instanceof Grid grid) {
            return grid.move(direction); // move() gère la copie et la validation du mouvement
        }
        throw new IllegalArgumentException("Move peut seulement être appliqué à une instance de Grid.");
    }

    @Override
    public String toString() {
        return "Move{" + "direction=" + direction + '}';
    }
}
