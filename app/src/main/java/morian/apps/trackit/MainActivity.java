package morian.apps.trackit;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.MenuItem;

import com.google.android.material.navigation.NavigationView;
import com.google.android.material.tabs.TabLayout;

import morian.apps.trackit.Date.DateFragment;
import morian.apps.trackit.Nutrition.NutritionFragment;
import morian.apps.trackit.Nutrition.NutritionListFragment;
import morian.apps.trackit.Sport.SportFragment;
import morian.apps.trackit.Sport.SportListFragment;
import morian.apps.trackit.Weather.WeatherFragment;
import morian.apps.trackit.Work.WorkFragment;

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
                adapter.updateTitle(0, "Nutrition");
                adapter.notifyDataSetChanged();
                getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                        new NutritionFragment()).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                        new NutritionListFragment()).commit();
                break;
            case (R.id.nav_sport):
                adapter.updateTitle(0, "Sport");
                adapter.notifyDataSetChanged();
                getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                        new SportFragment()).commit();
                getSupportFragmentManager().beginTransaction().replace(R.id.list_frame,
                        new SportListFragment()).commit();
                break;
            case (R.id.nav_work):
                adapter.updateTitle(0, "Work");
                adapter.notifyDataSetChanged();
                getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                        new WorkFragment()).commit();
                break;
            case (R.id.nav_weather):
                adapter.updateTitle(0, "Weather");
                adapter.notifyDataSetChanged();
                getSupportFragmentManager().beginTransaction().replace(R.id.root_add_frame,
                        new WeatherFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
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
