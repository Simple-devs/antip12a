package org.carlgo11.anti.p12a;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

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
    String x = "";
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

    public void read() {
        try {
            File f = new File(m.getDataFolder() + "/backup.txt");
            BufferedReader o = new BufferedReader(new FileReader(f));

            String ol;
            while ((ol = o.readLine()) != null) {
                x = ol;
            }

            m.getLogger().info(f.getAbsolutePath() + "");
            m.getLogger().info(f.getPath());
            m.getLogger().info(f.getName());
            m.getLogger().info(f.toString());
            m.getLogger().info(x);
            m.getLogger().info(ol);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void save() {
        try {
            File f = new File(m.getDataFolder() + "/backup.txt");
            File s = new File(m.getDataFolder() + "/backup/backup_" + l.format(d));
            boolean l = f.createNewFile();
            PrintWriter h = new PrintWriter(f, "UTF-8");
            String w = r.format(d);

            read();

            if (l)
            {
                m.getLogger().info("backup.txt file created!");
                h.println(x);
            }

            m.getLogger().info(w);
            m.getLogger().info(x);
            m.getLogger().info(w.equalsIgnoreCase(x) + "");


            if (!w.equalsIgnoreCase(x)) {
                i = new FileInputStream(f);
                c = new FileOutputStream(s);
                byte[] u = new byte[1024];
                int y;

                while ((y = i.read(u)) > 0){
                    c.write(u , 0, y);
                }

                h.println(r.format(d));
            }

            i.close();
            c.close();
            h.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
