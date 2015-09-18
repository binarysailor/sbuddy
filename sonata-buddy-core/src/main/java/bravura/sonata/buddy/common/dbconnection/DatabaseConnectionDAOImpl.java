package bravura.sonata.buddy.common.dbconnection;

import bravura.sonata.buddy.config.xmlconfig.XmlDatabaseConnections;
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
}
