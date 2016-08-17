package ca.httpstmarysorthodoxchurch.tools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import ca.httpstmarysorthodoxchurch.tools.databinding.ToolsPagerFragmentBinding;

/**
 * Created by roneythomas on 2016-08-16.
 */

public class ToolsPagerFragment extends Fragment {
    private static final int NUM_ITEMS = 2;
    private ToolsPagerFragmentBinding binding;
    private ActionBarDrawerToggle mDrawerToggle;

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
        binding.viewPager.setAdapter(new FragmentStatePagerAdapter(fragmentManager) {
            private String tabTitles[] = new String[]{"Lectionary", "Schedule"};

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
                return NUM_ITEMS;
            }

            @Override
            public CharSequence getPageTitle(int position) {
                return tabTitles[position];
            }
        });
        mDrawerToggle = new ActionBarDrawerToggle(getActivity(), binding.drawerLayout, binding.toolbar, R.string.drawer_open, R.string.drawer_close);
        binding.drawerLayout.addDrawerListener(mDrawerToggle);
        mDrawerToggle.setDrawerIndicatorEnabled(true);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mDrawerToggle.syncState();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                binding.drawerLayout.openDrawer(GravityCompat.START);
                return true;
            default:
                return super.onOptionsItemSelected(item);

        }
    }
}
