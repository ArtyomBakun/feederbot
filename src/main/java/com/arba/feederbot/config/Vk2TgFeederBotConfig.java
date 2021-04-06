package com.arba.feederbot.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class Vk2TgFeederBotConfig {
    public static final String TG_BOT_TOKEN_KEY = "tg.bot.token";
    public static final String TG_BOT_USERNAME_KEY = "tg.bot.username";
    public static final String VK_ACCESS_TOKEN_KEY = "vk.access.token";

    public static String getPropertyByKey(String key) {
        try (InputStream prod = resourceAsStream("prod.properties"); InputStream mock = resourceAsStream("mock.properties")) {
            Properties prop = new Properties();
            prop.load(prod != null ? prod : mock);
            return prop.getProperty(key);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static InputStream resourceAsStream(String resource) {
        return Vk2TgFeederBotConfig.class.getClassLoader().getResourceAsStream(resource);
    }
}
