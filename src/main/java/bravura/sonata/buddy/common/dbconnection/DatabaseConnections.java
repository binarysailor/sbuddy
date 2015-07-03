package bravura.sonata.buddy.common.dbconnection;

import javax.sql.DataSource;
import javax.xml.crypto.Data;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by tszymanski on 23/06/2015.
 */
public class DatabaseConnections {
    private DatabaseConnection[] connections;
    private DatabaseConnection current;

    public void initialize() {
        connections = loadDatabaseConnections();
        setCurrent(connections[0]);
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

    private DatabaseConnection[] loadDatabaseConnections() {
        return new DatabaseConnectionDAOImpl().getDatabaseConnections();
    }

    public void closeAll() {
        for (DatabaseConnection connection : connections) {
            connection.closeDataSource();
        }
    }
}
