package visualbasics.petsavers;

import android.annotation.SuppressLint;
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

    SearchFragment searchFragment;
    HomeFragment homeFragment;
    ProfileFragment profileFragment;

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
                                break;
                            case R.id.navigation_home:
                                viewPager.setCurrentItem(1, false);
                                break;
                            case R.id.navigation_profile:
                                viewPager.setCurrentItem(2, false);
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
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        searchFragment = new SearchFragment();
        homeFragment = new HomeFragment();
        profileFragment = new ProfileFragment();
        adapter.addFragment(searchFragment);
        adapter.addFragment(homeFragment);
        adapter.addFragment(profileFragment);
        viewPager.setAdapter(adapter);
    }


}
