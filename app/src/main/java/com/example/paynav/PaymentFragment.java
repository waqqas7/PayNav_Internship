package com.example.paynav;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class PaymentFragment extends Fragment {
    private View PaymentView;
    private TextView payment;

    public PaymentFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        PaymentView =  inflater.inflate(R.layout.fragment_payment, container, false);


        getActivity().findViewById(R.id.godfather).setVisibility(View.GONE);
        payment = (TextView) PaymentView.findViewById(R.id.payment_details);
        Bundle bundle = getArguments();
        String retrieve = bundle.getString("details");
        payment.setText(retrieve);

        return PaymentView;
    }
}