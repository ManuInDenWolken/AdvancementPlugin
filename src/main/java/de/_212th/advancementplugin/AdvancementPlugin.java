package de._212th.advancementplugin;

import de._212th.advancementplugin.core.AdvancementLoader;
import de._212th.advancementplugin.core.Advancements;
import de._212th.advancementplugin.listener.PlayerAdvancementDoneListener;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.Listener;
import org.bukkit.plugin.java.JavaPlugin;

import java.io.IOException;
import java.util.Properties;

public final class AdvancementPlugin extends JavaPlugin {

    private Properties messageProperties;

    @Override
    public void onEnable() {

        try {
            loadMessageProperties();
            loadAdvancements();
        } catch (IOException e) {
            e.printStackTrace();
        }
        disableDefaultAdvancementMessages();
        registerListeners();

        sendColoredToConsole("&7Plugin &aenabled&7!");

    }

    @Override
    public void onDisable() {

        sendColoredToConsole("&7Plugin &cdisabled&7!");

    }

    private void loadAdvancements() throws IOException {

        loadAdvancement(Advancements.AdvancementType.ACHIEVEMENT);
        loadAdvancement(Advancements.AdvancementType.GOAL);
        loadAdvancement(Advancements.AdvancementType.CHALLENGE);

    }

    private void registerListeners() {

        registerListener(new PlayerAdvancementDoneListener(this));

    }

    private void disableDefaultAdvancementMessages() {
        for (World world: Bukkit.getWorlds()) {
            world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);
        }
    }

    private void loadMessageProperties() throws IOException {
        messageProperties = new Properties();
        messageProperties.load(getClassLoader().getResourceAsStream("messages.properties"));
    }

    private void registerListener(Listener listener) {
        Bukkit.getPluginManager().registerEvents(listener, this);
    }

    private void loadAdvancement(Advancements.AdvancementType type) throws IOException {
        Properties properties = new Properties();
        String path = "advancements/" + type.name().toLowerCase() + "s.properties";
        properties.load(getClassLoader().getResourceAsStream(path));
        Advancements.initialize(type, new AdvancementLoader(properties));
    }

    private void sendColoredToConsole(String text) {
        Bukkit.getConsoleSender().sendMessage(colorText(text));
    }

    public String getColoredMessage(String key) {
        return colorText(messageProperties.getProperty(key));
    }

    private String colorText(String text) {
        return ChatColor.translateAlternateColorCodes('&', text);
    }

}
