package bravura.sonata.buddy.dbconnection;

import org.springframework.context.ApplicationEvent;

/**
 * Created by tszymanski on 02/11/2015.
 */
public class DatabaseConnectionConfigChangedEvent extends ApplicationEvent {
    public DatabaseConnectionConfigChangedEvent(Object source) {
        super(source);
    }
}
