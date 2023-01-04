package agh.oop.GUI;

import agh.oop.SimulationEngine;
import agh.oop.animal.*;
import agh.oop.map.Earth;
import agh.oop.map.Hell;
import agh.oop.map.IMapType;
import agh.oop.map.MapSize;
import agh.oop.plant.IPlantType;
import agh.oop.plant.Toxic;
import agh.oop.plant.Trees;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.stage.Stage;

public class AppHolder extends Application {
    int span=1;
    private int width;
    private int height;
    private int energyFromGrass;
    private int energyToReproduce;
    private int animalStartEnergy;
    private int grasPerCycle;
    private int energyLostPerCycle;
    private int numberOfAnimalsOnStart;
    private IPlantType plantType = new Trees();
    private IGeneMutator mutatorType = new MutatorRandom();
    private INextGene nextGeneType = new NextGeneNormal();
    private IMapType mapType;
    private int cols = 20 + 1;
    private int rows = 20 + 1;
    private int cellSize = 40;
    private int animalImgSize = cellSize - 10;
    private int imgSize = cellSize + 1;
    private SimulationEngine engine;
    @Override
    public void start(Stage primaryStage) throws Exception {
        TextField movesInput = new TextField();
        movesInput.setPrefWidth(Math.floor(600));
        movesInput.setText("");

        Label mapWidthLabel = new Label("map width: ");
        mapWidthLabel.setStyle("-fx-text-fill: white");
        Label mapHeightLabel = new Label("map height: ");
        Label energyFromGrassLabel = new Label("energy per plant: ");
        Label energyRequiredToReproduceLabel = new Label("energy required to reproduce: ");
        Label animalStartEnergyLabel = new Label("animal starting energy: ");
        //TODO:remove
        Label grassPerCycleLabel = new Label("grassPerCycle-depricated: ");
        Label energyLostPerCycleLabel = new Label("energy lost per cycle: ");
        Label numberOfAnimalsOnStartLabel = new Label("number of animals: ");
        Label worldTypeLabel = new Label("type of world: ");
        Label nextGeneTypeLabel = new Label("type of nextGene: ");
        Label geneMutatorTypeLabel = new Label("type of geneMutator: ");
        Label plantTypeLabel = new Label("type of plants: ");


        mapHeightLabel.setStyle("-fx-text-fill: white");
        energyFromGrassLabel.setStyle("-fx-text-fill: white");
        energyRequiredToReproduceLabel.setStyle("-fx-text-fill: white");
        animalStartEnergyLabel.setStyle("-fx-text-fill: white");
        grassPerCycleLabel.setStyle("-fx-text-fill: white");
        energyLostPerCycleLabel.setStyle("-fx-text-fill: white");
        numberOfAnimalsOnStartLabel.setStyle("-fx-text-fill: white");

        worldTypeLabel.setStyle("-fx-text-fill: white");
        nextGeneTypeLabel.setStyle("-fx-text-fill: white");
        geneMutatorTypeLabel.setStyle("-fx-text-fill: white");
        plantTypeLabel.setStyle("-fx-text-fill: white");


        TextField mapWidthField = new TextField("20");
        TextField mapHeightField = new TextField("20");
        TextField energyFromGrassField = new TextField("5");
        TextField energyRequiredToReproduceField = new TextField("30");
        TextField animalStartEnergyField = new TextField("15");
        TextField grassPerCycleField = new TextField("1");
        TextField energyLostPerCycleField = new TextField("1");
        TextField numberOfAnimalsOnStartField = new TextField("35");
//
        ComboBox worldTypeField = new ComboBox();
        worldTypeField.getItems().addAll(
                "hell",
                "round"
        );
        ComboBox nextGeneTypeField = new ComboBox();
        nextGeneTypeField.getItems().addAll(
                "NextGeneNormal",

                "nextGeneCreazy" //TODO: not implemented
        );
        ComboBox geneMutatorTypeField = new ComboBox();
        geneMutatorTypeField.getItems().addAll(
                "MutatorRandom",
                "SlightImprovement"
        );
        ComboBox plantTypeField = new ComboBox();
        plantTypeField.getItems().addAll(
                "Toxic",
                "Trees"
        );


        worldTypeField.getSelectionModel().selectFirst();
        nextGeneTypeField.getSelectionModel().selectFirst();
        geneMutatorTypeField.getSelectionModel().selectFirst();
        plantTypeField.getSelectionModel().selectFirst();

        GridPane txt = new GridPane();
        txt.add(mapWidthLabel, 0, 0, span, span);
        txt.add(mapWidthField, 1, 0, span, span);

        txt.add(mapHeightLabel, 0, 1, span, span);
        txt.add(mapHeightField, 1, 1, span, span);

        txt.add(energyFromGrassLabel, 0, 2, span, span);
        txt.add(energyFromGrassField, 1, 2, span, span);

        txt.add(energyRequiredToReproduceLabel, 0, 3, span, span);
        txt.add(energyRequiredToReproduceField, 1, 3, span, span);

        txt.add(animalStartEnergyLabel, 0, 4, span, span);
        txt.add(animalStartEnergyField, 1, 4, span, span);

        txt.add(grassPerCycleLabel, 0, 5, span, span);
        txt.add(grassPerCycleField, 1, 5, span, span);

        txt.add(energyLostPerCycleLabel, 0, 6, span, span);
        txt.add(energyLostPerCycleField, 1, 6, span, span);

        txt.add(numberOfAnimalsOnStartLabel, 0, 7, span, span);
        txt.add(numberOfAnimalsOnStartField, 1, 7, span, span);

        txt.add(worldTypeLabel, 0, 8, span, span);
        txt.add(worldTypeField, 1, 8, span, span);
        txt.add(nextGeneTypeLabel, 0, 9, span, span);
        txt.add(nextGeneTypeField, 1, 9, span, span);
        txt.add(geneMutatorTypeLabel, 0, 10, span, span);
        txt.add(geneMutatorTypeField, 1, 10, span, span);
        txt.add(plantTypeLabel, 0, 11, span, span);
        txt.add(plantTypeField, 1, 11, span, span);

        var images = new Image("border_textures/wbricks.jpg", true);
        var bgImages = new BackgroundImage(
                images,
                BackgroundRepeat.REPEAT,
                BackgroundRepeat.REPEAT,
                BackgroundPosition.DEFAULT,
                new BackgroundSize(30, 30, false, false, false, false)

        );
        txt.setBackground(new Background(bgImages));

        VBox vBox = new VBox();
        Button btn2 = new Button("new simulation");
        btn2.setOnAction(ac -> {

            this.width = Integer.parseInt(mapWidthField.getText());
            this.height = Integer.parseInt(mapHeightField.getText());
            this.rows = height + 2;
            this.cols = width + 2;
            //TODO: set cellsize proportion
//            this.cellSize = (int) (width*0.1*height*0.1*10);
            this.animalImgSize = (int) (cellSize * 0.8);
            this.imgSize = cellSize + 1;
            this.energyFromGrass = Integer.parseInt(energyFromGrassField.getText());
            this.energyToReproduce = Integer.parseInt(energyRequiredToReproduceField.getText());
            this.animalStartEnergy = Integer.parseInt(animalStartEnergyField.getText());
            this.grasPerCycle = Integer.parseInt(grassPerCycleField.getText());
            this.energyLostPerCycle = Integer.parseInt(energyLostPerCycleField.getText());
            this.numberOfAnimalsOnStart = Integer.parseInt(numberOfAnimalsOnStartField.getText());
            Object value = plantTypeField.getValue();
            if ("Toxic".equals(value)) {
                this.plantType = new Toxic();
            } else if ("Trees".equals(value)) {
                this.plantType = new Trees();
            }
            Object mp = worldTypeField.getValue();
            if ("hell".equals(mp)) {
                this.mapType = new Hell(this.energyToReproduce);
            } else if ("round".equals(mp)) {
                this.mapType = new Earth();
            }
            Object gm = geneMutatorTypeField.getValue();
            if ("MutatorRandom".equals(gm)) {
                this.mutatorType = new MutatorRandom();
            } else if ("slightCorrection".equals(gm)) {
                this.mutatorType = new SlightImprovement();
            }
            Object ng = geneMutatorTypeField.getValue();
            if ("nextGeneCreazy".equals(ng)) {
                this.nextGeneType = new NextGeneCrazy();
            } else if ("NextGeneNormal".equals(ng)) {
                this.nextGeneType = new NextGeneNormal();
            }

            App newInstance = new App();
            newInstance.setupInfo(width, height, energyFromGrass, energyToReproduce,animalStartEnergy, grasPerCycle, energyLostPerCycle, numberOfAnimalsOnStart, plantType,mutatorType, nextGeneType, mapType, cols, rows, cellSize, animalImgSize, imgSize );

        });
        HBox hBox = new HBox();
        hBox.getChildren().addAll(txt);
        hBox.setAlignment(Pos.CENTER);
        vBox.getChildren().addAll(hBox,btn2);
        vBox.setAlignment(Pos.CENTER);
        vBox.setStyle("-fx-background-color: #56565e;");

        Scene scene = new Scene(vBox, 720, 780);
        primaryStage.setScene(scene);
        primaryStage.setTitle("World");
        primaryStage.show();
    }
}
