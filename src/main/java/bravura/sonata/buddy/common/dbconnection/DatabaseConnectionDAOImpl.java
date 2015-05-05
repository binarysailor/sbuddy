package bravura.sonata.buddy.common.dbconnection;

class DatabaseConnectionDAOImpl {
    DatabaseConnection[] getDatabaseConnections() {
        // TODO
        return new DatabaseConnection[] {
                new DatabaseConnection("Kaedwen", "url1", "user1", "password1"),
                new DatabaseConnection("Bergen", "url2", "user2", "password2")
        };
    }
}
