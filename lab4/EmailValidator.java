package Laba4;


class CustomEmailFormatException extends Exception {
   public CustomEmailFormatException(String message) {
       super(message);
   }
}


public class EmailValidator {
   public static void main(String[] args) {
       String myEmail = "mtuci@mail.ru";


       try {
           validate(myEmail);
           System.out.println("Email '" + myEmail + "' верифицирован.");
       }
       catch (CustomEmailFormatException e) {
           System.out.println("Ошибка валидации: " + e.getMessage());
       }
   }


   public static void validate(String email) throws CustomEmailFormatException {
       if (!email.contains("@")) {
           throw new CustomEmailFormatException("Нет символа @");
       }


       int lastDotIndex = email.lastIndexOf(".");
       int dogIndex = email.indexOf("@");


       if (lastDotIndex == -1 || lastDotIndex < dogIndex) {
           throw new CustomEmailFormatException("После @ должна быть точка домена ");
       }


       if (lastDotIndex >= email.length() - 2) {
           throw new CustomEmailFormatException("После точки должно быть расширение домена");
       }
   }
}
