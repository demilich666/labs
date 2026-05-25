package lab3.example2;

import java.util.LinkedList;

public class ProductHash {
    public static void main(String[] args) {
        // Создаём хеш-таблицу
        HashTable table = new HashTable();

        // 1. Добавляем несколько товаров
        table.put("1234567890", new Product("Молоко", 50, 10));
        table.put("0987654321", new Product("Хлеб", 30, 5));
        table.put("1111111111", new Product("Cыр", 120, 2));

        // 2. Обновляем товар с тем же штрихкодом
        table.put("1234567890", new Product("Свежее молоко", 55, 8));

        // 3. Ищем товары
        System.out.println("Поиск 1234567890: " + table.get("1234567890"));
        System.out.println("Поиск 0987654321: " + table.get("0987654321"));
        System.out.println("Поиск 0000000000: " + table.get("0000000000")); // такого нет

        // 4. Удаляем товар
        table.remove("1111111111");
        System.out.println("После удаления 1111111111: " + table.get("1111111111"));

        // 5. Добавляем ещё один и проверяем
        table.put("2222222222", new Product("Масло", 80, 3));
        System.out.println("Поиск 2222222222: " + table.get("2222222222"));
    }

    public static class HashTable {

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

    public static class Product {

        private String name;
        private int price;
        private int quantity;

        public Product(String name, int price, int quantity) {
            this.name = name;
            this.price = price;
            this.quantity = quantity;
        }

        public String getName() {
            return name;
        }

        public int getPrice() {
            return price;
        }

        public int getQuantity() {
            return quantity;
        }

        @Override
        public String toString() {
            return "Product{name='" + name + '\'' +
                    ", price=" + price +
                    ", quantity=" + quantity +
                    '}';
        }
    }
}
