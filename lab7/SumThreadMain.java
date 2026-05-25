package lab7.example1;

public class SumThreadMain {

    public static void main(String[] args) {

        int[] numbers = {
                1, 2, 3, 4,
                5, 6, 7, 8
        };

        // Середина массива
        int middle = numbers.length / 2;

        SumThread thread1 =
                new SumThread(numbers, 0, middle);

        SumThread thread2 =
                new SumThread(numbers, middle, numbers.length);

        thread1.start();
        thread2.start();

        try {

            thread1.join();
            thread2.join();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        // Получаем суммы от потоков
        int totalSum =
                thread1.getSum() + thread2.getSum();

        System.out.println("Общая сумма: " + totalSum);
    }

    public static class SumThread extends Thread {

        private int[] array;
        private int start; //откуда поток начинает отсчет
        private int end; //где заканчивает
        private int sum;

        public SumThread(int[] array, int start, int end) {
            this.array = array;
            this.start = start;
            this.end = end;
        }

        @Override
        public void run() {

            sum = 0;

            for (int i = start; i < end; i++) {
                sum += array[i];
            }

            System.out.println(
                    getName() + " посчитал сумму: " + sum
            );
        }

        public int getSum() {
            return sum;
        }
    }
}
