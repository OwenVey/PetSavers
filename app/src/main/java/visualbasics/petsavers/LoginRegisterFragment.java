package visualbasics.petsavers;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class LoginRegisterFragment extends Fragment {

    public static LoginRegisterFragment newInstance() {
        LoginRegisterFragment fragment = new LoginRegisterFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_register, container, false);
    }

    public void openLoginPage() {
        Intent intent = new Intent(getActivity(), LoginActivity.class);
        startActivityForResult(intent, 10001);
    }

    public void openRegisterPage() {
        Intent intent = new Intent(getActivity(), RegisterActivity.class);
        startActivityForResult(intent, 10001);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            MainActivity mainActivity = (MainActivity)getActivity();
            ViewPagerAdapter adapter = mainActivity.adapter;
            adapter.removeFragment(mainActivity.loginRegisterFragment);
            adapter.addFragment(ProfileFragment.newInstance());
            adapter.notifyDataSetChanged();
        }
    }

}