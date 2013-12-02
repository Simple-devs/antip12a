package org.simpledevs.antip12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;
import org.simpledevs.antip12a.Main;

public class ChatListener implements Listener
{
    Main plugin;

    public ChatListener(Main plug)
    {
        this.plugin = plug;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent e) {
        Player p = e.getPlayer();
        String s = p.getName();
        if (!p.hasPermission("AntiP12a.ignoreplayer"))
        {
            if (!plugin.names.contains(s))
            {
                e.setCancelled(true);

            }
        }
    }
}
