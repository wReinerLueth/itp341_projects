package itp341.firebase;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class NoteListActivity extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.d("MainActivity", "onCreate called");

        setContentView(R.layout.activity_host);

        FragmentManager fm = getSupportFragmentManager();
        Fragment fragmentList = fm.findFragmentById(R.id.fragment_container);
        if (fragmentList == null){
            fragmentList = new NoteListFragment();
        }

        FragmentTransaction ftList = fm.beginTransaction();
        ftList.replace(R.id.fragment_container, fragmentList);
        ftList.commit();


    }

}
