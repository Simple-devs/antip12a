package org.simpledevs.antip12a.Commands;

import org.bukkit.Location;
import org.bukkit.World;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.simpledevs.antip12a.Language.Lang;
import org.simpledevs.antip12a.Main;

public class VerifyCommand implements CommandExecutor {


    private Main plugin;

    public VerifyCommand(Main plug) {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.console_error.toString());
            return true;
        } else {
            if (args.length != 1) {
                sender.sendMessage(Lang.prefix.toString() + Lang.verify_derp.toString());
                return true;
            } else {
                Player p = (Player) sender;
                String l = p.getName();
                String y = l + " " + args[0];
                int Line = plugin.randomText.indexOf(y);
                Location loc = p.getLocation();
                World w = p.getWorld();
                if (plugin.randomText.contains(y)) {
                    plugin.randomText.remove(Line);
                    plugin.names.add(l);
                    plugin.save();

                    sender.sendMessage(Lang.prefix.toString() + Lang.nop12a.toString());
                    return true;
                } else {
                    sender.sendMessage(Lang.prefix.toString() + Lang.verify_derp.toString());
                    w.createExplosion(loc, 0F, false);
                    p.damage(0.5);
                    return true;
                }
            }
        }
    }
}
