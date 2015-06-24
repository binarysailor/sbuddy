package bravura.sonata.buddy.navigsearch;

import java.util.Collection;

public class NavigatorSearchResult {
    private String query;
    private Collection<NavigatorLocation> foundLocations;

    public NavigatorSearchResult(String query, Collection<NavigatorLocation> foundLocations) {
        this.foundLocations = foundLocations;
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public Collection<NavigatorLocation> getFoundLocations() {
        return foundLocations;
    }
}
