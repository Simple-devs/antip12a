package org.simpledevs.antip12a.Commands;

import org.bukkit.Bukkit;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;
import org.simpledevs.antip12a.Language.Lang;
import org.simpledevs.antip12a.Main;

public class Antip12aCommand implements CommandExecutor {

    private Main plugin;

    public Antip12aCommand(Main plug) {
        this.plugin = plug;
    }

    @Override
    public boolean onCommand(CommandSender sender, Command command, String label, String[] args) {

            String badperm = Lang.prefix.toString() + Lang.permission.toString();

                if (args.length == 0) {
                    plugin.helpMessage(sender);
                    return true;
                } else if (args.length == 1) {
                    if (args[0].equalsIgnoreCase("verify")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("check")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("help")) {
                        plugin.helpMessage(sender);
                        return true;
                    } else if (args[0].equalsIgnoreCase("reload")) {
                        if(sender.hasPermission("antip12a.reload")){
                            plugin.reload();
                            sender.sendMessage(Lang.prefix.toString() + Lang.antip12a_reload.toString());
                            return true;
                        }else{
                            sender.sendMessage(badperm);
                            return true;
                        }
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
                                if(Bukkit.getOfflinePlayer(args[1]).isOnline()){
                                    Player gummibjornar = Bukkit.getPlayer(args[1]);
                                    gummibjornar.sendMessage(Lang.prefix.toString()+Lang.nop12a.toString());
                                }
                                return true;
                            }
                        } else {
                            sender.sendMessage(badperm);
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
                            sender.sendMessage(badperm);
                            return true;
                        }
                    }
                } else {
                    plugin.helpMessage(sender);
                    return true;
                }
        return true;
    }
}
