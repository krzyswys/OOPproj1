package agh.oop.map;

import agh.oop.animal.Animal;

public class MoveEarth implements IMove {

    @Override
    public void move(Animal animal, Map map) {
        animal.setPosition(map.newLocation(animal.nextPosition()));
    }
}
