package com.example.wizytowka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Full extends AppCompatActivity {

    Button BtnBack2;
    TextView NameFieldFull;
    TextView SurnameFieldFull;
    TextView EmailFieldFull;
    TextView PhoneFieldFull;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_full);

        BtnBack2 = (Button)findViewById(R.id.btnBack2);
        NameFieldFull = (TextView)findViewById(R.id.nameFieldFul);
        SurnameFieldFull = (TextView)findViewById(R.id.surnameFieldFul);
        EmailFieldFull = (TextView)findViewById(R.id.mailFieldFul);
        PhoneFieldFull = (TextView)findViewById(R.id.phoneFieldFul);


        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String surname = extras.getString("surname");
            String email = extras.getString("email");
            String phone = extras.getString("phone");

            NameFieldFull.setText(name);
            SurnameFieldFull.setText(surname);
            EmailFieldFull.setText(email);
            PhoneFieldFull.setText(phone);
        }

        BtnBack2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;

                        Intent main = new Intent(Full.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );
    }
}