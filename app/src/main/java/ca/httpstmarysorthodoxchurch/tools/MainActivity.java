package ca.httpstmarysorthodoxchurch.tools;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import com.google.firebase.database.*;

public class MainActivity extends AppCompatActivity {

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
