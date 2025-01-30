package fr.apex.grid;

public class GridGame {
    private final Grid initialGrid;
    private final Grid finalGrid;

    public GridGame(Grid initialGrid, Grid finalGrid) {
        this.initialGrid = initialGrid;
        this.finalGrid = finalGrid;
    }

    public Grid getInitialGrid() {
        return initialGrid;
    }

    public Grid getFinalGrid() {
        return finalGrid;
    }

    @Override
    public String toString() {
        return "Grid initial :\n" + initialGrid + "\nFinal Grid final:\n" + finalGrid;
    }
}
