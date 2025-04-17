
# 🧩 Taquin Solver - JavaFX & CLI

Ce projet permet de **résoudre automatiquement des grilles de Taquin** en utilisant plusieurs algorithmes de recherche (DFS, BFS, Best-First) via :

- ✅ Une **interface graphique JavaFX**
- ⚙️ Une **interface en ligne de commande (CLI)** pour l'automatisation ou les tests

---

## 🚀 Lancement rapide

### 🎨 Interface graphique (JavaFX)

```bash
./gradlew run
```

Par défaut, cela lance `gui.TaquinApp`.

> Necessite d'avoir des fichiers `.grid` dans `src/main/resources/grids/`.

---

### 💻 Mode Ligne de commande (CLI)

```bash
./gradlew runCLI --args="BEST_FIRST grids/taquin_3x3.grid"
```

Cela utilise une tâche personnalisée pour exécuter la classe `cli.TaquinCLI`.

---

## 📂 Organisation du projet

```
├── src/
│   ├── main/
│   │   ├── java/
│   │   │   ├── gui/              # JavaFX Controller & App
│   │   │   ├── cli/              # Interface ligne de commande
│   │   │   ├── search/           # Algorithmes (DFS, BFS, BestFirst)
│   │   │   ├── core/             # Moteur de recherche
│   │   │   ├── game/             # Modèle du jeu
│   │   │   └── ...
│   │   ├── resources/
│   │   │   ├── gui/
│   │   │   │   └── TaquinView.fxml
│   │   │   ├── style.css
│   │   │   └── grids/
│   │   │       └── taquin_2x4.grid, ...
├── build.gradle
├── README.md
└── ...
```

---

## 🧠 Algorithmes disponibles

- `DFS` – Recherche en profondeur
- `BFS` – Recherche en largeur
- `BEST_FIRST` – Meilleure première recherche avec distance de Manhattan

---

## 🖼️ Fonctionnalités de l'interface

- Chargement de fichiers `.grid`
- Choix de l'algorithme
- Exécution automatique (`▶️ Play`) ou pas-à-pas (`🔂 Étape`)
- Barre de progression
- Barre de vitesse ajustable
- Logs détaillés
- Coloration verte du taquin lorsqu'une solution est trouvée ✅

---

## 🧪 Tests

Lance tous les tests JUnit (avec grilles solvables et insolubles) :

```bash
./gradlew test
```

---

## 📜 Licence

Ce projet est open-source. Utilisation libre à but éducatif ou personnel.

---
