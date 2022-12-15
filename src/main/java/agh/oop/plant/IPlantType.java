package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.List;

public interface IPlantType {
    public List<Vector2d> calculateFertileArea(WorldMap map);
    public Vector2d getFertileField(WorldMap map);
}
