package bravura.sonata.buddy.codetypeviewer.datamodel;

/**
 * Created by tszymanski on 24/06/2015.
 */
public class Code {
    private long id;
    private String code;
    private String description;
    private String shortDescription;
    private String tags;

    public Code(long id, String code, String description, String shortDescription, String tags) {
        this.id = id;
        this.code = code;
        this.description = description;
        this.shortDescription = shortDescription;
        this.tags = tags;
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

    public String getShortDescription() {
        return shortDescription;
    }

    public String getTags() {
        return tags;
    }
}
