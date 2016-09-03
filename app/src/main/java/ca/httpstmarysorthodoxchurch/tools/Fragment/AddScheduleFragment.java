package ca.httpstmarysorthodoxchurch.tools.Fragment;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.httpstmarysorthodoxchurch.tools.R;
import ca.httpstmarysorthodoxchurch.tools.databinding.AddScheduleFragmentBinding;


/**
 * Created by roneythomas on 2016-08-02.
 */

public class AddScheduleFragment extends Fragment {
    public static AddScheduleFragment newInstance() {

        Bundle args = new Bundle();

        AddScheduleFragment fragment = new AddScheduleFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        AddScheduleFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.add_schedule_fragment, container, false);
        View view = binding.getRoot();
        return view;
    }
}
