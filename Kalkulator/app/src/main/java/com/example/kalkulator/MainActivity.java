package com.example.kalkulator;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.TextView;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String equation = "";

    TextView TextView;
    Button Button0;
    Button Button1;
    Button Button2;
    Button Button3;
    Button Button4;
    Button Button5;
    Button Button6;
    Button Button7;
    Button Button8;
    Button Button9;
    Button Button_pl;
    Button Button_min;
    Button Button_mno;
    Button Button_dzi;
    Button Button_wyn;
    Button Button_dot;

    Button Button_sil;
    Button Button_back;
    Button Button_pot;
    Button Button_sr;
    Button Button_space;
    Button ButtonC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        TextView = (TextView)findViewById((R.id.textView));

        Button0 = (Button)findViewById(R.id.button0);
        Button0.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("0");
                    }
                }
        );

        Button1 = (Button)findViewById(R.id.button1);
        Button1.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("1");
                    }
                }
        );

        Button2 = (Button)findViewById(R.id.button2);
        Button2.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("2");
                    }
                }
        );
        Button3 = (Button)findViewById(R.id.button3);
        Button3.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("3");
                    }
                }
        );
        Button4 = (Button)findViewById(R.id.button4);
        Button4.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("4");
                    }
                }
        );
        Button5 = (Button)findViewById(R.id.button5);
        Button5.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("5");
                    }
                }
        );
        Button6 = (Button)findViewById(R.id.button6);
        Button6.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("6");
                    }
                }
        );
        Button7 = (Button)findViewById(R.id.button7);
        Button7.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("7");
                    }
                }
        );
        Button8 = (Button)findViewById(R.id.button8);
        Button8.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("8");
                    }
                }
        );
        Button9 = (Button)findViewById(R.id.button9);
        Button9.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append("9");
                    }
                }
        );

        Button_pl = (Button)findViewById(R.id.button_pl);
        Button_pl.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append(" + ");
                    }
                }
        );

        Button_min = (Button)findViewById(R.id.button_min);
        Button_min.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append(" - ");
                    }
                }
        );
        Button_mno = (Button)findViewById(R.id.button_mno);
        Button_mno.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append(" * ");
                    }
                }
        );
        Button_dzi = (Button)findViewById(R.id.button_dzi);
        Button_dzi.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append(" / ");
                    }
                }
        );

        Button_space = (Button)findViewById(R.id.button_space);
        Button_space.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.append(" ");
                    }
                }
        );

        Button_sr = (Button)findViewById(R.id.button_sr);
        Button_sr.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        String numbers = TextView.getText().toString();
                        String[] splited = numbers.split("\\s+");

                        Double sum = 0.0;
                        int i;

                        for (i = 0; i < splited.length; i++) {
                            sum = sum + Double.parseDouble(splited[i]);
                        }
                        Double result = sum / i;


                        TextView.setText(String.valueOf(result));
                    }
                }
        );

        Button_dot = (Button)findViewById(R.id.button_dot);
        Button_dot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        String rownanie = TextView.getText().toString();

                            int n = rownanie.length();
                            if (n == 0) {
                                TextView.append("0.");
                            }else if(n > 0){
                                char lastElem = rownanie.charAt(n - 1);
                                if (rownanie.charAt(n - 1) == ' ') {
                                    rownanie = rownanie.substring(0, rownanie.length() - 1);
                                    TextView.setText(rownanie + ".");
                                } else if (lastElem == '%' || lastElem == '/' || lastElem == '*' || lastElem == '+' || lastElem == '-') {
                                    TextView.append("0.");
                                } else {
                                    if(rownanie.charAt(n - 1) != '.'){
                                        TextView.append(".");
                                    }
                                }
                            }

                    }
                }
        );

        ButtonC = (Button)findViewById(R.id.buttonC);
        ButtonC.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        TextView.setText("");
                    }
                }
        );

        Button_back = (Button)findViewById(R.id.button_back);
        Button_back.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;


                        String rownanie = TextView.getText().toString();
                        if (rownanie != null && rownanie.length() > 0 ) {
                            rownanie = rownanie.substring(0, rownanie.length() - 1);
                        }
                        TextView.setText(rownanie);
                    }
                }
        );

        Button_pot = (Button)findViewById(R.id.button_po);
        Button_pot.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        String regex = ("([\\d\\.]+)");
                        Pattern pattern = Pattern.compile(regex);
                        String equation = TextView.getText().toString();


                        Matcher matcher = pattern.matcher(equation);
                        if (matcher.find()) {
                            Double Operand = Double.parseDouble(matcher.group(1));

                            Log.d("Cokolwiek", String.valueOf(Operand));

                            Double result = Operand * Operand;

                            TextView.setText(result.toString());
                        } else {
                            TextView.append("\n");
                            TextView.append("Niepoprawne równanie");
                        }
                    }
                }
        );

        Button_sil = (Button)findViewById(R.id.button_sil);
        Button_sil.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;
                        String regex = ("^([0-9]+)(\\.[0-9]+)?$");
                        Pattern pattern = Pattern.compile(regex);
                        String equation = TextView.getText().toString();

                        Matcher matcher = pattern.matcher(equation);
                        if (matcher.find()) {
                            int Operand = Integer.parseInt(matcher.group(1));

                            Log.d("Cokolwiek", String.valueOf(Operand));

                            int wynik = 1;
                            for (int i = 2; i <= Operand; i++) {
                                wynik *= i;
                            }
                            TextView.setText(String.valueOf(wynik));
                        } else {
                            TextView.append("\n");
                            TextView.append("Niepoprawne równanie");
                        }
                    }
                }
        );

        Button_wyn = (Button)findViewById(R.id.button_wyn);
        Button_wyn.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {;

                        String regex = ("([\\d\\.]+)\\s*([-+*/])\\s*([\\d\\.]+)");
                        Pattern pattern = Pattern.compile(regex);
                        String equation = TextView.getText().toString();

//                        String equation = "2.5 + 3.14 * 4";
                        Matcher matcher = pattern.matcher(equation);

                        if (matcher.find()) {

                            Double result = 0.0;
                                Double firstOperand = Double.parseDouble(matcher.group(1));
                                String operator = matcher.group(2);
                                Double secondOperand = Double.parseDouble(matcher.group(3));



                                switch (operator) {
                                    case "+":
                                        result = firstOperand + secondOperand;
                                        break;
                                    case "-":
                                        result = firstOperand - secondOperand;
                                        break;
                                    case "*":
                                        result = firstOperand * secondOperand;
                                        break;
                                    case "/":
                                        if (secondOperand != 0) {
                                            result = firstOperand / secondOperand;
                                        } else {
                                            TextView.append("Nie można dzielić przez zero.");
                                            return;
                                        }
                                        result = firstOperand / secondOperand;
                                        break;
                                }


                            TextView.setText(result.toString());
                        } else {
                            TextView.append("\n");
                            TextView.append("Niepoprawne równanie");
                        }
                    }
                }
        );

    }


}