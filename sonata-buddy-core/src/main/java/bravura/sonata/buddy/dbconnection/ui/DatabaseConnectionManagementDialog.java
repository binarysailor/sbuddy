package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnections;

import javax.swing.*;
import java.awt.*;

/**
 * Created by tszymanski on 18/09/2015.
 */
class DatabaseConnectionManagementDialog extends JDialog implements DialogButtonsPanel.DialogButtonsListener, ConnectionListPanel.ConnectionListListener {

    private DatabaseConnections connections;
    private ConnectionListPanel connectionListPanel;
    private ConnectionDetailsPanel connectionDetailsPanel;

    public DatabaseConnectionManagementDialog(Frame owner, DatabaseConnections connections) {
        super(owner, "Database connection management", true);
        this.connections = connections;

        setPreferredSize(new Dimension(480, 300));
        setResizable(false);

        JPanel left = createLeft(connections);
        JPanel right = createRight();

        JSplitPane content = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT, left, right);
        content.setDividerLocation(0.3);
        setContentPane(content);

        pack();

        initialize();
    }

    private void initialize() {
        connectionListPanel.selectDefaultConnection(connections);
    }

    private JPanel createLeft(DatabaseConnections connections) {
        connectionListPanel = new ConnectionListPanel(connections);
        connectionListPanel.setConnectionListListener(this);
        return connectionListPanel;
    }

    private JPanel createRight() {
        JPanel rightPanel = new JPanel(new BorderLayout());
        connectionDetailsPanel = new ConnectionDetailsPanel();
        rightPanel.add(connectionDetailsPanel, BorderLayout.NORTH);
        rightPanel.add(new DialogButtonsPanel(this), BorderLayout.SOUTH);
        return rightPanel;
    }

    @Override
    public void onSave() {

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
