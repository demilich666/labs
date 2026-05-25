package Laba4;

import java.io.*;

public class FileCopy {
   public static void main(String[] args) {
       FileInputStream input = null;
       FileOutputStream output = null;

       try {
           input = new FileInputStream("C:/Users/SlavaDokhov/IdeaProjects/Laboratornaya/out/production/Laboratornaya/Laba4/source.txt");
           output = new FileOutputStream("sourceb.txt");

           int n;
           while ((n = input.read()) != -1) {
               output.write(n);
           }

           System.out.println("Файл успешно скопирован!");

       } catch (IOException e) {
           System.out.println("Ошибка при работе с файлом: " + e.getMessage());
       } finally {
           try {
               if (input != null) input.close();
               if (output != null) output.close();
           } catch (IOException e) {
               System.out.println("Не удалось закрыть файлы");
           }
       }
   }
}
