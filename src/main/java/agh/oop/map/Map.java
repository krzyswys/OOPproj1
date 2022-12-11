package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;

public class Map implements IWorldMap, IAnimalObserver {
    MapSize size = new MapSize(10,10);
    @Override
    public void positionChanged(Animal animal) {
        Vector2d oldLocation = animal.getPosition();
        Vector2d newLocation = this.newLocation(animal.nextPosition()).newPosition;
    }
    @Override
    public void death(Animal animal) {
        System.out.println("dead");
    }

    public ChangePositionMessage newLocation(Vector2d location) {
        // TODO: move to intervaace
        return new ChangePositionMessage(
                new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                (location.getY() + size.getHeight()) % size.getHeight()),
                0);
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
