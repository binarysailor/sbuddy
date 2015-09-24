package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;

/**
 * Created by tszymanski on 24/09/2015.
 */
class ConnectionModel {
    private String name = "";
    private String url = "";
    private String user = "";
    private String password = "";

    public ConnectionModel() {
    }

    public ConnectionModel(DatabaseConnection connection) {
        this.name = connection.getName();
        this.url = connection.getUrl();
        this.user = connection.getUser();
        this.password = connection.getPassword();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return getName();
    }
}
