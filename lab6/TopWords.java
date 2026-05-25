package lab6.example1;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;
import java.util.Map.Entry;

public class TopWords {

    private static final int TOP_N = 10;

    public static void main(String[] args) {
        String filePath = "./src/java/lab6/example1/text.txt";

        File file = new File(filePath);

        Map<String, Integer> wordCount = new HashMap<>();

        try (Scanner scanner = new Scanner(file)) {

            while (scanner.hasNext()) {
                String word = normalize(scanner.next());
                if (word.isEmpty()) {
                    continue;
                }
                wordCount.merge(word, 1, Integer::sum);
            }

        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + filePath);
            return;
        }

        List<Entry<String, Integer>> list = new ArrayList<>(wordCount.entrySet());

        list.sort((o1, o2) -> o2.getValue().compareTo(o1.getValue()));

        printTopWords(list);
    }

    private static String normalize(String word) {
        return word
                .toLowerCase()
                .replaceAll("[^a-zа-я0-9]", ""); // убираем пунктуацию
    }

    private static void printTopWords(List<Entry<String, Integer>> list) {
        System.out.println("Топ " + TOP_N + " слов:");

        for (int i = 0; i < Math.min(TOP_N, list.size()); i++) {
            Entry<String, Integer> entry = list.get(i);
            System.out.println(i+1 + ". " + entry.getKey() + " — " + entry.getValue());
        }
    }
}
