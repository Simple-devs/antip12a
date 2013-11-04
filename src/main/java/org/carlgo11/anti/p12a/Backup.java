package org.carlgo11.anti.p12a;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

/**
 * Bukkit Plugin
 * Plugin Name: antip12a
 * Author: tryy3
 * Date: 2013-11-03
 * Time: 13:20
 */
public class Backup {
    String p;
    boolean q;
    Main m;
    Date d = new Date();
    SimpleDateFormat r = new SimpleDateFormat("dd-MM-yyyy HH");
    String b;

    public Backup(Main r) {
        m = r;
    }

    public void getInfo() {
        p = m.getConfig().getString("Backup-Time");
        q = m.getConfig().getBoolean("Backup");
    }

    public void load() {
        if (q = true) {

        }
    }

    public void save() {
        try {
            File f = new File(m.getDataFolder() + "/backup.txt");
            boolean l = f.createNewFile();
            PrintWriter h = new PrintWriter(f, "UTF-8");
            BufferedReader o = new BufferedReader(new FileReader(f));

            if (l)
            {
                b = o.readLine();
                if (!r.format(d).equalsIgnoreCase(r.format(b)))
                {
                    h.println(r.format(b));
                }
            } else {
                h.println(r.format(d));
            }
        } catch (IOException e) {
            m.getLogger().warning(e + "");
        }
    }

    public static void runNow() {

    }
}
