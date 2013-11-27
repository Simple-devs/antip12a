package org.carlgo11.anti.p12a;

import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.ChatColor;
import org.bukkit.command.CommandSender;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.java.JavaPlugin;
import org.carlgo11.anti.p12a.Commands.Antip12aCommand;
import org.carlgo11.anti.p12a.Commands.VerifyCommand;
import org.carlgo11.anti.p12a.Language.Lang;
import org.carlgo11.anti.p12a.Language.loadlang;
import org.carlgo11.anti.p12a.Listener.*;
import org.carlgo11.anti.p12a.Metrics.Metrics;
import org.carlgo11.anti.p12a.Metrics.SimplePlotter;
import org.carlgo11.anti.p12a.Updater.updater;

import java.io.*;
import java.util.ArrayList;

public class Main extends JavaPlugin {
    public String Difficulty = "";
    public ArrayList<String> names = new ArrayList<String>();
    public ArrayList<String> randomText = new ArrayList<String>();
    public static YamlConfiguration LANG;
    public static File LANG_FILE;
    public Plugin p = this;
    public boolean gummibjorn = true; //WE NEED MOAR!!!!!

    @Override
    public void onEnable() {
        checkConfig();
        loadFile();
        checkMetrics();
        checkUpdater();
        commands();
        backup();

        this.Difficulty = getConfig().getString("Difficulty");

        getServer().getPluginManager().registerEvents(new loadlang(this), this);
        getServer().getPluginManager().registerEvents(new JoinListener(this), this);
        getServer().getPluginManager().registerEvents(new InteractListener(this), this);
        getServer().getPluginManager().registerEvents(new EntityDamageListener(this), this);
        getServer().getPluginManager().registerEvents(new ChatListener(this), this);
        getServer().getPluginManager().registerEvents(new MoveListener(this), this);
        getServer().getPluginManager().registerEvents(new CommandListener(this), this);
        getServer().getPluginManager().registerEvents(new PickupnDropListener(this), this);
    }

    @Override
    public void onDisable() {
        save();
        runBackup();
    }

    public void commands() {
        getCommand("Antip12a").setExecutor(new Antip12aCommand(this));
        getCommand("Verify").setExecutor(new VerifyCommand(this));
    }

    public void checkUpdater() {
        String s = getConfig().getString("Update");
        if (s.equalsIgnoreCase("Check")) {
            new updater(this, 56079, this.getFile(), updater.UpdateType.NO_DOWNLOAD, false);
            getLogger().info("Updater: check-update enabled!");
        } else if (s.equalsIgnoreCase("Auto")) {
            new updater(this, 56079, this.getFile(), updater.UpdateType.DEFAULT, false);
            getLogger().info("Updater: auto-updater enabled!");
        } else {
            getLogger().info("Updater: updater disabled!");
        }
    }

    public void checkMetrics() {
        try {
            Metrics metrics = new Metrics(this);
            graphs(metrics);
            metrics.start();
        } catch (IOException e) {
            System.out.println("[" + getDescription().getName() + "] " + "Error Submitting stats!");
        }
    }

    public void graphs(Metrics metrics) { // Custom Graphs. Sends data to mcstats.org
        try {

            //graph1
            Metrics.Graph graph1 = metrics.createGraph("auto-update");
            String s = getConfig().getString("Update");
            
            if (s.equalsIgnoreCase("Check")){
                graph1.addPlotter(new SimplePlotter("Check"));
            }else if( s.equalsIgnoreCase("Auto")) {
                graph1.addPlotter(new SimplePlotter("Auto"));
            } else if(s.equalsIgnoreCase("Off")){
                graph1.addPlotter(new SimplePlotter("Off"));
            }


            //graph2
            Metrics.Graph graph2 = metrics.createGraph("Language");
            String p = getConfig().getString("Language");
            
            if (p.isEmpty()) {
                graph2.addPlotter(new SimplePlotter("English"));
            } else if (p.equalsIgnoreCase("EN")) {
                graph2.addPlotter(new SimplePlotter("English"));
            } else if (p.equalsIgnoreCase("FR")) {
                graph2.addPlotter(new SimplePlotter("French"));
            } else if (p.equalsIgnoreCase("FI")) {
                graph2.addPlotter(new SimplePlotter("Finnish"));
            } else if (p.equalsIgnoreCase("DE")) {
                graph2.addPlotter(new SimplePlotter("German"));
            } else if (p.equalsIgnoreCase("SV")) {
                graph2.addPlotter(new SimplePlotter("Swedish"));
            } else {
                graph2.addPlotter(new SimplePlotter("Other"));
            }
        } catch(Exception e) {
            getLogger().warning(e.getMessage() + "(line 119)");
        }
    }

