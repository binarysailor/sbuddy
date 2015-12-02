package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.ConnectionValidator;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.dbconnection.dao.DatabaseConnectionDAO;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.awt.*;

/**
 * Created by tszymanski on 05/10/2015.
 */
class DbConnectionManagementDialogAssemblerImpl implements DbConnectionManagementDialogAssembler, ApplicationContextAware {
    private DatabaseConnections dbConnections;
    private ConnectionValidator connectionValidator;
    private DatabaseConnectionDAO connectionDao;
    private ApplicationContext appContext;

    public DbConnectionManagementDialogAssemblerImpl(DatabaseConnectionDAO connectionDao, DatabaseConnections dbConnections, ConnectionValidator connectionValidator) {
        this.dbConnections = dbConnections;
        this.connectionDao = connectionDao;
        this.connectionValidator = connectionValidator;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.appContext = applicationContext;
    }

    @Override
    public DatabaseConnectionManagementDialog createDbConnectionManagementDialog(Frame frame) {
        ConnectionListPanel listPanel = new ConnectionListPanel(dbConnections, connectionValidator);
        ConnectionDetailsPanel detailsPanel = new ConnectionDetailsPanel();
        DatabaseConnectionManagementDialog databaseConnectionManagementDialog = new DatabaseConnectionManagementDialog(frame, listPanel, detailsPanel, connectionDao);
        databaseConnectionManagementDialog.setApplicationEventPublisher(appContext);
        return databaseConnectionManagementDialog;
    }
}
