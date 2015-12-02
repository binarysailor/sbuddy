package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.ui.ComponentDefaults;
import bravura.sonata.buddy.ui.ComponentFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;

/**
 * Created by tszymanski on 23/09/2015.
 */
class ConnectionDetailsPanel extends JPanel {

    private final FocusListener FIELD_FOCUS_LISTENER = new FocusListener() {
        @Override
        public void focusGained(FocusEvent e) {
        }

        @Override
        public void focusLost(FocusEvent e) {
            saveFieldsToCurrentConnection();
        }
    };

    private JTextField nameField;
    private JTextField urlField;
    private JTextField userField;
    private JTextField passwordField;

    private ConnectionModel currentConnection = new ConnectionModel();

    ConnectionDetailsPanel() {

        nameField = createFieldWithFocusListener(ComponentDefaults.TextFieldSize.LARGE);
        urlField = createFieldWithFocusListener(ComponentDefaults.TextFieldSize.LARGE);
        userField = createFieldWithFocusListener(ComponentDefaults.TextFieldSize.MEDIUM);
        passwordField = createFieldWithFocusListener(ComponentDefaults.TextFieldSize.MEDIUM);

        buildUi();

        loadFieldsFromCurrentConnection();
    }

    public void populate(ConnectionModel connection) {
        saveFieldsToCurrentConnection();
        setCurrentConnection(connection);
    }

    public void clear() {
        saveFieldsToCurrentConnection();
        setCurrentConnection(null);
    }

    private JTextField createFieldWithFocusListener(ComponentDefaults.TextFieldSize fieldSize) {
        JTextField field = ComponentFactory.createDefaultTextField(fieldSize);
        field.addFocusListener(FIELD_FOCUS_LISTENER);
        return field;
    }

    private void setCurrentConnection(ConnectionModel currentConnection) {
        this.currentConnection = currentConnection;
        if (currentConnection != null) {
            loadFieldsFromCurrentConnection();
        } else {
            clearFields();
            disableFields();
        }
    }

    private void enableFields() {
        toggleFields(true);
    }

    private void disableFields() {
        toggleFields(false);
    }

    private void clearFields() {
        nameField.setText("");
        urlField.setText("");
        userField.setText("");
        passwordField.setText("");
    }

    private void toggleFields(boolean enable) {
        nameField.setEnabled(enable);
        urlField.setEnabled(enable);
        userField.setEnabled(enable);
        passwordField.setEnabled(enable);
    }

    private void loadFieldsFromCurrentConnection() {
        enableFields();
        nameField.setText(currentConnection.getName());
        urlField.setText(currentConnection.getUrl());
        userField.setText(currentConnection.getUser());
        passwordField.setText(currentConnection.getPassword());
    }

    private void saveFieldsToCurrentConnection() {
        if (currentConnection != null) {
            currentConnection.setName(nameField.getText());
            currentConnection.setUrl(urlField.getText());
            currentConnection.setUser(userField.getText());
            currentConnection.setPassword(passwordField.getText());
        }
    }

    private void buildUi() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        GridBagConstraints c = new GridBagConstraints();

        c.gridx = 0;
        c.gridy = 0;
        c.anchor = GridBagConstraints.WEST;
        c.ipadx = 5;
        add(new JLabel("Name:"), c);
        c.gridx = 1;
        add(nameField, c);

        c.gridx = 0;
        c.gridy = 1;
        add(new JLabel("URL:"), c);
        c.gridx = 1;
        add(urlField, c);

        c.gridx = 0;
        c.gridy = 2;
        add(new JLabel("User:"), c);
        c.gridx = 1;
        add(userField, c);

        c.gridx = 0;
        c.gridy = 3;
        add(new JLabel("Password:"), c);
        c.gridx = 1;
        add(passwordField, c);
    }
}
