package agh.oop.animal;

public class NextGeneNormal implements INextGene {
    @Override
    public int NextGene(int currentGene, int genomeLength) {
        return (currentGene + 1) % genomeLength;
    }
}
