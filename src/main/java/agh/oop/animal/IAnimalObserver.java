package agh.oop.animal;

import agh.oop.animal.Animal;

public interface IAnimalObserver {
    void positionChanged(Animal animal);

    void death(Animal animal);
}
