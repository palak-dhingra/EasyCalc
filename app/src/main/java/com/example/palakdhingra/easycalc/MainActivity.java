package com.example.palakdhingra.easycalc;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import static android.app.PendingIntent.getActivity;

public class MainActivity extends AppCompatActivity {
    SharedPreferences sharedpreferences;
    TextView nameTextField;
    TextView passwordTextField;
    public static final String mypreference = "mypref";
    public static final String Name = "nameKey";
    public static final String Password = "passwordKey";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        nameTextField = (TextView) findViewById(R.id.name);
        passwordTextField = (TextView) findViewById(R.id.password);

        sharedpreferences = getSharedPreferences(mypreference,
                Context.MODE_PRIVATE);
        if (sharedpreferences.contains(Name)) {
            nameTextField.setText(sharedpreferences.getString(Name, ""));
        }
        if (sharedpreferences.contains(Password)) {
            passwordTextField.setText(sharedpreferences.getString(Password, ""));

        }

        Button show = findViewById(R.id.show);
        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String enterName = nameTextField.getText().toString();
                String enterPassword = passwordTextField.getText().toString();

                SharedPreferences.Editor editor = sharedpreferences.edit();
                if(enterName.isEmpty() || enterPassword.isEmpty())
                {
                    Toast.makeText(MainActivity.this,"Fields Are Empty",Toast.LENGTH_LONG).show();
                }
                else{
                editor.putString(Name, enterName);
                editor.putString(Password, enterPassword);
                editor.commit();}
            }

        });

        Button signIn = findViewById(R.id.signIn);
        signIn.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                nameTextField = (TextView) findViewById(R.id.name);
                passwordTextField = (TextView) findViewById(R.id.password);

                sharedpreferences = getSharedPreferences(mypreference,
                        Context.MODE_PRIVATE);


                    String enterName = nameTextField.getText().toString();
                    String enterPassword = passwordTextField.getText().toString();
                    if(enterName.equals(sharedpreferences.getString(Name, "")) && enterPassword.equals(sharedpreferences.getString(Password, ""))){
                        Log.i("MainActivity.this","Hola");

                        Intent intent = new Intent(MainActivity.this,DisplayActivity.class);
                        startActivity(intent);
                    }
                    else{
                        Toast.makeText(MainActivity.this,"Loser !!",Toast.LENGTH_LONG).show();
                    }

            }
        });

    }
}
