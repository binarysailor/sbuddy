package bravura.sonata.buddy;


import bravura.sonata.buddy.dbconnection.ui.DatabaseConnectionPicker;
import bravura.sonata.buddy.dbconnection.DatabaseConnections;
import bravura.sonata.buddy.config.Preferences;
import bravura.sonata.buddy.ui.ComponentDefaults;
import bravura.sonata.buddy.ui.SonataBuddyTab;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Collection;
import java.util.Map;


public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private final JTabbedPane tabs;

    MainWindow(ApplicationContext appContext) {
        super("Sonata Buddy");

        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        boolean preferencesLoaded = Preferences.load();
        if (!preferencesLoaded) {
            // defaults
            Preferences.set().searchDelay(500 * 1000 * 1000);
            Preferences.save();
        }

        Container contentPane = getContentPane();

        JPanel upperPanel = new JPanel(new BorderLayout());
        DatabaseConnectionPicker dbConnectionPicker = appContext.getBean(DatabaseConnectionPicker.class);
        upperPanel.add(dbConnectionPicker, BorderLayout.EAST);
        contentPane.add(upperPanel, BorderLayout.NORTH);

        tabs = new JTabbedPane();

        contentPane.add(tabs, BorderLayout.CENTER);

        addModuleTabs(appContext);

        pack();

        DatabaseConnections dbConnections = appContext.getBean(DatabaseConnections.class);
        addWindowListener(new ConnectionClosingWindowListener(dbConnections));
    }

    private void addModuleTabs(ApplicationContext appContext) {
        Map<String, SonataBuddyModule> modules = appContext.getBeansOfType(SonataBuddyModule.class);
        for (SonataBuddyModule module : modules.values()) {
            Collection<SonataBuddyTab> moduleTabs = module.getTabs();
            for (SonataBuddyTab moduleTab : moduleTabs) {
                JPanel tabUi = moduleTab.createUI();
                ComponentDefaults.setupTab(tabUi );
                tabs.addTab(moduleTab.getLabel(), tabUi);
            }
        }
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
