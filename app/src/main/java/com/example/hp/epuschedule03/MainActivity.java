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

import com.example.hp.epuschedule03.Database.RealmHandle;
import com.example.hp.epuschedule03.Database.monHocModel;
import com.example.hp.epuschedule03.Database.tuanHocModel;
import com.example.hp.epuschedule03.Utils.Utils;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

import io.realm.Realm;

import static android.R.id.list;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private monHocModel mhModel;
    private tuanHocModel thModel;
    private RealmHandle realmHandle;
    private Dialog dialog;
    private Button btnDialog;
    private EditText edtMaSV;
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Realm.init(this);
        realmHandle = new RealmHandle();
        Log.d("thaohandsome", "onCreate: "+realmHandle.findWeek("1381310069"));
//        realmHandle.findWeek("1381310069");

        //
//        Log.d("thaohandsome", "onCreate: "+realmHandle.findTenSV("1481310029").toString());
        init();
        // toolbar
        toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);// foat button here :))
        fab.setImageResource(R.drawable.ic_pen);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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

    // initializing somme  neccessary object
    private void init() {
        Realm.init(this);
//        mhModel = new monHocModel();
        createDialog();

    }
    // get week's time
    private void getTimeOfWeek(String maSV) throws IOException {
        int tuan=1;
        int i=0;
        //
        thModel = new tuanHocModel();
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/Default.aspx?page=thoikhoabieu&id=" + maSV)
                .maxBodySize(0)
                .timeout(0)
                .get();

        // starting parse the html
        Elements elements = doc.select("#ctl00_ContentPlaceHolder1_ctl00_ddlTuan option");
        //
        for (Element element : elements){// loop

            thModel.setMaSV(maSV);
            thModel.setTuan(tuan);
            ArrayList<String> list= Utils.splitWeek(element.select("option:eq("+i+")").text());
            thModel.setThoiGianBD(list.get(0));
            thModel.setThoiGianKT(list.get(1));
            realmHandle.addtuanHocModel(thModel);
            Log.d("thaohandsome", "getTimeOfWeek: "+list.get(0));
            Log.d("thaohandsome", "getTimeOfWeek: "+list.get(1));
            Log.d("thaohandsome", "getTimeOfWeek: "+tuan);
            Log.d("thaohandsome", "getTimeOfWeek: "+element.select("option:eq("+i+")").text());
            // increase
            i++;
            tuan++;

        }


    }

    // get html to process
    private void getSchedule(String maSV) throws IOException {
        //
        mhModel = new monHocModel();
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/default.aspx?page=thoikhoabieu&sta=1&id=" + maSV)
                .maxBodySize(0)
                .timeout(0)
                .get();
        Elements elements = doc.select("table.body-table td");
        //
        int a= elements.size();
        Log.d("thaohandsome01", "getTimeOfWeek: "+a);
        for (Element element : elements) {
            String value01 = element.select("td:eq(1)").text();
            String value = element.select("td:eq(0)").text();
            System.out.println(value);
            System.out.println(value01);
            //
            mhModel.setTenSV(doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentTenSV").text()); // get by ID
            mhModel.setMaSV(doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentMaSV").text());
            mhModel.setMaMH(element.select("td:eq(0)").text());
            mhModel.setTenMH(element.select("td:eq(1)").text());
            mhModel.setNhomMH(element.select("td:eq(2)").text());
            mhModel.setSTC(element.select("td:eq(3)").text());
            mhModel.setMaLop(element.select("td:eq(4)").text());
            mhModel.setSTCHP(element.select("td:eq(5)").text());
            mhModel.setKDK(element.select("td:eq(6)").text());
            mhModel.setTH(element.select("td:eq(7)").text());
            mhModel.setThu(element.select("td:eq(8)").text());
            mhModel.setTietBatDau(element.select("td:eq(9)").text());
            mhModel.setST(element.select("td:eq(10)").text());
            mhModel.setPhong(element.select("td:eq(11)").text());
            mhModel.setCBGD(element.select("td:eq(12)").text());
            mhModel.setTuan(element.select("td:eq(13)").text());
            // add above object to schedule on database :))
            realmHandle.addMonHoc(mhModel);

        }


    }

    // processing  the dialog here :))
    public void createDialog() {
        dialog = new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_popup_dialog);
        btnDialog = (Button) dialog.findViewById(R.id.btnDone);
        edtMaSV = (EditText) dialog.findViewById(R.id.edtMaSV);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                toolbar.setTitle(edtMaSV.getText().toString());// change title of tool bar here :))
                realmHandle = new RealmHandle();
                // before dismissing the pop-up dialog , check internet and student's id is exist on database??
                // first case, check it on database
                if (realmHandle.existStudent(edtMaSV.getText().toString()) == true && Utils.checkInternet(MainActivity.this) == true) {
                    // do something here :))
                    Toast.makeText(MainActivity.this, "student is already on database, do you want to update ???", Toast.LENGTH_SHORT).show();
                    return;
                } else if (realmHandle.existStudent(edtMaSV.getText().toString()) == true && Utils.checkInternet(MainActivity.this) == false) {
                    // displaying information about student imidiately :))

                    Toast.makeText(MainActivity.this, "Welcome! " + realmHandle.findTenSV(edtMaSV.getText().toString()).toString(), Toast.LENGTH_SHORT).show();
                    return;
                } else if (realmHandle.existStudent(edtMaSV.getText().toString()) == false) {
                    if (Utils.checkInternet(MainActivity.this) == false) {
                        Toast.makeText(MainActivity.this, "Enable your connection and try again, plz!!!", Toast.LENGTH_SHORT).show();
                    } else {
                        // begin parse html here
                        Toast.makeText(MainActivity.this, "begin parse html first time", Toast.LENGTH_SHORT).show();
                        new Thread(new Runnable() {
                            @Override
                            public void run() {
                                try {
                                    realmHandle = new RealmHandle();
                                    getSchedule(edtMaSV.getText().toString());// getting html from dkmh.epu.edu.vn to process
                                    getTimeOfWeek(edtMaSV.getText().toString());

                                } catch (IOException e) {
                                    e.printStackTrace();
                                }
                            }
                        }).start();
                    }
                }

//                dialog.dismiss();

//                Toast.makeText(MainActivity.this, "press button done on pop up dialog", Toast.LENGTH_SHORT).show();// change later :))
            }
        });

    }

}
