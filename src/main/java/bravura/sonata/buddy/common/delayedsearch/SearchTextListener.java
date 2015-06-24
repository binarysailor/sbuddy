package bravura.sonata.buddy.common.delayedsearch;

import bravura.sonata.buddy.common.quietperiod.QuietPeriod;
import bravura.sonata.buddy.common.quietperiod.QuietPeriodListener;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchTextListener implements KeyListener, QuietPeriodListener<String> {

    private QuietPeriod<String> quietPeriod;
    private SearchEngine searchEngine;

    public SearchTextListener(SearchEngine searchEngine, long searchDelay) {
        this.searchEngine = searchEngine;
        quietPeriod = new QuietPeriod(this, searchDelay);
        quietPeriod.start();
    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

    @Override
    public void keyPressed(KeyEvent e) {
    }

    @Override
    public void keyReleased(KeyEvent e) {
        JTextField searchField = (JTextField)e.getComponent();
        String query = searchField.getText();
        quietPeriod.restartQuietPeriod(query);
    }

    @Override
    public void quietPeriodFinished(String query) {
        searchEngine.search(query);
    }
}
