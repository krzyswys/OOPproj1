package agh.oop;

import agh.oop.map.MapSize;

public interface IWorldMap {

    void createAnimalAt(Vector2d position);
    boolean isOccupied(Vector2d position);
    Object objectAt(Vector2d position);
    MapSize getSize();

    int getAutosomalDominant();
    long getAverageEnergy();
    long getAverageLifespan();

}
