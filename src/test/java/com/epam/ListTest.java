package com.epam;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class ListTest {

    private static class Transfer implements Callable<String> {

        @Override
        public String call() throws Exception {
            return null;
        }
    }

    static List<Long> arrList = Collections.synchronizedList(new ArrayList<>());
    static List<Long> coList = new CopyOnWriteArrayList<>();

    public static void main(String[] args) {

        fillList(arrList, 100);
        fillList(coList, 100);
        System.out.println("List synchronized: ");

        System.out.println("List copyOnWrite: ");
        checkList(arrList);
    }

    private static void checkList(List<Long> list) {
        CountDownLatch latch = new CountDownLatch(1);
        ExecutorService ex = Executors.newFixedThreadPool(2);
        Future<Long> f1 = (Future<Long>) ex.submit(new ListRunner(0, 50, list, latch));
        Future<Long> f2 = (Future<Long>) ex.submit(new ListRunner(50, 100, list, latch));
        latch.countDown();
        ex.shutdown();
        /*try {
            System.out.println("Thread 1: " + f1.get());
            System.out.println("Thread 2: " + f2.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }*/
    }

    private static void fillList(List<Long> list, int n) {
        for (int i = 0; i < n; i++) {
            list.add(Long.valueOf(i));
        }
    }

    private static class ListRunner implements Runnable {
        List<Long> list;
        int from;
        int to;
        CountDownLatch latch;

        public ListRunner(int from, int to, List<Long> list, CountDownLatch latch) {
            this.from = from;
            this.to = to;
            this.list = list;
            this.latch = latch;
        }

        @Override
        public void run() {
            long timeBefore = System.currentTimeMillis();
            for (int i = from; i < to; i++) {
                System.out.println(arrList.get(i));
            }
            long time = System.currentTimeMillis() - timeBefore;
            System.out.println(time+" sec.");
        }
    }
}
