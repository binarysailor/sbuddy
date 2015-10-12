package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.ConnectionValidator;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.dbconnection.dao.DatabaseConnectionDAO;

import java.awt.*;

/**
 * Created by tszymanski on 05/10/2015.
 */
class DbConnectionManagementDialogAssemblerImpl implements DbConnectionManagementDialogAssembler {
    private DatabaseConnections dbConnections;
    private ConnectionValidator connectionValidator;
    private DatabaseConnectionDAO connectionDao;

    public DbConnectionManagementDialogAssemblerImpl(DatabaseConnectionDAO connectionDao, DatabaseConnections dbConnections, ConnectionValidator connectionValidator) {
        this.dbConnections = dbConnections;
        this.connectionDao = connectionDao;
        this.connectionValidator = connectionValidator;
    }

    @Override
    public DatabaseConnectionManagementDialog createDbConnectionManagementDialog(Frame frame) {
        ConnectionListPanel listPanel = new ConnectionListPanel(dbConnections, connectionValidator);
        ConnectionDetailsPanel detailsPanel = new ConnectionDetailsPanel();
        return new DatabaseConnectionManagementDialog(frame, listPanel, detailsPanel, connectionDao);
    }
}
