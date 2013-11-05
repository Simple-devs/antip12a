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
    SimpleDateFormat l = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    InputStream i = null;
    OutputStream c = null;

    public Backup(Main r) {
        m = r;
        getInfo();
    }

    public void getInfo() {
        p = m.getConfig().getString("Backup-Time");
        q = m.getConfig().getBoolean("Backup");

        if (q) {
            save();
        }
    }

    public void save() {
        try {
            File f = new File(m.getDataFolder() + "/backup.txt");
            File s = new File(m.getDataFolder() + "/backup/backup_" + l.format(d));
            boolean l = f.createNewFile();
            PrintWriter h = new PrintWriter(f, "UTF-8");
            BufferedReader o = new BufferedReader(new FileReader(f));
            String b = o.readLine();
            String w = r.format(d);

            if (l)
            {
                m.getLogger().info("backup.txt file created!");
                h.println(r.format(d));
            }


            if (!w.equalsIgnoreCase(b)) {
                i = new FileInputStream(f);
                c = new FileOutputStream(s);
                byte[] u = new byte[1024];
                int y;

                while ((y = i.read(u)) > 0){
                    c.write(u , 0, y);
                }

                i.close();
                c.close();

                h.println(r.format(d));
            }
        } catch (IOException e) {
            m.getLogger().warning(e + "");
        }
    }
}
