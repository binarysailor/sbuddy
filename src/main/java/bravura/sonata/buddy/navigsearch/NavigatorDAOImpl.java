package bravura.sonata.buddy.navigsearch;

import bravura.sonata.buddy.common.dbconnection.DatabaseConnections;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.jdbc.core.RowMapper;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Deque;
import java.util.LinkedList;

public class NavigatorDAOImpl implements NavigatorDAO {

    private DatabaseConnections connections;
    private RowMapper<NavigatorLocation> navigatorLocationRowMapper;

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
                        "start with opt.nopn_name like ?",
                new Object[]{"%" + optionNameFragment + "%"},
                rowHandler);

        return rowHandler.getNavigatorLocations();
    }
}
