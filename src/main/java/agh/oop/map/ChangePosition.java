package agh.oop.map;

import agh.oop.Vector2d;

public class ChangePosition {
    public Vector2d newPosition;
    public int energyCost;

    public ChangePosition(Vector2d newPosition, int energyCost) {
        this.newPosition = newPosition;
        this.energyCost = energyCost;
    }
}
