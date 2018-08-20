package com.example.palakdhingra.easycalc;

import android.app.DatePickerDialog;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;
import com.example.palakdhingra.easycalc.data.TransactionContract.TransactionEntry;

import com.example.palakdhingra.easycalc.data.TransactionDbHelper;

import java.util.Calendar;

public class InsertDataActivity extends AppCompatActivity {

    EditText mSetDate,mAmount, mPayableTo;
    private int mYear, mMonth, mDay;
    /** Database helper that will provide us access to the database */
    private TransactionDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert_details);


        mSetDate = findViewById(R.id.enterDate);
        mDbHelper = new TransactionDbHelper(this);


        final Button selectDate = findViewById(R.id.selectDate);
        selectDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Calendar c = Calendar.getInstance();
                mYear = c.get(Calendar.YEAR);
                mMonth = c.get(Calendar.MONTH);
                mDay = c.get(Calendar.DAY_OF_MONTH);

                DatePickerDialog datePickerDialog = new DatePickerDialog(InsertDataActivity.this,
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {

                                mSetDate.setText(dayOfMonth + "-" + (monthOfYear + 1) + "-" + year);

                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });

        Button addToDatabase = findViewById(R.id.addToDatabase);
        addToDatabase.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertData();
            }
        });


    }

    private void insertData() {

        mSetDate = findViewById(R.id.enterDate);
        mAmount = findViewById(R.id.amount);
        mPayableTo = findViewById(R.id.payableTo);
        // Read from input fields
        // Use trim to eliminate leading or trailing white space
        String dateString = mSetDate.getText().toString().trim();
        String amountString = mAmount.getText().toString().trim();
        String payableToString = mPayableTo.getText().toString().trim();

        Log.i("InsertDataActivity", dateString);
        Log.i("InsertDataActivity", payableToString);
        Log.i("InsertDataActivity", amountString);
        //Log.i("InsertDataActivity", dateString);
        // Create database helper
        if(dateString.isEmpty() || payableToString.isEmpty() || amountString.isEmpty())
        {

            Toast.makeText(InsertDataActivity.this,"Enter Data !! ",Toast.LENGTH_LONG).show();
        }
        else {
            int amount = Integer.parseInt(amountString);

            TransactionDbHelper mDbHelper = new TransactionDbHelper(this);

            // Gets the database in write mode
            SQLiteDatabase db = mDbHelper.getWritableDatabase();

            // Create a ContentValues object where column names are the keys,
            // and pet attributes from the editor are the values.
            ContentValues values = new ContentValues();
            values.put(TransactionEntry.COLUMN_DATE, dateString);
            values.put(TransactionEntry.COLUMN_PAYABLE_TO, payableToString);
            values.put(TransactionEntry.COLUMN_AMOUNT, amount);


            // Insert a new row for pet in the database, returning the ID of that new row.
            long newRowId = db.insert(TransactionEntry.TABLE_NAME, null, values);

            // Show a toast message depending on whether or not the insertion was successful
            if (newRowId == -1) {
                // If the row ID is -1, then there was an error with insertion.
                Toast.makeText(this, "Error while saving transaction", Toast.LENGTH_SHORT).show();
            } else {
                // Otherwise, the insertion was successful and we can display a toast with the row ID.
                Toast.makeText(this, "Transaction saved with row id: " + newRowId, Toast.LENGTH_SHORT).show();
            }
        }

    }


}

