package Laba4;


public class ArrayAverage {
   public static void main(String[] args) {
       String[] arr = {"10", "20", "abc", "30"};
       int sum = 0;
       int count = 0;


       try {


           for (int i = 0; i <= arr.length; i++) {
               int number = Integer.parseInt(arr[i]);
               sum += number;
               count++;
           }
       }
       catch (ArrayIndexOutOfBoundsException e) {
           System.out.println("Выход за границы массива ");
       }
       catch (NumberFormatException e) {
           System.out.println("В массиве попалось не int");
       }


       finally {
           if (count > 0) {
               double avg = (double) sum / count;
               System.out.println("Среднее, что удалось посчитать: " + avg);
           }
       }
   }
}
