package com.solvd.laba.block1.connectionPool;

public class Connection {
    // Mocked Connection class
    private final String id;

    public Connection(String id) {
        this.id = id;
    }

    public void executeQuery(String query) {
        System.out.println("Executing query '" + query + "' on connection " + id);
        // Your actual query execution logic here
    }
}
