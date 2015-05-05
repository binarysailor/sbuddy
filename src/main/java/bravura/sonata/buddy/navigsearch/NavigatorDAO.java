package bravura.sonata.buddy.navigsearch;

import java.util.Collection;

public interface NavigatorDAO {
    Collection<NavigatorLocation> findLocations(String optionNameFragment);
}
