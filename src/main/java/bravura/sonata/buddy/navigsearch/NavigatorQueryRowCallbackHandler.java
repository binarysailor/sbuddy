package bravura.sonata.buddy.navigsearch;

import org.springframework.jdbc.core.RowCallbackHandler;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

/**
 * Created by tszymanski on 23/06/2015.
 */
class NavigatorQueryRowCallbackHandler implements RowCallbackHandler {
    private Deque<NavigatorLocation> locations = new LinkedList<>();
    private boolean addNewLocation = true;

    @Override
    public void processRow(ResultSet resultSet) throws SQLException {
        String optionName = resultSet.getString("NOPN_NAME");
        addParentOption(optionName);

        boolean hasParent = resultSet.getObject("NHRY_NAV_PARENT_ID") != null;
        addNewLocation = !hasParent;
    }

    private void addParentOption(String optionName) {
        if (addNewLocation) {
            locations.add(new NavigatorLocation());
        }
        locations.getLast().addParentOption(optionName);
    }

    Collection<NavigatorLocation> getNavigatorLocations() {
        return locations;
    }
}
