package com.epam.training.radu_andrei_chelu.webdriver.practice_task2.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.time.Duration;
import java.util.Properties;

public final class TestConfig {
    private static final String CONFIG_FILE = "config.properties";
    private static final Properties PROPS = load();

    private TestConfig() {
    }

    public static String homeUrl() {
        return get("home.url");
    }

    public static Duration timeout() {
        return seconds("timeout.seconds");
    }

    public static Duration optionalBannerTimeout() {
        return seconds("optional.banner.timeout.seconds");
    }

    private static Properties load() {
        Properties props = new Properties();
        try (InputStream in = TestConfig.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (in == null) {
                throw new IllegalStateException(CONFIG_FILE + " not found on the classpath");
            }
            props.load(in);
        } catch (IOException e) {
            throw new UncheckedIOException("Could not read " + CONFIG_FILE, e);
        }
        return props;
    }

    private static String get(String key) {
        String value = System.getProperty(key, PROPS.getProperty(key));
        if (value == null || value.isBlank()) {
            throw new IllegalStateException("Missing config value: " + key);
        }
        return value.trim();
    }

    private static Duration seconds(String key) {
        String value = get(key);
        try {
            return Duration.ofSeconds(Long.parseLong(value));
        } catch (NumberFormatException e) {
            throw new IllegalStateException("Config value '" + key + "' must be a whole number of seconds, got: " + value, e);
        }
    }
}
