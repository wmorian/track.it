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

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    private DrawerLayout drawer;
    private TabLayout tabLayout;
    private ViewPager viewPager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar,
                R.string.navigation_draw_open, R.string.navigation_draw_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        tabLayout = findViewById(R.id.tablayout_id);
        viewPager = findViewById(R.id.viewPager_id);
        ViewPageAdapter adapter = new ViewPageAdapter(getSupportFragmentManager());

        // Adding Fragments
        adapter.AddFragment(new SportFragment(), "Sport");
        adapter.AddFragment(new SportListFragment(), "List");

        //Adapter Setup
        viewPager.setAdapter(adapter);
        tabLayout.setupWithViewPager(viewPager);

        // init with this fragment when first call
        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction().replace(R.id.viewPager_id,
                    new SportFragment()).commit();
            navigationView.setCheckedItem(R.id.nav_sport);
        }
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
            case (R.id.nav_sport):
                getSupportFragmentManager().beginTransaction().replace(R.id.viewPager_id,
                        new SportFragment()).commit();
                break;
            case (R.id.nav_work):
                getSupportFragmentManager().beginTransaction().replace(R.id.viewPager_id,
                        new WorkFragment()).commit();
                break;
            case (R.id.nav_weather):
                getSupportFragmentManager().beginTransaction().replace(R.id.viewPager_id,
                        new WeatherFragment()).commit();
                break;
        }

        drawer.closeDrawer(GravityCompat.START);

        return true;
    }
}
