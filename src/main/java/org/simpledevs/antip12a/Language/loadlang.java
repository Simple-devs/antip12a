package org.simpledevs.antip12a.Language;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.Listener;
import org.simpledevs.antip12a.Main;

public class loadlang implements Listener {

    Main plugin;

    public loadlang(Main plug) {
        super();
        this.plugin = plug;
        this.loadLang();
    }

    public void loadLang() {
        File dir = new File(plugin.getDataFolder() + "/language");
        dir.mkdir();
        if (!plugin.getConfig().getString("Language").isEmpty()) {
            File lang = new File(plugin.getDataFolder() + "/language", plugin.getConfig().getString("Language") + "_lang.yml");
            if (!lang.exists()) {
                try {
                    plugin.getDataFolder().mkdir();
                    lang.createNewFile();
                    InputStream defConfigStream = plugin.getResource(plugin.getConfig().getString("Language") + "_lang.yml");
                    if (defConfigStream != null) {
                        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                        defConfig.save(lang);
                        Lang.setFile(defConfig);
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Couldn't create language file.");
                    plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "This is a fatal error. Now disabling");
                    plugin.getServer().getPluginManager().disablePlugin(plugin);
                }
            }

            YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
            for (Lang item : Lang.values()) {
                if (conf.getString(item.getPath()) == null) {
                    conf.set(item.getPath(), item.getDefault());
                }
            }
            Lang.setFile(conf);
            Main.LANG = conf;
            Main.LANG_FILE = lang;
            try {
                conf.save(plugin.getLangFile());
            } catch (IOException e) {
                plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Failed to save lang.yml.");
                plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Report this stack trace to an developer.");
                e.printStackTrace();
            }
        } else {
            File lang = new File(plugin.getDataFolder() + "/language", "src/org.carlgo11.anti.p12a/EN_lang.yml");
            if (!lang.exists()) {
                try {
                    plugin.getDataFolder().mkdir();
                    lang.createNewFile();
                    InputStream defConfigStream = plugin.getResource("src/org.carlgo11.anti.p12a/EN_lang.yml");
                    if (defConfigStream != null) {
                        YamlConfiguration defConfig = YamlConfiguration.loadConfiguration(defConfigStream);
                        defConfig.save(lang);
                        Lang.setFile(defConfig);
                        return;
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                    plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Couldn't create language file.");
                    plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "This is a fatal error. Now disabling");
                    plugin.getServer().getPluginManager().disablePlugin(plugin);
                }
            }

            YamlConfiguration conf = YamlConfiguration.loadConfiguration(lang);
            for (Lang item : Lang.values()) {
                if (conf.getString(item.getPath()) == null) {
                    conf.set(item.getPath(), item.getDefault());
                }
            }
            Lang.setFile(conf);
            Main.LANG = conf;
            Main.LANG_FILE = lang;
            try {
                conf.save(plugin.getLangFile());
            } catch (IOException e) {
                plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Failed to save lang.yml.");
                plugin.getLogger().warning("[" + plugin.getDescription().getName() + "] " + "Report this stack trace to an developer.");
                e.printStackTrace();
            }
        }
    }
}