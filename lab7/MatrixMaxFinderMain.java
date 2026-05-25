package lab7.example2;

public class MatrixMaxFinderMain {

    public static void main(String[] args) throws InterruptedException {

        int[][] matrix = {
                {3, 5, 1, 7},
                {9, 2, 8, 4},
                {6, 11, 0, 10},
                {15, 13, 12, 14}
        };

        MatrixMaxFinder matrixMaxFinder = new MatrixMaxFinder(matrix);

        int max = matrixMaxFinder.findMax();

        System.out.println("Наибольший элемент матрицы: " + max);
    }

    public static class MatrixMaxFinder {

        private int[][] matrix; //двумерный массив (матрица)

        public MatrixMaxFinder(int[][] matrix) {
            this.matrix = matrix;
        }

        public int findMax() throws InterruptedException {

            RowMaxThread[] threads = new RowMaxThread[matrix.length];

            // Создание и запуск потоков
            for (int i = 0; i < matrix.length; i++) {

                threads[i] = new RowMaxThread(matrix[i]); //создаем поток, принимающий массив из матрицы, после чего записывается в массив threads

                threads[i].start();
            }

            // Ожидание завершения потоков
            for (int i = 0; i < threads.length; i++) {
                threads[i].join(); //проходим по массиву потоков и ждем заверешения
            }

            // Поиск общего максимума
            int max = threads[0].getRowMax();

            for (int i = 1; i < threads.length; i++) {

                if (threads[i].getRowMax() > max) {
                    max = threads[i].getRowMax();
                }
            }

            return max;
        }
    }

    public static class RowMaxThread extends Thread {

        private int[] row;

        private int rowMax;

        public RowMaxThread(int[] row) {
            this.row = row;
        }

        @Override
        public void run() {

            rowMax = row[0];

            for (int i = 1; i < row.length; i++) {

                if (row[i] > rowMax) {
                    rowMax = row[i];
                }
            }

            System.out.println(
                    Thread.currentThread().getName()
                            + " нашел максимум строки: "
                            + rowMax
            );
        }

        public int getRowMax() {
            return rowMax;
        }
    }
}
