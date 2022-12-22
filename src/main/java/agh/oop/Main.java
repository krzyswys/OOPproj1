package agh.oop;

import agh.oop.GUI.App;
import agh.oop.animal.Animal;
import agh.oop.map.Earth;
import agh.oop.map.MapSize;

import agh.oop.plant.Trees;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
        System.out.println("system has started\n");

        Application.launch(App.class);


        System.out.println("\nsystem has stopped");
    }
}