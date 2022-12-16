package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;
import agh.oop.plant.IPlantType;
import agh.oop.plant.Plant;
import agh.oop.plant.Trees;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class WorldMap implements IWorldMap, IAnimalObserver {
    private MapVisualizer visualizer = new MapVisualizer(this);
    private List<Animal> animals = new ArrayList<>();
    private List<Plant> plants = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<>();
    IPlantType plantType;
    IMapType mapType;
    MapSize size;
    HashMap<Vector2d, Integer> deadAnimalsPerVector = new HashMap<Vector2d, Integer>();

     // simulation engine
    public WorldMap(MapSize size, IMapType mapType, IPlantType plantType) {
        this.plantType = plantType;
        this.mapType = mapType;
        this.size = size;
        for(int i=0; i< size.getWidth(); i++){
            for(int j=0; j< size.getHeight(); j++){
//                deadAnimalsPerVector.put(new Vector2d(i,j), ThreadLocalRandom.current().nextInt(0, 20));
                deadAnimalsPerVector.put(new Vector2d(i,j), 0);

            }
        }
    }

    private Vector2d generateRandomPosition() {
        return new Vector2d(ThreadLocalRandom.current().nextInt(0, size.getWidth()), ThreadLocalRandom.current().nextInt(0, size.getHeight()));
    }

    public List<Animal> getDeadAnimals() {
        return deadAnimals;
    }

    public List<Animal> getAnimals() {
        return animals;
    }

    public List<Plant> getPlants() {
        return plants;
    }

    public HashMap<Vector2d, Integer> getDeadAnimalsPerVector() {
        return deadAnimalsPerVector;
    }

    @Override
    public String toString() {
        return visualizer.draw(new Vector2d(0, 0), new Vector2d(size.getWidth(), size.getHeight()));
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

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public void createNAnimals(int amount) {
        for (int i = 0; i < amount; i++) {
            Vector2d position = generateRandomPosition();
            while (!(objectAt(position) instanceof Animal)) {
                position = generateRandomPosition();
                createAnimalAt(position);
            }
        }
    }

    public void createAnimalAt(Vector2d position) {
        Animal animal = new Animal(this, position);
        animals.add(animal);
    }

    public void createNPlants(int amount) {
        for (int i = 0; i < amount; i++) {
            Vector2d positon = plantType.getFertileField(this);
            if (positon != null){
                createPlantAt(positon);
            }
        }

    }

    public void createPlantAt(Vector2d position) {
        Plant plant = new Plant(position, 5, this.plantType);
        plants.add(plant);
    }
    public void removePlantAt(Vector2d position){
        for(Plant plant : plants){
            if(plant.getPosition().equals(position)){
                plants.remove(plant);
                break;
            }
        }
    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
                return true;
            }
        }
        for (Plant plant : plants) {
            if (position.equals(plant.getPosition())) {
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
        for (Plant plant : plants) {
            if (position.equals(plant.getPosition())) {
                return plant;
            }
        }
        return null;
    }

    @Override
    public MapSize getSize() {
        return size;
    }


    public int getAutosomalDominant() {
        int[] geneCount = {0, 0, 0, 0, 0, 0, 0, 0};
        for (Animal animal : animals) {
            geneCount[animal.getActiveGene()]++;
        }
        Arrays.sort(geneCount);
        return geneCount[7];
    }


    public long getAverageEnergy() {
        double energy = 0.0;
        for (Animal animal : animals) {
            energy += animal.getEnergy();
        }

        return Math.round(energy / animals.size());
    }


    public long getAverageLifespan() {
        double lifespan = 0.0;
        for (Animal animal : animals) {
            lifespan += animal.getEnergy();
        }

        return Math.round(lifespan / animals.size());

    }


    public ChangePosition newLocation(Vector2d location) {
        return mapType.newLocation(this.size, location);
    }


}
