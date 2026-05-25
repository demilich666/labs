package lab3.example2;

public class Main {
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

}
