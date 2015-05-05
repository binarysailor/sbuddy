package bravura.sonata.buddy.common.dbconnection;

public class DatabaseConnection {
    private String name;
    private String url;
    private String user;
    private String password;

    public DatabaseConnection(String name, String url, String user, String password) {
        this.name = name;
        this.url = url;
        this.user = user;
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public String getUrl() {
        return url;
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }

    @Override
    public String toString() {
        return name;
    }
}
