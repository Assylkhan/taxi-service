package com.epam;

import com.epam.pool.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.atomic.AtomicInteger;

public class PoolTest {
    static List<Connection> connections = new CopyOnWriteArrayList<>();
    static AtomicInteger count = new AtomicInteger(0);
    static ConnectionPool pool;
    public static void main(String[] args) {
        try {
            pool = new ConnectionPool();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        Timer timer = new Timer(true);
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                System.out.println("Collect " + connections.size());
                for (Connection con : connections) {
                    try {
                        con.close();
                        System.out.println("closed");
                    } catch (SQLException e) {
                    }
                }
            }
        }, 3000, 1000);
        for (int i = 0; i < 100000; i++) {
            new Thread(() -> {
                Connection connection = null;
                try {
                    connection = pool.getConnection();
                    if (connection != null) {
                        connections.add(connection);
                        count.incrementAndGet();
                        System.out.println("added connection: " + count);
                    }
                } catch (SQLException e) {
                }
            }).start();
        }
    }
}
