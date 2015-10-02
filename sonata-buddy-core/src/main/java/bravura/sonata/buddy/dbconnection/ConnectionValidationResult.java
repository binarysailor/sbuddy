package bravura.sonata.buddy.dbconnection;

import java.util.EnumMap;

/**
 * Created by tszymanski on 01/10/2015.
 */
public class ConnectionValidationResult {
    public enum Field {
        NAME, URL, USER, PASSWORD
    }

    private final EnumMap<Field, String> errors = new EnumMap<>(Field.class);

    public void setError(Field field, String message) {
        errors.put(field, message);
    }

    public String getError(Field field) {
        return errors.get(field);
    }
}
