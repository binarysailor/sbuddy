package bravura.sonata.buddy.dbconnection;

import com.mchange.v2.c3p0.ComboPooledDataSource;
import org.springframework.util.ObjectUtils;

import javax.sql.DataSource;
import java.beans.PropertyVetoException;
import java.util.logging.Logger;

public class DatabaseConnection {

    private static DatabaseConnection EMPTY = new DatabaseConnection(ID.ZERO, "", "", "", "");

    public static DatabaseConnection empty() {
        return EMPTY;
    }

    public boolean isEmpty() {
        return id.isZero();
    }

    public static class ID {

        private static final ID ZERO = new ID(0);
        private static int lastId = 1;

        public static synchronized ID newInstance() {
            ID result = new ID(++ID.lastId);
            return result;
        }

        public static ID getZero() {
            return ZERO;
        }

        private int id;

        private ID(int id) {
            this.id = id;
        }

        @Override
        public boolean equals(Object obj) {
            return (obj instanceof ID && ((ID)obj).id == this.id);
        }

        @Override
        public int hashCode() {
            return id;
        }

        public boolean isZero() {
            return id == 0;
        }
    }

    private ID id;
    private final String name;
    private final String url;
    private final String user;
    private final String password;
    private ComboPooledDataSource dataSource;

    public DatabaseConnection(ID id, String name, String url, String user, String password) {
        this.id = id;
        this.name = name;
        this.url = url;
        this.user = user;
        this.password = password;

    }

    public DatabaseConnection(String name, String url, String user, String password) {
        this(ID.newInstance(), name, url, user, password);
    }

    public ID getId() {
        return id;
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
            Logger.getLogger(getClass().getName()).info(
                    "Closing database connection: " + dataSource.getJdbcUrl());
            dataSource.close();
            dataSource = null;
        }
    }

    private void createDataSource() {
        Logger.getLogger(getClass().getName()).info("Creating a database connection: " + url);
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

    public boolean isSameAs(DatabaseConnection other) {
        return ObjectUtils.nullSafeEquals(this.name, other.name)
                && ObjectUtils.nullSafeEquals(this.url, other.url)
                && ObjectUtils.nullSafeEquals(this.user, other.user)
                && ObjectUtils.nullSafeEquals(this.password, other.password);
    }
}
