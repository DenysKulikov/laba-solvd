package com.solvd.laba.block1.universityEnrollment.connectionPoolWithCompletableFuture;

import com.solvd.laba.block1.universityEnrollment.connectionPool.Connection;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final int MAX_POOL_SIZE = 5;
    private static ConnectionPool instance;
    private final BlockingQueue<Connection> connectionQueue;

    private ConnectionPool() {
        // Initialize the connection pool
        connectionQueue = new LinkedBlockingQueue<>(MAX_POOL_SIZE);
        initializePool();
    }

    public static ConnectionPool getInstance() {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    private void initializePool() {
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            Connection connection = new Connection("Connection-" + (i + 1));
            connectionQueue.offer(connection);
        }
    }

    public CompletableFuture<Connection> getConnectionAsync() {
        return CompletableFuture.supplyAsync(() -> {
            Connection connection = null;
            try {
                connection = connectionQueue.take();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
            return connection;
        });
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionQueue.offer(connection);
        }
    }
}
