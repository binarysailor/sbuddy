package bravura.sonata.buddy;


import bravura.sonata.buddy.common.dbconnection.DatabaseConnectionPicker;
import bravura.sonata.buddy.config.Configuration;
import bravura.sonata.buddy.navigsearch.NavigSearchTab;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.BorderLayout;
import java.awt.Container;


public class MainWindow extends JFrame {

    private static final long serialVersionUID = 1L;
    
    private JTabbedPane tabs;

    MainWindow() {

        super("Sonata BuddyLauncher");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Configuration.with().searchDelay(1*1000*1000*1000).build();

        Container contentPane = getContentPane();

        JPanel upperPanel = new JPanel(new BorderLayout());
        upperPanel.add(new DatabaseConnectionPicker(), BorderLayout.EAST);
        contentPane.add(upperPanel, BorderLayout.NORTH);

        tabs = new JTabbedPane();
        contentPane.add(tabs, BorderLayout.CENTER);
        tabs.addTab("Navigator Search", new NavigSearchTab());

        pack();

    }
}
