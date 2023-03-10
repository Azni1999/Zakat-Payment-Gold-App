package com.example.zakatpaymentgoldapp;

import androidx.appcompat.app.AppCompatActivity;


import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.os.Bundle;
import android.widget.Toast;

import java.text.DecimalFormat;
public class CalcGold extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemSelectedListener {
    EditText gram, value;
    Button calculatebutton, resetbutton;
    TextView output1, output2, output3;
    Spinner spinner;
    ArrayAdapter<CharSequence> adapter;
    float goldWeight;
    float goldValue;

    SharedPreferences sharedP;
    SharedPreferences sharedP2;
    private Menu menu;

    @Override
    public void onItemSelected(AdapterView<?> parent, View view,
                               int position, long id) {

    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {

    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calc_gold);

        spinner = (Spinner) findViewById(R.id.spinner);
        adapter = ArrayAdapter.createFromResource(this, R.array.status, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        gram = (EditText) findViewById(R.id.Amount);
        value = (EditText) findViewById(R.id.CurrentGold);
        output1 = (TextView) findViewById(R.id.TotalGoldValue);
        output2 = (TextView) findViewById(R.id.ZakatPay);
        output3 = (TextView) findViewById(R.id.TotalZakat);
        calculatebutton = (Button) findViewById(R.id.ButtonCalc);
        resetbutton = (Button) findViewById(R.id.ButtonResert);

        calculatebutton.setOnClickListener(this);
        resetbutton.setOnClickListener(this);
        spinner.setOnItemSelectedListener(this);

        sharedP = this.getSharedPreferences("weight", Context.MODE_PRIVATE);
        goldWeight = sharedP2.getFloat("weight", 0.0F);

        sharedP2 = this.getSharedPreferences("value", Context.MODE_PRIVATE);
        goldValue = sharedP2.getFloat("value", 0.0F);

        gram.setText("" + goldWeight);
        value.setText("" + goldValue);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater Inflater = getMenuInflater();
        Inflater.inflate(R.menu.menu,menu);

        return true;
    }

    @Override
    public void onClick(View v) {
        try {
            switch (v.getId()) {

                case R.id.ButtonCalc:
                    calc();
                    break;


                case R.id.ButtonResert:
                    gram.setText("");
                    value.setText("");
                    output1.setText("Total Gold Value: RM");
                    output2.setText("");
                    output3.setText("");
                    break;

            }
        } catch (java.lang.NumberFormatException nfe) {
            Toast.makeText(this, "Input Missing!", Toast.LENGTH_SHORT).show();

        } catch (Exception exp) {
            Toast.makeText(this, "Unknown Exception" + exp, Toast.LENGTH_SHORT).show();

        }


    }

    public void calc() {
        DecimalFormat df = new DecimalFormat("##.00");
        float goldWeight = Float.parseFloat(gram.getText().toString());
        float goldValue = Float.parseFloat(value.getText().toString());
        String stat = spinner.getSelectedItem().toString();
        double value, ruff, zakatpay, tZakat;

        SharedPreferences.Editor editor = sharedP.edit();
        editor.putFloat("weight",goldWeight);
        editor.apply() ;

        SharedPreferences.Editor editor1 = sharedP2.edit();
        editor1.putFloat("value", goldValue);
        editor1.apply();


        if (stat.equals("Keep")) {
            value = goldWeight * goldValue;
            ruff = goldWeight - 85;

            if (ruff <= 0.0) {
                zakatpay = ruff + goldValue;
                tZakat = zakatpay * 0.025;
            } else {
                zakatpay = 0.0;
                tZakat = zakatpay * 0.025;
            }
            output1.setText("Total Gold Value: RM" + df.format(value));
            output2.setText("Zakat payable : RM" + df.format(zakatpay));
            output3.setText("Total Zakat : RM" + df.format(tZakat));
        }
        else {
            value = goldWeight * goldValue;
            ruff = goldWeight - 200;

            if (ruff >= 0.0) {
                zakatpay = ruff * goldValue;
                tZakat = zakatpay * 0.025;
            }
            else {
                zakatpay = 0.0;
                tZakat = zakatpay * 0.025;
            }
            output1.setText("Total Gold Value: RM" + df.format(value));
            output2.setText("Zakat payable : RM" + df.format(zakatpay));
            output3.setText("Total Zakat : RM" + df.format(tZakat));

        }
    }

}




