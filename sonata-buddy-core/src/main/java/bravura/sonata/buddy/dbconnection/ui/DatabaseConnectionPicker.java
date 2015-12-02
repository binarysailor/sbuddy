package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnectionConfigException;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.ui.WindowPosition;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.WindowListener;

public class DatabaseConnectionPicker extends JPanel {

    private final JComboBox connectionDropdown;
    private final DatabaseConnections connections;
    private final DbConnectionManagementDialogAssembler dialogAssembler;
    private final WindowListener dbConnectionMgmtCloseListener = new DatabaseConnectionMgmtDialogCloseListener(this);

    public DatabaseConnectionPicker(DatabaseConnections connections, DbConnectionManagementDialogAssembler assembler) {
        this.connections = connections;
        this.dialogAssembler = assembler;

        LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel("Database connection:");
        add(label);

        DefaultComboBoxModel<DatabaseConnection> connectionModel = new DefaultComboBoxModel<DatabaseConnection>();
        connectionDropdown = new JComboBox(connectionModel);
        connectionDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DatabaseConnection selectedConnection = (DatabaseConnection) e.getItem();
                connections.setCurrent(selectedConnection);
            }
        });
        initializeConnectionDropdownModel();
        add(connectionDropdown, BorderLayout.EAST);

        JButton addConnection = new JButton("Manage...");
        addConnection.addActionListener(e -> {
            SwingUtilities.invokeLater(() -> {
                Frame frame = findFrame();
                DatabaseConnectionManagementDialog databaseConnectionManagementDialog = assembler.createDbConnectionManagementDialog(frame);
                WindowPosition.centerRelativeTo(databaseConnectionManagementDialog, frame);
                databaseConnectionManagementDialog.addWindowListener(dbConnectionMgmtCloseListener);
                databaseConnectionManagementDialog.setVisible(true);
            });
        });
        add(addConnection);
    }

    private void initializeConnectionDropdownModel() {
        DefaultComboBoxModel<DatabaseConnection> connectionModel = (DefaultComboBoxModel<DatabaseConnection>) connectionDropdown.getModel();
        connectionModel.removeAllElements();
        for (DatabaseConnection connection : connections.all()) {
            connectionModel.addElement(connection);
        }
        connectionDropdown.insertItemAt(DatabaseConnection.empty(), 0);

        DatabaseConnection current = connections.getCurrent();
        if (current == null) {
            connectionModel.setSelectedItem(DatabaseConnection.empty());
        } else {
            connectionModel.setSelectedItem(current);
        }
    }

    public void reload() throws DatabaseConnectionConfigException {
        initializeConnectionDropdownModel();
    }

    private Frame findFrame() {
        Container parent = getParent();
        while (!(parent instanceof Frame) && parent != null) {
            parent = parent.getParent();
        }
        if (parent instanceof Frame) {
            return (Frame)parent;
        } else {
            return null;
        }
    }
}
