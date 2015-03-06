package com.epam;

import javax.xml.crypto.Data;
import java.io.FileOutputStream;
import java.util.*;

public class SetTest {


    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
//        FileOutputStream
        Collections.synchronizedList(list);

        list.remove("f");
        /*Map<Object, String> map = new WeakHashMap<>();
        Object object = new Object();
        map.put(object, "info");
        object = null;
        System.gc();

        for (int i = 0; i < 1000; i++) {
            if (map.isEmpty()){
                System.out.println("Empty! "+i);
                break;
            }
        }*/
//        System.out.println(map);
        /*map.put(4, "b");
        map.put(3, "c");
        map.put(2, "d");
        map.put(1, "e");*/
        /*Queue<Integer> q = new PriorityQueue<Integer>((Integer a, Integer b) -> {
            if (((a % 2) == 0) && ((b % 2) == 0)){
                return a.compareTo(b);
            } else if (((a % 2) == 0) && ((b % 2) != 0)){
                return -1;
            } else if (((a % 2) != 0) && ((b % 2) == 0)){
                return 1;
            } else return a.compareTo(b);
        });
        for (int i = 5; i > 0; i--) {
            q.add(i);
        }
        while (!q.isEmpty()) {
            System.out.println(q.poll());
        }*/
    }


}
