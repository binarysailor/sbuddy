package bravura.sonata.buddy;


import bravura.sonata.buddy.common.dbconnection.DatabaseConnectionPicker;
import bravura.sonata.buddy.config.Preferences;
import bravura.sonata.buddy.navigsearch.NavigSearchTab;
import org.springframework.context.ApplicationContext;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Container;


public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;

    private JTabbedPane tabs;

    MainWindow(ApplicationContext appContext) {


        super("Sonata BuddyLauncher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Preferences.set().searchDelay(1 * 1000 * 1000 * 1000);

        Container contentPane = getContentPane();

        JPanel upperPanel = new JPanel(new BorderLayout());
        DatabaseConnectionPicker dbConnectionPicker = appContext.getBean(DatabaseConnectionPicker.class);
        upperPanel.add(dbConnectionPicker, BorderLayout.EAST);
        contentPane.add(upperPanel, BorderLayout.NORTH);

        tabs = new JTabbedPane();
        contentPane.add(tabs, BorderLayout.CENTER);
        NavigSearchTab navigSearchTab = appContext.getBean(NavigSearchTab.class);
        tabs.addTab("Navigator Search", navigSearchTab);

        pack();
    }
}
