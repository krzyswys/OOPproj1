package agh.oop;

import agh.oop.GUI.App;
import agh.oop.animal.*;
import agh.oop.map.*;
import agh.oop.plant.IPlantType;
import agh.oop.plant.Plant;
import javafx.application.Application;

import java.util.List;

public class SimulationEngine implements Runnable {
    private MapSize mapSize;
    private IMapType mapType;
    private IPlantType plantType;
    int startingAnimals;
    int startingPlants;
    private final int energyNeededForReproduction = 30;
    private final int energyInheritedFromParent = 20;

    IGeneMutator geneMutator;
    INextGene nextGene;
    WorldMap map;
    public boolean go = false;
    MapVisualizer mapVisualizer;

    //https://stackoverflow.com/questions/25873769/launch-javafx-application-from-another-class
    public SimulationEngine(MapSize mapSize, IMapType mapType, IPlantType plantType, int startingAnimals, int startingPlants) {
        this.mapSize = mapSize;
        this.mapType = mapType;
        this.plantType = plantType;
        this.startingPlants = startingPlants;
        this.startingAnimals = startingAnimals;
        this.map = new WorldMap(mapSize, mapType, plantType);
        this.mapVisualizer = new MapVisualizer(map);
        map.createNAnimals(startingAnimals, 100, 5, 2, 2, new NextGeneNormal(), new MutatorRandom());
        map.createNPlants(startingPlants, 5);

    }

    public List<Plant> getPlants() {
        return map.getPlants();
    }

    public List<Animal> getAnimals() {
        return map.getAnimals();
    }

    public void gate() {
        go = !go;
    }

    public void run() {
        if (go) {

            List<Animal> animals = map.getAnimals();

            for (int i = 0; i < 10; i++) {
                animals = map.getAnimals();
                System.out.println(animals.get(0).stats());

                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    System.out.println(e);
                }
                map.cycle(energyNeededForReproduction, energyInheritedFromParent, startingAnimals);
                System.out.println(map.getAnimals().size());
                System.out.println(mapVisualizer.draw(
                        new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
            }
        }


    }
}
