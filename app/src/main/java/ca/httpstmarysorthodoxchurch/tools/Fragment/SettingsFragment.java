package ca.httpstmarysorthodoxchurch.tools.Fragment;

import android.os.Bundle;
import android.support.v7.preference.PreferenceFragmentCompat;

import ca.httpstmarysorthodoxchurch.tools.R;

/**
 * Created by roneythomas on 2016-08-17.
 */

public class SettingsFragment extends PreferenceFragmentCompat {

    public static SettingsFragment newInstance() {
        Bundle args = new Bundle();

        SettingsFragment fragment = new SettingsFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreatePreferences(Bundle savedInstanceState, String rootKey) {
        addPreferencesFromResource(R.xml.settings_prefernce);
    }
}
