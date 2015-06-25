package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.common.dbconnection.DatabaseConnections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import java.util.Collection;
import java.util.stream.Collectors;

class NavigatorDAOImpl implements NavigatorDAO {

    private DatabaseConnections connections;

    NavigatorDAOImpl(DatabaseConnections connections) {
        this.connections = connections;
    }

    @Override
    public Collection<NavigatorLocation> findLocations(String optionNameFragment) {
        JdbcTemplate template = new JdbcTemplate(connections.getCurrentDataSource());
        NavigatorQueryRowCallbackHandler rowHandler = new NavigatorQueryRowCallbackHandler();
        template.query(
                "select opt.nopn_name, h.nhry_nav_parent_id " +
                        "from tscm_nav_option opt " +
                        "join tscm_nav_hierarchy h on h.nhry_nav_child_id = opt.nopn_id " +
                        "connect by opt.nopn_id = prior h.nhry_nav_parent_id " +
                        "start with lower(opt.nopn_name) like ?",
                new Object[]{"%" + optionNameFragment.toLowerCase() + "%"},
                rowHandler);

        return filterLocations(rowHandler.getNavigatorLocations());
    }

    private Collection<NavigatorLocation> filterLocations(Collection<NavigatorLocation> navigatorLocations) {
        return navigatorLocations.stream().filter(location -> { return location.isClassicMenu(); })
                .collect(Collectors.toList());
    }
}
