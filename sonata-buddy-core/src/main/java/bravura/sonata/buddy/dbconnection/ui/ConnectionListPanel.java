package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;

import javax.swing.*;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Arrays;

/**
 * Created by tszymanski on 19/09/2015.
 */
class ConnectionListPanel extends JPanel {

    interface ConnectionListListener {
        void connectionSelected(ConnectionModel connection);
    }

    class ConnectionModel {
        private String name;
        private String url;
        private String user;
        private String password;

        public ConnectionModel() {
        }

        public ConnectionModel(DatabaseConnection connection) {
            this.name = connection.getName();
            this.url = connection.getUrl();
            this.user = connection.getUser();
            this.password = connection.getPassword();
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public String getUser() {
            return user;
        }

        public void setUser(String user) {
            this.user = user;
        }
    }

    private DatabaseConnections connections;
    private JList<ConnectionModel> connectionList;
    private DefaultListModel<ConnectionModel> connectionListModel;
    private ConnectionListListener listener;

    ConnectionListPanel(DatabaseConnections connections) {
        this.connections = connections;

        setLayout(new GridBagLayout());

        connectionListModel = new DefaultListModel();
        Arrays.stream(connections.all()).forEach(c -> {
            connectionListModel.addElement(new ConnectionModel(c));
        });

        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 5;
        c.ipady = 2;
        connectionList = new JList(connectionListModel);
        add(connectionList, c);

        JPanel buttonsPanel = new JPanel();
        JButton addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            ConnectionModel newConnection = new ConnectionModel();
            connectionListModel.addElement(newConnection);
            connectionList.setSelectedValue(newConnection, true);
            if (listener != null) {
                listener.connectionSelected(newConnection);
            }
        });
        buttonsPanel.add(addButton);
        JButton deleteButton = new JButton("Delete");
        buttonsPanel.add(deleteButton);

        c.gridy = 1;
        c.weighty = 0;
        add(buttonsPanel, c);
        setMinimumSize(new Dimension(150, 0));
    }

    void setConnectionListListener(ConnectionListListener listener) {
        this.listener = listener;
    }
}
