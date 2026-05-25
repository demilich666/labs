package org.example.lab2;


public abstract class Bicycle {


   protected String color;
   protected String model;
   protected int wheelsCount;
   protected int speed;
   protected String sound;
   protected String rotating;


   public static int bicycleCount = 0;






   public Bicycle(String color, String model, int wheelsCount, int speed, String sound, String rotating) {
       this.color = color;
       this.model = model;
       this.wheelsCount = wheelsCount;
       this.speed = speed;
       this.sound = sound;
       this.rotating = rotating;
       bicycleCount += 1;
   }


   public Bicycle(int speed, String color, int wheelsCount) {
       this.speed = speed;
       this.color = color;
       this.wheelsCount = wheelsCount;
       bicycleCount += 1;
   }


   public Bicycle() {
       bicycleCount += 1;
   }


   public String getColor() {
       return color;
   }


   public void setColor(String color) {
       this.color = color;
   }


   public String getModel() {
       return model;
   }


   public void setModel(String model) {
       this.model = model;
   }


   public int getWheelsCount() {
       return wheelsCount;
   }


   public void setWheelsCount(int wheelsCount) {
       this.wheelsCount = wheelsCount;
   }


   public int getSpeed() {
       return speed;
   }


   public void setSpeed(int speed) {
       this.speed = speed;
   }


   public String getSound() {
       return sound;
   }


   public void setSound(String sound) {
       this.sound = sound;
   }


   public String getRotating() {
       return rotating;
   }


   public void setRotating(String rotating) {
       this.rotating = rotating;
   }


   public abstract void riding();


   public void makeSound(){
       System.out.println("Велосипед звенит звонком");
   }
   public static void getBicycleCount(){
       System.out.println("Было создано велосипедов: " + bicycleCo unt);
   }




}
