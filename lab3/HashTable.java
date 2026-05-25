package lab3.example2;

import java.util.LinkedList;

public class HashTable {

    // Внутренний класс для хранения пары ключ-значение
    private static class Entry {

        String barcode;
        Product product;

        public Entry(String barcode, Product product) {
            this.barcode = barcode;
            this.product = product;
        }
    }

    // Размер таблицы
    private static final int SIZE = 10;

    // Массив списков
    private LinkedList<Entry>[] table;

    public HashTable() {
        table = new LinkedList[SIZE];

        for (int i = 0; i < SIZE; i++) {
            table[i] = new LinkedList<Entry>();
        }
    }

    // Хэш-функция
    private int getHash(String barcode) {
        return Math.abs(barcode.hashCode()) % SIZE;
    }

    // Вставка товара
    public void put(String barcode, Product product) {

        int index = getHash(barcode);

        LinkedList<Entry> list = table[index];

        // Проверяем, есть ли уже такой ключ
        for (Entry entry : list) {
            if (entry.barcode.equals(barcode)) {
                entry.product = product;
                return;
            }
        }

        // Если ключа нет — добавляем
        list.add(new Entry(barcode, product));
    }

    // Поиск товара
    public Product get(String barcode) {

        int index = getHash(barcode);

        LinkedList<Entry> list = table[index];

        for (Entry entry : list) {
            if (entry.barcode.equals(barcode)) {
                return entry.product;
            }
        }

        return null;
    }

    // Удаление товара
    public void remove(String barcode) {

        int index = getHash(barcode);

        LinkedList<Entry> list = table[index];

        for (Entry entry : list) {
            if (entry.barcode.equals(barcode)) {
                list.remove(entry);
                return;
            }
        }
    }
}
