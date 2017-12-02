package visualbasics.petsavers;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationView;

    private ViewPager viewPager;
    ViewPagerAdapter adapter;

    SearchFragment searchFragment;
    HomeFragment homeFragment;
    LoginRegisterFragment loginRegisterFragment;

    MenuItem prevMenuItem;

    @SuppressLint("ClickableViewAccessibility")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        viewPager = (ViewPager) findViewById(R.id.viewpager);
        bottomNavigationView = findViewById(R.id.bottom_navigation);

        // handles bottom menubar clicks for changing fragments
        bottomNavigationView.setOnNavigationItemSelectedListener(
                new BottomNavigationView.OnNavigationItemSelectedListener() {
                    @Override
                    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.navigation_search:
                                viewPager.setCurrentItem(0, false);
                                setTitle("Search");
                                break;
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(1, false);
                                setTitle("Home");
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(2, false);
                                setTitle("Profile");
                                break;
                        }
                        return false;
                    }
                });

        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            // changes the icon on the bottom menubar to the one that is clicked
            @Override
            public void onPageSelected(int position) {
                if (prevMenuItem != null) {
                    prevMenuItem.setChecked(false);
                } else {
                    bottomNavigationView.getMenu().getItem(0).setChecked(false);
                }
                bottomNavigationView.getMenu().getItem(position).setChecked(true);
                prevMenuItem = bottomNavigationView.getMenu().getItem(position);

            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });

        // disable ViewPager Swipe
        viewPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return true;
            }
        });

        setupViewPager(viewPager);

        // sets initial tab to home tab on startup
        bottomNavigationView.setSelectedItemId(R.id.navigation_home);
    }

    // adds 3 tab fragments to the adapter. Only happens once
    private void setupViewPager(ViewPager viewPager) {
        adapter = new ViewPagerAdapter(getSupportFragmentManager());
        searchFragment = new SearchFragment();
        homeFragment = new HomeFragment();
        loginRegisterFragment = new LoginRegisterFragment();
        adapter.addFragment(searchFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(loginRegisterFragment);
        viewPager.setAdapter(adapter);
    }

    public void openLoginPage(View view) {
        loginRegisterFragment.openLoginPage();
    }

    public void openRegisterPage(View view) {
        loginRegisterFragment.openRegisterPage();
    }

    public void clearFilters(View view)
    {
        searchFragment.clearFilters();
    }

    public void showDogs(View view)
    {
        homeFragment.showDogs();
    }

    public void showCats(View view)
    {
        homeFragment.showCats();
    }

    public void showHamsters(View view)
    {
        homeFragment.showHamsters();
    }

    public void showHedgehogs(View view)
    {
        homeFragment.showHedgehogs();
    }

    public void showRabbits(View view)
    {
        homeFragment.showRabbits();
    }

    public void showFish(View view)
    {
        homeFragment.showFish();
    }

    public void showReptiles(View view)
    {
        homeFragment.showReptiles();
    }

    public void showBirds(View view)
    {
        homeFragment.showBirds();
    }

    public void showFarmAnimals(View view)
    {
        homeFragment.showFarmAnimals();
    }

}
