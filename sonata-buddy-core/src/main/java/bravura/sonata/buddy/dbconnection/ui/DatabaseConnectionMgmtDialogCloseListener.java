package bravura.sonata.buddy.dbconnection.ui;

import bravura.sonata.buddy.ErrorReporter;
import bravura.sonata.buddy.dbconnection.DatabaseConnectionConfigException;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

/**
 * Created by tszymanski on 02/11/2015.
 */
public class DatabaseConnectionMgmtDialogCloseListener extends WindowAdapter {
    private DatabaseConnectionPicker connectionPicker;

    public DatabaseConnectionMgmtDialogCloseListener(DatabaseConnectionPicker databaseConnectionPicker) {
        this.connectionPicker = databaseConnectionPicker;
    }

    @Override
    public void windowClosed(WindowEvent event) {
        super.windowClosed(event);
        try {
            connectionPicker.reload();
        } catch (DatabaseConnectionConfigException e) {
            ErrorReporter.report(e);
        }
    }
}
