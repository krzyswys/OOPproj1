package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Toxic implements IPlantType {

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
        HashMap<Vector2d, Integer> x =map.getDeadAnimalsPerVector();
        System.out.println(x.size());
            HashMap<Vector2d, Integer> sortedMapOfPositions = sortByValue(x);
            for (Map.Entry<Vector2d, Integer> en : sortedMapOfPositions.entrySet()) {
                positionsByDeath.add(en.getKey());
            }
            System.out.println(positionsByDeath.size());
            return positionsByDeath;
    }

    @Override
    public Vector2d getFertileField(WorldMap map) { //should be moved into absract clas or pulled up into map
        List<Vector2d> orderedFields = calculateFertileArea(map);
        int n = orderedFields.size();
        int n1 = (int) (orderedFields.size() * 0.2);
        List<Vector2d> preferedFertileArea = new ArrayList<>(orderedFields.stream().limit(n1).toList());

        int n2 = (int) (orderedFields.size() * 0.8);
        List<Vector2d> unPreferedFertileArea = orderedFields.subList(Math.max(n - n2, 0), n);


        int[] randompic = new int[]{0, 0, 0, 0, 1}; // 0 - prefered, 1 - unpreferd
        int decison = randompic[ThreadLocalRandom.current().nextInt(0, 5)]; // is it with clousure?
        if (decison == 0) {
            int i = 0;
            Vector2d position = preferedFertileArea.get(0);
            while (map.objectAt(position) instanceof Plant && i < n1) {
                int l = ThreadLocalRandom.current().nextInt(0, n1-i);
                position = preferedFertileArea.get(l);
                preferedFertileArea.remove(l);
                i++; //what to do when there is no more places for plants
            }
            return position;
        } else if (decison == 1) {
            int i = 0;
            Vector2d position = unPreferedFertileArea.get(i);
            while (map.objectAt(position) instanceof Plant && i < n1) {
                position = unPreferedFertileArea.get(i);
                i++;
            }
            return position;
        }
        return null;
    }
}
