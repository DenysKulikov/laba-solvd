package com.solvd.laba.block1.universityEnrollment.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class ConnectionPoolExampleWithCompletableFuture {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPoolExampleWithCompletableFuture.class);
    public static void main(String[] args) throws InterruptedException, ExecutionException {
        // Get an instance of the connection pool
        ConnectionPool connectionPool = ConnectionPool.getInstance();

        // Create a CompletableFuture list to store the results
        List<CompletableFuture<Void>> futures = new ArrayList<>();

        // Run 5 CompletableFuture tasks to get a connection
        for (int i = 0; i < 5; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                Connection connection = connectionPool.getConnection();
                connection.executeQuery("SELECT * FROM example_table");
                connectionPool.releaseConnection(connection);
            });

            futures.add(future);
        }

        // Run 2 CompletableFuture tasks to wait for the next available connection
        for (int i = 0; i < 2; i++) {
            CompletableFuture<Void> future = CompletableFuture.runAsync(() -> {
                LOGGER.trace("CompletableFuture waiting for the next available connection");
                Connection connection = connectionPool.getConnection();
                connection.executeQuery("SELECT * FROM example_table");
                connectionPool.releaseConnection(connection);
            });

            futures.add(future);
        }

        // Wait for all CompletableFuture tasks to complete
        CompletableFuture<Void> allOf = CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));
        allOf.get(); // This blocks until all CompletableFuture tasks are completed

        // Shutdown resources
        ExecutorService executorService = ((ThreadPoolExecutor) Executors.newFixedThreadPool(7));
        executorService.shutdown();
    }
}
