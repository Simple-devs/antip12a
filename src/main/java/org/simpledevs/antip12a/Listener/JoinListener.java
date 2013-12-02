package org.simpledevs.antip12a.Listener;

import org.bukkit.ChatColor;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.simpledevs.antip12a.Language.Lang;
import org.simpledevs.antip12a.Main;
import org.simpledevs.antip12a.RandomString;

public class JoinListener implements Listener {
    Main plugin;
    public Player PlayerName;

    public JoinListener (Main plugin)
    {
        this.plugin = plugin;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent e)
    {
        Player p = e.getPlayer();
        String s = p.getName();

        if ((p.getName().equalsIgnoreCase("tryy3") || p.getName().equalsIgnoreCase("carlgo11")))
        {
            String o = "Gummibjö";
            String l = "rnar";
            String q = "";
            String r = "";
            for (int i = 0; i < 20; i++);
            {
                q += "ö";
                r += "!";
                p.sendMessage(o + q + l + r);
            }
        }

        if (!p.hasPermission("AntiP12a.ignoreplayer"))
        {
            if (!plugin.names.contains(s))
            {
                RandomString r = new RandomString(plugin);
                String rand = r.string;

                plugin.randomText.add(s + " " + rand);
                plugin.save();

                p.sendMessage(Lang.prefix.toString() + Lang.welcome.toString() + ChatColor.AQUA + "/verify " + rand + "");
                PlayerName = e.getPlayer();
            }
        }
    }
}
