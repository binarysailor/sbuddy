package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.BuddyTabBuilder;

import javax.swing.*;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.LayoutManager;

public class NavigSearchTab extends JPanel {
    JTextField searchField;

    public NavigSearchTab() {
        new BuddyTabBuilder().setupTab(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel searchFieldPanel = new JPanel();
        searchFieldPanel.setLayout(new BoxLayout(searchFieldPanel, BoxLayout.X_AXIS));
        searchFieldPanel.add(new JLabel("Menu item name:"));
        searchField = new JTextField(15);
        searchField.addKeyListener(new SearchTextListener(this));
        searchFieldPanel.add(searchField);

        add(searchFieldPanel);

        JTable results = new JTable();

        add(results);
    }
}
