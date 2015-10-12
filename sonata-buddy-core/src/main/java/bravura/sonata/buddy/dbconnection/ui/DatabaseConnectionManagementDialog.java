package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnectionConfigException;
import bravura.sonata.buddy.dbconnection.dao.DatabaseConnectionDAO;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tszymanski on 18/09/2015.
 */
public class DatabaseConnectionManagementDialog extends JDialog implements DialogButtonsPanel.DialogButtonsListener, ConnectionListPanel.ConnectionListListener {

    private DatabaseConnectionDAO connectionDao;
    private ConnectionListPanel connectionListPanel;
    private ConnectionDetailsPanel connectionDetailsPanel;

    DatabaseConnectionManagementDialog(Frame owner, ConnectionListPanel listPanel, ConnectionDetailsPanel detailsPanel, DatabaseConnectionDAO connectionDao) {
        super(owner, "Database connection management", true);
        this.connectionDao = connectionDao;

        setPreferredSize(new Dimension(480, 300));
        setResizable(false);

        listPanel.setConnectionListListener(this);
        this.connectionListPanel = listPanel;
        this.connectionDetailsPanel = detailsPanel;

        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, createLeft(), createRight());
        content.setDividerLocation(0.3);
        setContentPane(content);

        pack();

        initialize();
    }

    private void initialize() {
        connectionListPanel.selectDefaultConnection();
    }

    private JPanel createLeft() {
        return connectionListPanel;
    }

    private JPanel createRight() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.add(connectionDetailsPanel, BorderLayout.NORTH);
        rightPanel.add(new DialogButtonsPanel(this), BorderLayout.SOUTH);
        return rightPanel;
    }

    @Override
    public void onSave() {
        if (connectionListPanel.validateConnections()) {
            DatabaseConnection[] connections = buildDatabaseConnections();

            try {
                connectionDao.saveDatabaseConnections(connections);
            } catch (DatabaseConnectionConfigException e) {
                e.printStackTrace();
            }
        }
    }

    private DatabaseConnection[] buildDatabaseConnections() {
        ConnectionModel[] connectionModels = connectionListPanel.getConnections();
        DatabaseConnection[] connections = new DatabaseConnection[connectionModels.length];

        for (int i = 0; i < connectionModels.length; i++) {
            connections[i] = connectionModels[i].toConnection();
        }

        return connections;
    }

    @Override
    public void onCancel() {
        setVisible(false);
    }

    @Override
    public void connectionSelected(ConnectionModel connection) {
        connectionDetailsPanel.populate(connection);
    }

    @Override
    public void connectionDeselected() {
        connectionDetailsPanel.clear();
    }
}
