package com.example.exercise1cw1;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;
import java.util.Arrays;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText inputValue;
    private Spinner fromUnit;
    private Spinner toUnit;
    private Button convertButton;
    private TextView outputResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        inputValue = findViewById(R.id.input_value);
        fromUnit = findViewById(R.id.from_unit);
        toUnit = findViewById(R.id.to_unit);
        convertButton = findViewById(R.id.convert_button);
        outputResult = findViewById(R.id.output_result);

        convertButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = inputValue.getText().toString();

                // Input validation
                if (input.isEmpty()) {
                    Toast.makeText(MainActivity.this, "Please enter a value", Toast.LENGTH_SHORT).show();
                    return;
                }

                double value = Double.parseDouble(input);
                String from = fromUnit.getSelectedItem().toString();
                String to = toUnit.getSelectedItem().toString();
                double result = convertLength(value, from, to);

                outputResult.setText(String.format("%.4f %s", result, to));
            }
        });
    }

    private double convertLength(double value, String from, String to) {
        // Conversion to metres first
        double metres = 0;
        switch (from) {
            case "Metre": metres = value; break;
            case "Millimetre": metres = value / 1000; break;
            case "Mile": metres = value * 1609.34; break;
            case "Foot": metres = value * 0.3048; break;
        }

        // Conversion from metres to target unit
        switch (to) {
            case "Metre": return metres;
            case "Millimetre": return metres * 1000;
            case "Mile": return metres / 1609.34;
            case "Foot": return metres / 0.3048;
        }
        return 0;
    }
}