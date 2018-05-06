package com.vanando.quanlysinhvien.activity;

import android.content.Intent;
import android.support.annotation.NonNull;
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

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.github.clans.fab.FloatingActionMenu;
import com.vanando.quanlysinhvien.R;
import com.vanando.quanlysinhvien.ViewPagerAdapter;

import java.util.HashMap;
import java.util.Map;

public class MainActivity extends AppCompatActivity
            implements NavigationView.OnNavigationItemSelectedListener{

    // tablayout va viewPgaer
    private TabLayout tablayout;
    private ViewPager viewPager;
    private ViewPagerAdapter adapter;
    // floattingButton
    private com.github.clans.fab.FloatingActionButton fabLopHoc, fabSinhVien;
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

        fabLopHoc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ThemLopHocActivity.class);
                startActivity(intent);
            }
        });

        fabSinhVien.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MainActivity.this, "them sinh vien", Toast.LENGTH_SHORT).show();
            }
        });

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
    // anh xa
    private void initView() {
        tablayout = (TabLayout) findViewById(R.id.tablayout);
        viewPager = (ViewPager) findViewById(R.id.viewpager);
        // fab
        fabLopHoc = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemLopHoc);
        fabSinhVien = (com.github.clans.fab.FloatingActionButton) findViewById(R.id.fabThemSinhVien);
        //navigation
        drawerLayout = (DrawerLayout) findViewById(R.id.drawerLayout);
        navigationView = (NavigationView) findViewById(R.id.navigationView);
    }

}
