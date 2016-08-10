package ca.httpstmarysorthodoxchurch.tools;

import android.databinding.DataBindingUtil;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import ca.httpstmarysorthodoxchurch.tools.databinding.LectionaryFragmentBinding;


/**
 * Created by roneythomas on 2016-08-09.
 */

public class LectionaryFragment extends Fragment {

    private RecyclerView mLectionaryRecyclerView;
    private RecyclerView.Adapter mAdapter;
    public static LectionaryFragment newInstance() {

        Bundle args = new Bundle();

        LectionaryFragment fragment = new LectionaryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        LectionaryFragmentBinding binding = DataBindingUtil.inflate(inflater, R.layout.lectionary_fragment, container, false);
        View view = binding.getRoot();
        //binding.lectionaryRecyclerView.setAdapter(mAdapter);

        return view;
    }
}
