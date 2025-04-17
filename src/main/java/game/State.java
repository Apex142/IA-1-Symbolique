package game;

import java.util.List;

public interface State {

    /**
     * Génère la liste des états accessibles depuis l'état actuel.
     * @return Une liste des états voisins atteignables.
     */
    List<State> getSuccessors();

    /**
     * Retourne une représentation sous forme de chaîne de caractères de l'état actuel.
     * @return Une représentation textuelle de l'état.
     */
    String toString();

}
