package agh.oop.animal;

public class MutatorRandom implements IGeneMutator {
    @Override
    public int mutateGene(int gene) {
        return Animal.generateGene();
    }
}
