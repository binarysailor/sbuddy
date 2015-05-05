package bravura.sonata.buddy.config;

public class Configuration {

    static Configuration instance;
    long searchDelay;

    Configuration() {
    }

    public static ConfigurationBuilder with() {
        return new ConfigurationBuilder();
    }

    public static Configuration getInstance() {
        return instance;
    }

    public long getSearchDelay() {
        return searchDelay;
    }
}
