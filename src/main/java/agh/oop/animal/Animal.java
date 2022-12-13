package agh.oop.animal;

import agh.oop.*;
import agh.oop.map.Map;

import java.util.concurrent.ThreadLocalRandom;

import java.util.ArrayList;
import java.util.List;

public class Animal extends AbstractMapElement {
    private final List<Integer> genome = new ArrayList<>();
    private final List<IAnimalObserver> observerList = new ArrayList<>();
    private final Map map;
    private final INextGene nextGeneGenerator;
    private final IGeneMutator geneMutator;
    private int activeGene = 0;
    private int timeAlive = 0;
    private int plantsEaten = 0;
    private int kids = 0;

    public Animal(Map map, Vector2d position, int energy, int genomeLength, int mutationAmount,
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

    public Animal(Map map, Vector2d position) {
        this(map, position, 10, 5, 2, new NextGeneNormal(), new MutatorRandom());
    }

    public Animal(Animal parent1, Animal parent2) {
        this.map = parent1.map;
        this.observerList.addAll(parent1.observerList);
        this.nextGeneGenerator = parent1.nextGeneGenerator;
        this.geneMutator = parent1.geneMutator;

        //TODO:
        this.genome.addAll(parent1.genome);
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
            //czy tu nie powinno się ubjać zwierzaka?
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

    @Override
    public String toString() {
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
//    @Override
//    public String toString() {
//        return String.valueOf(genome.get(activeGene));
//    }
}
