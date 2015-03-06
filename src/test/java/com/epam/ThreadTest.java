package com.epam;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadTest {
    static ThreadTest monitor = new ThreadTest();

    public void method1() {
        System.out.println("method 1");
    }

    public synchronized void method2() {
        System.out.println("method 2");
    }

    public synchronized void method3() {
        System.out.println("method 3");
    }

    public static synchronized void method4() {
        System.out.println("method 4");
    }

    public static void main(String[] args) throws IOException, ClassNotFoundException {


        /*List rawList = new ArrayList();
        List<String> list = new ArrayList<>();
        rawList = list;
        rawList.add(8);
        String s = list.get(0);
        System.out.println(s);*/
        final int N = 4;
        ExecutorService executor = Executors.newFixedThreadPool(N);
        CountDownLatch startSignal = new CountDownLatch(1);
        CountDownLatch doneSignal = new CountDownLatch(N);

        List<Thread> threads = new ArrayList<>();
        for (int i = 1; i <= N; i++) {
            final int finalI = i;
            executor.submit(new Worker(startSignal, doneSignal, String.valueOf(i)));
            System.out.println(i);
        }
        try {
            doneSignal.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("end at last!");
    }

    private static class Worker implements Runnable {
        private CountDownLatch startSignal;
        private CountDownLatch doneSignal;
        private String methodCount;

        public Worker(CountDownLatch startSignal, CountDownLatch doneSignal, String methodCount) {
            this.startSignal = startSignal;
            this.doneSignal = doneSignal;
            this.methodCount = methodCount;
        }

        @Override
        public void run() {
            try {
                System.out.println("before countDown thread - " + methodCount);
                doneSignal.countDown();
                System.out.println("after countDown thread - " + methodCount);
                Method method = monitor.getClass().getMethod("method" + methodCount, null);
                method.invoke(monitor, null);
                System.out.println("in last line of run thread - " + methodCount);
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            }
        }
    }
}
