package gui;

import game.Grid;
import game.Move;
import search.SearchAlgorithm;

import java.util.List;

public class TaquinModel {

    private Grid initial;
    private Grid goal;
    private List<Move> solution;
    private int currentStep;

    public void setInitialAndGoal(Grid initial, Grid goal) {
        this.initial = initial;
        this.goal = goal;
        this.solution = null;
        this.currentStep = 0;
    }

    public boolean prepareResolution(SearchAlgorithm algorithm) {
        if (initial == null || goal == null) return false;

        this.solution = algorithm.search(initial, goal);
        this.currentStep = 0;
        return solution != null;
    }

    public boolean step() {
        if (solution == null || currentStep >= solution.size()) return false;
        currentStep++;
        return true;
    }

    public Grid getCurrentState() {
        Grid current = initial;
        for (int i = 0; i < currentStep; i++) {
            current = (Grid) solution.get(i).getNextState();
        }
        return current;
    }

    public int getCurrentStep() {
        return currentStep;
    }

    public int getTotalSteps() {
        return solution == null ? 1 : solution.size();
    }

    public Grid getGoal() {
        return goal;
    }

    public Grid getInitialState() {
        return initial;
    }
}
