package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.List;

public interface IPlantType {
    public void calculateFertileArea(WorldMap map);
    public int getFertileField(Vector2d pos);
}
