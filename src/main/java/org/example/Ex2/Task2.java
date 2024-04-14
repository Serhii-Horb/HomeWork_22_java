package org.example.Ex2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static java.lang.Integer.MAX_VALUE;
import static java.lang.Integer.MIN_VALUE;

/* 2*. Посчитайте количество всех целых чисел в диапазоне от Integer.MINVALUE до Integer.MAXVALUE,
которые делятся на введенное пользователем число number без остатка.
Просчитайте время, которое необходимо для вычисления данного результата в одном потоке.
Просчитайте время, которое необходимо пользователю для выполнения этих задачи параллельно,
количество создаваемых потоков countThreads пользователь тоже должен ввести вручную . */
public class Task2 {
    public static void main(String[] args) throws InterruptedException {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Введите число: ");
        int number = scanner.nextInt();

        System.out.print("Введите количество потоков: ");
        int threadCount = scanner.nextInt();

        long startTimeSingle = System.currentTimeMillis();
        int countSingle = numberOfNumbersSingleThreaded(number);
        long endTimeSingle = System.currentTimeMillis();

        long startTimeMulti = System.currentTimeMillis();
        int countMulti = numberOfNumbersMultiThreaded(number, threadCount);
        long endTimeMulti = System.currentTimeMillis();

        System.out.println("Количество делимых чисел: " + countSingle);
        System.out.println("Время выполнения (один поток): " + (endTimeSingle - startTimeSingle) + " миллисекунд");
        System.out.println("Количество делимых чисел: " + countMulti);
        System.out.println("Время выполнения (" + threadCount + " потоков): " + (endTimeMulti - startTimeMulti) +
                " миллисекунд");
    }

    private static int numberOfNumbersSingleThreaded(int number) {
        int count = 0;
        for (int i = MIN_VALUE; i < MAX_VALUE; i++) {
            if (i % number == 0) {
                count++;
            }
        }
        return count;
    }

    private static int numberOfNumbersMultiThreaded(int number, int threadCount) throws InterruptedException {
        int chunkSize = (MAX_VALUE / threadCount - MIN_VALUE / threadCount);
        int start = MIN_VALUE;
        int resultCount = 0;

        for (int i = 1; i < threadCount; i++) {
            MyRunnable runnable = new MyRunnable(start, start + chunkSize, number);
            Thread thread = new Thread(runnable);
            thread.start();
            thread.join();
            start = start + chunkSize;
            resultCount += runnable.getResult();
        }
        MyRunnable runnable = new MyRunnable(start, MAX_VALUE, number);
        Thread thread = new Thread(runnable);
        thread.start();
        thread.join();
        resultCount += runnable.getResult();
        return resultCount;
    }
}
