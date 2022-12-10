package agh.oop;

public abstract class AbstractMapElement {
    protected Vector2d location;
    protected int energy = 0;

    public Vector2d getLocation() {
        return new Vector2d(location);
    }

    public void setLocation(Vector2d newLocation) {
        this.location = newLocation;
    }

    boolean isAt(Vector2d position) {
        return this.location.equals(position);
    }
}
