package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import javax.swing.*;
import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.stream.Collectors;

public class Trees implements IPlantType {
    Set<Vector2d> positionsByDistance = new HashSet<>();
    public Trees() {

    }

    public void calculateFertileArea(WorldMap map) {
        int w = map.getSize().getWidth();
        int h = map.getSize().getHeight();
        int i = h / 2;
        int j = h / 2;
        int lines = (int) Math.ceil( h*0.1 );
        for( int l = 0 ; l < lines ; ++l ) {
            for (int k = 0; k < w / 2 + 1; k++) {
                positionsByDistance.add(new Vector2d(w / 2 + k, i));
                positionsByDistance.add(new Vector2d(w / 2 - k, i));
                positionsByDistance.add(new Vector2d(w / 2 + k, j));
                positionsByDistance.add(new Vector2d(w / 2 - k, j));
            }
            i++;
            j--;
        }
        System.out.println(positionsByDistance);

    }

    @Override
    public int getFertileField(Vector2d pos) {
        return positionsByDistance.contains(pos) ? 40 : 3;
    }
}
