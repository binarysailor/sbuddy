package bravura.sonata.buddy.config;

public class Preferences {

    static Preferences instance = new Preferences();
    static PreferencesSetter setter = new PreferencesSetter();

    private long searchDelay;

    Preferences() {
    }

    public static PreferencesSetter with() {
        return new PreferencesSetter();
    }

    public static Preferences getInstance() {
        return instance;
    }

    public static PreferencesSetter set() {
        return setter;
    }

    public long getSearchDelay() {
        return searchDelay;
    }

    void setSearchDelay(long searchDelay) {
        this.searchDelay = searchDelay;
    }
}
