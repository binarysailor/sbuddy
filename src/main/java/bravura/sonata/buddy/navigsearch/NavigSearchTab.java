package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.BuddyTabBuilder;
import bravura.sonata.buddy.common.delayedsearch.SearchEngine;
import bravura.sonata.buddy.common.delayedsearch.SearchTextListener;
import bravura.sonata.buddy.config.Preferences;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.Dimension;
import java.util.Collection;

public class NavigSearchTab extends JPanel implements SearchEngine {

    private JTextField searchField;
    private JTable resultTable;
    private DefaultTableModel resultTableModel;
    private NavigatorDAO navigatorDAO;
    private String recentSearchedQuery;
    private JLabel searchedQueryLabel;

    public NavigSearchTab(Preferences preferences, NavigatorDAO navigatorDAO) {
        this.navigatorDAO = navigatorDAO;

        new BuddyTabBuilder().setupTab(this);
        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));

        JPanel searchFieldPanel = new JPanel();
        searchFieldPanel.setLayout(new BoxLayout(searchFieldPanel, BoxLayout.X_AXIS));
        searchFieldPanel.add(new JLabel("Menu item name:"));
        searchField = new JTextField(15);
        searchField.setMaximumSize(new Dimension(200, 24));
        searchField.addKeyListener(new SearchTextListener(this, preferences.getSearchDelay()));
        searchFieldPanel.add(searchField);

        add(searchFieldPanel);

        searchedQueryLabel = new JLabel();
        searchedQueryLabel.setHorizontalAlignment(JLabel.LEFT);
        add(searchedQueryLabel);

        resultTableModel = new DefaultTableModel(new String[] {"Navigator path"}, 0);
        resultTable = new JTable(resultTableModel);
        add(resultTable);
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
        System.out.println(String.format("Navigator locations for query '%s' found: %d",
                recentSearchedQuery, locations.size()));
        SwingUtilities.invokeLater(() -> {
            searchedQueryLabel.setText("Showing results for: " + recentSearchedQuery);
            resultTableModel.setRowCount(0);
            for (NavigatorLocation location : locations) {
                resultTableModel.addRow(new Object[] { location.asPathString() });
            }
        });

    }
}
