package lab7.example3;

import java.util.concurrent.CompletableFuture;

public class LoaderTaskMain {

    public static void main(String[] args) {

        int[] products = {
                40, 30, 20,
                50, 10, 60,
                25, 35, 15
        };

        Warehouse warehouse = new Warehouse(products);

        CompletableFuture<Void> future = CompletableFuture.runAsync(
                new LoaderTask(warehouse, "Грузчик 1")
        );

        CompletableFuture<Void> future2 = CompletableFuture.runAsync(
                new LoaderTask(warehouse, "Грузчик 2")
        );

        CompletableFuture<Void> future3 = CompletableFuture.runAsync(
                new LoaderTask(warehouse, "Грузчик 3")
        );

        CompletableFuture.allOf(future, future2, future3).join();

        System.out.println("Все товары перенесены");
    }

    public static class LoaderTask implements Runnable {

        private static final int MAX_WEIGHT = 150;

        private Warehouse warehouse;

        private String loaderName;

        public LoaderTask(Warehouse warehouse, String loaderName) {
            this.warehouse = warehouse;
            this.loaderName = loaderName;
        }

        @Override
        public void run() {

            int currentWeight = 0;

            while (true) {

                int product = warehouse.getProduct();

                if (product == -1) {

                    if (currentWeight > 0) {
                        unload(currentWeight);
                    }

                    break;
                }

                // Проверяем лимит веса
                if (currentWeight + product > MAX_WEIGHT) {

                    unload(currentWeight);

                    currentWeight = 0;
                }

                currentWeight += product;

                System.out.println(
                        loaderName
                                + " взял товар весом "
                                + product
                                + " кг. Текущий вес: "
                                + currentWeight
                                + " кг"
                );
            }
        }

        private void unload(int weight) {

            System.out.println(
                    loaderName
                            + " переносит товары на другой склад. Вес: "
                            + weight
                            + " кг"
            );

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            System.out.println(
                    loaderName
                            + " разгрузил товары"
            );
        }
    }

    public static class Warehouse {

        private int[] products;

        private int currentIndex = 0;

        public Warehouse(int[] products) {
            this.products = products;
        }

        public synchronized int getProduct() {

            if (currentIndex >= products.length) {
                return -1;
            }

            int product = products[currentIndex];

            currentIndex++;

            return product;
        }
    }
}
