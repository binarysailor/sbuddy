package bravura.sonata.buddy.common.dbconnection;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.LayoutManager;

public class DatabaseConnectionPicker extends JPanel {

    private JComboBox connections;

    public DatabaseConnectionPicker() {
        LayoutManager layout = new BoxLayout(this, BoxLayout.X_AXIS);
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        ComboBoxModel<DatabaseConnection> connectionModel = new DefaultComboBoxModel<DatabaseConnection>(loadDatabaseConnections());
        connections = new JComboBox(connectionModel);
        add(connections, BorderLayout.EAST);

        JButton addConnection = new JButton("New...");
        add(addConnection);
    }

    private DatabaseConnection[] loadDatabaseConnections() {
        return new DatabaseConnectionDAOImpl().getDatabaseConnections();
    }
}
