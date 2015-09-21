package bravura.sonata.buddy.delayedsearch;

import bravura.sonata.buddy.quietperiod.QuietPeriod;
import bravura.sonata.buddy.quietperiod.QuietPeriodListener;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class SearchTextListener<T> implements KeyListener, QuietPeriodListener<T> {

    private final QuietPeriod<T> quietPeriod;
    private final SearchEngine<T> searchEngine;
    private final SearchCriteriaProducer<T> criteriaProducer;

    public SearchTextListener(SearchEngine searchEngine, SearchCriteriaProducer<T> criteriaProducer, long searchDelay) {
        this.searchEngine = searchEngine;
        this.criteriaProducer = criteriaProducer;
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
        quietPeriod.restartQuietPeriod(criteriaProducer.getSearchCriteria());
    }

    @Override
    public void quietPeriodFinished(T searchCriteria) {
        searchEngine.search(searchCriteria);
    }
}
