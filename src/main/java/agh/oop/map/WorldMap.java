package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;

import java.util.*;

public  class WorldMap implements IWorldMap, IAnimalObserver { //abstract
    MapSize size = new MapSize(10,10);
    private MapVisualizer visualizer = new MapVisualizer(this);
    private  List<Animal> animals = new ArrayList<>();
    private  List<Integer> plants = new ArrayList<>();
    private  List<Integer> fertileLocations = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<Animal>();


    Map<Vector2d, Integer> deadAnimalsPerVector = new HashMap<Vector2d,Integer>();
    @Override
    public void positionChanged(Animal animal) {
        Vector2d oldLocation = animal.getPosition();
        Vector2d newLocation = this.newLocation(animal.nextPosition()).newPosition;
    }
    @Override
    public void death(Animal animal) {
        deadAnimals.add(animal);
        if(deadAnimalsPerVector.containsValue(animal.getPosition())){
            Integer num = deadAnimalsPerVector.get(animal.getPosition());
            deadAnimalsPerVector.put(animal.getPosition(), num+1);
        }
        else {
            deadAnimalsPerVector.put(animal.getPosition(), 1);

        }
        //deaths per sqare
        animals.remove(animal);
    }



    @Override
    public void createAnimalAt(Vector2d position) {
        Animal animal = new Animal(this, position);
        animals.add(animal);
    }
//    @Override
//    public void createGrassAt(Vector2d position) {
//        Plant plant = new Plant(this, position);
//        plants.add(plant);
//    }

    @Override
    public boolean isOccupied(Vector2d position) {
        for (Animal animal : animals){
            if(position.equals(animal.getPosition())){
                return true;
            }
        }
//        for (Plant plant : plants){
//            if(position.equals(plant.getPosition())){
//                return true;
//            }
//        }
        return false;
    }

    @Override
    public Object objectAt(Vector2d position) {
        for (Animal animal : animals){
            if(position.equals(animal.getPosition())){
                return animal;
            }
        }
//        for (Plant plant : plants){
//            if(position.equals(plant.getPosition())){
//                return plant;
//            }
//        }
        return null;
    }

    @Override
    public MapSize getSize() {
        return size;
    }

    @Override
    public int getAutosomalDominant() {
        int[] geneCount = {0,0,0,0,0,0,0,0};
        for (Animal animal : animals){
           geneCount[animal.getActiveGene()]++;
        }
        Arrays.sort(geneCount);
        return geneCount[7];
    }

    @Override
    public long getAverageEnergy() {
        double energy =0.0;
        for (Animal animal : animals){
            energy+=animal.getEnergy();
        }

        return  Math.round(energy/animals.size());
    }

    @Override
    public long getAverageLifespan() {
        double lifespan =0.0;
        for (Animal animal : animals){
            lifespan+=animal.getEnergy();
        }

        return  Math.round(lifespan/animals.size());

    }


    // TODO: move to interface
    public ChangePosition newLocation(Vector2d location) {
        return new ChangePosition(
                new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                        (location.getY() + size.getHeight()) % size.getHeight()),
                0);
    }

//    public List<Integer> calculateFertileAreaToxic(){
//
//    }
}
