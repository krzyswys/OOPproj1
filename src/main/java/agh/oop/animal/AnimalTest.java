package agh.oop.animal;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {
    @Test
    void reproduce() {
        Animal parent1 = new Animal(10);
        Animal parent2 = new Animal(100);
        Animal kid = new Animal(parent1,parent2,5);

        assertTrue( checkGenome(kid.getGenome(),parent1.getGenome(),parent2.getGenome(),1) );

        System.out.println(parent1);
        System.out.println(parent2);
        System.out.println(kid);
    }

    @Test
    void reproduceEqualEnergy() {
        Animal parent1 = new Animal(50);
        Animal parent2 = new Animal(50);
        Animal kid = new Animal(parent1,parent2,20);

        System.out.println(parent1);
        System.out.println(parent2);
        System.out.println(kid);
    }

    boolean checkGenome(List<Integer> genomeResult, List<Integer> genome1, List<Integer> genome2, int division) {
        var r1 = genome1.subList(0,division);
        r1.addAll(genome2.subList(division,genome2.size()));
        var r2 = genome2.subList(0,genome2.size()-division);
        r2.addAll(genome1.subList(genome2.size()-division,genome2.size()));
        return genomeResult.equals( r1 ) || genomeResult.equals(r2);
    }
}