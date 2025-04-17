package gui;

import core.GameLoader;
import core.SearchRunner;
import game.Grid;
import game.Move;
import heuristique.ManhattanDistance;
import heuristique.ManhattanPlusLinearConflict;
import heuristique.MisplacedTilesHeuristic;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.FileChooser;
import javafx.util.Duration;
import search.*;
import search.report.SearchReport;

import java.io.File;
import java.util.List;

public class TaquinController {

    @FXML private ComboBox<String> algorithmChoice;
    @FXML private Button playButton, resetButton, browseButton, stopButton, stepButton;
    @FXML private ProgressBar progressBar;
    @FXML private TextArea logArea;
    @FXML private GridPane gridDisplay;
    @FXML private Slider speedSlider;

    private Grid initialState;
    private Grid goalState;
    private SearchReport lastReport;
    private Timeline timeline;
    private int stepIndex = 0;
    private List<Move> solution;

    public void initialize() {
        algorithmChoice.getItems().addAll("DFS", "BFS", "BEST_FIRST", "BEST_FIRST_MISPLACED", "BEST_FIRST_LINEAR");
        algorithmChoice.getSelectionModel().selectFirst();
        progressBar.setProgress(0);
        disableButtonsExceptBrowse();

        // Mise à jour dynamique de la vitesse si on change le slider pendant la timeline
        speedSlider.valueProperty().addListener((obs, oldVal, newVal) -> {
            if (timeline != null && timeline.getStatus() == Timeline.Status.RUNNING) {
                timeline.stop();
                playButton.setDisable(true); // empêcher double play
                handlePlay(); // relancer avec nouvelle vitesse
            }
        });
    }

