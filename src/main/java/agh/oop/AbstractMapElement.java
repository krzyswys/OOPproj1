package agh.oop;

public abstract class AbstractMapElement {
    protected Vector2d location;
    protected int energy;

    public Vector2d getLocation() {
        return new Vector2d(location);
    }

    boolean isAt(Vector2d position) {
        return this.location.equals(position);
    }
}
