package com.solvd.laba.block1.universityEnrollment.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LogManager.getLogger(ConnectionPool.class);
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
        LOGGER.info("Initializing Connection Pool");
        for (int i = 0; i < MAX_POOL_SIZE; i++) {
            Connection connection = new Connection("Connection-" + (i + 1));
            connectionQueue.offer(connection);
            LOGGER.debug("Connection '{}' added to the pool", connection.getId());
        }
    }

    public Connection getConnection() {
        Connection connection = null;
        try {
            connection = connectionQueue.take();
            LOGGER.debug("Connection '{}' taken from the pool", connection.getId());
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            connectionQueue.offer(connection);
            LOGGER.debug("Connection '{}' released back to the pool", connection.getId());
        }
    }
}
