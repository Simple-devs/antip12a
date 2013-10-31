package org.carlgo11.anti.p12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.carlgo11.anti.p12a.Main;

public class ChatListener implements Listener
{
    Main plugin;

    public ChatListener(Main plug)
    {
        this.plugin = plug;
    }
    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player player = e.getPlayer();
        if (!plugin.names.contains(player))
        {
            e.setCancelled(true);
        }
    }
}
