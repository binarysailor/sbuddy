package bravura.sonata.buddy.config.xmlconfig;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.dao.DatabaseConnections;
import bravura.sonata.buddy.files.Directory;

import javax.xml.bind.*;
import javax.xml.namespace.QName;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.logging.Logger;

/**
 * Created by tszymanski on 03/07/2015.
 */
public class XmlDatabaseConnections {

    public static final String DATABASE_CONNECTIONS_XML_NAME = "database-connections.xml";
    private static final String NAMESPACE_URI = "http://www.bravurasolutions.com/sonatabuddy/database-connections";
    private static final String ROOT_ELEMENT_NAME = "database-connections";

    public static DatabaseConnection[] load(Directory directory) throws IOException, JAXBException {
        try (InputStream configInput = directory.getAsInputStream(DATABASE_CONNECTIONS_XML_NAME)){
            List<DatabaseConnection> results = new ArrayList<>();
            bravura.sonata.buddy.dbconnection.dao.DatabaseConnections xmlDbConnections = unmarshalDatabaseConnections(configInput);

            for (bravura.sonata.buddy.dbconnection.dao.DatabaseConnection xmlConnection : xmlDbConnections.getDatabaseConnection()) {
                DatabaseConnection connection = new DatabaseConnection(
                        xmlConnection.getName(),
                        xmlConnection.getUrl(),
                        xmlConnection.getUser(),
                        xmlConnection.getPassword());
                results.add(connection);
            }
            return results.toArray(new DatabaseConnection[0]);
        } catch (UnmarshalException e) {
            Logger.getLogger(XmlDatabaseConnections.class.getName()).severe("Could not load database connection configuration");
            return new DatabaseConnection[0];
        } catch (FileNotFoundException e) {
            return new DatabaseConnection[0];
        }
    }

    private static DatabaseConnections unmarshalDatabaseConnections(InputStream configInput) throws JAXBException {
        JAXBContext jc = createJaxbContext();
        Unmarshaller unmarshaller = jc.createUnmarshaller();
        JAXBElement unmarshalledElement = (JAXBElement)unmarshaller.unmarshal(configInput);
        return (DatabaseConnections) unmarshalledElement.getValue();
    }

    public static void save(Directory directory, DatabaseConnection[] connections) throws IOException, JAXBException {
        try (OutputStream configOutput = directory.getAsOutputStream(DATABASE_CONNECTIONS_XML_NAME)) {
            bravura.sonata.buddy.dbconnection.dao.DatabaseConnections xmlDbConnections =
                    new bravura.sonata.buddy.dbconnection.dao.DatabaseConnections();
            Arrays.stream(connections).map(c -> {
                bravura.sonata.buddy.dbconnection.dao.DatabaseConnection xmlConn =
                        new bravura.sonata.buddy.dbconnection.dao.DatabaseConnection();
                xmlConn.setName(c.getName());
                xmlConn.setUrl(c.getUrl());
                xmlConn.setUser(c.getUser());
                xmlConn.setPassword(c.getPassword());

                return xmlConn;
            }).forEach(
                    xc -> {
                        xmlDbConnections.getDatabaseConnection().add(xc);
                    }
            );
            marshalDatabseConnections(configOutput, xmlDbConnections);
        }
    }

    private static void marshalDatabseConnections(OutputStream configOutput, DatabaseConnections xmlDbConnections) throws JAXBException {
        JAXBContext jc = createJaxbContext();
        Marshaller marshaller = jc.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
        marshaller.marshal(new JAXBElement<>(new QName(NAMESPACE_URI, ROOT_ELEMENT_NAME),
                DatabaseConnections.class, xmlDbConnections), configOutput);
    }

    private static JAXBContext createJaxbContext() throws JAXBException {
        return JAXBContext.newInstance(
                bravura.sonata.buddy.dbconnection.dao.ObjectFactory.class);

    }
}
