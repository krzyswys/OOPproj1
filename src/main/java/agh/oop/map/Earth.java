package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.plant.IPlantType;

public class Earth implements IMapType {
    @Override
    public ChangePosition newLocation(MapSize size, Vector2d location) {
        return new ChangePosition(
                new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                        (location.getY() + size.getHeight()) % size.getHeight()),
                0);
    }
}
