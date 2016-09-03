package ca.httpstmarysorthodoxchurch.tools.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Map;

import ca.httpstmarysorthodoxchurch.tools.R;
import ca.httpstmarysorthodoxchurch.tools.Utils;
import ca.httpstmarysorthodoxchurch.tools.databinding.LectionaryFragmentBinding;
import ca.httpstmarysorthodoxchurch.tools.models.Lectionary;


/**
 * Created by roneythomas on 2016-08-09.
 */

public class LectionaryFragment extends Fragment {

    final ArrayList<Lectionary> mLectionarys = new ArrayList<>();
    LectionaryFragmentBinding binding;
    private FirebaseDatabase mDatabase;
    private DatabaseReference myRef;

    public static LectionaryFragment newInstance() {

        Bundle args = new Bundle();

        LectionaryFragment fragment = new LectionaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        binding = DataBindingUtil.inflate(inflater, R.layout.lectionary_fragment, container, false);
        View view = binding.getRoot();
        mDatabase = Utils.getmDatabase();
        myRef = mDatabase.getReference("lectionary");
        myRef.keepSynced(true);
        myRef.orderByChild("date").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot child : dataSnapshot.getChildren()) {
                    mLectionarys.add(child.getValue(Lectionary.class));
                    Log.d("sdf", "done " + mLectionarys.toString());
                }
                updateUI(inflater);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        return view;
    }

    private void updateUI(final LayoutInflater inflater) {
        Log.d("sdf", "updateUI: yes i am called " + mLectionarys.get(0).toString());
        ArrayAdapter<Lectionary> mSpinnerArrayAdapter = new ArrayAdapter<Lectionary>(getActivity(), R.layout.support_simple_spinner_dropdown_item, mLectionarys);
        mSpinnerArrayAdapter.setDropDownViewResource(R.layout.support_simple_spinner_dropdown_item);
        binding.lectionarySpinner.setAdapter(mSpinnerArrayAdapter);
        GregorianCalendar currentDate = new GregorianCalendar();
        currentDate.set(Calendar.HOUR_OF_DAY, 0);
        currentDate.set(Calendar.MINUTE, 0);
        currentDate.set(Calendar.SECOND, 0);
        currentDate.set(Calendar.MILLISECOND, 0);
        int x = 0;
        for (long date = currentDate.getTimeInMillis(); date >= mLectionarys.get(x).getDate(); x++) {
            Log.d("dsfsdf", "x is " + x + "    " + mLectionarys.get(x));
        }
        binding.lectionarySpinner.setSelection(x);
        binding.lectionarySpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                binding.readingsList.removeAllViews();
                Lectionary mLectionary = mLectionarys.get(i);
                TextView mTitleTextView = (TextView) inflater.inflate(R.layout.list_item_title, null);
                mTitleTextView.setText(mLectionary.getTitle());
                binding.readingsList.addView(mTitleTextView);
                for (Map.Entry<String, ArrayList<String>> reading : mLectionary.getReading().entrySet()) {
                    TextView mReadingTitleTextView = (TextView) inflater.inflate(R.layout.list_item_reading_title, null);
                    mReadingTitleTextView.setText(reading.getKey());
                    binding.readingsList.addView(mReadingTitleTextView);
                    for (String verse : reading.getValue()) {
                        TextView mVerse = (TextView) inflater.inflate(R.layout.list_item_reading_verse, null);
                        mVerse.setText(verse);
                        binding.readingsList.addView(mVerse);
                    }
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
    }

//    @Override
//    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
//        super.onActivityCreated(savedInstanceState);
//        ArrayList<Reading> mReading = new ArrayList<>();
//        ArrayList<String> verse = new ArrayList<>();
//        verse.add("St.Luke14:25-35");
//        mReading.add(new Reading("Evening", verse));
//        Map<String, ArrayList<String>> value = new HashMap<>();
//        value.put("Evening", verse);
//        Lectionary mLectionary = new Lectionary("August-9999: Second Sunday after the Festival of Transfiguration", new GregorianCalendar(2016, 4, 14).getTimeInMillis(), value);
//        Lectionary mLectionary1 = new Lectionary("August-9999: The Festival of the Assumption of St. Mary (Marth Mariam Samajam Day)", new GregorianCalendar(2016, 10, 15).getTimeInMillis(), value);
//        Lectionary mLectionary2 = new Lectionary("August-9999: First Sunday after the Festival of the Assumption", new GregorianCalendar(2016, 3, 28).getTimeInMillis(), value);
//        myRef.push().setValue(mLectionary);
//        myRef.push().setValue(mLectionary1);
//        myRef.push().setValue(mLectionary2);
//    }
}
