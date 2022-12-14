package agh.oop.map;

import agh.oop.animal.IAnimalObserver;
import agh.oop.IWorldMap;
import agh.oop.Vector2d;
import agh.oop.animal.Animal;

import java.util.*;
import java.util.stream.Stream;

public class WorldMap implements IWorldMap, IAnimalObserver { //abstract
    MapSize size = new MapSize(10, 10);
    private MapVisualizer visualizer = new MapVisualizer(this);
    private List<Animal> animals = new ArrayList<>();
    private List<Integer> plants = new ArrayList<>();
    private List<Integer> fertileLocations = new ArrayList<>();
    private List<Animal> deadAnimals = new ArrayList<Animal>();


    HashMap<Vector2d, Integer> deadAnimalsPerVector = new HashMap<Vector2d, Integer>();

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
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
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
        for (Animal animal : animals) {
            if (position.equals(animal.getPosition())) {
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


    // TODO: (move to interface), or make this class abctract and only fertile area would go into specified map classes?
    public ChangePosition newLocation(Vector2d location) { // used for earth and hell
        return new ChangePosition(
                new Vector2d((location.getX() + size.getWidth()) % size.getWidth(),
                        (location.getY() + size.getHeight()) % size.getHeight()),
                0);
    }
    // TODO: move this to specified Plant class
    //https://www.geeksforgeeks.org/sorting-a-hashmap-according-to-values/
    // TODO: in sortByValue return list, currently works without it but uses unnecessary space and time

    public static HashMap<Vector2d, Integer> sortByValue(HashMap<Vector2d, Integer> hashmap) {
        List<Map.Entry<Vector2d, Integer>> list =
                new LinkedList<Map.Entry<Vector2d, Integer>>(hashmap.entrySet());

        Collections.sort(list, new Comparator<Map.Entry<Vector2d, Integer>>() {
            public int compare(Map.Entry<Vector2d, Integer> first,
                               Map.Entry<Vector2d, Integer> second) {
                return (first.getValue()).compareTo(second.getValue());
            }
        });

        HashMap<Vector2d, Integer> temp = new LinkedHashMap<Vector2d, Integer>();
        for (Map.Entry<Vector2d, Integer> element : list) {
            temp.put(element.getKey(), element.getValue());
        }
        return temp;
    }

    public List<Vector2d> calculateFertileAreaToxic() {
        List<Vector2d> positionsByDeath = new ArrayList<Vector2d>();
        HashMap<Vector2d, Integer> sortedMapOfPositions = sortByValue(deadAnimalsPerVector);
        for (Map.Entry<Vector2d, Integer> en : sortedMapOfPositions.entrySet()) {
            positionsByDeath.add(en.getKey());
        }
        return positionsByDeath;
    }

    public List<Vector2d> calculateFertileAreaTrees() {
        List<Vector2d> positionsByDistance = new ArrayList<Vector2d>();
        int w = size.getWidth();
        int h = size.getHeight();
        int i = h / 2;
        int j = h / 2;
        while (i < h && j >= 0) {
            for (int k = 0; k < w; k++) {
                // TODO: ?change it so it starts fromm middle?
                positionsByDistance.add(new Vector2d(k, i));
                positionsByDistance.add(new Vector2d(k, j));
            }
            i++;
            i--;
        }
        return positionsByDistance;

    }


}
