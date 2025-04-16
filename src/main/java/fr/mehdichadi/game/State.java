package fr.mehdichadi.game;

import java.util.List;

public interface State {

    /**
     * Vérifie si l'état actuel correspond à l'état final.
     * @return true si l'état actuel est l'état final, sinon false.
     */
    boolean isGoalState();

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

    /**
     * Évalue un coût de passage de l'état actuel à un autre état.
     * Peut être utilisé dans des algorithmes comme A*.
     * @param nextState L'état vers lequel on se déplace.
     * @return Le coût de déplacement.
     */
    default int getTransitionCost(State nextState) {
        return 1;  // Valeur par défaut (peut être surchargée)
    }
}
