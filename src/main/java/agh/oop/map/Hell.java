package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.plant.IPlantType;

import java.util.concurrent.ThreadLocalRandom;

public class Hell implements IMapType{
    @Override
    public ChangePosition newLocation(MapSize size, Vector2d location) { // can be teleported to already taken place?
        return new ChangePosition(
                new Vector2d(ThreadLocalRandom.current().nextInt(0, size.getWidth()),ThreadLocalRandom.current().nextInt(0, size.getHeight())),0);
    }
}
