package bravura.sonata.buddy;


import bravura.sonata.buddy.codetypeviewer.CodesAndTypesTab;
import bravura.sonata.buddy.common.dbconnection.DatabaseConnectionPicker;
import bravura.sonata.buddy.common.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.config.Preferences;
import bravura.sonata.buddy.navigsearch.NavigSearchTab;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;


public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane tabs;

    MainWindow(ApplicationContext appContext) {
        super("Sonata BuddyLauncher");
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        Preferences.set().searchDelay(500 * 1000 * 1000);

        Container contentPane = getContentPane();

        JPanel upperPanel = new JPanel(new BorderLayout());
        DatabaseConnectionPicker dbConnectionPicker = appContext.getBean(DatabaseConnectionPicker.class);
        upperPanel.add(dbConnectionPicker, BorderLayout.EAST);
        contentPane.add(upperPanel, BorderLayout.NORTH);

        tabs = new JTabbedPane();

        contentPane.add(tabs, BorderLayout.CENTER);

        CodesAndTypesTab codesAndTypesTab = appContext.getBean(CodesAndTypesTab.class);
        tabs.addTab("Codes & Types", codesAndTypesTab);

        NavigSearchTab navigSearchTab = appContext.getBean(NavigSearchTab.class);
        tabs.addTab("Navigator Search", navigSearchTab);

        pack();

        DatabaseConnections dbConnections = appContext.getBean(DatabaseConnections.class);
        addWindowListener(new ConnectionClosingWindowListener(dbConnections));
    }

    private static class ConnectionClosingWindowListener extends WindowAdapter {
        private final DatabaseConnections connections;

        public ConnectionClosingWindowListener(DatabaseConnections connections) {
            this.connections = connections;
        }

        @Override
        public void windowClosing(WindowEvent e) {
            connections.closeAll();
        }
    }
}
