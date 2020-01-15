package com.example.docbaorss.view;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;

import com.example.docbaorss.R;
import com.example.docbaorss.adapter.ViewPagerAdapter;
import com.example.docbaorss.fragment.CarFragment;
import com.example.docbaorss.fragment.EntertainmentFragment;
import com.example.docbaorss.fragment.HealthyFragment;
import com.example.docbaorss.fragment.HomeFragment;
import com.example.docbaorss.fragment.NewsFragment;
import com.example.docbaorss.fragment.SportFragment;
import com.example.docbaorss.fragment.TechnologyFragment;
import com.example.docbaorss.login.LoginActivity;
import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    ViewPager viewPager;
    private DrawerLayout drawerLayout;
    TabLayout tabLayout;

    private int[] tabIcons = {
            R.drawable.home,
            R.drawable.ball,
            R.drawable.hardware,
            R.drawable.news,
            R.drawable.radio
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolBar);
        setSupportActionBar(toolbar);

        drawerLayout = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);


        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,drawerLayout, toolbar,R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();

        viewPager = findViewById(R.id.viewPager);

        addTabs(viewPager);
        tabLayout = (TabLayout) findViewById(R.id.tablayout);
        tabLayout.setupWithViewPager(viewPager);
        setupTabIcons();



    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        Intent intent;
        switch (menuItem.getItemId()){
            case R.id.nav_Infor:
                intent = new Intent(getApplicationContext(), InforActivity.class);
                startActivity(intent);
                break;
            case R.id.nav_healthy:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new HealthyFragment()).commit();
                break;
            case R.id.nav_Car:
                getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container,new CarFragment()).commit();
                break;
            case  R.id.nav_Login:
                intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
                break;

        }
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
    private void setupTabIcons() {
        tabLayout.getTabAt(0).setIcon(tabIcons[0]);
        tabLayout.getTabAt(1).setIcon(tabIcons[1]);
        tabLayout.getTabAt(2).setIcon(tabIcons[2]);
        tabLayout.getTabAt(3).setIcon(tabIcons[3]);
        tabLayout.getTabAt(4).setIcon(tabIcons[4]);
    }

    @Override
    public void onBackPressed() {
        if(drawerLayout.isDrawerOpen(GravityCompat.START)){
            drawerLayout.closeDrawer(GravityCompat.START);
        }else {
            super.onBackPressed();
        }
    }

    public void addTabs(ViewPager viewPager) {
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(getSupportFragmentManager());
        viewPagerAdapter.addFrg(new HomeFragment(),"Trang chủ");
        viewPagerAdapter.addFrg(new SportFragment(),"Bóng đá");
        viewPagerAdapter.addFrg(new TechnologyFragment(),"Công nghệ");
        viewPagerAdapter.addFrg(new NewsFragment(),"Tin tức");
        viewPagerAdapter.addFrg(new EntertainmentFragment(),"Giải trí");
        viewPager.setAdapter(viewPagerAdapter);
    }
}
