package org.example.lab2;

public class BMX extends Bicycle{

  
   public BMX(String color, String model, int wheelsCount, int speed, String rotating, String sound) {
       super(color, model, wheelsCount, speed, rotating, sound);
   }
  
 @Override
   public void riding() {
       System.out.println("BMX делает трюки");
   }


   @Override
   public void makeSound() {
       System.out.println("BMX не имеет звонка");


   }
}


