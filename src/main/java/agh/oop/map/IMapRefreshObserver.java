package agh.oop.map;

import agh.oop.animal.IAnimalObserver;

public interface IMapRefreshObserver {
   public default void refresh(){
      System.out.println("alal");
   }
}
