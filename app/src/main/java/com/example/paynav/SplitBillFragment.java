package com.example.paynav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class SplitBillFragment extends Fragment {

    public SplitBillFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        getActivity().findViewById(R.id.godfather).setVisibility(View.GONE);

        return inflater.inflate(R.layout.fragment_split_bill, container, false);
    }
}