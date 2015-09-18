package bravura.sonata.buddy.common.dbconnection;

import javax.swing.*;
import java.awt.Frame;
import java.awt.GridBagLayout;

/**
 * Created by tszymanski on 18/09/2015.
 */
public class DatabaseConnectionManagementDialog extends JDialog {
    public DatabaseConnectionManagementDialog(Frame owner) {
        super(owner, "Database connection management", true);
        GridBagLayout layoutManager = new GridBagLayout();
        setLayout(layoutManager);
    }
}
