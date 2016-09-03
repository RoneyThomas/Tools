package ca.httpstmarysorthodoxchurch.tools.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.NavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import ca.httpstmarysorthodoxchurch.tools.R;
import ca.httpstmarysorthodoxchurch.tools.databinding.ToolsPagerFragmentBinding;

/**
 * Created by roneythomas on 2016-08-16.
 */

public class ToolsPagerFragment extends Fragment {
    private static final String TAG = "ToolsPagerFragmnet";
    private static int NUM_ITEMS = 2;
    private ToolsPagerFragmentBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;
    private FirebaseAuth.AuthStateListener mAuthStateListener;

    public static ToolsPagerFragment newInstance() {

        Bundle args = new Bundle();

        ToolsPagerFragment fragment = new ToolsPagerFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.tools_pager_fragment, container, false);
        View view = binding.getRoot();
        ((AppCompatActivity) getActivity()).setSupportActionBar(binding.toolbar);
        binding.toolbar.setTitle(R.string.app_name);
        FragmentManager fragmentManager = getFragmentManager();
        binding.viewPager.setAdapter(new FragmentAdapter(fragmentManager));
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        binding.nvView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch (item.getItemId()) {
                    case R.id.nav_setting:
                        binding.fragmentContainer.removeAllViews();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, SettingsFragment.newInstance())
                                .commit();
                        return true;
                    case R.id.nav_sign_in_out:
                        binding.fragmentContainer.removeAllViews();
                        getFragmentManager().beginTransaction()
                                .replace(R.id.fragment_container, SignInFragment.newInstance())
                                .commit();
                        return true;
                }
                Log.d("dssdsd", "Yes I am called");
                return false;
            }
        });
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDrawerToggle.syncState();
    }

    private class FragmentAdapter extends FragmentStatePagerAdapter {
        private String tabTitles[] = new String[]{"Lectionary", "Schedule"};

        public FragmentAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return LectionaryFragment.newInstance();
                case 1:
                    return AddScheduleFragment.newInstance();
                default:
                    return null;

            }
        }

        @Override
        public int getCount() {
            //String uid = FirebaseAuth.getInstance().getCurrentUser().getUid();
            //Log.d(TAG, "getCount: uid "+uid);
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            if (user == null) {
                NUM_ITEMS = 1;
            }
            return NUM_ITEMS;
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return tabTitles[position];
        }
    }
}
