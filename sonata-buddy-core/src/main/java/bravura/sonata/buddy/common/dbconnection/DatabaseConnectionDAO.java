package bravura.sonata.buddy.common.dbconnection;

/**
 * Created by tszymanski on 12/09/2015.
 */
interface DatabaseConnectionDAO {
    DatabaseConnection[] getDatabaseConnections() throws DatabaseConnectionConfigException;
}
