package agh.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;
    protected int energy = 0;

    public Vector2d getPosition() {
        return new Vector2d(position);
    }

    public void setPosition(Vector2d newLocation) {
        this.position = newLocation;
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }

    public int getEnergy() {
        return energy;
    }
}
