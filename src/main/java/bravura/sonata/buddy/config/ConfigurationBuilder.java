package bravura.sonata.buddy.config;

public class ConfigurationBuilder {
    Configuration c = new Configuration();

    ConfigurationBuilder() {
    }

    public ConfigurationBuilder searchDelay(long delay) {
        c.searchDelay = delay;
        return this;
    }

    public void build() {
        Configuration.instance = c;
    }
}
