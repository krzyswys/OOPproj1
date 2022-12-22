package agh.oop;

import javafx.scene.image.Image;

public abstract class AbstractMapElement {
    protected Vector2d position;
    protected int energy = 0;
    protected Image texture;

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
    public Image getTexture(){ //can be dane the same with plant and moved into AbstractMapElement
        return this.texture;
    }

}
