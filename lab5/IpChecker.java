package org.example.lab5.example4;


import java.util.regex.Pattern;


public class IpChecker {


   private static final Pattern IP_PATTERN = Pattern.compile("^(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])" +
           "(\\.(25[0-5]|2[0-4][0-9]|1[0-9][0-9]|[1-9]?[0-9])){3}$");


   public static void main(String[] args) {
       String ip = "124.222.14.222";
       try {
           checkIp(ip);
       } catch (IllegalArgumentException e) {
           System.err.println("Ошибка: " + e.getMessage());
       }
   }


   public static void checkIp(String ip) {
       if (ip == null || ip.isBlank()) {
           throw new IllegalArgumentException("айпи не должен быть null или пустым");
       }
       if (!IP_PATTERN.matcher(ip).matches()){
           System.err.println("айпи невалиден");
       } else {
           System.out.println("айпи валиден");
       }




   }


}
