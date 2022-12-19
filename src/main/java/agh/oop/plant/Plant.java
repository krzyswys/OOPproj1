package agh.oop.plant;

import agh.oop.AbstractMapElement;
import agh.oop.Vector2d;
import agh.oop.animal.IAnimalObserver;
import agh.oop.animal.IGeneMutator;
import agh.oop.animal.INextGene;
import agh.oop.map.WorldMap;


import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Plant extends AbstractMapElement {
    IPlantType plantType;

    public Plant(Vector2d position, int energy, IPlantType plantType) {
        this.energy = energy;
        this.position = position;
        this.plantType = plantType;
    }

    @Override
    public String toString() {
        return "*";
    }

}
