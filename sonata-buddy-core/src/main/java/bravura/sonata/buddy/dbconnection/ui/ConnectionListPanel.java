package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.dbconnection.ConnectionValidator;
import bravura.sonata.buddy.dbconnection.DatabaseConnection;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;

import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.util.List;
import java.util.stream.Stream;

/**
 * Created by tszymanski on 19/09/2015.
 */
class ConnectionListPanel extends JPanel {

    interface ConnectionListListener {
        void connectionSelected(ConnectionModel connection);
        void connectionDeselected();
    }

    private DatabaseConnections connections;
    private JList<ConnectionModel> connectionList;
    private DefaultListModel<ConnectionModel> connectionListModel;
    private ConnectionListListener listener;
    private JButton addButton;
    private JButton deleteButton;
    private ConnectionValidator connectionValidator;

    ConnectionListPanel(DatabaseConnections connections, ConnectionValidator connectionValidator) {
        this.connections = connections;
        this.connectionValidator = connectionValidator;

        setLayout(new GridBagLayout());

        connectionListModel = new DefaultListModel();
        Arrays.stream(connections.all()).forEach(c -> {
            connectionListModel.addElement(new ConnectionModel(c));
        });
        connectionList = new JList(connectionListModel);
        connectionList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        connectionList.addListSelectionListener(e -> {
            if (listener != null) {
                int selectionIndex = connectionList.getSelectionModel().getMaxSelectionIndex();
                if (selectionIndex >= 0) {
                    onConnectionSelected(connectionListModel.get(selectionIndex));
                } else {
                    onConnectionDeselected();
                }
            }
        });

        addButton = new JButton("Add");
        addButton.addActionListener(e -> {
            ConnectionModel newConnection = new ConnectionModel();
            newConnection.setName("New connection");
            connectionListModel.addElement(newConnection);
            connectionList.setSelectedValue(newConnection, true);
        });

        deleteButton = new JButton("Delete");
        deleteButton.addActionListener(e -> {
            int selectionIndex = connectionList.getSelectionModel().getMaxSelectionIndex();
            if (selectionIndex >= 0) {
                connectionListModel.remove(selectionIndex);
            }
        });

        buildUi();
    }

    public void selectDefaultConnection() {
        DatabaseConnection current = connections.getCurrent();
        ConnectionModel connectionModel;
        if (current == null) {
            connectionModel = null;
        } else {
            connectionModel = findConnectionModel(current);
        }

        if (connectionModel == null) {
            onConnectionDeselected();
        } else {
            connectionList.setSelectedValue(connectionModel, true);
        }
    }

    public boolean validateConnections() {
        connectionModelsAsStream().forEach(cm -> {});
        return true;
    }

    private ConnectionModel findConnectionModel(DatabaseConnection current) {
        Enumeration<ConnectionModel> elements = connectionListModel.elements();
        while (elements.hasMoreElements()) {
            ConnectionModel model = elements.nextElement();
            if (model.getName().equals(current.getName())) {
                return model;
            }
        }
        return null;
    }

    private Stream<ConnectionModel> connectionModelsAsStream() {
        return Collections.list(connectionListModel.elements()).stream();
    }

    private void onConnectionDeselected() {
        deleteButton.setEnabled(false);
        listener.connectionDeselected();
    }

    private void onConnectionSelected(ConnectionModel connection) {
        deleteButton.setEnabled(true);
        listener.connectionSelected(connection);
    }

    private void buildUi() {
        GridBagConstraints c = new GridBagConstraints();
        c.gridx = 0;
        c.gridy = 0;
        c.weightx = 1.0;
        c.weighty = 1.0;
        c.fill = GridBagConstraints.BOTH;
        c.ipadx = 5;
        c.ipady = 2;
        add(connectionList, c);

        JPanel buttonsPanel = new JPanel();
        buttonsPanel.add(addButton);
        buttonsPanel.add(deleteButton);

        c.gridy = 1;
        c.weighty = 0;
        add(buttonsPanel, c);
        setMinimumSize(new Dimension(150, 0));
    }

    void setConnectionListListener(ConnectionListListener listener) {
        this.listener = listener;
    }

    public ConnectionModel[] getConnections() {
        List<ConnectionModel> modelsAsList = new ArrayList<>();
        for (Object e : connectionListModel.toArray()) {
            modelsAsList.add((ConnectionModel)e);
        }
        return modelsAsList.toArray(new ConnectionModel[0]);
    }
}
