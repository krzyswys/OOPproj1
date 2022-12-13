package agh.oop;

import agh.oop.animal.Animal;
import agh.oop.map.Map;
import agh.oop.map.MapVisualizer;

public class Main {
    public static void main(String[] args) {
        Map map = new Map();
        Animal a = new Animal(map, new Vector2d(4, 4));
        System.out.println(a);
        for (int i = 0; i < 10; ++i) {
            a.move();
            System.out.println(a);
        }
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        //adaed
        System.out.println(mapVisualizer.draw(
                new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));

    }
}