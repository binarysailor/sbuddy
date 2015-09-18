package bravura.sonata.buddy.files;

import java.io.FileNotFoundException;
import java.io.InputStream;

/**
 * Created by tszymanski on 12/09/2015.
 */
public interface Directory {
    InputStream getAsInputStream(String name) throws FileNotFoundException;
}
