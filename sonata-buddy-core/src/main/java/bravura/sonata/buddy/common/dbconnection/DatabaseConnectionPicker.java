package bravura.sonata.buddy.common.dbconnection;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;

public class DatabaseConnectionPicker extends JPanel {

    private final JComboBox connectionDropdown;
    private final DatabaseConnections connections;

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
        final Frame frame = findFrame();
        addConnection.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new JDialog(frame, "Manage database connections", true).setVisible(true);
            }
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
