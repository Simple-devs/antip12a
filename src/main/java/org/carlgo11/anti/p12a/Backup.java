package org.carlgo11.anti.p12a;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;

/**
 * Bukkit Plugin
 * Plugin Name: antip12a
 * Author: tryy3
 * Date: 2013-11-03
 * Time: 13:20
 */
public class Backup {
    Main main;
    Date current_date = new Date();
    SimpleDateFormat save_format = new SimpleDateFormat("dd-MM-yyyy_HH:mm:ss");
    InputStream readOldFile = null;
    OutputStream saveBackupFile = null;

    public Backup(Main plug) {
        main = plug;
        save();
    }

    public void save() {
        try {
            String CurrentDateHour = save_format.format(current_date);
            File OldNamesFile = new File(main.getDataFolder() + "/names.txt");
            File NewBackupFile = new File(main.getDataFolder() + "/backup/names_" + CurrentDateHour);
            PrintWriter writer = new PrintWriter(OldNamesFile, "UTF-8");

            readOldFile = new FileInputStream(OldNamesFile);
            saveBackupFile = new FileOutputStream(NewBackupFile);
            byte[] bytes = new byte[1024];
            int whileI;

            while ((whileI = readOldFile.read(bytes)) > 0){
                saveBackupFile.write(bytes , 0, whileI);
            }

            readOldFile.close();
            saveBackupFile.close();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
