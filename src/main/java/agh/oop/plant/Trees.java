package agh.oop.plant;

import agh.oop.Vector2d;
import agh.oop.map.WorldMap;

import java.util.ArrayList;
import java.util.List;

public class Trees implements IPlantType{
    @Override
    public List<Vector2d> calculateFertileArea(WorldMap map) {
        List<Vector2d> positionsByDistance = new ArrayList<Vector2d>();
        int w = map.getSize().getWidth();
        int h =  map.getSize().getHeight();
        int i = h / 2;
        int j = h / 2;
        while (i < h && j >= 0) {
            for (int k = 0; k < w; k++) {
                // TODO: ?change it so it starts fromm middle?
                positionsByDistance.add(new Vector2d(k, i));
                positionsByDistance.add(new Vector2d(k, j));
            }
            i++;
            i--;
        }
        return positionsByDistance;

    }
}
