package visualbasics.petsavers;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserProfileFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_user_profile, container, false);
    }

    public void viewFavorties() {
        Intent intent = new Intent(getActivity(), FavoriteAnimalsActivity.class);
        startActivity(intent);
    }

    public void logOut() {
        MainActivity mainActivity = (MainActivity) getActivity();
        ViewPagerAdapter adapter = mainActivity.adapter;
        adapter.removeFragment(mainActivity.userProfileFragment);
        adapter.addFragment(mainActivity.loginRegisterFragment);
        adapter.notifyDataSetChanged();
    }
}