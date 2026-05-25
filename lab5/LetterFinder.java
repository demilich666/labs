package org.example.lab5.example3;


public class LetterFinder {

   public static void main(String[] args) {
       String text = "abCdeFghIJkLm";
       System.out.println(highlightMatches(text));
   }


   public static String highlightMatches(String text) {
       if (text == null || text.isBlank()) {
           throw new IllegalArgumentException("Текст не может быть null или пустым");
       }

       return text.replaceAll("([a-z][A-Z])", "!$1!");
   }


}
