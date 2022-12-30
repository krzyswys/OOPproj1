package agh.oop.map;

public interface IDayCycle {
    void cleanCorpses();

    void moveAllAnimals();

    void consumePlants();

    void reproduce(int energyThreshold, int energyInheritedFromParent);

    void regrowPlants(int number);

    default void cycle(int energyThreshold, int energyInheritedFromParent, int numberOfPlants, IMapRefreshObserver o) {
        cleanCorpses();
        moveAllAnimals();
        consumePlants();
        reproduce(energyThreshold, energyInheritedFromParent);
        //TODO:
        regrowPlants(20);
        o.refresh();
    }
}
