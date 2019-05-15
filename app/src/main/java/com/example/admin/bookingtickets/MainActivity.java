package com.example.admin.bookingtickets;

import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.Locale;
import java.util.regex.Pattern;

public class MainActivity extends AppCompatActivity {
    String name,error;
    int money;
    int result;
    String phone;
    String language, city;
    String moneylocal;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate ( savedInstanceState );
        setContentView ( R.layout.activity_main );
        final EditText editetName = findViewById ( R.id.name );
        final EditText editextphone = findViewById ( R.id.phone );
        final CheckBox checkBox = findViewById ( R.id.checkBox_vip );
        final RadioButton seat = findViewById ( R.id.radioButton_Seat );
        final RadioButton berth = findViewById ( R.id.radioButton_Berth );

        final Spinner dropdown;
        dropdown = (Spinner) findViewById(R.id.spinner);
       final ArrayList<String> country = new ArrayList<String>();
       country.add("USD");
       country.add("VND");
       ArrayAdapter arrayAdapter = new ArrayAdapter(this,android.R.layout.simple_spinner_item,country);
       dropdown.setAdapter(arrayAdapter);


       checkBox.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View v) {
               if (checkBox.isChecked()) {
                   Toast.makeText(MainActivity.this, "Discount 20% for vip member", Toast.LENGTH_LONG).show();
               }
           }
       });

        Button book = findViewById ( R.id.btn_book );
        book.setOnClickListener ( new View.OnClickListener () {
            @Override
            public void onClick(View v) {
                if (editetName.getText().toString().isEmpty() || editextphone.getText().toString().isEmpty()){
                        warning(error);
                } else if (seat.isChecked() == false && berth.isChecked() == false){
                    Toast.makeText(MainActivity.this,"Please select Seat or Berth!",Toast.LENGTH_LONG).show();
                }
                else {
                    name = "Name: " + editetName.getText().toString();
                    phone = "\nPhone: " + editextphone.getText().toString();

                   if (seat.isChecked()) {
                       money = 600000;
                   }
                   if (berth.isChecked()) {
                           money = 1200000;
                       }


                    if (checkBox.isChecked()) {
                        result = money - (money / 100 * 20);
                        if (dropdown.getSelectedItem().toString() == "USD") {
                            city = "US";
                            language = "en";
                            moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result / 21000);
                        } else if (dropdown.getSelectedItem().toString() == "VND") {
                            city = "VN";
                            language = "vi";
                            moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(result);
                        }
                    } else if (dropdown.getSelectedItem().toString() == "USD") {
                        city = "US";
                        language = "en";
                        moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money / 21000);
                    } else if (dropdown.getSelectedItem().toString() == "VND") {
                        city = "VN";
                        language = "vi";
                        moneylocal = NumberFormat.getCurrencyInstance(new Locale(language, city)).format(money);
                    }

                    String mess = name + phone + "\nPrice: " + moneylocal;
                    display(mess);

                }
            }
        } );

        Button cancel = findViewById ( R.id.btn_cancel );
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }
    private void display(String mess){
        AlertDialog.Builder dlgAlert = new AlertDialog.Builder (MainActivity.this );
        dlgAlert.setMessage (mess);
        dlgAlert.setTitle("Information");
        dlgAlert.setPositiveButton("OK",null);
        dlgAlert.setCancelable(true);
        dlgAlert.create().show();

    }
    private void warning(String error){
        AlertDialog.Builder warning = new AlertDialog.Builder(MainActivity.this);
        warning.setMessage("Please enter your information!");
        warning.setTitle("Warning");
        warning.setPositiveButton("OK",null);
        warning.setCancelable(true);
        warning.create().show();
    }
}
