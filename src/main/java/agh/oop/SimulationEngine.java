package agh.oop;

import agh.oop.animal.Animal;
import agh.oop.animal.IGeneMutator;
import agh.oop.animal.INextGene;
import agh.oop.map.*;
import agh.oop.plant.IPlantType;

import java.util.List;

public class SimulationEngine {
    MapSize mapSize;
    IMapType mapType;
    IPlantType plantType;
    int animalsToStart;
    int plantsToStart;

    IGeneMutator geneMutator;
    INextGene nextGene;
    WorldMap map;
    MapVisualizer mapVisualizer;

    public SimulationEngine(MapSize mapSize, IMapType mapType, IPlantType plantType, int animalsToStart, int plantsToStart) {
        this.mapSize = mapSize;
        this.mapType = mapType;
        this.plantType = plantType;
        this.plantsToStart = plantsToStart;
        this.animalsToStart = animalsToStart;
        this.map = new WorldMap(mapSize, mapType, plantType);
        this.mapVisualizer = new MapVisualizer(map);

    }

    public void run() {
        map.createNAnimals(animalsToStart);
        map.createNPlants(plantsToStart);
        List<Animal> animals = map.getAnimals();
        for(int i=0; i<15; i++){
            map.cycle();
            System.out.println(mapVisualizer.draw(
                    new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
        }
        System.out.println(map.getTopGeneFromAllGenomes());
        System.out.println(animals.get(0).stats());


    }
}
