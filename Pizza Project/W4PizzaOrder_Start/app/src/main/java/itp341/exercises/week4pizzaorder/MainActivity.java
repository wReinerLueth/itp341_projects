package itp341.exercises.week4pizzaorder;

import android.os.Bundle;
import android.support.annotation.IdRes;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.util.StringBuilderPrinter;
import android.view.KeyEvent;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;

public class MainActivity extends AppCompatActivity {

    // TAG
    private static final String TAG = MainActivity.class.getSimpleName();

    //  variables for widgets
    CheckBox checkPepperoni;
    CheckBox checkPineapple;
    CheckBox checkTofu;
    RadioGroup radioGroupSize;
    SeekBar seekBarNumPizzas;
    TextView textNumPizzasSeekBarProgress;
    Spinner spinnerSpecials;
    TextView textOrderDisplay;
    EditText editName;

    //TODO instance variables

    private boolean wantsPepperoni = false;
    private boolean wantsPinapple = false;
    private boolean wantsTofu = false;
    private String name = "";
    private int numPizzas = 1;
    private String size = "";
    private String specials = "none";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // get references to widgets

        radioGroupSize = (RadioGroup) findViewById(R.id.radio_group_size);

        //TODO create EditorActionListener


        //TODO create checkbox listeners


        //TODO create radiogroup listener

        radioGroupSize.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup radioGroup, @IdRes int i) {
                switch (i) {
                    case R.id.radio_small:
                        size = getResources().getString(R.string.label_small);
                        break;
                    case R.id.radio_medium:
                        size = getResources().getString(R.string.label_medium);
                        break;
                    case R.id.radio_large:
                        size = getResources().getString(R.string.label_large);
                        break;
                    default:
                }
                displayPizzaOrder();
            }
        });
    }


        //TODO seekbar listener


        //TODO create spinner listener


        //TODO generate "human-readable" description of pizza and write to textView

        public void displayPizzaOrder() {
            StringBuilder output = new StringBuilder();
            
            // check if order valid

            output.append("Order for " + name + "\nYou ordered: ");
            output.append(numPizzas);
            output.append(" ");
            output.append(size);
            // TODO: 9/13/2017 Continue for extra credit 
        }


    //TODO determine what qualifies as a valid order
    public boolean isOrderValid() {
        return false;
    }


}
