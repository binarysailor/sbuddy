package bravura.sonata.buddy.common.dbconnection;

class DatabaseConnectionDAOImpl {
    DatabaseConnection[] getDatabaseConnections() {
        // TODO
        return new DatabaseConnection[] {
                new DatabaseConnection("Kaedwen", "jdbc:oracle:thin:@//127.0.0.1:1522/oracle12c", "kaedwen47", "password"),
                new DatabaseConnection("Bergen", "url2", "user2", "password2")
        };
    }
}
