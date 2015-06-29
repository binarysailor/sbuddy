package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.codetypeviewer.datamodel.Code;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;
import bravura.sonata.buddy.common.dbconnection.DatabaseConnections;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

/**
 * Created by tszymanski on 25/06/2015.
 */
class CodesAndTypesDAOImpl implements CodesAndTypesDAO {

    private static final String SELECT_FROM =
            "select cdty_id, cdty_code, cdty_description, code_id, code_code, code_description, code_short_description, code_tags " +
            "from tscm_code_type join tscm_code on cdty_id = code_code_type_id ";

    private static final String ORDER_BY =
            "order by cdty_code, code_sort_order, code_code";

    private DatabaseConnections connections;

    CodesAndTypesDAOImpl(DatabaseConnections connections) {
        this.connections = connections;
    }

    @Override
    public List<CodeType> findByCodeTypeId(long codeTypeId) {
        JdbcTemplate t = new JdbcTemplate(connections.getCurrentDataSource());
        CodeTypeRowCallbackHandler rowHandler = new CodeTypeRowCallbackHandler();
        t.query(SELECT_FROM + "where cdty_id = ? " + ORDER_BY,
                new Object[] { codeTypeId }, rowHandler);
        return finalize(rowHandler, null);
    }

    @Override
    public List<CodeType> findByCodeTypeCode(String codeTypeCode) {
        JdbcTemplate t = new JdbcTemplate(connections.getCurrentDataSource());
        CodeTypeRowCallbackHandler rowHandler = new CodeTypeRowCallbackHandler();
        t.query(SELECT_FROM + "where cdty_code = ? " + ORDER_BY,
                new Object[] { codeTypeCode }, rowHandler);
        return finalize(rowHandler, null);
    }

    @Override
    public List<CodeType> findByCodeId(long codeId) {
        JdbcTemplate t = new JdbcTemplate(connections.getCurrentDataSource());
        CodeTypeRowCallbackHandler rowHandler = new CodeTypeRowCallbackHandler();
        t.query(SELECT_FROM + "where cdty_id = (" +
                        "select code_code_type_id from tscm_code where code_id = ?) " + ORDER_BY,
                new Object[] { codeId }, rowHandler);
        return finalize(rowHandler, code -> code.getId() == codeId);
    }

    @Override
    public List<CodeType> findByCodeCode(String codeCode) {
        JdbcTemplate t = new JdbcTemplate(connections.getCurrentDataSource());
        CodeTypeRowCallbackHandler rowHandler = new CodeTypeRowCallbackHandler();
        t.query(SELECT_FROM + "where cdty_id in (" +
                        "select code_code_type_id from tscm_code where code_code = ?) " + ORDER_BY,
                new Object[]{codeCode}, rowHandler);
        return finalize(rowHandler, code -> code.getCode().equals(codeCode));
    }

    private List<CodeType> finalize(CodeTypeRowCallbackHandler handler, Predicate<Code> selection) {
        handler.end();
        List<CodeType> codeTypes = handler.getCodeTypes();
        if (selection != null) {
            for (CodeType codeType : codeTypes) {
                Optional<Code> toBeSelected = codeType.getCodes().stream().filter(selection).findFirst();
                if (toBeSelected.isPresent()) {
                    codeType.selectCode(toBeSelected.get());
                }
            }
        }
        return codeTypes;
    }
}
