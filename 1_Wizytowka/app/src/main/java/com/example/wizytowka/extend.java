package com.example.wizytowka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class extend extends AppCompatActivity {

    EditText EmailField;
    EditText PhoneField;

    Button Button_next;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_extend);

        EmailField = (EditText)findViewById(R.id.emailField);
        PhoneField = (EditText)findViewById(R.id.phoneField);
        Button_next = (Button)findViewById(R.id.button_next);

        Bundle extras = getIntent().getExtras();



        Button_next.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        String email = EmailField.getText().toString();
                        String phone = PhoneField.getText().toString();
                        if(extras != null) {
                            Intent full = new Intent(extend.this, Full.class);
                            full.putExtra("name", extras.getString("name"));
                            full.putExtra("surname", extras.getString("surname"));
                            full.putExtra("email", email);
                            full.putExtra("phone", phone);
                            startActivity(full);
                        }


                    }
                }
        );
    }
}