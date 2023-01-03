package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.plant.IPlantType;

import java.util.concurrent.ThreadLocalRandom;

public class Hell implements IMapType {
    private int teleportationCost;

    public Hell(int teleportationCost) {
        this.teleportationCost = teleportationCost;
    }

    @Override
    public ChangePosition newLocation(MapSize size, Vector2d location) {
        if (location.x >= size.getWidth() || location.x < 0 || location.y >= size.getHeight() || location.y < 0) {

            return new ChangePosition(
                    new Vector2d(ThreadLocalRandom.current().nextInt(0, size.getWidth() - 1), ThreadLocalRandom.current().nextInt(0, size.getHeight() - 1)), teleportationCost);

        } else {
            return new ChangePosition(
                    new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                            (location.getY() + size.getHeight()) % size.getHeight()),
                    0);

        }
    }
}
