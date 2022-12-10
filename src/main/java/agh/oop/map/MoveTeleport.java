package agh.oop.map;

import agh.oop.Vector2d;
import agh.oop.animal.Animal;

public class MoveTeleport implements IMove{
    private int teleportCost;
    MoveTeleport(int teleportCost) {
        this.teleportCost = teleportCost;
    }

    @Override
    public void move(Animal animal, Map map) {
        map.positionChanged(animal);
        Vector2d newLocation = map.newLocation(animal.nextPosition());
        if( !newLocation.equals(animal.nextPosition())) {
            animal.removeEnergy(teleportCost);
        }
        animal.setPosition(newLocation);
    }
}
