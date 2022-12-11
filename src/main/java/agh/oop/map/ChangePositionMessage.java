package agh.oop.map;

import agh.oop.Vector2d;

public class ChangePositionMessage {
    public Vector2d newPosition;
    public int energyCost;

    public ChangePositionMessage(Vector2d newPosition, int energyCost) {
        this.newPosition = newPosition;
        this.energyCost = energyCost;
    }
}
