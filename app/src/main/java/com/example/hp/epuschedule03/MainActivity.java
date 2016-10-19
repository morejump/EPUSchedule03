package com.example.hp.epuschedule03;

import android.app.Dialog;
import android.net.sip.SipAudioCall;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.widget.Button;
import android.widget.Toast;

import com.example.hp.epuschedule03.Database.RealmHandle;
import com.example.hp.epuschedule03.Database.monHocModel;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {
    private monHocModel model;
    private RealmHandle realmHandle;
    private Dialog dialog;
    private Button btnDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //
        createDialog();
        // creating a new thread to avoid exception
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    init();
                    getHTML();// getting html from dkmh.epu.edu.vn to process

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }).start();

        // toolbar
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);// foat button here :))
        fab.setImageResource(R.drawable.ic_pen);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                        .setAction("Action", null).show();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
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
        model = new monHocModel();
        realmHandle = new RealmHandle();

    }

    // get html to process
    private void getHTML() throws IOException {
        //
        Document doc = Jsoup.connect("http://dkmh.epu.edu.vn/default.aspx?page=thoikhoabieu&sta=1&id=1481310029")
                .maxBodySize(0)
                .timeout(0)
                .get();
        Elements elements = doc.select("table.body-table td");
        //
        for (Element element : elements) {
            String value01 = element.select("td:eq(1)").text();
            String value = element.select("td:eq(0)").text();
            System.out.println(value);
            System.out.println(value01);
//            System.out.println(doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentMaSV").text());
            //
            model.setMaSV(doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentMaSV").text());
            model.setMaMH(element.select("td:eq(0)").text());
            model.setTenMH(element.select("td:eq(1)").text());
            model.setNhomMH(element.select("td:eq(2)").text());
            model.setSTC(element.select("td:eq(3)").text());
            model.setMaLop(element.select("td:eq(4)").text());
            model.setSTCHP(element.select("td:eq(5)").text());
            model.setKDK(element.select("td:eq(6)").text());
            model.setTH(element.select("td:eq(7)").text());
            model.setThu(element.select("td:eq(8)").text());
            model.setTietBatDau(element.select("td:eq(9)").text());
            model.setST(element.select("td:eq(10)").text());
            model.setPhong(element.select("td:eq(11)").text());
            model.setCBGD(element.select("td:eq(12)").text());
            model.setTuan(element.select("td:eq(13)").text());
            // add above object to schedule on database :))
            realmHandle.addMonHoc(model);

        }
        List<monHocModel> list = realmHandle.findMonHoc("tử");
        for (monHocModel monHocModel : list) {
            Log.d("thaohandsome", "getHTML: " + monHocModel.getTenMH());
        }


    }
    public void createDialog(){
        LayoutInflater inflater = getLayoutInflater();
        View alertLayout = inflater.inflate(R.layout.custom_popup_dialog, null);
        dialog= new Dialog(MainActivity.this);
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.custom_popup_dialog);
//        dialog.reques
//        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        btnDialog= (Button) alertLayout.findViewById(R.id.btnDone);
        btnDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "press button done on pop up dialog", Toast.LENGTH_SHORT).show();
            }
        });

    }
}
