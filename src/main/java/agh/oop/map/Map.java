package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;

public class Map implements IWorldMap, IAnimalObserver {
    MapSize size = new MapSize(10,10);
    private final IMove guide = new MoveEarth();
    @Override
    public void positionChanged(Animal animal) {

    }
    @Override
    public void death(Animal animal) {

    }

    @Override
    public void move(Animal animal) {
        guide.move(animal, this.getSize());
    }

    @Override
    public boolean place(Animal animal) {
        return false;
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        return null;
    }

    @Override
    public MapSize getSize() {
        return size;
    }
}
