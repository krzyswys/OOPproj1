package agh.oop.map;

import agh.oop.Vector2d;

public interface IMapType {
    ChangePosition newLocation(MapSize size, Vector2d location);
}
