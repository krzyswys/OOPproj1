package agh.oop.GUI;

import agh.oop.SimulationEngine;
import agh.oop.animal.Animal;
import agh.oop.animal.IAnimalObserver;
import agh.oop.map.Earth;
import agh.oop.map.MapSize;
import agh.oop.plant.Plant;
import agh.oop.plant.Trees;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.*;
import javafx.stage.Stage;
import javafx.geometry.HPos;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.util.LinkedList;
import java.util.List;



public class App extends Application implements IAnimalObserver {


    private List<Animal> animals;
    public List<Plant> plants;

    public int span = 1;

    GridPane grid = new GridPane();
    SimulationEngine engine;

    int cellSize = 40;
    int cols = 20;
    int rows = 20;


    @Override
    public void init() {
        try {

            this.engine = new SimulationEngine(new MapSize(19, 19), new Earth(), new Trees(), 18, 40);
            plants = engine.getPlants();
            animals = engine.getAnimals();
            this.engine.gate();

            for (Animal a : animals) {
                a.addObserver(this);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e + "abc");
        }


    }

    @Override
    public void positionChanged(Animal animal) {
        Platform.runLater(() -> {

            while (this.grid.getRowConstraints().size() > 0) {
                this.grid.getRowConstraints().remove(0);
            }

            while (this.grid.getColumnConstraints().size() > 0) {
                this.grid.getColumnConstraints().remove(0);
            }

            Node node = this.grid.getChildren().get(0);
            this.grid.getChildren().clear();
            this.grid.getChildren().add(0, node);


            generateMap();
        });
    }

    @Override
    public void death(Animal animal) {

    }


    public VBox setupConst() {
        this.grid.setGridLinesVisible(true);
        this.grid.setMaxHeight(0);
        grid.setMaxWidth(0);
        Button startButton = new Button("START");
        startButton.setOnAction(action -> {
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
            this.engine.gate();

        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(startButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-font-size: 20px");
        VBox vBox = new VBox();
        vBox.getChildren().addAll(this.grid, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #56565e;");
        VBox.setMargin(hBox, new Insets(50, 0, 50, 0));
        this.grid.setStyle("-fx-background-color: gray");
        this.grid.setGridLinesVisible(true);
        return vBox;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = setupConst();
        generateMap();
        Scene scene = new Scene(vBox, 40 * 20, 40 * 20);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World");
        primaryStage.show();
    }

    public void addConstaints() {
        for (int i = 0; i < rows - 1; i++) {
            this.grid.getColumnConstraints().add(new ColumnConstraints(cellSize));
        }
        for (int i = 0; i < cols - 1; i++) {
            this.grid.getRowConstraints().add(new RowConstraints(cellSize));
        }
    }

    public void populateWorld() {
        for (Plant iMapElement : plants) {
            Label field = new Label(iMapElement.toString());
            field.setStyle("-fx-background-color: green");
            field.setAlignment(Pos.CENTER);
            field.setPrefSize(40, 40);
            this.grid.add(field, iMapElement.getPosition().x, iMapElement.getPosition().y, span, span);
        }
        for (Animal d : animals) {
            System.out.println(d.getPosition());
            Label field = new Label(d.toString());
            field.setStyle("-fx-background-color: brown");
            field.setAlignment(Pos.CENTER);
            field.setPrefSize(40, 40);
            this.grid.add(field, d.getPosition().x, d.getPosition().y, span, span);
        }
    }

    private void generateMap() {
        plants = engine.getPlants();
        animals = engine.getAnimals();
        addConstaints();
//        populateBackground();
        populateWorld();
    }
}