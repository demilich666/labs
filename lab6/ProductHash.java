package lab6.example3;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

public class ProductHash {

    public static void main(String[] args) {

        Product milk = new Product("Молоко",59);
        Product bread = new Product("Хлеб", 90);
        Product juice = new Product("Сок", 119);
        Product meat = new Product("Мясо", 249);
        Product chocolate = new Product("Шоколад",90);
        Service service = new Service();
        service.add(milk,9);
        service.add(bread,14);
        service.add(juice,7);
        service.add(meat, 6);
        service.add(chocolate, 4);
        service.printSales();
        System.out.println("Общая сумма продаж: " + service.getTotalRevenue());
        System.out.println("Самый продаваемый продукт: " + service.getMostPopularProduct());
    }

    public static class Product {

        private final String name; //final - после записи значения нельзя его поменять
        private Integer price;

        public Product(String name, Integer price) {
            this.name = name;
            this.price = price;
        }

        public String getName() {
            return name;
        }

        public Integer getPrice() {
            return price;
        }

        public void setPrice(Integer price) {
            this.price = price;
        }

        @Override
        public boolean equals(Object o) {
            if (o == null || getClass() != o.getClass()) return false;
            Product product = (Product) o;
            return Objects.equals(name, product.name) && Objects.equals(price, product.price);
        }

        @Override
        public int hashCode() { //возвращает уникальное числовое представление объекта (при идентичных данных)
            return Objects.hash(name, price);
        }

        @Override
        public String toString() { //весь объект в одну строку
            return "Product{" +
                    "name='" + name + '\'' +
                    ", price=" + price +
                    '}';
        }
    }

    public static class Service {

        private Map<Product, Integer> products = new HashMap<>();

        // Добавление продажи
        public void add(Product product, int quantity) {
            if (products.containsKey(product)) {
                int current = products.get(product);
                products.put(product, current + quantity);
            } else {
                products.put(product, quantity);
            }
        }

        // Вывод всех продаж
        public void printSales() {
            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                System.out.println(entry.getKey() + " -> " + entry.getValue());
            }
        }

        // Общая сумма продаж
        public Integer getTotalRevenue() {
            Integer total = 0;

            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                Product product = entry.getKey();
                Integer quantity = entry.getValue();

                Integer sum = product.getPrice() * quantity;

                total = total + sum;
            }

            return total;
        }

        // Самый популярный товар
        public Product getMostPopularProduct() {
            Product mostPopular = null;
            int maxQuantity = 0;

            for (Map.Entry<Product, Integer> entry : products.entrySet()) {
                if (entry.getValue() > maxQuantity) {
                    maxQuantity = entry.getValue();
                    mostPopular = entry.getKey();
                }
            }

            return mostPopular;
        }

    }
}
//1 - создать объект сервис. 2. несколько продуктов 3. взаимодействие с сервисом
