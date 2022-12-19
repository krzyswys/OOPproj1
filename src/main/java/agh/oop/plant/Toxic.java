package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;

public class Toxic implements IPlantType {

    @Override
    public List<Vector2d> calculateFertileArea(WorldMap map) {
        HashMap<Vector2d, Integer> deathPerVector = map.getdeadAnimalsPerPosition();
        HashMap<Integer, List<Vector2d>> vectorsPerDeaths = new HashMap<Integer, List<Vector2d>>();

        for (Map.Entry<Vector2d, Integer> entry : deathPerVector.entrySet()) {
            if (vectorsPerDeaths.get(entry.getValue()) == null) {
                List<Vector2d> tmp = new ArrayList<Vector2d>();
                tmp.add(entry.getKey());
                vectorsPerDeaths.put(entry.getValue(), tmp);
            } else {
                List<Vector2d> tmp = vectorsPerDeaths.get(entry.getValue());
                tmp.add(entry.getKey());
                vectorsPerDeaths.put(entry.getValue(), tmp);
            }

        }
        TreeMap<Integer, List<Vector2d>> sortedVectorPerDeaths = new TreeMap<>(vectorsPerDeaths);
        NavigableMap<Integer, List<Vector2d>> rSortedVectorPerDeaths = sortedVectorPerDeaths.descendingMap();
        List<Vector2d> orderedFertileArea = new ArrayList<>();
        for (Map.Entry<Integer, List<Vector2d>> entry : rSortedVectorPerDeaths.entrySet()) {
            List<Vector2d> tmp = entry.getValue();
            Collections.shuffle(tmp);
            orderedFertileArea.addAll(tmp);
        }
        return orderedFertileArea;
    }

    @Override
    public Vector2d getFertileField(WorldMap map) {
        List<Vector2d> orderedFields = calculateFertileArea(map);
        int n = orderedFields.size();
        int n1 = (int) (orderedFields.size() * 0.2);
        List<Vector2d> preferedFertileArea = new ArrayList<>(orderedFields.stream().limit(n1).toList());

        int n2 = (int) (orderedFields.size() * 0.8);


        int decison = ThreadLocalRandom.current().nextInt(0, 6) == 5 ? 1 : 0;
        if (decison == 0) {
            int i = 0;
            Vector2d position = preferedFertileArea.get(0);
            while (map.objectAt(position) instanceof Plant && i < n1) {
                position = preferedFertileArea.get(i);
                i++;
            }
            if (map.objectAt(position) instanceof Plant) {
                i = 0;
                int x = ThreadLocalRandom.current().nextInt(0, map.getSize().getWidth());
                int y = ThreadLocalRandom.current().nextInt(0, map.getSize().getHeight());
                position = new Vector2d(x, y);
                while (!(preferedFertileArea.contains(position)) && map.objectAt(position) instanceof Plant && i < n2) {
                    x = ThreadLocalRandom.current().nextInt(0, map.getSize().getWidth());
                    y = ThreadLocalRandom.current().nextInt(0, map.getSize().getHeight());
                    position = new Vector2d(x, y);
                    i++;
                }
            }
            if (map.objectAt(position) instanceof Plant) {
                return null;
            }

            return position;
        } else {
            int i = 0;
            int x = ThreadLocalRandom.current().nextInt(0, map.getSize().getWidth());
            int y = ThreadLocalRandom.current().nextInt(0, map.getSize().getHeight());
            Vector2d position = new Vector2d(x, y);
            while (!(preferedFertileArea.contains(position)) && map.objectAt(position) instanceof Plant && i < n2) {
                x = ThreadLocalRandom.current().nextInt(0, map.getSize().getWidth());
                y = ThreadLocalRandom.current().nextInt(0, map.getSize().getHeight());
                position = new Vector2d(x, y);
                i++;
            }
            if (map.objectAt(position) instanceof Plant) {
                i = 0;
                position = preferedFertileArea.get(0);
                while (map.objectAt(position) instanceof Plant && i < n1) {
                    position = preferedFertileArea.get(i);
                    i++;
                }}
                if (map.objectAt(position) instanceof Plant) {
                    return null;
                }
                return position;
            }

        }
    }

