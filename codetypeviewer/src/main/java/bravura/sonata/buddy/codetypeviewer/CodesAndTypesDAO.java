package bravura.sonata.buddy.codetypeviewer;

import bravura.sonata.buddy.codetypeviewer.datamodel.Code;
import bravura.sonata.buddy.codetypeviewer.datamodel.CodeType;

import java.util.List;

/**
 * Created by tszymanski on 24/06/2015.
 */
public interface CodesAndTypesDAO {
    List<CodeType> findByCodeTypeId(long codeTypeId);
    List<CodeType> findByCodeTypeCode(String codeTypeCode);
    List<CodeType> findByCodeId(long codeId);
    List<CodeType> findByCodeCode(String codeCode);
}
