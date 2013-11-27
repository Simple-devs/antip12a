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
            String start = "Gummibjö";
            String nextEnd = "rnar";
            String middle = "";
            String end = "";
            for (int spamBear = 0; spamBear < 20; spamBear++);
            {
                end += "!";
                p.sendMessage(start + middle + nextEnd + end);
                middle += "ö";
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
                timer();
            }
        }
    }

    public void timer()
    {
        plugin.getServer().getScheduler().scheduleSyncDelayedTask(plugin, new Runnable() {
            @Override
            public void run() {
                if (!plugin.names.contains(PlayerName.getName()))
                {
                    PlayerName.kickPlayer("You took to long to verify!");
                }
            }
        }, plugin.getTime("Kick-Time"));
    }
}
