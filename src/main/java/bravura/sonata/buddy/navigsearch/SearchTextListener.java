package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.common.quietperiod.QuietPeriod;
import bravura.sonata.buddy.common.quietperiod.QuietPeriodListener;
import bravura.sonata.buddy.config.Configuration;
import com.google.inject.Inject;

import javax.swing.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

class SearchTextListener implements KeyListener, QuietPeriodListener<String> {

    private QuietPeriod<String> quietPeriod;
    private NavigatorDAO dao;

    public SearchTextListener(NavigSearchTab navigSearchTab) {
        quietPeriod = new QuietPeriod(this, Configuration.getInstance().getSearchDelay());
        quietPeriod.start();
    }

    @Inject
    public void setDao(NavigatorDAO dao) {
        this.dao = dao;
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
        quietPeriod.restartQuietPeriod(searchField.getText());
    }

    @Override
    public void quietPeriodFinished(String query) {
        System.out.println("quiet period finished");
        dao.findLocations(query);
    }
}
