package org.example.Ex1;

import java.util.Scanner;

/*  1. Пользователь с клавиатуры вводит цифру. Вы создаете динамически нужное количество потоков равное
введенной цифре, нумеруете их и запускаете на выполнение.
Каждый поток должен выводить свой номер на экран 100 раз с интервалом 100 миллисекунд перед каждым выводом.
Сделайте так, чтобы главный поток выполнения программы не завершился до окончания работы всех дочерних потоков. */
public class Task1 {
    public static void main(String[] args) throws InterruptedException {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите количество потоков: ");
        int countThreads = scanner.nextInt();

        Thread[] threads = new Thread[countThreads];

        for (int i = 0; i < countThreads; i++) {
            threads[i] = new Thread(() -> {
                String threadNumber = Thread.currentThread().getName();
                for (int j = 0; j < 100; j++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException exception) {
                        exception.printStackTrace();
                    }
                    System.out.println("Поток - " + threadNumber + ": " + (j + 1));
                }
            });
            threads[i].start();
        }
        for (Thread thread : threads) {
            thread.join();
        }
    }
}
