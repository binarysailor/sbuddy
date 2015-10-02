package bravura.sonata.buddy.dbconnection;

import org.springframework.util.StringUtils;

/**
 * Created by tszymanski on 01/10/2015.
 */
class ConnectionValidatorImpl implements ConnectionValidator {
    @Override
    public ConnectionValidationResult validate(DatabaseConnection connection) {
        ConnectionValidationResult result = new ConnectionValidationResult();

        validateName(connection.getName(), result);
        validateUrl(connection.getUrl(), result);
        validateUser(connection.getUser(), result);
        validatePassword(connection.getUser(), result);
        
        return result;
    }

    private void validateName(String name, ConnectionValidationResult result) {
        if (StringUtils.isEmpty(name)) {
            putErrorEmpty(ConnectionValidationResult.Field.NAME, result);
        }
    }

    private void validateUrl(String url, ConnectionValidationResult result) {
        if (StringUtils.isEmpty(url)) {
            putErrorEmpty(ConnectionValidationResult.Field.URL, result);
        }
    }

    private void validateUser(String user, ConnectionValidationResult result) {

    }

    private void validatePassword(String user, ConnectionValidationResult result) {

    }

    private void putErrorEmpty(ConnectionValidationResult.Field field, ConnectionValidationResult result) {
        result.setError(field, "Please enter a value for this field");
    }
}
