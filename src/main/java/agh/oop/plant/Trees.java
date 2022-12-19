package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Trees implements IPlantType {
    @Override
    public List<Vector2d> calculateFertileArea(WorldMap map) {
        List<Vector2d> positionsByDistance = new ArrayList<Vector2d>();
        int w = map.getSize().getWidth();
        int h = map.getSize().getHeight();
        int i = h / 2;
        int j = h / 2;
        while (i < h && j >= 0) {
            for (int k = 0; k < w / 2 + 1; k++) {
                positionsByDistance.add(new Vector2d(w / 2 + k, i));
                positionsByDistance.add(new Vector2d(w / 2 - k, i));
                positionsByDistance.add(new Vector2d(w / 2 + k, j));
                positionsByDistance.add(new Vector2d(w / 2 - k, j));
            }
            i++;
            j--;
        }
        return positionsByDistance;

    }

    @Override
    public Vector2d getFertileField(WorldMap map) {
        List<Vector2d> orderedFields = calculateFertileArea(map);
        int n = orderedFields.size();
        int n1 = (int) (orderedFields.size() * 0.2) + 1;
        List<Vector2d> preferedFertileArea = orderedFields.stream().limit(n1).toList();

        int n2 = (int) (orderedFields.size() * 0.8);
        List<Vector2d> unPreferedFertileArea = orderedFields.subList(Math.max(n - n2, 0), n);


        int decison = ThreadLocalRandom.current().nextInt(0, 6) == 5 ? 1 : 0;
        if (decison == 0) { //could be shorten in future
            int i = 0;
            int j = 0;

            Vector2d position = preferedFertileArea.get(i);
            while (map.objectAt(position) instanceof Plant && i < n1) {
                position = preferedFertileArea.get(i);
                i++;
            }
            while (map.objectAt(position) instanceof Plant && j < n2) {
                position = unPreferedFertileArea.get(j);
                j++;
            }

            if( map.objectAt(position) instanceof Plant) {
                System.out.println("zajete");
                return null;
            }

        return position;
    }
        else

    {
        int i = 0;
        int j = 0;

        Vector2d position = unPreferedFertileArea.get(i);
        while (map.objectAt(position) instanceof Plant && i < n2) {
            position = unPreferedFertileArea.get(i);
            i++;
        }
        while (map.objectAt(position) instanceof Plant && j < n1) {
                position = preferedFertileArea.get(j);
                j++;
            }
        if (map.objectAt(position) instanceof Plant) {
            return null;
        }
        return position;
    }

}
}
