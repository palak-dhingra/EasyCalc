package com.example.palakdhingra.easycalc;

import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SingleActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single);

        DataActivity data = (DataActivity) getIntent().getSerializableExtra("data");
        TextView date = findViewById(R.id.display_date);
        TextView payableTo = findViewById(R.id.display_payableTo);
        TextView amount = findViewById(R.id.display_amount);

        date.setText(data.getmDate());
        payableTo.setText(data.getmPayableTo());
        amount.setText(data.getmAmount()+"");
    }
}
