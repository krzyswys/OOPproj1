package agh.oop.map;

import agh.oop.animal.Animal;

public class MoveEarth implements IMove {

    @Override
    public void move(Animal animal, MapSize mapSize) {
        animal.setLocation(animal.nextPosition());
        animal.removeEnergy(1);
    }
}