    @FXML
    private void handleBrowse() {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle("Choisir un fichier .grid");
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("Grid files", "*.grid"));
        File file = fileChooser.showOpenDialog(browseButton.getScene().getWindow());
        if (file != null) {
            try {
                GameLoader loader = new GameLoader();
                initialState = loader.loadGame(file);
                goalState = loader.getGoalGrid();
                updateGridDisplay(initialState);
                log("✅ Grille chargée : " + file.getName());
                disableButtonsAfterLoad();
                handleStart();
            } catch (Exception e) {
                log("❌ Erreur de chargement : " + e.getMessage());
            }
        }
    }

    @FXML
    private void handleStart() {
        if (initialState == null || goalState == null) {
            log("⚠️ Veuillez d'abord charger une grille.");
            return;
        }

        String choice = algorithmChoice.getValue();
        SearchAlgorithm algorithm = switch (choice) {
            case "DFS" -> new DepthFirstSearch();
            case "BFS" -> new BreadthFirstSearch();
            case "BEST_FIRST" -> new BestFirstSearch(new ManhattanDistance(), goalState);
            case "BEST_FIRST_MISPLACED" -> new BestFirstSearch(new MisplacedTilesHeuristic(), goalState);
            case "BEST_FIRST_LINEAR" -> new BestFirstSearch(new ManhattanPlusLinearConflict(), goalState);
            default -> throw new IllegalArgumentException("Algorithme inconnu");
        };

        log("▶️ Lancement de l'algorithme : " + choice);
        progressBar.setProgress(ProgressIndicator.INDETERMINATE_PROGRESS);
        disableAllButtonsExceptReset();
        algorithmChoice.setDisable(true);

        new Thread(() -> {
            SearchRunner runner = new SearchRunner();
            lastReport = runner.runSearch(algorithm, initialState, goalState);

            Platform.runLater(() -> {
                solution = lastReport.getSolution();
                stepIndex = 0;

                log("📊 Résultat pour " + choice + " :");
                log("🔢 Étapes : " + (solution == null ? 0 : solution.size()));
                log("🌳 Nœuds explorés : " + lastReport.getNodesExplored());
                log("⏱️ Temps : " + lastReport.getDurationMillis() + " ms");
                log("📬 Ouverts : " + lastReport.getOpenList().size());
                log("📂 Fermés : " + lastReport.getClosedList().size());

                if (solution == null || solution.isEmpty()) {
                    log("❌ Aucune solution trouvée.");
                } else {
                    log("✅ Solution trouvée. Cliquez sur ▶️ Play ou 🔂 Étape.");
                    playButton.setDisable(false);
                    stepButton.setDisable(false);
                }
                stopButton.setDisable(false);
                resetButton.setDisable(false);
                progressBar.setProgress(1);
            });
        }).start();
    }

    @FXML
    private void handlePlay() {
        if (solution == null || solution.isEmpty()) return;
        playButton.setDisable(true);
        algorithmChoice.setDisable(true);

        double delay = 0.05 + (1.0 - speedSlider.getValue()) * 1.5;

        timeline = new Timeline(new KeyFrame(Duration.seconds(delay), event -> {
            if (stepIndex < solution.size()) {
                Grid next = (Grid) solution.get(stepIndex).getNextState();
                updateGridDisplay(next);
                log("▶️ Étape " + (stepIndex + 1));
                stepIndex++;
            } else {
                timeline.stop();
                highlightGrid(goalState, Color.LIGHTGREEN);
                log("🎉 Fin de l'animation. Solution affichée !");
            }
        }));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    @FXML
    private void handleStep() {
        if (solution == null || stepIndex >= solution.size()) return;
        Grid next = (Grid) solution.get(stepIndex).getNextState();
        updateGridDisplay(next);
        log("🔂 Étape manuelle " + (stepIndex + 1));
        stepIndex++;
        if (stepIndex == solution.size()) {
            highlightGrid(goalState, Color.LIGHTGREEN);
            log("✅ Fin de la résolution.");
        }
    }

    @FXML
    private void handleStop() {
        if (timeline != null) {
            timeline.stop();
            log("⏹️ Animation stoppée.");
            playButton.setDisable(false);
        }
    }

    @FXML
    private void handleReset() {
        if (timeline != null) timeline.stop();
        initialState = null;
        goalState = null;
        stepIndex = 0;
        lastReport = null;
        solution = null;
        gridDisplay.getChildren().clear();
        logArea.clear();
        progressBar.setProgress(0);
        algorithmChoice.getSelectionModel().selectFirst();
        algorithmChoice.setDisable(false);
        disableButtonsExceptBrowse();
        log("🔄 Réinitialisation complète.");
    }

    private void updateGridDisplay(Grid grid) {
        gridDisplay.getChildren().clear();
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                String value = String.valueOf(grid.getTile(row, col));
                Label label = new Label(value);
                label.setFont(new Font("Arial", 24));
                label.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-background-color: white;");
                label.setMinSize(80, 80);
                gridDisplay.add(label, col, row);
            }
        }
    }

    private void highlightGrid(Grid grid, Color color) {
        gridDisplay.getChildren().clear();
        for (int row = 0; row < grid.getRows(); row++) {
            for (int col = 0; col < grid.getCols(); col++) {
                String value = String.valueOf(grid.getTile(row, col));
                Label label = new Label(value);
                label.setFont(new Font("Arial", 24));
                label.setStyle("-fx-border-color: black; -fx-alignment: center; -fx-background-color: " + toRgb(color) + ";");
                label.setMinSize(80, 80);
                gridDisplay.add(label, col, row);
            }
        }
    }

    private String toRgb(Color c) {
        return String.format("rgb(%d,%d,%d)",
                (int)(c.getRed()*255),
                (int)(c.getGreen()*255),
                (int)(c.getBlue()*255));
    }

    private void log(String msg) {
        logArea.appendText(msg + "\n");
    }

    private void disableButtonsExceptBrowse() {
        playButton.setDisable(true);
        resetButton.setDisable(true);
        stopButton.setDisable(true);
        stepButton.setDisable(true);
    }

    private void disableButtonsAfterLoad() {
        playButton.setDisable(true);
        resetButton.setDisable(false);
        stopButton.setDisable(true);
        stepButton.setDisable(true);
    }

    private void disableAllButtonsExceptReset() {
        playButton.setDisable(true);
        resetButton.setDisable(false);
        stopButton.setDisable(true);
        stepButton.setDisable(true);
    }
}