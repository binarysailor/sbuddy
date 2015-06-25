package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.codetypeviewer.CodesAndTypesDAO;
import bravura.sonata.buddy.codetypeviewer.datamodel.Code;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;
import bravura.sonata.buddy.common.dbconnection.DatabaseConnections;

import java.util.Arrays;
import java.util.List;

/**
 * Created by tszymanski on 25/06/2015.
 */
class CodesAndTypesDAOImpl implements CodesAndTypesDAO {

    private DatabaseConnections connections;

    CodesAndTypesDAOImpl(DatabaseConnections connections) {
        this.connections = connections;
    }

    @Override
    public List<CodeType> findByCodeTypeId(long codeTypeId) {
        return dummyResult();
    }

    @Override
    public List<CodeType> findByCodeTypeCode(String codeTypeCode) {
        return dummyResult();
    }

    @Override
    public List<CodeType> findByCodeId(long codeId) {
        return dummyResult();
    }

    @Override
    public List<CodeType> findByCodeCode(String codeCode) {
        return dummyResult();
    }

    private List<CodeType> dummyResult() {
        Code c = new Code(1, "ABCD", "Description", "SD", "tag=3");
        CodeType ct = new CodeType(100, "PQRS", "Code Type Description", Arrays.asList(c));
        return Arrays.asList(ct);
    }
}
