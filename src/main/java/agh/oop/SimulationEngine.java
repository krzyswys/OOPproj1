package agh.oop;

import agh.oop.animal.*;
import agh.oop.map.*;
import agh.oop.plant.IPlantType;

import java.util.List;

public class SimulationEngine {
    private MapSize mapSize;
    private  IMapType mapType;
    private IPlantType plantType;
    int startingAnimals;
    int startingPlants;
    private final int energyNeededForReproduction = 30;
    private final int energyInheritedFromParent = 20;

    IGeneMutator geneMutator;
    INextGene nextGene;
    WorldMap map;
    MapVisualizer mapVisualizer;

    public SimulationEngine(MapSize mapSize, IMapType mapType, IPlantType plantType, int startingAnimals, int startingPlants) {
        this.mapSize = mapSize;
        this.mapType = mapType;
        this.plantType = plantType;
        this.startingPlants = startingPlants;
        this.startingAnimals = startingAnimals;
        this.map = new WorldMap(mapSize, mapType, plantType);
        this.mapVisualizer = new MapVisualizer(map);

    }

    public void run() {

        map.createNAnimals(startingAnimals, 100, 5, 2, 2, new NextGeneNormal(), new MutatorRandom());
        map.createNPlants(startingPlants, 5);
        List<Animal> animals = map.getAnimals();
        for(int i=0; i<10; i++){
            System.out.println(animals.get(0).stats());
            map.cycle(energyNeededForReproduction,energyInheritedFromParent,startingAnimals);
            System.out.println(map.getAnimals().size());
            System.out.println(mapVisualizer.draw(
                    new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
        }
        System.out.println(map.getTopGeneFromAllGenomes());
        System.out.println(animals.get(0).stats());


    }
}
