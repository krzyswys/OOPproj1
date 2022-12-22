package agh.oop.plant;

import agh.oop.AbstractMapElement;
import agh.oop.Vector2d;
import agh.oop.animal.IAnimalObserver;
import agh.oop.animal.IGeneMutator;
import agh.oop.animal.INextGene;
import agh.oop.map.WorldMap;
import javafx.scene.image.Image;


import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Plant extends AbstractMapElement {
    IPlantType plantType;
    private final WorldMap map;
    public Plant(WorldMap mpa, Vector2d position, int energy, IPlantType plantType) {
        this.map = mpa;
        this.energy = energy;
        this.position = position;
        this.plantType = plantType;
        File dir = new File("src/main/resources/flower_textures");
        File[] files = dir.listFiles();
        Random rand = new Random();
        File file = files[rand.nextInt(files.length)];
        texture = new Image("flower_textures/"+file.getName());
    }

    @Override
    public String toString() {
        return "*";
    }

}
