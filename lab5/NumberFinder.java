package org.example.lab5.example1;


import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class NumberFinder {


   private static final Pattern NUMBER_PATTERN = Pattern.compile("-?\\d+(\\.\\d+)?");


   public static void main(String[] args) {
       String text = "124 qwrttq 124165";


       try {
           List<String> numbers = extractNumbers(text);
           for (String number : numbers) {
               System.out.println(number);
           }
       } catch (IllegalArgumentException e) {
           System.err.println("Ошибка: " + e.getMessage());
       }
   }


   public static List<String> extractNumbers(String text) {
       if (text == null || text.isBlank()) {
           throw new IllegalArgumentException("Текст не должен быть null или пустым");
       }


       List<String> result = new ArrayList<>();
       Matcher matcher = NUMBER_PATTERN.matcher(text);


       while (matcher.find()) {
           result.add(matcher.group());
       }


       return result;
   }
}
