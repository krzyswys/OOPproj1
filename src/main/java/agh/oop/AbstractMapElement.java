package agh.oop;

public abstract class AbstractMapElement {
    protected Vector2d position;
    protected int energy;

    public Vector2d getPosition() {
        return new Vector2d(position);
    }

    boolean isAt(Vector2d position) {
        return this.position.equals(position);
    }
}
