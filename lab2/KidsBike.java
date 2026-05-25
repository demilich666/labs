package org.example.lab2;


public class KidsBike extends Bicycle{


   public KidsBike(String color, String model, int wheelsCount, int speed, String rotating, String sound) {
       super(color, model, wheelsCount, speed, rotating, sound);
   }


   @Override
   public void riding() {
       System.out.println("Детский велосипед медленно едет");
   }
}
