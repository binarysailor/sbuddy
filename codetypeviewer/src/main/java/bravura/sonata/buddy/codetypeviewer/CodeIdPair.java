package bravura.sonata.buddy.codetypeviewer;

import java.util.Optional;

/**
 * Created by tszymanski on 25/06/2015.
 */
class CodeIdPair {
    private Long id;
    private String code;

    public Long getId() {
        return id;
    }

    void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    void setCode(String code) {
        this.code = code;
    }

    public Optional<CodeIdPair> asOptional() {
        if (id == null && code == null) {
            return Optional.empty();
        } else {
            return Optional.of(this);
        }
    }
}
