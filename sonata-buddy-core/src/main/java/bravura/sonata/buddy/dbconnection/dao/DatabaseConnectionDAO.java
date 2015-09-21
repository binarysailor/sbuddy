package bravura.sonata.buddy.dbconnection.dao;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnectionConfigException;

/**
 * Created by tszymanski on 12/09/2015.
 */
public interface DatabaseConnectionDAO {
    DatabaseConnection[] getDatabaseConnections() throws DatabaseConnectionConfigException;
}
