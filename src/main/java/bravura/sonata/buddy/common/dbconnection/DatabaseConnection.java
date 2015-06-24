package bravura.sonata.buddy.common.dbconnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.dao.DataAccessException;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;

public class DatabaseConnection {
    private String name;
    private String url;
    private String user;
    private String password;
    private ComboPooledDataSource dataSource;

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

    public synchronized DataSource getDataSource() {
        if (dataSource == null) {
            createDataSource();
        }
        return dataSource;
    }

    public synchronized void closeDataSource() {
        if (dataSource != null) {
            dataSource.close();
            dataSource = null;
        }
    }

    private void createDataSource() {
        ComboPooledDataSource ds = new ComboPooledDataSource();
        try {
            ds.setDriverClass("oracle.jdbc.OracleDriver");
        } catch (PropertyVetoException e) {
            e.printStackTrace();
        }
        ds.setJdbcUrl(url);
        ds.setUser(user);
        ds.setPassword(password);
        ds.setMinPoolSize(1);
        ds.setMaxPoolSize(2);
        ds.setAcquireIncrement(1);
        dataSource = ds;
    }

    @Override
    public String toString() {
        return name;
    }
}
