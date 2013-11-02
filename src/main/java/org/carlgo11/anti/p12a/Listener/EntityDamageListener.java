package org.carlgo11.anti.p12a.Listener;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.carlgo11.anti.p12a.Main;

public class EntityDamageListener implements Listener {

    Main plugin;

    public EntityDamageListener(Main plug) {
        this.plugin = plug;
    }

    @EventHandler
    public void onEntityDamage(EntityDamageEvent e){
        if (e.getEntity() instanceof Player) {
            Player p = (Player) e.getEntity();
            String s = p.getName();
            if (!p.hasPermission("AntiP12a.ignoreplayer")) {
                if (!plugin.names.contains(s)) {
                    e.setCancelled(true);
                }
            }
        }

    }
}
