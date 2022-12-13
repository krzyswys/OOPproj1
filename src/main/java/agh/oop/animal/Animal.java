package agh.oop.animal;

import agh.oop.*;
import agh.oop.map.WorldMap;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement {
    private final List<Integer> genome = new ArrayList<>();
    private final List<IAnimalObserver> observerList = new ArrayList<>();
    private final WorldMap map;
    private final INextGene nextGeneGenerator;
    private final IGeneMutator geneMutator;
    private int activeGene = 0;
    private int timeAlive = 0;
    private int plantsEaten = 0;
    private int kids = 0;

    public Animal(WorldMap map, Vector2d position, int energy, int genomeLength, int mutationAmount,
                  INextGene nextGeneGenerator, IGeneMutator geneMutator) {
        this.map = map;
        addObserver(map);
        this.position = position;
        this.energy = energy;
        this.nextGeneGenerator = nextGeneGenerator;
        this.geneMutator = geneMutator;

        for (int i = 0; i < genomeLength; ++i) {
            genome.add(generateGene());
        }
    }

    public Animal(WorldMap map, Vector2d position) {
        this(map, position, 12, 5, 2, new NextGeneNormal(), new MutatorRandom());
    }

    protected Animal(int energy) {
        this(new Map(), new Vector2d(0, 0), energy, 10, 2, new NextGeneNormal(), new MutatorRandom());
    }

    public Animal(Animal parent1, Animal parent2, int energyFromParents) {
        this.map = parent1.map;
        this.position = parent1.position;
        this.observerList.addAll(parent1.observerList);
        this.nextGeneGenerator = parent1.nextGeneGenerator;
        this.geneMutator = parent1.geneMutator;



        //TODO:
        int totalEnergy = parent1.energy + parent2.energy;
        int genomeLength = parent1.genome.size();
        int a = Math.round(((float) parent1.energy / totalEnergy) * (genomeLength));
        if (ThreadLocalRandom.current().nextBoolean()) {
            this.genome.addAll(parent1.genome.subList(0, a));
            this.genome.addAll(parent2.genome.subList(a, genomeLength));
        } else {
            this.genome.addAll(parent2.genome.subList(0, genomeLength - a));
            this.genome.addAll(parent1.genome.subList(genomeLength - a, genomeLength));
        }

        this.energy = energyFromParents*2;
        parent1.removeEnergy(energyFromParents);
        parent2.removeEnergy(energyFromParents);
    }

    public void move() {
        timeAlive += 1;
        notifyObservers(ActionType.POSITION_CHANGED);
        var newLocation = map.newLocation(this.nextPosition());
        this.setPosition(newLocation.newPosition);
        this.removeEnergy(1 + newLocation.energyCost);
        activeGene = nextGeneGenerator.NextGene(activeGene, genome.size());
    }

    public void removeEnergy(int amount) {
        energy -= amount;
        if (energy <= 0) {
            notifyObservers(ActionType.DEATH);
        }
    }

    public void addEnergy(int amount) {
        energy += amount;
    }

    public int getActiveGene() {
        return activeGene;
    }

    public int getTimeAlive() {
        return timeAlive;
    }

    public int getPlantsEaten() {
        return plantsEaten;
    }

    public int getKids() {
        return kids;
    }

    public List<Integer> getGenome() {
        return new ArrayList<>(genome);
    }

    public Vector2d nextPosition() {
        return this.getPosition().add(MapDirection.fromNumber(genome.get(activeGene)).toUnitVector());
    }

    private void addObserver(IAnimalObserver o) {
        observerList.add(o);
    }

    private void removeObserver(IAnimalObserver o) {
        observerList.remove(o);
    }

    private void notifyObservers(ActionType actionType) {
        switch (actionType) {
            case POSITION_CHANGED -> observerList.forEach(x -> x.positionChanged(this));
            case DEATH -> observerList.forEach(x -> x.death(this));
        }
    }

    protected static int generateGene() {
        return ThreadLocalRandom.current().nextInt(0, 8);
    }

//    @Override
//    public String toString() {
//        return "Animal{" +
//                "position=" + position +
//                ", energy=" + energy +
//                ", genome=" + genome +
//                ", activeGene=" + activeGene +
//                ", timeAlive=" + timeAlive +
//                ", plantsEaten=" + plantsEaten +
//                ", kids=" + kids +
//                '}';
//    }


    public String info() {
        return "Animal{" +
                "position=" + position +
                ", energy=" + energy +
                ", genome=" + genome +
                ", activeGene=" + activeGene +
                ", timeAlive=" + timeAlive +
                ", plantsEaten=" + plantsEaten +
                ", kids=" + kids +
                '}';
    }
    @Override
    public String toString() {
        return String.valueOf(genome.get(activeGene));
    }
}
