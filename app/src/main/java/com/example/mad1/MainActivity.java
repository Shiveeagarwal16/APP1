package com.example.currencyconverter;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner from, to;
    Button convert, settings;
    TextView result;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        from = findViewById(R.id.from);
        to = findViewById(R.id.to);
        convert = findViewById(R.id.convert);
        result = findViewById(R.id.result);
        settings = findViewById(R.id.settings);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> convertCurrency());

        settings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }

    private void convertCurrency() {
        double amt = Double.parseDouble(amount.getText().toString());

        String fromCur = from.getSelectedItem().toString();
        String toCur = to.getSelectedItem().toString();

        double inr = 0;

        // Convert to INR first
        switch (fromCur) {
            case "INR": inr = amt; break;
            case "USD": inr = amt * 83; break;
            case "EUR": inr = amt * 90; break;
            case "JPY": inr = amt * 0.55; break;
        }

        double finalAmt = 0;

        // Convert INR to target
        switch (toCur) {
            case "INR": finalAmt = inr; break;
            case "USD": finalAmt = inr / 83; break;
            case "EUR": finalAmt = inr / 90; break;
            case "JPY": finalAmt = inr / 0.55; break;
        }

        result.setText("Result: " + finalAmt);
    }
}