package com.example.wizytowka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class Minimal extends AppCompatActivity {

    Button BtnBack;
    TextView NameFieldMin;
    TextView SurnameFieldMin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_minimal);

        NameFieldMin = (TextView)findViewById(R.id.surnameFieldMin);
        SurnameFieldMin = (TextView)findViewById(R.id.nameFieldMin);
        BtnBack = (Button)findViewById(R.id.btnBack);

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            String name = extras.getString("name");
            String surname = extras.getString("surname");

            NameFieldMin.setText(name);
            SurnameFieldMin.setText(surname);

        }

        BtnBack.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;

                        Intent main = new Intent(Minimal.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );
    }
}