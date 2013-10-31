package org.carlgo11.anti.p12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerMoveEvent;
import org.carlgo11.anti.p12a.Main;

public class MoveListener implements Listener
{
    Main plugin;

    public MoveListener (Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onMove(PlayerMoveEvent e)
    {
        Player p = e.getPlayer();


        if (!plugin.names.contains(p))
        {
            e.setCancelled(true);
        }
    }
}
