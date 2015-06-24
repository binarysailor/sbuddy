package bravura.sonata.buddy.codetypeviewer;

import java.util.Optional;

/**
 * Created by tszymanski on 24/06/2015.
 */
class CodesAndTypesSearchCriteria {
    private Optional<CodeOrId> codeTypeCriteria = Optional.empty();
    private Optional<CodeOrId> codeCriteria = Optional.empty();
    private static final CodeOrId EMPTY_CRITERIA = new CodeOrId();

    public boolean isByCodeType() {
        return codeTypeCriteria.isPresent();
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

    private static class CodeOrId {
        private Long id;
        private String code;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public String getCode() {
            return code;
        }

        public void setCode(String code) {
            this.code = code;
        }
    }
}
