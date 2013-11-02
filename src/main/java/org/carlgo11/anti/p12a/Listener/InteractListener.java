package org.carlgo11.anti.p12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerInteractEvent;
import org.carlgo11.anti.p12a.Main;


public class InteractListener implements Listener {

    Main plugin;

    public InteractListener(Main plug) {
        this.plugin = plug;
    }

    @EventHandler
    public void onInteract(PlayerInteractEvent e) {
        Player p = e.getPlayer();
        String s = p.getName();
        if (!p.hasPermission("AntiP12a.ignoreplayer")) {
            if (!plugin.names.contains(s)) {
                e.setCancelled(true);
            }
        }
    }
}
