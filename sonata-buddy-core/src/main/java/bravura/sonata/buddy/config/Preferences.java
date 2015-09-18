package bravura.sonata.buddy.config;

import bravura.sonata.buddy.config.xmlconfig.XmlPreferences;

import java.io.File;

public class Preferences {

    static Preferences instance = new Preferences();
    static PreferencesSetter setter = new PreferencesSetter();

    private long searchDelay;

    Preferences() {
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

    public static boolean load() {
        return XmlPreferences.load();
    }

    public static void save() {

    }
}
