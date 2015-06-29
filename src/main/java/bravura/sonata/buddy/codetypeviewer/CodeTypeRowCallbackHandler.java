package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.codetypeviewer.datamodel.Code;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;
import org.springframework.jdbc.core.RowCallbackHandler;
import org.springframework.util.ObjectUtils;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

/**
 * Created by tszymanski on 25/06/2015.
 */
class CodeTypeRowCallbackHandler implements RowCallbackHandler {
    private List<CodeType> codeTypes = new LinkedList<>();

    private long currentCodeTypeId;
    private String currentCodeTypeCode;
    private String currentCodeTypeDescription;

    private List<Code> currentCodes;

    @Override
    public void processRow(ResultSet resultSet) throws SQLException {
        String codeTypeCode = resultSet.getString("CDTY_CODE");
        if (!ObjectUtils.nullSafeEquals(codeTypeCode, currentCodeTypeCode)) {
            addCurrentCodeType();
            currentCodeTypeId = resultSet.getLong("CDTY_ID");
            currentCodeTypeCode = codeTypeCode;
            currentCodeTypeDescription = resultSet.getString("CDTY_DESCRIPTION");
            currentCodes = new LinkedList<>();
        }

        long codeId = resultSet.getLong("CODE_ID");
        String codeCode = resultSet.getString("CODE_CODE");
        String codeDescription = resultSet.getString("CODE_DESCRIPTION");
        String codeShortDesc = resultSet.getString("CODE_SHORT_DESCRIPTION");
        String codeTags = resultSet.getString("CODE_TAGS");
        Code c = new Code(codeId, codeCode, codeDescription, codeShortDesc, codeTags);
        currentCodes.add(c);
    }

    private void addCurrentCodeType() {
        if (currentCodes != null) {
            CodeType ct = new CodeType(
                    currentCodeTypeId, currentCodeTypeCode, currentCodeTypeDescription, currentCodes);
            codeTypes.add(ct);
            currentCodes = null;
        }
    }

    public void end() {
        addCurrentCodeType();
    }

    public List<CodeType> getCodeTypes() {
        return codeTypes;
    }
}
