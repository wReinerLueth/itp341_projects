package itp341.week7_review_frag_final;

import android.support.annotation.IdRes;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    TextView textNumVotes;
    RadioGroup radioGroupVote;
    FragmentManager fm;
    FragmentTransaction ft;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        radioGroupVote = (RadioGroup) findViewById(R.id.radio_group_vote);
        textNumVotes = (TextView) findViewById(R.id.text_num_votes);

        setListeners();

        fm = getSupportFragmentManager();
        Fragment frag = fm.findFragmentById(R.id.fragment_container);
        if(frag == null) frag = new MochaFragment();
        ft = fm.beginTransaction();
        ft.add(R.id.fragment_container, frag);
    }

    public void setListeners(){
        radioGroupVote.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, @IdRes int checkedId) {
                if(checkedId == R.id.radio_pirate) clickedPirate();
                if(checkedId == R.id.radio_wolf) clickedWolf();
            }
        });
    }

    public void clickedPirate(){
        Fragment pirate = new MochaFragment();
        ft.replace(R.id.fragment_container, pirate);
        ft.commit();
    }

    public void clickedWolf(){
        Fragment wolf = new MochaFragment();
        ft.replace(R.id.fragment_container, wolf);
        ft.commit();
    }


    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
