package org.example.lab2;


public class MountainBicycle extends Bicycle {




   public MountainBicycle(String color, String model, int wheelsCount, int speed, String rotating, String sound) {
       super(color, model, wheelsCount, speed, rotating, sound);
   }


   @Override
   public void riding() {
       System.out.println("Горный велосипед поднимается в гору");
   }
}
