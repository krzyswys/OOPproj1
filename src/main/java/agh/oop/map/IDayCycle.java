package agh.oop.map;

public interface IDayCycle {
    void cleanCorpses();

    void moveAllAnimals( int moveCost );

    void consumePlants();

    void reproduce(int energyThreshold, int energyInheritedFromParent);

    void regrowPlants(int amount, int number);

    default void cycle(int energyThreshold, int energyInheritedFromParent, int numberOfPlants, int energyPerPlant, int energyLostPerCycle, IMapRefreshObserver o) {
        cleanCorpses();
        moveAllAnimals(energyLostPerCycle);
        consumePlants();
        reproduce(energyThreshold, energyInheritedFromParent);
        regrowPlants(numberOfPlants, energyPerPlant);
        o.refresh();
    }
}
