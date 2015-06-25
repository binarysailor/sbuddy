package bravura.sonata.buddy.common.dbconnection;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.LayoutManager;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class DatabaseConnectionPicker extends JPanel {

    private JComboBox connectionDropdown;
    private DatabaseConnections connections;

    public DatabaseConnectionPicker(DatabaseConnections connections) {
        this.connections = connections;

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

        JButton addConnection = new JButton("New...");
        add(addConnection);
    }

}
