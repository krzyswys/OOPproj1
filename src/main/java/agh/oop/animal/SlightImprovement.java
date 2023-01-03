package agh.oop.animal;

import java.util.concurrent.ThreadLocalRandom;

public class SlightImprovement implements IGeneMutator{
    @Override
    public int mutateGene(int gene) {
        return ThreadLocalRandom.current().nextBoolean() ? gene - 1 : gene + 1 ;
    }
}
