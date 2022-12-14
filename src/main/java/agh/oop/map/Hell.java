package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.plant.IPlantType;

import java.util.concurrent.ThreadLocalRandom;

public class Hell extends WorldMap{

    public Hell (int sizeOfMap, IPlantType planttype) {
        this.size = new MapSize(sizeOfMap, sizeOfMap);
        this.plantType = planttype;
    }
    @Override
    public ChangePosition newLocation(Vector2d location) { // can be teleported to already taken place?
        return new ChangePosition(
                new Vector2d(ThreadLocalRandom.current().nextInt(0, size.getWidth()),ThreadLocalRandom.current().nextInt(0, size.getHeight())),0);
    }
}
