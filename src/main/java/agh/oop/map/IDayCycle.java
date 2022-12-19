package agh.oop.map;

public interface IDayCycle {
    void cleanCorpses();

    void moveAllAnimals();

    void consumePlants();

    public void reproduce(int energyThreshold, int energyInheritedFromParent);

    void regrowPlants(int number);

    default void cycle(int energyThreshold, int energyInheritedFromParent, int numberOfPlants) {
        cleanCorpses();
        moveAllAnimals();
        consumePlants();
        reproduce(energyThreshold, energyInheritedFromParent);
        regrowPlants(numberOfPlants);
    }
}
