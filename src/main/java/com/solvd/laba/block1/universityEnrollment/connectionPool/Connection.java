package com.solvd.laba.block1.universityEnrollment.connectionPool;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Connection {
    // Mocked Connection class
    private static final Logger LOGGER = LogManager.getLogger(Connection.class);
    private final String id;

    public Connection(String id) {
        this.id = id;
    }

    public void executeQuery(String query) {
        LOGGER.trace("Executing query '" + query + "' on connection " + id);
        // Your actual query execution logic here
    }

    public String getId() {
        return id;
    }
}
