package org.carlgo11.anti.p12a.Language;

import org.bukkit.ChatColor;
import org.bukkit.configuration.file.YamlConfiguration;

/**
 * An enum for requesting strings from the language file.
 * @author gomeow
 */
public enum Lang {
    prefix("Prefix", "antip12a:"),
    antip12a("antip12a", "&eShows all the commands!"),
    antip12a_reload("antip12a_reload", "&Reloads the config.yml!"),
    antip12a_verify("antip12a_verify", "&eManually verify a player!"),
    antip12a_check("antip12a_check", "&eChecks if a player has verified!"),
    console_error("console_error", "This command can only be used by a player!"),
    permission("permission", "&cYou don't have permission to use that command!"),
    verify_already("verify_already", "&cThis player is already verified!"),
    verify_confirmed("verify_confirmed", "&a%p is now verified!"),
    check_verified("check_verified", "&a%p is verified!"),
    check_no_verified("check_no_verified", "&e%p is not verified!"),
    verify_derp("verify_derp", "&eThink you messed something up! Try again!"),
    nop12a("nop12a", "&aOkay! Go ahead!"),
    welcome("welcome", "&cWelcome to the server! To make sure that you aren't a noob, please type the following command");

    private String path;
    private String def;
    private static YamlConfiguration LANG;

    /**
     * Lang enum constructor.
     * @param path The string path.
     * @param start The default string.
     */
    Lang(String path, String start) {
        this.path = path;
        this.def = start;
    }

    /**
     * Set the {@code YamlConfiguration} to use.
     * @param config The config to set.
     */
    public static void setFile(YamlConfiguration config) {
        LANG = config;
    }

    @Override
    public String toString() {
        if (this == prefix)
            return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def)) + " ";
        return ChatColor.translateAlternateColorCodes('&', LANG.getString(this.path, def));
    }

    /**
     * Get the default value of the path.
     * @return The default value of the path.
     */
    public String getDefault() {
        return this.def;
    }

    /**
     * Get the path to the string.
     * @return The path to the string.
     */
    public String getPath() {
        return this.path;
    }
}
 