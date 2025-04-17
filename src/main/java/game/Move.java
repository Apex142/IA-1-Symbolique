package game;

import java.util.Objects;

public class Move {
    private final State parentState;
    private final State nextState;

    /**
     * Construit un mouvement entre deux états successifs.
     * @param parentState L'état d'origine.
     * @param nextState L'état obtenu après un mouvement.
     */
    public Move(State parentState, State nextState) {
        if (parentState == null || nextState == null) {
            throw new IllegalArgumentException("Les états parent et successeur ne peuvent pas être null.");
        }
        this.parentState = parentState;
        this.nextState = nextState;
    }

    /**
     * Applique le mouvement à un état donné et retourne le nouvel état.
     * @param state L'état actuel du jeu.
     * @return Le nouvel état après application du mouvement ou null si impossible.
     */
    public State apply(State state) {
        if (state.equals(parentState)) {
            return nextState; // Retourne l'état suivant correspondant
        }
        throw new IllegalArgumentException("Ce mouvement ne peut pas être appliqué à cet état.");
    }

    @Override
    public String toString() {
        return "Move{" + "from=" + parentState + ", to=" + nextState + '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Move move = (Move) o;
        return Objects.equals(parentState, move.parentState) &&
                Objects.equals(nextState, move.nextState);
    }

    public State getNextState() {
        return nextState;
    }


    @Override
    public int hashCode() {
        return Objects.hash(parentState, nextState);
    }
}