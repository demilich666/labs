package lab6.example2;

import java.util.EmptyStackException;

public class StackMain {
    public static void main(String[] args) {

        Stack<Integer> stack = new Stack<>(10);
        stack.push(1);
        stack.push(2);
        stack.push(3);
        System.out.println(stack.pop());
        System.out.println(stack.peek());
        stack.push(4);
        System.out.println(stack.pop());

    }

    public static class Stack<T> {

        private T[] data;
        private int size;

        public Stack(int capacity) {
            if (capacity <= 0) {
                throw new IllegalArgumentException("Размер должен быть > 0");
            }
            this.data = (T[]) new Object[capacity];
            this.size = 0;
        }

        public void push(T element) {
            if (size == data.length) {
                throw new IllegalStateException("Стэк переполнен");
            }
            data[size++] = element; //вставляем элемент в массив, после чего увеличиваем size
        }

        public T pop() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            T element = data[--size]; //получаем элемент из массива (изменение сайз сразу же)
            data[size] = null;
            return element;
        }

        public T peek() {
            if (isEmpty()) {
                throw new EmptyStackException();
            }
            return data[size - 1];
        }

        public boolean isEmpty() {
            return size == 0;
        }

        public int size() {
            return size;
        }
    }
}
