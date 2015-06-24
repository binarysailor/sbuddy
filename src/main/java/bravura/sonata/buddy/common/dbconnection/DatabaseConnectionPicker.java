package bravura.sonata.buddy.common.dbconnection;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

public class DatabaseConnectionPicker extends JPanel {

    private JComboBox connectionDropdown;
    private DatabaseConnections connections;

    public DatabaseConnectionPicker(DatabaseConnections connections) {
        this.connections = connections;

        LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ComboBoxModel<DatabaseConnection> connectionModel = new DefaultComboBoxModel<DatabaseConnection>(connections.all());
        connectionDropdown = new JComboBox(connectionModel);
        add(connectionDropdown, BorderLayout.EAST);

        JButton addConnection = new JButton("New...");
        add(addConnection);
    }

}
