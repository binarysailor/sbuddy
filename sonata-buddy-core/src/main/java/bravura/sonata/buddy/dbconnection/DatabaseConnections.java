package bravura.sonata.buddy.dbconnection;

import bravura.sonata.buddy.ErrorReporter;
import bravura.sonata.buddy.dbconnection.dao.DatabaseConnectionDAO;
import org.springframework.context.ApplicationListener;

import javax.sql.DataSource;

/**
 * Created by tszymanski on 23/06/2015.
 */
public class DatabaseConnections implements ApplicationListener<DatabaseConnectionConfigChangedEvent> {
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

    public boolean reinitialize() throws DatabaseConnectionConfigException {
        DatabaseConnection[] newConnections = loadDatabaseConnections();
        boolean currentChanged = false;
        if (current != null) {
            DatabaseConnection currentConnectionLookAlike = null;
            for (DatabaseConnection connection : newConnections) {
                if (connection.isSameAs(current)) {
                    currentConnectionLookAlike = connection;
                }
            }
            if (currentConnectionLookAlike != null) {
                current = currentConnectionLookAlike;
            } else {
                current = DatabaseConnection.empty();
                currentChanged = true;
            }
        }
        this.connections = newConnections;

        return currentChanged;
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
        DatabaseConnection connectionToSet = null;
        if (connection.isEmpty()) {
            connectionToSet = connection;
        } else {
            for (DatabaseConnection registeredConnection : connections) {
                if (registeredConnection.equals(connection)) {
                    connectionToSet = registeredConnection;
                }
            }
        }

        if (connectionToSet != null) {
            current = connectionToSet;
        } else {
            throw new IllegalArgumentException("The connection you're trying to select is unknown");
        }
    }

    private DatabaseConnection[] loadDatabaseConnections() throws DatabaseConnectionConfigException {
        return dao.getDatabaseConnections();
    }

    public void closeAll() {
        for (DatabaseConnection connection : connections) {
            connection.closeDataSource();
        }
    }

    @Override
    public void onApplicationEvent(DatabaseConnectionConfigChangedEvent databaseConnectionConfigChangedEvent) {
        try {
            this.reinitialize();
        } catch (DatabaseConnectionConfigException e) {
            ErrorReporter.report(e);
        }
    }
}
