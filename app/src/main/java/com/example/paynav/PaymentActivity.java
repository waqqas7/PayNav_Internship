package com.example.paynav;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PaymentActivity extends AppCompatActivity {
    private TextView payment;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_payment);

        payment = (TextView) findViewById(R.id.payment_details);

        String retrieveDetails = getIntent().getExtras().get("details").toString();
        payment.setText(retrieveDetails);
    }
}