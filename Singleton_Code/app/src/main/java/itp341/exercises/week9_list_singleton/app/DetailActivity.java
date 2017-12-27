package itp341.exercises.week9_list_singleton.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;

import itp341.exercises.week9_list_singleton.R;


public class DetailActivity extends AppCompatActivity {

    public static final String TAG = DetailActivity.class.getSimpleName();

    //TODO how will we pass data from MainListFragment?
//    public static final String EXTRA_POSITION = "extra_position";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_host);

        //TODO get intent data

        Intent i = getIntent();
        int position = i.getIntExtra(MainListFragment.EXTRA_POSITION, -1);

        //Create fragment
        FragmentManager fm = getSupportFragmentManager();
        Fragment f = fm.findFragmentById(R.id.fragment_container);

        //TODO modify fragment creation
        if (f == null ) {
//             f = new DetailFragment();
            f = DetailFragment.newInstance(position);
        }
        FragmentTransaction fragmentTransaction = fm.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_container, f);
        fragmentTransaction.commit();
    }





}