    public void checkConfig() {
        saveDefaultConfig();
    }

    /* For future need
     * It was outputting warnings because it was never used!
     *
     * public YamlConfiguration getLang() {
     *    return LANG;
     *}
     */

    public File getLangFile() {
        return LANG_FILE;
    }

    public void loadFile() {
        try {
            boolean p = new File(getDataFolder() + "/backup").mkdirs();
            File file = new File(getDataFolder() + "/names.txt");
            boolean newFile = file.createNewFile();

            if (p){
                getLogger().info("Created a backup folder");
            }

            if (newFile) {
                getLogger().info("Created a file called names.txt");
            }

            BufferedReader read = new BufferedReader(new FileReader(file));
            String line;
            while ((line = read.readLine()) != null) {
                if (!names.contains(line)) {
                    names.add(line);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            File file = new File(getDataFolder() + "/names.txt");
            boolean createFile = file.createNewFile();
            if (createFile) {
                getLogger().info("Creating a file called names.txt");
            }
            PrintWriter write = new PrintWriter(file, "UTF-8");

            for (String name : names) {
                write.println(name);
            }
            write.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void reload() {
        save();
        checkConfig();
        reloadConfig();
        loadFile();
    }

    public void helpMessage(CommandSender p) {
        p.sendMessage(ChatColor.GREEN + "======== " + Lang.prefix.toString() + ChatColor.GREEN + " ======== ");
        if (p.hasPermission("antip12a.help")) {
            p.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a " + Lang.antip12a.toString());
            p.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a help " + Lang.antip12a.toString());
        }
        if (p.hasPermission("antip12a.reload")) {
            p.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a reload " + Lang.antip12a_reload.toString());
        }
        if (p.hasPermission("antip12a.verify")) {
            p.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a verify <player> " + Lang.antip12a_verify.toString());
        }
        if (p.hasPermission("antip12a.check")) {
            p.sendMessage(ChatColor.GRAY + "-  /" + ChatColor.RED + "Antip12a check <player> " + Lang.antip12a_check.toString());
        }

    }

    public boolean getBackupBoolean()
    {
        return getConfig().getBoolean("Backup");
    }
    public int getTime(String whatTime)
    {
        String time = "";

        if (whatTime == "Backup")
        {
            time = getConfig().getString("BackupTime");
        }
        else if (whatTime == "Timer")
        {
            time = getConfig().getString("KickTime");
        }
        getLogger().info(time);
        int timeLength = time.length()-1;
        getLogger().info(timeLength + "");
        char timeChar =  time.charAt(timeLength);
        int backupTime;
        int NewTime;

        if (timeChar == 'd')
        {
            NewTime = Integer.parseInt(time.replace(timeChar + "", ""));
            backupTime = 20 * 60 * 60 * 24 * NewTime;
            return backupTime;
        }
        else if (timeChar == 'h')
        {
            NewTime = Integer.parseInt(time.replace(timeChar + "", ""));
            backupTime = 20 * 60 * 60  * NewTime;
            return backupTime;

        }
        else if (timeChar == 'm')
        {
            NewTime = Integer.parseInt(time.replace(timeChar + "", ""));
            backupTime = 20 * 60 * NewTime;
            return backupTime;

        }
        else
        {
            NewTime = Integer.parseInt(time.replace(timeChar + "", ""));
            backupTime = 20 * NewTime;
            return backupTime;

        }
    }

    public void runBackup () {
        if (getBackupBoolean()){
            new Backup(this);
        }
    }

    public void backup() {
        if (getBackupBoolean()){
            getServer().getScheduler().scheduleSyncRepeatingTask(this, new Runnable() {
                public void run() {
                    runBackup();
                }
            }
                    , getTime("Backup"), getTime("Backup"));
        }
    }
}
