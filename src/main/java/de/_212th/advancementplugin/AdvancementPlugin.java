package de._212th.advancementplugin;

import org.bukkit.ChatColor;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Properties;

public final class AdvancementPlugin extends JavaPlugin {

    private Properties messageProperties;

    @Override
    public void onEnable() {

        try {
            loadMessageProperties();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    @Override
    public void onDisable() {



    }

    private void loadMessageProperties() throws IOException {
        messageProperties = new Properties();
        messageProperties.load(getClassLoader().getResourceAsStream("messages.properties"));
    }

    public String getColoredMessage(String key) {
        return colorText(messageProperties.getProperty(key));
    }

    private String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
