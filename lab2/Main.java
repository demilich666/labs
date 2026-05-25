package org.example.lab2;


public class Main {
   public static void main(String[] args) {
       Bicycle mountainBicycle = new MountainBicycle("Красный","Горный скоростной",2,30, "Хорошо поворачивает","звенит в звонок");
       System.out.println("Горный велосипед: " + " " + mountainBicycle.getColor() + "|" + mountainBicycle.getModel() + "|" + "Количество колес = " + " " + mountainBicycle.getWheelsCount() + "|" + "Скорость = " + " " +  mountainBicycle.getSpeed() + "|" + mountainBicycle.getRotating() + "|" + mountainBicycle.getSound());
       Bicycle kidsBike = new KidsBike("Синий ", " Безопасный", 4, 10, "Умеренно хорошо поворачивает", "громко звенит в звонок");
       System.out.println("Детский велосипед: " + kidsBike.getColor() + "|" + kidsBike.getModel() + "|"+ "Количество колес = " + " " + kidsBike.getWheelsCount() + "|" + "Скорость = " + " " +  kidsBike.getSpeed() + "|" + kidsBike.getRotating() + "|" + kidsBike.getSound());
       Bicycle BMX = new BMX("Серый", "Трюковой", 2, 35, "Отлично поворачивает ", "не имеет звонка");
       System.out.println("BMX: " + BMX.getColor() + "|" + BMX.getModel() + "|" + "Количество колес = " + " " + BMX.getWheelsCount() + "|" + "Скорость = " + " " + BMX.getSpeed() + "|" + BMX.getRotating() + "|" + BMX.getSound());

       Bicycle.getBicycleCount();


       }


   }




