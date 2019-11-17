package morian.apps.trackit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.room.Room;
import androidx.viewpager.widget.ViewPager;

import android.content.Context;
import android.os.Bundle;
import android.os.Environment;
import android.os.storage.StorageManager;
import android.view.MenuItem;
import android.widget.Toast;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.nio.channels.FileChannel;

import morian.apps.trackit.Date.DateFragment;
import morian.apps.trackit.Nutrition.NutritionFragment;
import morian.apps.trackit.Nutrition.NutritionListFragment;
import morian.apps.trackit.Sleep.SleepFragment;
import morian.apps.trackit.Sleep.SleepListFragment;
import morian.apps.trackit.Sport.SportFragment;
import morian.apps.trackit.Sport.SportListFragment;
import morian.apps.trackit.Weather.WeatherFragment;
import morian.apps.trackit.Work.WorkFragment;
import morian.apps.trackit.Work.WorkListFragment;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;
    private ViewPageAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        InitNavigation();
        InitViewPager();

        getSupportFragmentManager().beginTransaction().replace(R.id.global_date_frame,
                new DateFragment()).commit();
    }

    private void InitNavigation() {
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
    }

    private void InitViewPager() {
        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.main_viewPager_id);
        adapter = new ViewPageAdapter(getSupportFragmentManager());

        // Adding Fragments
        adapter.addFragment(new RootFragment(), "Nutrition");
        adapter.addFragment(new ListFragment(), "List");

        //Adapter Setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        viewPager.addOnPageChangeListener(pageChangeListener);
    }

    @Override
    public void onBackPressed() {
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        switch (menuItem.getItemId()) {
            case (R.id.nav_nutrition):
                openNutritionPage();
                break;
            case (R.id.nav_sport):
                openSportPage();
                break;
            case (R.id.nav_work):
                openWorkPage();
                break;
            case (R.id.nav_sleep):
                openSleepPage();
                break;
            case (R.id.nav_weather):
                adapter.updateTitle(0, "Weather");
                adapter.notifyDataSetChanged();
                getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                        new WeatherFragment()).commit();
                break;
            case (R.id.nav_export_db):
                try {
                    File sd = Environment.getExternalStorageDirectory();

                    if (sd.canWrite()) {
                        String currentDBPath = getDatabasePath("track_database").getAbsolutePath();
                        String backupDBPath = "track_database.db";

                        File currentDB = new File(currentDBPath);
                        File backupDB = new File(backupDBPath);

                        if (currentDB.exists()) {
                            FileChannel src = new FileInputStream(currentDB).getChannel();
                            FileChannel dst = new FileOutputStream(backupDB).getChannel();
                            dst.transferFrom(src, 0, src.size());
                            src.close();
                            dst.close();
                        }
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }

                Toast.makeText(this, "Done!", Toast.LENGTH_SHORT).show();
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }

    private void openSleepPage() {
        adapter.updateTitle(0, "Sleep");
        adapter.notifyDataSetChanged();
        getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                new SleepFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                new SleepListFragment()).commit();
    }

    private void openWorkPage() {
        adapter.updateTitle(0, "Work");
        adapter.notifyDataSetChanged();
        getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                new WorkFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                new WorkListFragment()).commit();
    }

    private void openSportPage() {
        adapter.updateTitle(0, "Sport");
        adapter.notifyDataSetChanged();
        getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                new SportFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                new SportListFragment()).commit();
    }

    private void openNutritionPage() {
        adapter.updateTitle(0, "Nutrition");
        adapter.notifyDataSetChanged();
        getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                new NutritionFragment()).commit();
        getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                new NutritionListFragment()).commit();
    }

    private ViewPager.OnPageChangeListener pageChangeListener = new ViewPager.OnPageChangeListener() {

        int currentPosition = 0;

        @Override
        public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        }

        @Override
        public void onPageSelected(int position) {
            ViewPageFragmentLifecycle fragmentToShow = (ViewPageFragmentLifecycle) adapter.getItem(position);
            fragmentToShow.onResumeFragment();

            ViewPageFragmentLifecycle fragmentToHide = (ViewPageFragmentLifecycle) adapter.getItem(currentPosition);
            fragmentToHide.onPauseFragment();

            currentPosition = position;
        }

        @Override
        public void onPageScrollStateChanged(int state) {
        }
    };
}
