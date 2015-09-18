package bravura.sonata.buddy.codetypeviewer.datamodel;

import java.util.Collection;
import java.util.List;

/**
 * Created by tszymanski on 24/06/2015.
 */
public class CodeType {
    private long id;
    private String code;
    private String description;
    private List<Code> codes;
    private Code selectedCode;

    public CodeType(long id, String code, String description, List<Code> codes) {
        this.code = code;
        this.codes = codes;
        this.description = description;
        this.id = id;
    }

    public long getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    public Collection<Code> getCodes() {
        return codes;
    }

    public Code getSelectedCode() {
        return selectedCode;
    }

    public void selectCode(Code toBeSelected) {
        for (Code code : codes) {
            if (code.equals(toBeSelected)) {
                selectedCode = code;
                return;
            }
        }
        throw new IllegalArgumentException("The code to be selected is unknown");
    }
}
