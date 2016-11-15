package common.configuration;

import common.browser.Browser;
import org.yaml.snakeyaml.Yaml;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Collections;
import java.util.Map;


public class Configuration {

    private static Map<String, String> config;
    public static Credentials credentials;


    public static Browser browser() {
        return Browser.lookUp(readProperty("browser"));
    }

    public static String ecommerceName() {
        return readProperty("ecommerceName");
    }

    public static Environment environment() {
        return Environment.lookUp(readProperty("env"));
    }

    public static String country() {
        return readProperty("country");
    }

    public static String language() {
        return readProperty("language");
    }

    public static String credentialsDirectoryPath() {
        return readProperty("credentialsPath");
    }

    public static String reportDirectoryPath() {
        return readProperty("reportsPath");
    }

    public static BrowserSize browserSize() {
        return new BrowserSize(readProperty("browserSize"));
    }

    public static Credentials credentials() {
        if (credentials == null) {
            credentials = new Credentials();
        }
        return credentials;
    }

    private static String readProperty(String propertyName) {
        return System.getProperty(propertyName) != null ? System.getProperty(propertyName) : config.get(propertyName);
    }

    private static Map loadConfigFrom(String configPath) {
        try {
            Yaml yaml = new Yaml();
            return (Map) yaml.load(new FileInputStream(new File(configPath)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException("Couldn't find property file for a given environment" ,e);
        }
    }

    private static void loadConfigFromFile() {
        String DEFAULT_CONFIG = "src/main/resources/configs/config_default.yml";
        String LOCAL_CONFIG = "src/main/resources/configs/config.yml";

        config = loadConfigFrom(DEFAULT_CONFIG);
        config.putAll(loadConfigFrom(LOCAL_CONFIG));
    }

    static {
        loadConfigFromFile();
    }

}
