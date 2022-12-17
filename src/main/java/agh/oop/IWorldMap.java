package agh.oop;

import agh.oop.map.MapSize;

public interface IWorldMap {
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);
    MapSize getSize();
}
