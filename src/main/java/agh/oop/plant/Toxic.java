package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.*;

public class Toxic implements IPlantType{

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
    @Override
    public List<Vector2d> calculateFertileArea(WorldMap map) {
        List<Vector2d> positionsByDeath = new ArrayList<Vector2d>();
        HashMap<Vector2d, Integer> sortedMapOfPositions = sortByValue(map.getDeadAnimalsPerVector());
        for (Map.Entry<Vector2d, Integer> en : sortedMapOfPositions.entrySet()) {
            positionsByDeath.add(en.getKey());
        }
        return positionsByDeath;
    }
}
