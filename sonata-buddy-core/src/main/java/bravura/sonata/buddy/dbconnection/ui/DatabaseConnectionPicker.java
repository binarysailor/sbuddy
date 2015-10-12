package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.ui.WindowPosition;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;

public class DatabaseConnectionPicker extends JPanel {

    private final JComboBox connectionDropdown;
    private final DatabaseConnections connections;
    private final DbConnectionManagementDialogAssembler dialogAssembler;

    public DatabaseConnectionPicker(DatabaseConnections connections, DbConnectionManagementDialogAssembler assembler) {
        this.connections = connections;
        this.dialogAssembler = assembler;

        LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JLabel label = new JLabel("Database connection:");
        add(label);

        ComboBoxModel<DatabaseConnection> connectionModel = new DefaultComboBoxModel<DatabaseConnection>(connections.all());
        connectionDropdown = new JComboBox(connectionModel);
        connectionDropdown.addItemListener(e -> {
            if (e.getStateChange() == ItemEvent.SELECTED) {
                DatabaseConnection selectedConnection = (DatabaseConnection) e.getItem();
                connections.setCurrent(selectedConnection);
            }
        });
        add(connectionDropdown, BorderLayout.EAST);

        JButton addConnection = new JButton("Manage...");
        addConnection.addActionListener(e ->  {
            SwingUtilities.invokeLater(() -> {
                Frame frame = findFrame();
                DatabaseConnectionManagementDialog databaseConnectionManagementDialog = assembler.createDbConnectionManagementDialog(frame);
                WindowPosition.centerRelativeTo(databaseConnectionManagementDialog, frame);
                databaseConnectionManagementDialog.setVisible(true);
            });
        });
        add(addConnection);
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
