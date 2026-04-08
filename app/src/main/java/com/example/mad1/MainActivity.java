package com.example.mad1;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.app.AppCompatDelegate;

public class MainActivity extends AppCompatActivity {

    EditText amount;
    Spinner from, to;
    Button convert, settings;
    TextView result;

    String[] currencies = {"INR", "USD", "EUR", "JPY"};

    // Static conversion rates (for assignment)
    double[][] rates = {
            {1, 0.012, 0.011, 1.8},   // INR
            {83, 1, 0.92, 150},       // USD
            {90, 1.1, 1, 160},        // EUR
            {0.55, 0.0067, 0.0062, 1} // JPY
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // Apply saved theme
        SharedPreferences prefs = getSharedPreferences("settings", MODE_PRIVATE);
        boolean darkMode = prefs.getBoolean("dark", false);

        if (darkMode) {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
        } else {
            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        }

        setContentView(R.layout.activity_main);

        amount = findViewById(R.id.amount);
        from = findViewById(R.id.fromCurrency);
        to = findViewById(R.id.toCurrency);
        convert = findViewById(R.id.convertBtn);
        settings = findViewById(R.id.settingsBtn);
        result = findViewById(R.id.result);

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_spinner_dropdown_item, currencies);

        from.setAdapter(adapter);
        to.setAdapter(adapter);

        convert.setOnClickListener(v -> {
            String amtStr = amount.getText().toString();

            if (amtStr.isEmpty()) {
                result.setText("Enter amount");
                return;
            }

            double amt = Double.parseDouble(amtStr);

            int fromIndex = from.getSelectedItemPosition();
            int toIndex = to.getSelectedItemPosition();

            double converted = amt * rates[fromIndex][toIndex];

            result.setText("Result: " + converted + " " + currencies[toIndex]);
        });

        settings.setOnClickListener(v -> {
            startActivity(new Intent(MainActivity.this, SettingsActivity.class));
        });
    }
}