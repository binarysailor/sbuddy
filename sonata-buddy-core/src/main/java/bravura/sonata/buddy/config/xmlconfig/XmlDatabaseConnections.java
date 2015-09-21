package bravura.sonata.buddy.config.xmlconfig;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.files.Directory;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tszymanski on 03/07/2015.
 */
public class XmlDatabaseConnections {

    public static DatabaseConnection[] load(Directory directory) throws IOException, JAXBException {
        try (InputStream configInput = directory.getAsInputStream("database-connections.xml")){
            List<DatabaseConnection> results = new ArrayList<>();
            JAXBContext jc = JAXBContext.newInstance(
                    DatabaseConnections.class);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            bravura.sonata.buddy.dbconnection.dao.DatabaseConnections xmlDbConnections =
                (bravura.sonata.buddy.dbconnection.dao.DatabaseConnections)
                        unmarshaller.unmarshal(configInput);
            for (bravura.sonata.buddy.dbconnection.dao.DatabaseConnection xmlConnection : xmlDbConnections.getDatabaseConnection()) {
                DatabaseConnection connection = new DatabaseConnection(
                        xmlConnection.getName(),
                        xmlConnection.getUrl(),
                        xmlConnection.getUser(),
                        xmlConnection.getPassword());
                results.add(connection);
            }
            return results.toArray(new DatabaseConnection[0]);
        } catch (FileNotFoundException e) {
            return new DatabaseConnection[0];
        }
    }
}
