package bravura.sonata.buddy.config;

public class PreferencesSetter {
    private long searchDelay = 1000;

    PreferencesSetter() {
    }

    public PreferencesSetter searchDelay(long delay) {
        Preferences.getInstance().setSearchDelay(delay);
        return this;
    }
}
