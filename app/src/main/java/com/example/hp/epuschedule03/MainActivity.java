package com.example.hp.epuschedule03;

import android.app.Dialog;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.hp.epuschedule03.Database.Database;
import com.example.hp.epuschedule03.Database.Student;
import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.Database.Week;
import com.example.hp.epuschedule03.Utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private Dialog dialog;
    private Button btnDialog;
    private EditText edtID;
    private Toolbar toolbar;
    private Student student;
    private Database database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);// foat button here :))
        fab.setImageResource(R.drawable.ic_pen);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                createDialog();
                dialog.show();
            }
        });

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {

        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


    // get week's time
    private void getTimeOfWeek(String ID) throws IOException {
        Realm realm = Realm.getDefaultInstance(); // avoid crashing when work with multi-thread
        Week week = new Week();
        int i = 0;
        int tuan = 1;
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/Default.aspx?page=thoikhoabieu&id=" + ID)
                .maxBodySize(0)
                .timeout(0)
                .get();
        Student student = database.findStudentByID(ID); // get the student with corresponding ID // create a new student
        // starting parse the html
        Elements elements = doc.select("#ctl00_ContentPlaceHolder1_ctl00_ddlTuan option");
        Log.d("thaohandsome", "getTimeOfWeek: " + elements.size());
        for (Element element : elements) {// loop
            // assign value to student's properties above
            ArrayList<String> list = Utils.splitWeek(element.select("option:eq(" + i + ")").text());
            realm.beginTransaction();
            week.setThoigianBD(list.get(0));
            week.setThoigianKT(list.get(1));
            week.setTuan(tuan);
            Log.d("thaohandsome", "getTimeOfWeek: " + list.get(0) + " " + tuan + "  " + list.get(1));// just for testing
            student.weekRealmList.add(week);
            // increase
            i++;
            tuan++;
            realm.commitTransaction();

        }


    }

    // get html to process
    private void getSchedule(String ID) throws IOException {
        Realm realm = Realm.getDefaultInstance();
        Student student = new Student();

        int i = 0;
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/default.aspx?page=thoikhoabieu&sta=1&id=" + ID)
                .maxBodySize(0)
                .timeout(0)
                .get();
        Elements elements = doc.select("table.body-table");
        //
        Log.d("thaohandsome", "getSchedule: " + elements.size());
        int size = elements.size();
        for (int j = 0; j < size; j++) {
            Element element = elements.get(j);
            Subject subject = new Subject();
            realm.beginTransaction();
            student.setID(ID);
            student.setName(doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentTenSV").text());
            subject.setTuan(element.select("td:eq(13)").text());
            subject.setMaMH(element.select("td:eq(0)").text());
            subject.setCBGD(element.select("td:eq(12)").text());
            subject.setMaLop(element.select("td:eq(4)").text());
            subject.setPhong(element.select("td:eq(11)").text());
            subject.setST(element.select("td:eq(10)").text());
            subject.setTenMH(element.select("td:eq(1)").text());
            subject.setThu(element.select("td:eq(8)").text());
            subject.setTietBD(element.select("td:eq(9)").text());
            Log.d("thaohandsome", "getSchedule: " + doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentTenSV").text());
            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(13)").text());
            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(0)").text());
            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(12)").text());
            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(11)").text());
            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(4)").text());
            Log.d("thaohandsome", "getSchedule: ---------------------------------------------------------------------------------");
            student.subjectRealmList.add(subject);
//            list.add(subject);
            realm.commitTransaction();

        }
        database.addStudent(student);

    }


    // processing  the dialog here :))
    public void createDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_popup_dialog);
        btnDialog = (Button) dialog.findViewById(R.id.btnDone);
        edtID = (EditText) dialog.findViewById(R.id.edtMaSV);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle(edtID.getText().toString());// change title of tool bar here :))
                if (Utils.checkInternet(MainActivity.this) == false) {
                    Toast.makeText(MainActivity.this, "Enable ur connection, plz!!!", Toast.LENGTH_SHORT).show();
                }
                // 
                if (Utils.checkInternet(MainActivity.this) == true) { // in case this connection is online
                    // getting value from dkmh.epu.edu.vn
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            database = new Database();
//                            Toast.makeText(MainActivity.this, "dcm no nhe", Toast.LENGTH_SHORT).show();
                            if (database.findStudentByID(edtID.getText().toString()) == null) {

                                // more clear about two methods, go inside them
                                try {
                                    getSchedule(edtID.getText().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                                try {
                                    getTimeOfWeek(edtID.getText().toString());
                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }

                        }
                    }).start();
                    database = new Database();
                    if (database.findStudentByID(edtID.getText().toString()) != null) {
                        database = new Database();
                        Toast.makeText(MainActivity.this, "Welcome " + database.findStudentByID(edtID.getText().toString()).subjectRealmList.get(2).getTenMH(), Toast.LENGTH_SHORT).show();


                    }


                }
            }

        });

    }

}
