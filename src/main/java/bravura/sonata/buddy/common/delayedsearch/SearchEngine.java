package bravura.sonata.buddy.common.delayedsearch;

import bravura.sonata.buddy.navigsearch.NavigatorSearchResult;

import java.util.Collection;

public interface SearchEngine<T> {

    /**
     * Called when the quiet period ends as a command to execute the search.
     * @param searchCriteria The query criteria
     */
    void search(T searchCriteria);
}
