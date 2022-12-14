package agh.oop.plant;

import agh.oop.AbstractMapElement;
import agh.oop.Vector2d;
import agh.oop.animal.IAnimalObserver;
import agh.oop.animal.IGeneMutator;
import agh.oop.animal.INextGene;
import agh.oop.map.WorldMap;


import java.util.ArrayList;
import java.util.List;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Plant extends AbstractMapElement {
    public final WorldMap map;
    private final int energy;
    private final Vector2d position;

    private final IPlantType fertileAreaAction;

    public Plant(WorldMap map, Vector2d position, int energy, IPlantType planttype) {
        this.fertileAreaAction = planttype;
        this.map = map;
        this.energy = energy;
        this.position = placePlant(position);
    }
   private Vector2d placePlant(Vector2d position){
        List<Vector2d> preffered = new ArrayList<>();
       List<Vector2d> unpreffered = new ArrayList<>();
       for (int i=0; i<fertileAreaAction.calculateFertileArea(map).size()*0.2; i++){
           preffered.add(fertileAreaAction.calculateFertileArea(map).get(i));
       }
       for (int i = (int) (fertileAreaAction.calculateFertileArea(map).size()*0.2); i<fertileAreaAction.calculateFertileArea(map).size(); i++){
           unpreffered.add(fertileAreaAction.calculateFertileArea(map).get(i));
       }
       //TODO: create option to choose 80% of time preffered array, 20% of time unpreffered array;
//       List[] rand = new List[]{preffered, preffered, preffered, preffered, unpreffered};
       List<Vector2d> list= preffered;
       Vector2d field = list.get(ThreadLocalRandom.current().nextInt(list.size()) % list.size());
       return field;
    }
    @Override
    public String toString() {
        return "*";
    }






}
