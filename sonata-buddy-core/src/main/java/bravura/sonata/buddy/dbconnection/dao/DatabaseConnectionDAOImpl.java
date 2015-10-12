package bravura.sonata.buddy.dbconnection.dao;

import bravura.sonata.buddy.config.xmlconfig.XmlDatabaseConnections;
import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnectionConfigException;
import bravura.sonata.buddy.files.Directory;

import javax.xml.bind.JAXBException;
import java.io.IOException;

class DatabaseConnectionDAOImpl implements DatabaseConnectionDAO {

    private Directory directory;

    DatabaseConnectionDAOImpl(Directory directory) {
        this.directory = directory;
    }

    @Override
    public DatabaseConnection[] getDatabaseConnections() throws DatabaseConnectionConfigException {
        try {
            return XmlDatabaseConnections.load(directory);
        } catch (IOException | JAXBException e) {
            throw new DatabaseConnectionConfigException(e);
        }
        /*
        // TODO
        return new DatabaseConnection[] {
                new DatabaseConnection("Kaedwen", "jdbc:oracle:thin:@//127.0.0.1:1522/oracle12c", "kaedwen47", "password"),
                new DatabaseConnection("Bergen", "url2", "user2", "password2")
        };
        */
    }

    @Override
    public void saveDatabaseConnections(DatabaseConnection[] connections) throws DatabaseConnectionConfigException {
        try {
            XmlDatabaseConnections.save(directory, connections);
        } catch (IOException | JAXBException e) {
            throw new DatabaseConnectionConfigException(e);
        }
    }
}
