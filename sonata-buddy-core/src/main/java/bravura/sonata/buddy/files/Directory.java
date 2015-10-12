package bravura.sonata.buddy.files;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by tszymanski on 12/09/2015.
 */
public interface Directory {
    InputStream getAsInputStream(String name) throws FileNotFoundException;

    OutputStream getAsOutputStream(String databaseConnectionsXmlName) throws FileNotFoundException;
}
