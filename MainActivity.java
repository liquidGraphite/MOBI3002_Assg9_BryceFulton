package com.example.brycefulton_calculator;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private EditText editTextNum1, editTextNum2;
    private TextView textViewResult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialize UI elements
        editTextNum1 = findViewById(R.id.editText_Num1);
        editTextNum2 = findViewById(R.id.editText_Num2);
        textViewResult = findViewById(R.id.textView_Result);
    }

    public void onOperatorClick(View view) {
        String operation = view.getTag().toString();
        performOperation(operation);
    }

    private void performOperation(String operation) {
        String num1String = editTextNum1.getText().toString();
        String num2String = editTextNum2.getText().toString();

        if (num1String.isEmpty() || num2String.isEmpty()) {
            Toast.makeText(this, "Please enter both numbers",
                    Toast.LENGTH_SHORT).show();
            Log.w("CalculatorApp", "Attempted operation with empty input");
            return;
        }

        try {
            double num1 = Double.parseDouble(num1String);
            double num2 = Double.parseDouble(num2String);
            double result = 0;

            switch (operation) {
                case "add":
                    result = num1 + num2;
                    Log.d("CalculatorApp", "Addition: " + num1 + " + " + num2 + " = " + result);
                    break;
                case "subtract":
                    result = num1 - num2;
                    Log.d("CalculatorApp", "Subtraction: " + num1 + " - " + num2 + " = " + result);
                    break;
                case "multiply":
                    result = num1 * num2;
                    Log.d("CalculatorApp", "Multiplication: " + num1 + " * " + num2 + " = " + result);
                    break;
                case "divide":
                    if (num2 == 0) {
                        Toast.makeText(this, "Cannot divide by zero", Toast.LENGTH_SHORT).show();
                        Log.e("CalculatorApp", "Division by zero attempted");
                        return;
                    }
                    result = num1 / num2;
                    Log.d("CalculatorApp", "Division: " + num1 + " / " + num2 + " = " + result);
                    break;
                default:
                    Toast.makeText(this, "Unknown operation", Toast.LENGTH_SHORT).show();
                    Log.e("CalculatorApp", "Unknown operation: " + operation);
                    return;
            }

            textViewResult.setText("Result: " + result);

        } catch (NumberFormatException e) {
            Toast.makeText(this, "Invalid input. Please enter valid numbers.",
                    Toast.LENGTH_SHORT).show();
            Log.e("CalculatorApp", "NumberFormatException: " + e.getMessage());
        }
    }
}