package de._212th.advancementplugin.listener;

import de._212th.advancementplugin.AdvancementPlugin;
import de._212th.advancementplugin.core.Advancements;
import lombok.AllArgsConstructor;
import org.bukkit.Bukkit;
import org.bukkit.GameRule;
import org.bukkit.World;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerAdvancementDoneEvent;

@AllArgsConstructor
public final class PlayerAdvancementDoneListener implements Listener {

    private AdvancementPlugin plugin;

    @EventHandler
    public void onPlayerAdvancementDone(PlayerAdvancementDoneEvent event) {

        // Temporary bug fix
        World world = event.getPlayer().getWorld();
        if (world.getGameRuleValue(GameRule.ANNOUNCE_ADVANCEMENTS)) world.setGameRule(GameRule.ANNOUNCE_ADVANCEMENTS, false);

        String key = event.getAdvancement().getKey().getKey();

        if (key.contains("root") || key.contains("recipes")) return;

        String messageKey = "advanced." + Advancements.getAdvancementType(key).name().toLowerCase();
        String message = plugin.getColoredMessage(messageKey);
        message = message.replace("%player%", event.getPlayer().getName());
        message = message.replace("%advancement%", Advancements.getTitle(key));
        Bukkit.broadcastMessage(message);

    }

}
