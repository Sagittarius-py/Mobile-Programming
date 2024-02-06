package com.example.wizytowka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    Button BtnMin;

    Button BtnFull;
    Button BtnRandom;
    EditText NameField;
    EditText SurnameField;


    EditText RandomField;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        View view = this.getCurrentFocus();
        if (view != null) {
            InputMethodManager imm = (InputMethodManager)getSystemService(Context.INPUT_METHOD_SERVICE);
            imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BtnMin = (Button)findViewById(R.id.btnMin);
        BtnFull = (Button)findViewById(R.id.btnFull);
        NameField = (EditText)findViewById(R.id.nameField);
        SurnameField = (EditText)findViewById(R.id.surnameFild);

        RandomField = (EditText)findViewById(R.id.randomField);
        BtnRandom = (Button)findViewById(R.id.btnRandom);

        BtnMin.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = NameField.getText().toString();
                        String surname = SurnameField.getText().toString();

                        Intent minimal = new Intent(MainActivity.this, Minimal.class);
                        minimal.putExtra("name", name);
                        minimal.putExtra("surname", surname);
                        startActivity(minimal);
                    }
                }
        );

        BtnFull.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String name = NameField.getText().toString();
                        String surname = SurnameField.getText().toString();

                        Intent full = new Intent(MainActivity.this, extend.class);
                        full.putExtra("name", name);
                        full.putExtra("surname", surname);

                        startActivity(full);
                    }
                }
        );

        BtnRandom.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int amount = Integer.parseInt(RandomField.getText().toString());

                        if(amount > 0) {



                            Intent random = new Intent(MainActivity.this, Random.class);
                            random.putExtra("amount", amount);

                            startActivity(random);
                        }
                    }
                }
        );
    }

}