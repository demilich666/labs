public class Palindrome {
    public static void main(String[] args) {
        for (int i = 0; i < args.length; i++) {
            String s = args[i];
            boolean result = isPalindrome(s);
            System.out.println(s + " " +result);
        }
    }
    public static String reverseString(String s) {
        String reversed = "";

        for (int i = 0; i < s.length(); i++) {
            reversed += s.charAt(s.length() - 1 - i);
        }
        return reversed;
    }
    public static boolean isPalindrome(String s) {
        return s.equals(reverseString(s));
    }
}
