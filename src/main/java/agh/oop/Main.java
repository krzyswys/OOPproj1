package agh.oop;

import agh.oop.animal.Animal;
import agh.oop.map.Earth;
import agh.oop.map.MapSize;
import agh.oop.map.WorldMap;
import agh.oop.map.MapVisualizer;
import agh.oop.plant.Toxic;
import agh.oop.plant.Trees;

public class Main {
    public static void main(String[] args) {
        WorldMap map = new WorldMap(new MapSize(15,15), new Earth(), new Toxic());
        Animal a = new Animal(map, new Vector2d(4, 4));
        map.createNAnimals(10);
        System.out.println(a);
        for (int i = 0; i < 10; ++i) {
            a.move();
            System.out.println(a.info());
        }
        MapVisualizer mapVisualizer = new MapVisualizer(map);
        map.createNPlants(90);
        map.addAnimal(a);
        System.out.println(mapVisualizer.draw(
                new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
        a.move();
        System.out.println(a.info());
        System.out.println(mapVisualizer.draw(
                new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
        a.move();
        System.out.println(a.info());
        System.out.println(mapVisualizer.draw(
                new Vector2d(0, 0), new Vector2d(map.getSize().getHeight(), map.getSize().getWidth())));
    }
}