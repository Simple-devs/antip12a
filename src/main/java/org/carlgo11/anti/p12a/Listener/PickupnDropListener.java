package org.carlgo11.anti.p12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerDropItemEvent;
import org.bukkit.event.player.PlayerPickupItemEvent;
import org.carlgo11.anti.p12a.Main;

public class PickupnDropListener implements Listener {

    Main plugin;

    public PickupnDropListener(Main plug)
    {
        this.plugin = plug;
    }

    @EventHandler
    public void onDrop(PlayerDropItemEvent e ){
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

    @EventHandler
    public void onPickupChicks(PlayerPickupItemEvent e){
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
