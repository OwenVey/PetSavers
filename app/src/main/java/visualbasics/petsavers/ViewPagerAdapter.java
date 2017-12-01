package visualbasics.petsavers;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.app.FragmentStatePagerAdapter;

import java.util.ArrayList;
import java.util.List;

public class ViewPagerAdapter extends FragmentStatePagerAdapter {

    private final List<Fragment> fragmentList = new ArrayList<>();

    static final int NUM_ITEMS = 3;
    private final FragmentManager fragmentManager;
    private Fragment mFragmentAtPos0;



    public ViewPagerAdapter(FragmentManager manager) {
        super(manager);
        fragmentManager = manager;
    }

    @Override
    public Fragment getItem(int position) {
        return fragmentList.get(position);
    }

    @Override
    public int getCount() {
        return fragmentList.size();
    }

    public void addFragment(Fragment fragment) {
        fragmentList.add(fragment);
    }

    public void removeFragment(Fragment fragment) {
        fragmentList.remove(fragment);
    }

    @Override
    public int getItemPosition(Object object) {
        return POSITION_NONE;
    }

//    @Override
//    public int getItemPosition(Object object)
//    {
//        if (object instanceof FirstPageFragment && mFragmentAtPos0 instanceof NextFragment)
//            return POSITION_NONE;
//        return POSITION_UNCHANGED;
//    }

}
