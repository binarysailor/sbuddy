package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.BuddyTabBuilder;
import bravura.sonata.buddy.common.delayedsearch.SearchEngine;
import bravura.sonata.buddy.common.delayedsearch.SearchTextListener;
import bravura.sonata.buddy.config.Preferences;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.border.BevelBorder;
import javax.swing.border.Border;
import javax.swing.table.DefaultTableModel;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.util.Collection;

public class NavigSearchTab extends JPanel implements SearchEngine {

    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel resultTableModel;
    private NavigatorDAO navigatorDAO;
    private String recentSearchedQuery;

    private JLabel searchedQueryTitle;
    private JLabel searchedQueryString;

    public NavigSearchTab(Preferences preferences, NavigatorDAO navigatorDAO) {
        this.navigatorDAO = navigatorDAO;

        new BuddyTabBuilder().setupTab(this);
        setLayout(new GridBagLayout());
        GridBagConstraints constraints = new GridBagConstraints();

        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel searchFieldPanel = new JPanel();
        searchFieldPanel.setLayout(new BoxLayout(searchFieldPanel, BoxLayout.X_AXIS));

        constraints.gridx = 0;
        constraints.gridy = 0;
        constraints.weightx = 0.15;
        constraints.anchor = GridBagConstraints.LINE_START;
        add(new JLabel("Menu item name:"), constraints);

        searchField = new JTextField(15);
        searchField.setMinimumSize(new Dimension(200, 24));
        searchField.setMaximumSize(new Dimension(200, 24));
        searchField.addKeyListener(new SearchTextListener(this, preferences.getSearchDelay()));
        constraints.gridx = 1;
        constraints.weightx = 0.85;
        add(searchField, constraints);

        searchedQueryTitle = new JLabel("Showing results for: ");

        constraints.gridx = 0;
        constraints.gridy = 2;
        constraints.weightx = 0;
        constraints.ipady = 6;
        add(searchedQueryTitle, constraints);
        searchedQueryTitle.setVisible(false);

        searchedQueryString = new JLabel();
        constraints.gridx = 1;
        add(searchedQueryString, constraints);

        resultTableModel = new DefaultTableModel(new String[] {"Navigator path"}, 0);
        resultTable = new JTable(resultTableModel);
        resultTable.setShowGrid(true);

        constraints.gridx = 0;
        constraints.gridy = 3;
        constraints.gridwidth = 2;
        constraints.weighty = 1.0;
        constraints.ipadx = 0;
        constraints.ipady = 0;
        constraints.anchor = GridBagConstraints.NORTH;
        constraints.fill = GridBagConstraints.HORIZONTAL;

        JScrollPane scrollPane = new JScrollPane(resultTable);
        scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        add(scrollPane, constraints);
    }

    @Override
    public void search(String navOptionNameFragment) {
        if (!StringUtils.isEmpty(navOptionNameFragment)
                && !ObjectUtils.nullSafeEquals(navOptionNameFragment, recentSearchedQuery)) {
            Collection<NavigatorLocation> locations = navigatorDAO.findLocations(navOptionNameFragment);
            recentSearchedQuery = navOptionNameFragment;
            displayResults(locations);
        }
    }

    private void displayResults(final Collection<NavigatorLocation> locations) {
        SwingUtilities.invokeLater(() -> {
            searchedQueryTitle.setVisible(true);
            searchedQueryString.setText(recentSearchedQuery + " (" + locations.size() + " locations)");
            resultTableModel.setRowCount(0);
            for (NavigatorLocation location : locations) {
                resultTableModel.addRow(new Object[] { location.asPathString() });
            }
        });

    }
}
