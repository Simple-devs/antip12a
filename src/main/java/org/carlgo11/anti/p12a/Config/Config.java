package org.carlgo11.anti.p12a.Config;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.Map.Entry;

import org.bukkit.configuration.file.YamlConfiguration;
import org.carlgo11.anti.p12a.Main;

public class Config {

    private final File config;
    private final Main plugin;

    private static Config instance;

    public Config() {
        this.plugin = Main.instance;
        this.config = new File(plugin.getDataFolder(), "config.yml");
        setupStatic(this);
        this.load();
    }

    private static void setupStatic(Config cfg) {
        instance = cfg;
    }

    private void load() {
        try {
            if(config.exists()) {
                this.writeConfig();
            } else {
                this.createConfig();
                this.writeConfig();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void writeConfig() throws IOException {
        // Use a short-circuit operator here so that both methods are always run
        if(!config.delete() && !config.createNewFile()) {
            plugin.getLogger().severe("Could not create configuration file! - 002");
        }
        PrintWriter o = new PrintWriter(config, "UTF-8");
        o.close();
        Main.instance.reloadConfig();
    }

    private void createConfig() throws IOException {
        if(!config.getParentFile().exists() && !config.getParentFile().mkdir()) {
            plugin.getLogger().severe("Could not create config directory");
        }
        if(!config.exists() && !config.createNewFile()) {
            plugin.getLogger().severe("Could not create new config file! - 001");
        }
    }

    public static Object getValue(String key) {
        return instance.plugin.getConfig().get(key);
    }

    public static boolean getBoolean(String key) {
        return instance.plugin.getConfig().getBoolean(key);
    }

    public static String getString(String key) {
        return instance.plugin.getConfig().getString(key);
    }

    public static int getInt(String key) {
        return instance.plugin.getConfig().getInt(key);
    }

    public static Map<String, Object> getValues() {
        return instance.plugin.getConfig().getValues(true);
    }
}
