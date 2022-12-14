package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.plant.IPlantType;

public class Earth extends WorldMap{





    public Earth (int sizeOfMap, IPlantType planttype) {
        this.size = new MapSize(sizeOfMap, sizeOfMap);
        this.plantType = planttype;
    }
    @Override
    public ChangePosition newLocation(Vector2d location) {
        return new ChangePosition(
                new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                        (location.getY() + size.getHeight()) % size.getHeight()),
                0);
    }
}
