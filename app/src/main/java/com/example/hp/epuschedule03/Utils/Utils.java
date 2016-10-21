package com.example.hp.epuschedule03.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP on 10/20/2016.
 */

public class Utils {

    public static Boolean checkInternet(Context context){ // check out whether internet is available or not
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnected();

    }
    public static ArrayList<String> splitWeek(String weekString){// this function uses regex to split string which has dd//mm/yyyy
        ArrayList<String> list= new ArrayList<>();
        String pattern = "\\d{2}/\\d{2}/\\d{4}";// pattern here
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(weekString);
        while (m.find()){
            list.add(m.group());
        }
        return list;

    }

}
