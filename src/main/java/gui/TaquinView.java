package gui;

import game.Grid;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import search.SearchType;

import java.util.function.Consumer;

public class TaquinView {
    private final BorderPane root = new BorderPane();
    private final GridPane gridPane = new GridPane();
    private final Button loadButton = new Button("Charger fichier");
    private final Button startButton = new Button("Play");
    private final Button pauseButton = new Button("Pause");
    private final Button stepButton = new Button("Step");
    private final ComboBox<SearchType> algorithmBox = new ComboBox<>(FXCollections.observableArrayList(SearchType.values()));

    public TaquinView(TaquinController controller, TaquinModel model) {
        HBox controls = new HBox(10, loadButton, startButton, pauseButton, stepButton, algorithmBox);
        controls.setPadding(new Insets(10));
        root.setTop(controls);
        root.setCenter(gridPane);
        algorithmBox.getSelectionModel().selectFirst();
    }

    public void renderGrid(Grid grid) {
        gridPane.getChildren().clear();
        for (int i = 0; i < grid.getRows(); i++) {
            for (int j = 0; j < grid.getCols(); j++) {
                Label tile = new Label(String.valueOf(grid.getTile(i, j)));
                tile.setMinSize(40, 40);
                tile.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-font-size: 16px;");
                gridPane.add(tile, j, i);
            }
        }
    }

    public void setOnLoadFile(Runnable action) {
        loadButton.setOnAction(e -> action.run());
    }

    public void setOnStart(Runnable action) {
        startButton.setOnAction(e -> action.run());
    }

    public void setOnPause(Runnable action) {
        pauseButton.setOnAction(e -> action.run());
    }

    public void setOnStep(Runnable action) {
        stepButton.setOnAction(e -> action.run());
    }

    public SearchType getSelectedSearchType() {
        return algorithmBox.getValue();
    }

    public Pane getRoot() {
        return root;
    }
}
