package org.carlgo11.anti.p12a.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.carlgo11.anti.p12a.Language.Lang;
import org.carlgo11.anti.p12a.Main;
import org.carlgo11.anti.p12a.RandomString;

public class JoinListener implements Listener {
    Main plugin;

    public JoinListener (Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        String s = p.getName();
        if ((!plugin.names.contains(s)) || (!p.hasPermission("AntiP12a.ignoreplayer")))
        {
            RandomString r = new RandomString(plugin);
            String rand = r.string;

            plugin.randomText.add(s + " " + rand);
            plugin.save();

            p.sendMessage(Lang.prefix.toString() + Lang.welcome.toString() +
                    ChatColor.AQUA + "/verify " + rand + "");
        }

    }
}
