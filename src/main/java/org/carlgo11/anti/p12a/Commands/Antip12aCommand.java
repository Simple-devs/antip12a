package org.carlgo11.anti.p12a.Commands;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.carlgo11.anti.p12a.Language.Lang;
import org.carlgo11.anti.p12a.Main;

public class Antip12aCommand implements CommandExecutor {

    private Main plugin;

    public Antip12aCommand(Main plug) {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {
        if (!(sender instanceof Player)) {
            sender.sendMessage(Lang.console_error.toString());
            return true;
        } else {
            String perm = Lang.prefix.toString() + Lang.permission.toString();

            if (command.getName().equalsIgnoreCase("antip12a")) {
                if (args.length == 0) {
                    if (sender.hasPermission("antip12a.help")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else {
                        sender.sendMessage(perm);
                        return true;
                    }
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("verify")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("check")) {
                        sender.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a check" + ChatColor.RED + " <player>");
                        return true;
                    } else if (args[0].equalsIgnoreCase("help")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else {
                        plugin.helpMessage(sender);
                        return true;
                    }
                } else if (args.length == 2) {
                    if (args[0].equalsIgnoreCase("verify")) {
                        if (sender.hasPermission("antip12a.verify")) {
                            if (plugin.names.contains(args[1])) {
                                sender.sendMessage(Lang.prefix.toString() + Lang.verify_already.toString());
                                return true;
                            } else {
                                plugin.names.add(args[1]);
                                plugin.save();
                                sender.sendMessage(Lang.prefix.toString() + Lang.verify_confirmed.toString().replace("%p", args[1]));
                                return true;
                            }
                        } else {
                            sender.sendMessage(perm);
                            return true;
                        }
                    } else if (args[0].equalsIgnoreCase("check")) {
                        if (sender.hasPermission("antip12a.check")) {
                            if (plugin.names.contains(args[1])) {
                                sender.sendMessage(Lang.prefix.toString() + Lang.check_verified.toString().replace("%p", args[1]));
                                return true;
                            } else {
                                sender.sendMessage(Lang.prefix.toString() + Lang.check_no_verified.toString().replace("%p", args[1]));
                                return true;
                            }
                        } else {
                            sender.sendMessage(perm);
                            return true;
                        }
                    }
                } else {
                    plugin.helpMessage(sender);
                    return true;
                }
            }
        }
        return true;
    }
}
