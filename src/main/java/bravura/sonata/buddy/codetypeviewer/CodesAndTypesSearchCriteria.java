package bravura.sonata.buddy.codetypeviewer;

import java.util.Optional;

/**
 * Created by tszymanski on 24/06/2015.
 */
class CodesAndTypesSearchCriteria {
    private Optional<CodeIdPair> codeTypeCriteria = Optional.empty();
    private Optional<CodeIdPair> codeCriteria = Optional.empty();
    private static final CodeIdPair EMPTY_CRITERIA = new CodeIdPair();

    public CodesAndTypesSearchCriteria(Optional<CodeIdPair> codeCriteria, Optional<CodeIdPair> codeTypeCriteria) {
        this.codeCriteria = codeCriteria;
        this.codeTypeCriteria = codeTypeCriteria;
    }

    public String getCodeTypeCode() {
        return codeTypeCriteria.orElse(EMPTY_CRITERIA).getCode();
    }

    public Long getCodeTypeId() {
        return codeTypeCriteria.orElse(EMPTY_CRITERIA).getId();
    }

    public String getCodeCode() {
        return codeCriteria.orElse(EMPTY_CRITERIA).getCode();
    }

    public Long getCodeId() {
        return codeCriteria.orElse(EMPTY_CRITERIA).getId();
    }
}
