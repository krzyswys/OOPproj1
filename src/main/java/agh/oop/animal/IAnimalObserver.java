package agh.oop.animal;

public interface IAnimalObserver {
    void positionChanged(Animal animal);
    void death(Animal animal);
}
