package agh.oop.GUI;

import agh.oop.MapDirection;
import agh.oop.SimulationEngine;
import agh.oop.animal.Animal;
import agh.oop.animal.IAnimalObserver;
import agh.oop.map.Earth;
import agh.oop.map.MapSize;
import agh.oop.plant.Plant;
import agh.oop.plant.Toxic;
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

import java.io.File;
import java.net.URL;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;


public class App extends Application implements IAnimalObserver {
    private List<Animal> animals;
    private List<Plant> plants;
    public int span = 1;
    final GridPane grid = new GridPane();
    SimulationEngine engine;
    private final int cellSize = 40;
    private final int cols = 20 + 1;
    private final int rows = 20 + 1;
    private final int animalImgSize = cellSize - 10;
    private final int imgSize = cellSize + 1; //replace this with cellsize if bcimage can be sized fully to fit sqare or when span removed

    @Override
    public void init() {
        try {

            this.engine = new SimulationEngine(new MapSize(19, 19), new Earth(), new Toxic(), 35, 40);
            this.plants = engine.getplants();
            this.animals = engine.getanimals();
            this.engine.gate();

            for (Animal a : animals) {
                a.addObserver(this);
            }
        } catch (IllegalArgumentException e) {
            System.out.println(e + " an unordinary exeption has been called upon this creation that should be dealt without further ado as it is urging matter to anihilate all exeptions ");
        }
    }
    private void generateMap() {
        this.plants = engine.getplants();
        this.animals = engine.getanimals();
        addConstaints();
        populateWorld();
    }

    @Override
    public void positionChanged(Animal animal) { // possibly can be replaced by refresh function: https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class

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

    //FIXME: create new observer for map or move death into animal?
    @Override
    public void death(Animal animal) {

    }


    public VBox setupConst() {
        this.grid.setMaxHeight(0); grid.setMaxWidth(0);
        TextField movesInput = new TextField();
        movesInput.setPrefWidth(Math.floor(600));
        movesInput.setText("");
//        Button startButton = new Button("START");
//        startButton.setOnAction(action -> {
//            String[] args = movesInput.getText().split(" ");
//            this.engine.applyMoves(OptionsParser.parse(args));
//            Thread engineThread = new Thread(this.engine);
//            engineThread.start();
//        });


        this.grid.setGridLinesVisible(false);
        this.grid.setMaxHeight(0);
        grid.setMaxWidth(0);
        Button startButton = new Button("start/stop");
        startButton.setOnAction(action -> {
            generateMap();
            Thread engineThread = new Thread(this.engine);
            engineThread.start();
            this.engine.gate();
        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(movesInput,startButton);
        hBox.setAlignment(Pos.CENTER);
        hBox.setStyle("-fx-font-size: 20px");

        VBox vBox = new VBox();
        vBox.getChildren().addAll(this.grid, hBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #56565e;");
        VBox.setMargin(hBox, new Insets(10, 0, 50, 0));

        var image = new Image("background_textures/grass.jpg", true);
        var bgImage = new BackgroundImage(
                image,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(1.0, 1.0, true, true, false, false)
        );
        this.grid.setBackground(new Background(bgImage));
        return vBox;
    }

    @Override
    public void start(Stage primaryStage) {
        VBox vBox = setupConst();
        Scene scene = new Scene(vBox, 720, 780);
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
        // for finding one uneatable plant:
//    File dir = new File("src/main/resources/flower_textures");
//    File[] files = dir.listFiles();
//    Random rand = new Random();
//    File file = files[rand.nextInt(files.length)];
//    System.out.println(file.getName());
//    Image imgp = new Image("flower_textures/"+file.getName());
        this.plants = engine.getplants();
        this.animals = engine.getanimals();
        System.out.println(this.animals.size());
        for (Plant iMapElement : this.plants) {
            Label field = new Label("");
//            ImageView view = new ImageView(imgp);
            ImageView view = new ImageView(iMapElement.getTexture());
            view.setFitHeight(imgSize);
            view.setPreserveRatio(true);
            field.setGraphic(view);
            this.grid.add(field, iMapElement.getPosition().x + 1, iMapElement.getPosition().y + 1, span, span);
            GridPane.setHalignment(field, javafx.geometry.HPos.CENTER);
        }
        for (Animal d : this.animals) {
            Label field = new Label("");
            Image img = d.getTexture();
            ImageView view = new ImageView(img);
            view.setFitHeight(animalImgSize);
            view.setPreserveRatio(true);
            field.setGraphic(view);

            int head = d.getActiveGene();
            switch (d.toString()) { // FIXME: getAcriveGene returns same value for all animals
                case "0" -> field.setRotate(0);
                case "1" -> field.setRotate(45);
                case "2" -> field.setRotate(90);
                case "3" -> field.setRotate(135);
                case "4" -> field.setRotate(180);
                case "5" -> field.setRotate(225);
                case "6" -> field.setRotate(270);
                case "7" -> field.setRotate(315);
            }

            this.grid.add(field, d.getPosition().x + 1, d.getPosition().y + 1, span, span);
            GridPane.setHalignment(field, javafx.geometry.HPos.CENTER);
        }
        Image img = new Image("border_textures/wood.jpg");
        for (int i = 0; i < rows; i++) {
            Label field = new Label("");
            ImageView view = new ImageView(img);
            view.setFitHeight(imgSize);
            view.setPreserveRatio(true);
            field.setGraphic(view);
            this.grid.add(field, 0, i, span, span);
            Label field2 = new Label("");
            ImageView view2 = new ImageView(img);
            view2.setFitHeight(imgSize);
            view2.setPreserveRatio(true);
            field2.setGraphic(view2);
            this.grid.add(field2, cols - 1, i, span, span);

        }
        for (int i = 0; i < cols; i++) {
            Label field = new Label("");
            ImageView view = new ImageView(img);
            view.setFitHeight(imgSize);
            view.setPreserveRatio(true);
            field.setGraphic(view);
            field.setRotate(90);
            this.grid.add(field, i, 0, span, span);
            Label field2 = new Label("");
            ImageView view2 = new ImageView(img);
            view2.setFitHeight(imgSize);
            view2.setPreserveRatio(true);
            field2.setGraphic(view2);
            field2.setRotate(90);
            this.grid.add(field2, i, rows - 1, span, span);
        }
    }


}