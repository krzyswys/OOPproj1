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
    private int startingAnimals;
    private int energyLostPerCycle;
    private final int energyFromGrass;
    private final int energyNeededForReproduction;
    private final int energyInheritedFromParent = 20;

    //TODO: make it as a variable when setting up engine
    private IGeneMutator geneMutator;
    private INextGene nextGene;
    public WorldMap map;
    int i=0;
    public boolean go = false;
    MapVisualizer mapVisualizer;
    private IMapRefreshObserver observer;

    public SimulationEngine(MapSize mapSize,IGeneMutator gene,  INextGene next, IMapType mapType, IPlantType plantType, int startingAnimals, int startingPlants, int animalStartEnergy, int energyFromGrass, int energyToReproduce, int grasPerCycle, int energyLostPerCycle) {
        this.mapSize = mapSize;
        this.mapType = mapType;
        this.geneMutator = gene;
        this.nextGene = next;
        this.plantType = plantType;
        this.startingAnimals = startingAnimals;
        this.energyNeededForReproduction = energyToReproduce;
        this.energyLostPerCycle = energyLostPerCycle;
        this.map = new WorldMap(mapSize, mapType, plantType);
        this.mapVisualizer = new MapVisualizer(map);
        this.energyFromGrass = energyFromGrass;
        map.createNAnimals(startingAnimals, animalStartEnergy, 5, 2, 2, nextGene, geneMutator);
        map.createNPlants(startingPlants,energyFromGrass);
        run();

    }

    public void addObserverMap(IMapRefreshObserver o) {
        observer = o;
    }

    public List<Plant> getplants() {
        return map.getPlants();
    }

    public List<Animal> getanimals() {
        return map.getAnimals();
    }

    public void gate() {
        go = !go;
    }

//    public void start(){
//
//
//    }
//    public void stop(){
//            Thread.sus
//
//    }
    public void run() {
        System.out.println("lmoa");
        System.out.println(go);
        try {
            Thread.sleep(500);
            System.out.println("tun");




        } catch (InterruptedException e) {
            System.out.println(e);
        }
        while (go && map.getAnimals().size() > 0) {
            System.out.println(go);

            try {
                Thread.sleep(500);
                //FIXME: add grass growth per cycle to cycle and energyLostPerCycle
                System.out.println(i);
                i++;
                map.cycle(energyNeededForReproduction, energyInheritedFromParent,10, energyFromGrass, energyLostPerCycle, observer);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
        }
    }


}
