package bravura.sonata.buddy.common.delayedsearch;

import bravura.sonata.buddy.navigsearch.NavigatorSearchResult;

import java.util.Collection;

public interface SearchEngine {

    /**
     * Called when the quiet period ends as a command to execute the search.
     * @param query The query string
     */
    void search(String query);
}
