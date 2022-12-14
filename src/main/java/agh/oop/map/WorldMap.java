package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;
import agh.oop.plant.IPlantType;
import agh.oop.plant.Plant;
import agh.oop.plant.Trees;

import java.util.*;

public abstract class WorldMap implements IWorldMap, IAnimalObserver {
    private MapVisualizer visualizer = new MapVisualizer(this);
    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();
    IPlantType plantType;
    MapSize size;

    HashMap<Vector2d, Integer> deadAnimalsPerVector = new HashMap<Vector2d, Integer>();
    public List<Animal> getDeadAnimals(){
        return deadAnimals;
    }
    public List<Animal> getAnimals(){
        return animals;
    }
    public List<Plant> getPlants(){
        return plants;
    }
    public HashMap<Vector2d, Integer> getDeadAnimalsPerVector(){
        return deadAnimalsPerVector;
    }
    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0,0), new Vector2d(size.getWidth(), size.getHeight()));
    }

    @Override
    public void positionChanged(Animal animal) {
        Vector2d oldLocation = animal.getPosition();
        Vector2d newLocation = this.newLocation(animal.nextPosition()).newPosition;
    }

    @Override
    public void death(Animal animal) {
        deadAnimals.add(animal);
        if (deadAnimalsPerVector.containsValue(animal.getPosition())) {
            Integer num = deadAnimalsPerVector.get(animal.getPosition());
            deadAnimalsPerVector.put(animal.getPosition(), num + 1);
        } else {
            deadAnimalsPerVector.put(animal.getPosition(), 1);

        }
        animals.remove(animal);
    }


    @Override
    public void createAnimalAt(Vector2d position) {
        Animal animal = new Animal(this, position);
        animals.add(animal);
    }
    @Override
    public void createPlantAt(Vector2d position) {
        Plant plant = new Plant(this, position, 5, plantType);
        plants.add(plant);
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
                return true;
            }
        }
        for (Plant plant : plants){
            if(position.equals(plant.getPosition())){
                return true;
            }
        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
                return animal;
            }
        }
        for (Plant plant : plants){
            if(position.equals(plant.getPosition())){
                return plant;
            }
        }
        return null;
    }

    @Override
    public MapSize getSize() {
        return size;
    }

    @Override
    public int getAutosomalDominant() {
        int[] geneCount = {0, 0, 0, 0, 0, 0, 0, 0};
        for (Animal animal : animals) {
            geneCount[animal.getActiveGene()]++;
        }
        Arrays.sort(geneCount);
        return geneCount[7];
    }

    @Override
    public long getAverageEnergy() {
        double energy = 0.0;
        for (Animal animal : animals) {
            energy += animal.getEnergy();
        }

        return Math.round(energy / animals.size());
    }

    @Override
    public long getAverageLifespan() {
        double lifespan = 0.0;
        for (Animal animal : animals) {
            lifespan += animal.getEnergy();
        }

        return Math.round(lifespan / animals.size());

    }


    public abstract ChangePosition newLocation(Vector2d location) ;




}
