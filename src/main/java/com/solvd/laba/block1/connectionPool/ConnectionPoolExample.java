package com.solvd.laba.block1.connectionPool;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConnectionPoolExample {
    public static void main(String[] args) {
        // Get an instance of the connection pool
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        // Create a thread pool with 7 threads
        ExecutorService executorService = Executors.newFixedThreadPool(7);

        // Run 5 threads to get a connection
        for (int i = 0; i < 5; i++) {
            executorService.submit(() -> {
                Connection connection = connectionPool.getConnection();
                connection.executeQuery("SELECT * FROM example_table");
                connectionPool.releaseConnection(connection);
            });
        }

        // Run 2 threads to wait for the next available connection
        for (int i = 0; i < 2; i++) {
            executorService.submit(() -> {
                System.out.println("Thread waiting for the next available connection");
                Connection connection = connectionPool.getConnection();
                connection.executeQuery("SELECT * FROM example_table");
                connectionPool.releaseConnection(connection);
            });
        }

        // Shutdown the thread pool
        executorService.shutdown();
    }
}
