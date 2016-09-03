package ca.httpstmarysorthodoxchurch.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;

import ca.httpstmarysorthodoxchurch.tools.Fragment.ToolsPagerFragment;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        FragmentManager manager = getSupportFragmentManager();
        Fragment fragment = manager.findFragmentById(R.id.contianer_main);
        if (fragment == null) {
            fragment = ToolsPagerFragment.newInstance();
            manager.beginTransaction().add(R.id.contianer_main, fragment).commit();
        }
        setContentView(R.layout.activity_main);
    }
}
