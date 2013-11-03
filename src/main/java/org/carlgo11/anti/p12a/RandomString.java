package org.carlgo11.anti.p12a;

import org.carlgo11.anti.p12a.Config.Config;

import java.util.Random;

public class RandomString {

    public String string = "";
    String easy = "0123456789";
    String normal = "0123456789abcdefghijklmnopqrstuvwxyz";
    String hard = "0123456789abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ";
    String diff;
    Main r;
    int l;

    public RandomString (Main p)
    {
        r = p;
        random();
    }

    public void getDifficulty()
    {
        String m = r.Difficulty;

        if (m.equalsIgnoreCase("Hard"))
        {
            diff = hard;
        }
        else if (m.equalsIgnoreCase("Normal"))
        {
            diff = normal;
        }
        else if (m.equalsIgnoreCase("Easy"))
        {
            diff = easy;
        }
    }
    public void random()
    {
        getDifficulty();
        Random n = new Random();
        l = Config.getInt("Length");

        for(int i = 0; i < 10; i++)
        {
            int num = n.nextInt(diff.length() - 1);
            String s = "" + diff.charAt(num);
            string += s;
        }

    }
}
