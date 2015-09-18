//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.11 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2015.07.03 at 03:57:22 PM CEST 
//


package bravura.sonata.buddy.config.xmlconfig;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the bravura.sonata.buddy.config.xmlconfig package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _DatabaseConnections_QNAME = new QName("http://www.bravurasolutions.com/sonatabuddy/database-connections", "database-connections");
    private final static QName _Preferences_QNAME = new QName("http://www.bravurasolutions.com/sonatabuddy/preferences", "preferences");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: bravura.sonata.buddy.config.xmlconfig
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link DatabaseConnections }
     * 
     */
    public DatabaseConnections createDatabaseConnections() {
        return new DatabaseConnections();
    }

    /**
     * Create an instance of {@link DatabaseConnection }
     * 
     */
    public DatabaseConnection createDatabaseConnection() {
        return new DatabaseConnection();
    }

    /**
     * Create an instance of {@link Preferences }
     * 
     */
    public Preferences createPreferences() {
        return new Preferences();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DatabaseConnections }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bravurasolutions.com/sonatabuddy/database-connections", name = "database-connections")
    public JAXBElement<DatabaseConnections> createDatabaseConnections(DatabaseConnections value) {
        return new JAXBElement<DatabaseConnections>(_DatabaseConnections_QNAME, DatabaseConnections.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link Preferences }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://www.bravurasolutions.com/sonatabuddy/preferences", name = "preferences")
    public JAXBElement<Preferences> createPreferences(Preferences value) {
        return new JAXBElement<Preferences>(_Preferences_QNAME, Preferences.class, null, value);
    }

}
