package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.animal.Animal;
import agh.oop.map.MapSize;

public interface IMove {
    void move(Animal animal, MapSize mapSize);
}
