package com.example.wizytowka;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.TextView;

public class Random extends AppCompatActivity {

    Button BtnBack2;
    Switch FullMimSwitch;

    Button RandomPrevBtn;
    Button RandomNextBtn;
    Button Button_fir;
    Button Button_lst;


    TextView NameFieldFull;
    TextView SurnameFieldFull;
    TextView EmailFieldFull;
    TextView PhoneFieldFull;
    int current = 0;
    int amount = 0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_random);

        FullMimSwitch = (Switch)findViewById(R.id.fullMinSwitch);

        BtnBack2 = (Button)findViewById(R.id.btnBack2);

        Button_fir = (Button)findViewById(R.id.button_fir);
        Button_lst = (Button)findViewById(R.id.button_lst);


        RandomPrevBtn = (Button)findViewById(R.id.randomPrevBtn);
        RandomNextBtn = (Button)findViewById(R.id.randomNextBtn);

        NameFieldFull = (TextView)findViewById(R.id.nameFieldFul);
        SurnameFieldFull = (TextView)findViewById(R.id.surnameFieldFul);
        EmailFieldFull = (TextView)findViewById(R.id.mailFieldFul);
        PhoneFieldFull = (TextView)findViewById(R.id.phoneFieldFul);

        String[][] osoby = {
                {"Jan", "Kowalski", "jan.kowalski@example.com", "123-456-7890"},
                {"Anna", "Nowak", "anna.nowak@example.com", "987-654-3210"},
                {"Bartek", "Małysz", "bartek.malysz@example.com", "567-890-1234"},
                {"Kasia", "Kwiatkowska", "kasia.kwiatkowska@example.com", "321-098-7654"},
                {"Tomasz", "Adamski", "tomasz.adamski@example.com", "765-432-1098"},
                {"Czesław", "Zieliński", "czeslaw.zielinski@example.com", "456-789-0123"},
                {"Daria", "Adamiak", "daria.adamiak@example.com", "567-890-1234"},
                {"Ewa", "Kwiatkowska", "ewa.kwiatkowska@example.com", "678-901-2345"},
                {"Filip", "Nowakowski", "filip.nowakowski@example.com", "789-012-3456"},
                {"Grzegorz", "Piotrowski", "grzegorz.piotrowski@example.com", "890-123-4567"},
                {"Hanna", "Wójcik", "hanna.wojcik@example.com", "901-234-5678"}
        };

        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            amount = extras.getInt("amount");
            if(amount > osoby.length){
                amount = osoby.length;
            }
            NameFieldFull.setText(osoby[current][0]);
            SurnameFieldFull.setText(osoby[current][1]);
            EmailFieldFull.setText(osoby[current][2]);
            PhoneFieldFull.setText(osoby[current][3]);
        }



        FullMimSwitch.setOnCheckedChangeListener(
                new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        if (isChecked) {
                            EmailFieldFull.setVisibility(View.INVISIBLE);
                            PhoneFieldFull.setVisibility(View.INVISIBLE);

                        } else {
                            EmailFieldFull.setVisibility(View.VISIBLE);
                            PhoneFieldFull.setVisibility(View.VISIBLE);

                        }
                    }

                }
        );

        RandomPrevBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        if(current - 1 < 0){
                            current = amount -1;
                        } else {
                            current -=1;
                        }

                        NameFieldFull.setText(osoby[current][0]);
                        SurnameFieldFull.setText(osoby[current][1]);
                        EmailFieldFull.setText(osoby[current][2]);
                        PhoneFieldFull.setText(osoby[current][3]);
                    }
                }
        );

        RandomNextBtn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        if(current + 1 > amount -1){
                            current = 0;
                        } else {
                            current +=1;
                        }

                        NameFieldFull.setText(osoby[current][0]);
                        SurnameFieldFull.setText(osoby[current][1]);
                        EmailFieldFull.setText(osoby[current][2]);
                        PhoneFieldFull.setText(osoby[current][3]);

                    }
                }
        );

        Button_fir.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        if(current > 0){
                            current = 0;
                        }

                        NameFieldFull.setText(osoby[current][0]);
                        SurnameFieldFull.setText(osoby[current][1]);
                        EmailFieldFull.setText(osoby[current][2]);
                        PhoneFieldFull.setText(osoby[current][3]);
                    }
                }
        );

        Button_lst.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        if(current < amount-1){
                            current = amount -1;
                        }

                        NameFieldFull.setText(osoby[current][0]);
                        SurnameFieldFull.setText(osoby[current][1]);
                        EmailFieldFull.setText(osoby[current][2]);
                        PhoneFieldFull.setText(osoby[current][3]);
                    }
                }
        );




        BtnBack2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        Intent main = new Intent(Random.this, MainActivity.class);
                        startActivity(main);
                    }
                }
        );
    }
}