package org.example.lab5.example5;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class WordFinder {


   public static void main(String[] args) {
   String text = "Abc Dfg Hij Dlm DN";
   char symbol = 'D';
   try{
       List<String> words = findWords(text, symbol);
       System.out.println(words);
   } catch (IllegalArgumentException e){
       System.err.println("Ошибка: " + e.getMessage());
       }




   }


   public static List<String> findWords (String text, char symbol) {
       if (text == null || text.isBlank()) {
           throw new IllegalArgumentException("Текст не должен быть null или пустым");
       }
       if (!Character.isLetter(symbol)) {
           throw new IllegalArgumentException("Символ должен быть буквой");
       }


       Pattern pattern = Pattern.compile( "\\b[" + Character.toLowerCase(symbol) +
               Character.toUpperCase(symbol) + "]\\w*");


       List<String> result = new ArrayList<>();
       Matcher matcher = pattern.matcher(text);


       while (matcher.find()) {
           result.add(matcher.group());
       }






       return result;


   }


}
