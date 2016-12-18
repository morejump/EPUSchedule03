package com.example.hp.epuschedule03;

import android.app.Dialog;
import android.content.Intent;
import android.net.Uri;
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
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.hp.epuschedule03.Database.Database;
import com.example.hp.epuschedule03.Database.Student;
import com.example.hp.epuschedule03.Database.Subject;
import com.example.hp.epuschedule03.Database.Week;
import com.example.hp.epuschedule03.Database.passSubject;
import com.example.hp.epuschedule03.Model.CustomAdapter;
import com.example.hp.epuschedule03.Model.Thu;
import com.example.hp.epuschedule03.Utils.Utils;
import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import io.realm.Realm;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {
    private int tuan;
    private Button btnNext;
    private Button btnPrevious;
    private EditText edtTuan;
    private String maSV;
    private Boolean hasMaSV;
    private Dialog dialog;
    private Button btnDialog;
    private EditText edtID;
    private Toolbar toolbar;
    private Student student;
    private Database database;
    private ListView listView;
    private ArrayList<Thu> list;
    private CustomAdapter adapter;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Realm.init(this);
        addListenr();
        init();

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
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
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
        Intent intent;
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
//        } else if (id == R.id.nav_gallery) {
//
//        } else if (id == R.id.nav_slideshow) {
//
//        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_us) {
            intent= new Intent(this,Intro.class);
            startActivity(intent);


        } else if (id == R.id.nav_product) {
            intent= new Intent(this,Guide.class);
            startActivity(intent);

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    private void addListenr() {
        btnNext = (Button) findViewById(R.id.btnNext);
        btnPrevious = (Button) findViewById(R.id.btnPrevious);
        edtTuan = (EditText) findViewById(R.id.edtTuan);
        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuan > 1 && tuan <= 31) {
                    tuan = tuan - 1;
                    edtTuan.setText("" + tuan);
                } else {
                    Toast.makeText(MainActivity.this, "Tuần chỉ từ 1 đến 31", Toast.LENGTH_SHORT).show();
                }

            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tuan >= 1 && tuan < 31) {
                    tuan = tuan + 1;
                    edtTuan.setText("" + tuan);
                } else {
                    Toast.makeText(MainActivity.this, "Tuần chỉ từ 1 đến 31", Toast.LENGTH_SHORT).show();
                }
            }
        });


    }

    // initialize method
    private void init() {
        tuan = 9;
        hasMaSV = false;
        listView = (ListView) findViewById(R.id.listView); // linking to listview in xml file
        list = new ArrayList<>(); // initializing arraylist :))
        list.add(new Thu("Monday"));
        list.add(new Thu("Tueday"));
        list.add(new Thu("Wednesday"));
        list.add(new Thu("Thurday"));
        list.add(new Thu("Friday"));
        list.add(new Thu("Saturday"));
        list.add(new Thu("Sunday"));
        Log.d("bug", "init: " + list.size());
        for (int i = 0; i < list.size(); i++) {
            int a = i + 1;
            int resourceID = getResources().getIdentifier("thu" + a, "drawable", getPackageName());
            list.get(i).setImagePath(resourceID);
        }
        adapter = new CustomAdapter(list, this);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() { // catch click event when someone click item on listview // swtich to new activity
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (hasMaSV) {
                    database = new Database();
                    maSV = edtID.getText().toString();
                    ///------------------------------------------
                    Student student = database.findStudentByID(maSV);
                    if (student != null) {
                        toolbar.setTitle(student.getName());
                        //
                        List<Subject> list = new ArrayList<Subject>();
                        List<passSubject> passSubjects= new ArrayList<passSubject>();
                        //
                        list = Utils.getFinalResults(tuan, position, student);
                        if (list.size() == 0) {
                            Toast.makeText(MainActivity.this, "No subject :))", Toast.LENGTH_SHORT).show();
                        } else { // have something to display
                            int size = list.size();
                            for (int i=0;i<size;i++){
                                //
                                Subject subject= list.get(i);
                                passSubject passSubject= new passSubject();
                                //
                                passSubject.setMaLop(subject.getMaLop());
                                passSubject.setMaMH(subject.getMaMH());
                                passSubject.setPhong(subject.getPhong());
                                passSubject.setTenMH(subject.getTenMH());
                                passSubject.setST(subject.getST());
                                passSubject.setTietBD(subject.getTietBD());
                                //
                                passSubjects.add(passSubject);

                            }
                            Intent intent = new Intent(MainActivity.this, DetailSubject.class);
                            Bundle bundle = new Bundle();
                            bundle.putSerializable("key", (Serializable) passSubjects);
                            intent.putExtras(bundle);
                            startActivity(intent);



                        }

//                        for (int i = 0; i < passSubjects.size(); i++) {
//                            Log.d("bug", "onItemClick: ---------------------------------" + list.get(i).getTenMH());
//                        }
                    } else {
                        Toast.makeText(MainActivity.this, "Your ID is Incorrect", Toast.LENGTH_SHORT).show();
                    }

                } else {
                    Toast.makeText(MainActivity.this, "Enter ur ID, plz!!!", Toast.LENGTH_SHORT).show();
                }

            }
        });
        adapter.notifyDataSetChanged();
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
        // get student to asign week to student's database
        Student student = database.findStudentByID(ID); // get the student with corresponding ID // create a new student
        // starting parse the html
        Elements elements = doc.select("#ctl00_ContentPlaceHolder1_ctl00_ddlTuan option");
        for (Element element : elements) {// loop
            // assign value to student's properties above
            ArrayList<String> list = Utils.splitWeek(element.select("option:eq(" + i + ")").text());
            realm.beginTransaction();
            week.setThoigianBD(list.get(0));
            week.setThoigianKT(list.get(1));
            week.setTuan(tuan);
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
//            Log.d("thaohandsome", "getSchedule: " + doc.select("#ctl00_ContentPlaceHolder1_ctl00_lblContentTenSV").text());
//            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(13)").text());
//            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(0)").text());
//            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(12)").text());
//            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(11)").text());
//            Log.d("thaohandsome", "getSchedule: " + element.select("td:eq(4)").text());
//            Log.d("thaohandsome", "getSchedule: ---------------------------------------------------------------------------------");
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
                database = new Database();
                maSV = edtID.getText().toString();
                toolbar.setTitle("Your ID, plz!!!");// change title of tool bar here :))

                if (maSV != null && !maSV.isEmpty()) {
                    hasMaSV = true;
                    toolbar.setTitle(maSV);

                }
                if (Utils.checkInternet(MainActivity.this) == false & database.findStudentByID(maSV) == null) {
                    Toast.makeText(MainActivity.this, "Enable ur connection, plz!!!", Toast.LENGTH_SHORT).show();
                }
                if (Utils.checkInternet(MainActivity.this) == false & database.findStudentByID(maSV) != null) {
                    Toast.makeText(MainActivity.this, "Done!!!", Toast.LENGTH_SHORT).show();
                }
                // 
                if (Utils.checkInternet(MainActivity.this) == true & database.findStudentByID(maSV) == null) { // in case this connection is online
                    Toast.makeText(MainActivity.this, "Wait a minute !", Toast.LENGTH_SHORT).show();
                    // getting value from dkmh.epu.edu.vn
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            database = new Database();
                            if (database.findStudentByID(maSV) == null) {
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

                }
                if (Utils.checkInternet(MainActivity.this) == true & database.findStudentByID(maSV) != null) {
                    Toast.makeText(MainActivity.this, "Database's student is already exsit!", Toast.LENGTH_SHORT).show();
                }
            }

        });

    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction() {
        Thing object = new Thing.Builder()
                .setName("Main Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart() {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop() {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }
}
