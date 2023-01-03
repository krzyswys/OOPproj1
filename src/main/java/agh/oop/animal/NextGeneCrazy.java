package agh.oop.animal;

import java.util.concurrent.ThreadLocalRandom;

public class NextGeneCrazy implements INextGene{
    @Override
    public int NextGene(int currentGene, int genomeLength) {
        if(ThreadLocalRandom.current().nextInt(100) < 80 ){
            return (currentGene + 1) % genomeLength;
        } else {
            return ThreadLocalRandom.current().nextInt(genomeLength);
        }
    }
}
