package agh.oop.map;

public interface IDayCycle {
    void cleanCorpses();
    void moveAllAnimals();
    void consumePlants();
    void reproduce();
    void regrowPlants();
    default void cycle() {
        cleanCorpses();
        moveAllAnimals();
        consumePlants();
        reproduce();
        regrowPlants();
    }
}
