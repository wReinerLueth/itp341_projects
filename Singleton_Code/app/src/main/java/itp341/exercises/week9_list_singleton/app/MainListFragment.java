package itp341.exercises.week9_list_singleton.app;

import itp341.exercises.week9_list_singleton.R;
import itp341.exercises.week9_list_singleton.app.model.CoffeeShop;
import itp341.exercises.week9_list_singleton.app.model.CoffeeShopSingleton;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.List;

public class MainListFragment extends Fragment {

    private static final String TAG = MainListFragment.class.getSimpleName();
    public static final String EXTRA_POSITION = "extra_position";       // key for the intent extra

    Button buttonAdd;
    ListView listView;

    //TODO create array and adapter
//    private ArrayAdapter<CoffeeShop> adapter;
    private CoffeeShopAdapter adapter;

    public MainListFragment() {
        // Required empty public constructor
    }


    public static MainListFragment newInstance() {
        Bundle args = new Bundle();

        MainListFragment f = new MainListFragment();
        f.setArguments(args);

        return f;
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.main_list_fragment, container, false);

        Log.d(TAG, "onCreateView");

        //find views
        buttonAdd = (Button) v.findViewById(R.id.button_add);
        listView = (ListView)v.findViewById(R.id.listView);

        //TODO access coffee shop list and load it in the list
        List<CoffeeShop> shops = CoffeeShopSingleton.get(getContext()).getAllCoffeeShops();
//        adapter = new ArrayAdapter<CoffeeShop>(getContext(), android.R.layout.simple_list_item_1, shops);
        adapter = new CoffeeShopAdapter(getContext(), R.layout.list_coffee_shop_row, shops);
        listView.setAdapter(adapter);


        //TODO What happens when user clicks add?
        buttonAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.d(TAG, "mButtonadd: onClick ");
                Intent i = new Intent(getActivity(), DetailActivity.class);
                startActivityForResult(i, 0);
            }
        });

        //TODO create listview item click listener
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long id) {
                Log.d(TAG, "listView: onListItemClick");
                Intent i = new Intent(getActivity(), DetailActivity.class);
                i.putExtra(EXTRA_POSITION, position);
                startActivityForResult(i, 0);

            }
        });

        return v;
    }




    //TODO finish onActivityResult
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.d(TAG, "onActivityResult: requestCode: " + requestCode);

        if (resultCode == Activity.RESULT_OK) {
            // this means user hit SAVE so we need to refresh
            adapter.notifyDataSetChanged();
        }

    }

    private class CoffeeShopAdapter extends ArrayAdapter<CoffeeShop>{
        // default constructor
        // this you do EVERY TIME--almost always the same
        public CoffeeShopAdapter(Context c, int resID, List<CoffeeShop> shops){
            super(c, resID, shops);
        }

        // getView() generates a SINGLE row
        // almost always the same as well

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            // Step 1 -- inflate view (row) if necessary
            if(convertView == null){        // row has NEVER BEEN CREATED
                convertView = getActivity().getLayoutInflater().inflate(R.layout.list_coffee_shop_row, null);
            }

            // Step 2 -- get references to the xml views in the row
            TextView textName = (TextView) convertView.findViewById(R.id.list_row_name);
            TextView textCity = (TextView) convertView.findViewById(R.id.list_row_city);

            // Step 3 -- get the new model data
            CoffeeShop cs = getItem(position);      // getting the specified coffee shop from the adapter

            // Step 4 -- load the data from the model to the view
            textName.setText(cs.getName());
            textCity.setText(cs.getCity());

            return convertView;
        }
    }
}
