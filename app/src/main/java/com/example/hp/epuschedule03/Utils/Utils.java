package com.example.hp.epuschedule03.Utils;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.util.Log;
import android.widget.Toast;

import com.example.hp.epuschedule03.Database.Student;
import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.MainActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by HP on 10/20/2016.
 */

public class Utils {

    public static Boolean checkInternet(Context context) { // check out whether internet is available or not
        ConnectivityManager cm =
                (ConnectivityManager) context.getSystemService(Context.CONNECTIVITY_SERVICE);

        return cm.getActiveNetworkInfo() != null &&
                cm.getActiveNetworkInfo().isConnected();

    }

    public static ArrayList<String> splitWeek(String weekString) {// this function uses regex to split string which has dd//mm/yyyy
        ArrayList<String> list = new ArrayList<>();
        String pattern = "\\d{2}/\\d{2}/\\d{4}";// pattern here
        Pattern r = Pattern.compile(pattern);
        Matcher m = r.matcher(weekString);
        while (m.find()) {
            list.add(m.group());
        }
        return list;

    }

    public static Boolean dateComparation(String thoiGianBatDau, String thoiGianKT) throws ParseException { // getting current a week by comparing sith system's time
//        Date dateSystem = Calendar.getInstance().getTime();
        String testDate ="21/02/2017";
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateSystem = dateFormat.parse(testDate);
//        SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        Date dateBD= dateFormat.parse(thoiGianBatDau);
        Date dateKT= dateFormat.parse(thoiGianKT);
        if (dateBD.compareTo(dateSystem)<=0 && dateKT.compareTo(dateSystem)>=0){
            return true;
        }

        return false;
    }
    public static  List<Subject> monPhaiHocTheoTuan(List<Subject> list, int tuan){
        List<Subject> subjects= new ArrayList<Subject>();
        int size= list.size();
        for (int i=0;i<size;i++){
            if (list.get(i).getTuan().length()!=0 && list.get(i).getTuan().charAt(tuan-1)!='-')
            {
                subjects.add(list.get(i));
            }
        }
        return subjects;
    }
    public static List<Subject> monPhaiHocTheoThu(int i, List<Subject> list){
        ArrayList<Subject> subjects= new ArrayList<>();
        int thu=i+2;
        String s1= Integer.toString(thu);
        //------------------
        for (int j=0;j<list.size();j++){
            String s2= list.get(j).getThu();
            if (s2.toLowerCase().contains(s1.toLowerCase())){
                subjects.add(list.get(j));
            }
        }

        return subjects;
    }
    public static int getCurrentWeek(Student student){
        int size =student.weekRealmList.size();
        for (int i = 0; i < size; i++) {
            String TGBD = student.weekRealmList.get(i).getThoigianBD();
            String TGKT = student.weekRealmList.get(i).getThoigianKT();
            try {
                if (Utils.dateComparation(TGBD, TGKT)) {
                    int tuan = student.weekRealmList.get(i).getTuan();
                    return tuan;

                }
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }


        return -1;
    }
    // position is position of item on list: postion 0 ---> thu 2
    public static List<Subject> getFinalResults(int tuan, int position, Student student){
        int thu;
        thu= position+2;
        List<Subject> finalList= new ArrayList<>();
        List<Subject> listWeek = new ArrayList<>();
        listWeek= monPhaiHocTheoTuan(student.subjectRealmList, tuan);
        finalList= Utils.monPhaiHocTheoThu(thu,listWeek);

        return  finalList;

    }


}
