package com.vanando.quanlysinhvien;

import android.app.ActionBar;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.TabLayout;
import android.support.v4.app.FragmentManager;
import android.support.v4.view.GravityCompat;
import android.support.v4.view.ViewPager;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener{


    // tablayout va viewPgaer
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    // floattingButton
    private FloatingActionButton fab;
    private FloatingActionButton fab_lop;
    private FloatingActionButton fab_sinhVien;
    private FloatingActionButton fab_cancel;
    // navigation
    private DrawerLayout drawerLayout;
    ActionBarDrawerToggle toggle;
    private NavigationView navigationView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //toolbar
        initToolbar();
        // anh xa
        initView();
        // viewPager
        creatViewPager();
        // floatingButtonAction
        floatingButtonAction();
        // navigation
        creatDrawer();
    }

    // menu Khoi Tao
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_khoitao, menu);
        return super.onCreateOptionsMenu(menu);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // click menu Search
        switch (item.getItemId()) {
            case R.id.menuSearch:
                Toast.makeText(this, "menu Search", Toast.LENGTH_SHORT).show();
                break;
        }
        // click vao toggle: 3 dau gach ngang
        if (toggle.onOptionsItemSelected(item)) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
    // floattingButton
    private void floatingButtonAction() {

        fab_lop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Lop", Toast.LENGTH_SHORT).show();
            }
        });

        fab_sinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Sinh Vien", Toast.LENGTH_SHORT).show();
            }
        });

        fab.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View view) {
                fab_lop.show();
                fab_sinhVien.show();
                fab_cancel.show();
                fab.hide();
            }
        });

        fab_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                fab_lop.hide();
                fab_sinhVien.hide();
                fab_cancel.hide();
                fab.show();
            }
        });

    }
    // anh xa
    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        fab = (FloatingActionButton) findViewById(R.id.floatingAction);
        fab_lop = (FloatingActionButton) findViewById(R.id.floatingActionLop);
        fab_sinhVien = (FloatingActionButton) findViewById(R.id.floatingActionSinhVien);
        fab_cancel = (FloatingActionButton) findViewById(R.id.floatingActionExit);
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
    }
    // toolbar
    private void initToolbar() {
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle("Quản lý sinh viên");
        getSupportActionBar().setDisplayShowHomeEnabled(false);
    }
    // viewPager
    private void creatViewPager() {
        FragmentManager manager = getSupportFragmentManager();
        adapter = new ViewPagerAdapter(manager);
        viewPager.setAdapter(adapter);
        tablayout.setupWithViewPager(viewPager);
    }
    // drawerLayout
    private void creatDrawer() {
        toggle = new ActionBarDrawerToggle(MainActivity.this,drawerLayout,
                R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        navigationView.setNavigationItemSelectedListener(this);
    }
    @Override
    public void onBackPressed() {
        if (drawerLayout.isDrawerOpen(GravityCompat.START)) {
            drawerLayout.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    // navigation
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {

        switch (item.getItemId()) {
            case  R.id.itemEvent:
                Toast.makeText(this, "item Event", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.itemSearch:
                Toast.makeText(this, "item Search", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.itemSetting:
                Toast.makeText(this, "item Setting", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.itemLogin:
                Toast.makeText(this, "item Login", Toast.LENGTH_SHORT).show();
                break;
            case  R.id.itemLogout:
                Toast.makeText(this, "item Logout", Toast.LENGTH_SHORT).show();
                break;

            default:
                drawerLayout.closeDrawer(GravityCompat.START);
                break;

        } drawerLayout.closeDrawer(GravityCompat.START);

        return false;
    }
}
