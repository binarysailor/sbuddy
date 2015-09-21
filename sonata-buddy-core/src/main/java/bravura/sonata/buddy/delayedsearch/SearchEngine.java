package bravura.sonata.buddy.delayedsearch;

public interface SearchEngine<T> {

    /**
     * Called when the quiet period ends as a command to execute the search.
     * @param searchCriteria The query criteria
     */
    void search(T searchCriteria);
}
