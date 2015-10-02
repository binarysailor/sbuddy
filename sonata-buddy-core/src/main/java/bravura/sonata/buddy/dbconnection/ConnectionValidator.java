package bravura.sonata.buddy.dbconnection;

/**
 * Created by tszymanski on 01/10/2015.
 */
public interface ConnectionValidator {
    ConnectionValidationResult validate(DatabaseConnection connection);
}
