package com.example.palakdhingra.easycalc;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.example.palakdhingra.easycalc.data.TransactionContract;
import com.example.palakdhingra.easycalc.data.TransactionDbHelper;

import java.io.Serializable;
import java.util.ArrayList;

public class DisplayActivity extends AppCompatActivity{
    private TransactionDbHelper mDbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display);

//        ArrayList<DataActivity> transactionDetails = new ArrayList<>();
//        transactionDetails.add(new DataActivity("2-5-2019","Palak",78));
//        transactionDetails.add(new DataActivity("1-6-2012","Tanya",780));
//        transactionDetails.add(new DataActivity("25-8-2018","Freddy",7867));
//
//        TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactionDetails);
//
//        // Get a reference to the ListView, and attach the adapter to the listView.
//        ListView listView = (ListView) findViewById(R.id.listview_transaction);
////        listView.setAdapter(transactionAdapter);

        Button addTransaction = findViewById(R.id.addTransaction);
        addTransaction.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DisplayActivity.this,InsertDataActivity.class);
                startActivity(intent);

            }
        });

        mDbHelper = new TransactionDbHelper(this);

        Button viewAll = findViewById(R.id.viewAllTransaction);
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                displayDatabaseInfo();
            }
        });
    }

    @Override
    protected void onStart() {
        super.onStart();
        displayTopTenInfo();
    }

    private void displayTopTenInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TransactionContract.TransactionEntry.COLUMN_DATE,
                TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO,
                TransactionContract.TransactionEntry.COLUMN_AMOUNT
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                TransactionContract.TransactionEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups//
                TransactionContract.TransactionEntry._ID+ " DESC" ,"10");                   // The sort order


        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
            // displayView.setText("The transaction table contains " + cursor.getCount() + " transaction.\n\n");
//            displayView.append(
//                    TransactionContract.TransactionEntry.COLUMN_DATE + " - " +
//                            TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO + " - " +
//                            TransactionContract.TransactionEntry.COLUMN_AMOUNT + "\n");

            // Figure out the index of each column
            int dateColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_DATE);
            int payableToColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO);
            int amountColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_AMOUNT);
            final ArrayList<DataActivity> transactionDetails = new ArrayList<>();

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentDate = cursor.getString(dateColumnIndex);
                String currentPayableTo = cursor.getString(payableToColumnIndex);
                int currentAmount = cursor.getInt(amountColumnIndex);

                transactionDetails.add(new DataActivity(currentDate,currentPayableTo,currentAmount));
                // Display the values from each column of the current row in the cursor in the TextView
//                displayView.append(("\n" +
//                        currentDate + " - " +
//                        currentPayableTo + " - " +
//                        currentAmount));

            }
            TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactionDetails);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) findViewById(R.id.listview_transaction);
            listView.setAdapter(transactionAdapter);


        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void displayDatabaseInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = mDbHelper.getReadableDatabase();

        // Define a projection that specifies which columns from the database
        // you will actually use after this query.
        String[] projection = {
                TransactionContract.TransactionEntry.COLUMN_DATE,
                TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO,
                TransactionContract.TransactionEntry.COLUMN_AMOUNT
        };

        // Perform a query on the pets table
        Cursor cursor = db.query(
                TransactionContract.TransactionEntry.TABLE_NAME,   // The table to query
                projection,            // The columns to return
                null,                  // The columns for the WHERE clause
                null,                  // The values for the WHERE clause
                null,                  // Don't group the rows
                null,                  // Don't filter by row groups
                null);                   // The sort order


        try {
            // Create a header in the Text View that looks like this:
            //
            // The pets table contains <number of rows in Cursor> pets.
            // _id - name - breed - gender - weight
            //
            // In the while loop below, iterate through the rows of the cursor and display
            // the information from each column in this order.
           // displayView.setText("The transaction table contains " + cursor.getCount() + " transaction.\n\n");
//            displayView.append(
//                    TransactionContract.TransactionEntry.COLUMN_DATE + " - " +
//                            TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO + " - " +
//                            TransactionContract.TransactionEntry.COLUMN_AMOUNT + "\n");

            // Figure out the index of each column
            int dateColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_DATE);
            int payableToColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_PAYABLE_TO);
            int amountColumnIndex = cursor.getColumnIndex(TransactionContract.TransactionEntry.COLUMN_AMOUNT);
            final ArrayList<DataActivity> transactionDetails = new ArrayList<>();

            // Iterate through all the returned rows in the cursor
            while (cursor.moveToNext()) {
                // Use that index to extract the String or Int value of the word
                // at the current row the cursor is on.
                String currentDate = cursor.getString(dateColumnIndex);
                String currentPayableTo = cursor.getString(payableToColumnIndex);
                int currentAmount = cursor.getInt(amountColumnIndex);

                transactionDetails.add(new DataActivity(currentDate,currentPayableTo,currentAmount));
                // Display the values from each column of the current row in the cursor in the TextView
//                displayView.append(("\n" +
//                        currentDate + " - " +
//                        currentPayableTo + " - " +
//                        currentAmount));

            }
            TransactionAdapter transactionAdapter = new TransactionAdapter(this, transactionDetails);

            // Get a reference to the ListView, and attach the adapter to the listView.
            ListView listView = (ListView) findViewById(R.id.listview_transaction);
            listView.setAdapter(transactionAdapter);

            listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                public void onItemClick(AdapterView<?> parent, View view,
                                        int position, long id) {


                        Intent intent = new Intent(view.getContext(), SingleActivity.class);
                        intent.putExtra("data", transactionDetails.get(position));
                        startActivityForResult(intent, 0);




                }
            });

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }


}
