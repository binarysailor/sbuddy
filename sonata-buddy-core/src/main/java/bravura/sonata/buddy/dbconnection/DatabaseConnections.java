package bravura.sonata.buddy.dbconnection;

import bravura.sonata.buddy.dbconnection.dao.DatabaseConnectionDAO;

import javax.sql.DataSource;

/**
 * Created by tszymanski on 23/06/2015.
 */
public class DatabaseConnections {
    private DatabaseConnectionDAO dao;
    private DatabaseConnection[] connections;
    private DatabaseConnection current;

    DatabaseConnections(DatabaseConnectionDAO dao) {
        this.dao = dao;
    }

    public void initialize() throws DatabaseConnectionConfigException {
        connections = loadDatabaseConnections();
        if (connections.length > 0) {
            setCurrent(connections[0]);
        }
    }

    public DatabaseConnection[] all() {
        return connections;
    }

    public DatabaseConnection getCurrent() {
        return current;
    }

    public DataSource getCurrentDataSource() {
        return current.getDataSource();
    }

    public void setCurrent(DatabaseConnection connection) {
        for (DatabaseConnection registeredConnection : connections) {
            if (registeredConnection.equals(connection)) {
                current = registeredConnection;
                return;
            }
        }

        throw new IllegalArgumentException("The connection you're trying to select is unknown");
    }

    private DatabaseConnection[] loadDatabaseConnections() throws DatabaseConnectionConfigException {
        return dao.getDatabaseConnections();
    }

    public void closeAll() {
        for (DatabaseConnection connection : connections) {
            connection.closeDataSource();
        }
    }
}
