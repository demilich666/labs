package org.example.lab5.example2;


import java.util.regex.Pattern;


public class PasswordChecker {

   private static final Pattern PASSWORD_PATTERN = Pattern.compile("^(?=.*[A-Z])(?=.*\\d)[A-Za-z0-9]{8,16}$");


   public static void main(String[] args) {
       String password = "1234Qwапr";


       try {
           check(password);
       } catch (IllegalArgumentException e) {
           System.err.println("Ошибка: " + e.getMessage());
       }


   }

   public static void check(String password) {
       if (password == null || password.isBlank()) {
           throw new IllegalArgumentException("Пароль не должен быть null или пустым");
       }
       if (!PASSWORD_PATTERN.matcher(password).matches()){
           System.err.println("Пароль невалиден");
       } else {
           System.out.println("Пароль валиден");
       }




   }


}
