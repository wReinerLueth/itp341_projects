package itp341.firebase;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import itp341.firebase.model.Note;


public class NoteListFragment extends Fragment {

    private final static String TAG = NoteListFragment.class.getSimpleName();
    private ListView list;
    private TextView textCount;
    private Button buttonNewNote;

    //todo database references


    FirebaseListAdapter mAdapter;

    public NoteListFragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //todo get database

        //todo get database reference paths
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.fragment_note_list, container, false);

        list = (ListView) v.findViewById(R.id.noteFragmentList);

        //todo instantiate adapter
        //todo set adapter for listview


        //todo list item click listener


        textCount = (TextView) v.findViewById(R.id.fragmentMainCount);
        buttonNewNote = (Button) v.findViewById(R.id.fragmentMainNewNote);
        buttonNewNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), NoteWriteActivity.class);
                startActivity(i);
            }
        });
        //todo read count value


        return v;
    }

    //todo onDetach


    //todo create custom FirebaseListAdapter

}
